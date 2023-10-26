require("dotenv-safe").config();

const jwt = require("jsonwebtoken");
const express = require("express");
const httpProxy = require("express-http-proxy");
const helmet = require("helmet");
const app = express();

var http = require("http");
var cookieParser = require("cookie-parser");
var bodyParser = require("body-parser");
var logger = require('morgan');

const urlencodedParser = bodyParser.urlencoded({ extended: false });
// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({extended: false}))

//parse application/json
app.use(bodyParser.json())

app.use(logger("dev"));
app.use(helmet());
app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(cookieParser());

const authServiceProxy = httpProxy("http://localhost:5000", {
    proxyReqBodyDecorator: function(bodyContent, srcReq){
        console.log(srcReq);
        try {
            retBody = {};
            retBody.user = bodyContent.user;
            retBody.senha = bodyContent.password;
            bodyContent = retBody;
        }catch(e){
            console.log("-ERRO: " + e);
        }
        return bodyContent;
    },
    proxyReqOptDecorator: function(proxyReqOpts, srcReq){
        proxyReqOpts.headers["Content-Type"] = "application/json";
        proxyReqOpts.method = "POST";
        return proxyReqOpts;
    },
    userResDecorator: function(proxyRes, proxyResData, userReq, userRes){
        if(proxyRes.statusCode == 200){
            var str = Buffer.from(proxyResData).toString("utf-8");
            var objBody = JSON.parse(str);
            const id = objBody.id;
            const token = jwt.sign({id}, process.env.SECRET, {
                expiresIn: 300
            });
            userRes.status(200);
            return {auth:true, token: token, data: objBody};
        }
        else {
            userRes.status(401);
            return {message: "Login inválido!"};
        }
    }
});

const usuariosServiceProxy = httpProxy("http://localhost:5000");
const boisServiceProxy = httpProxy("http://localhost:5001");

function verifyJWT(req, res, next){
    const token = req.headers["x-access-token"];
    if(!token)
        return res.status(401).json({auth:false, message: "Token não fornecido."});

    jwt.verify(token, process.env.SECRET, function(err, decoded){
        if(err)
            return res.status(500).json({auth:false, message: "Falha ao autenticar o token."});
        //deu boa
        req.userId = decoded.id;
        next();
    })

}

app.post("/login", (req, res, next)=>{
    authServiceProxy(req, res, next);
})

// app.post("/login", urlencodedParser, (req, res, next)=>{
//     if(req.body.user === "admin" && req.body.password === "admin"){

//         const id = 1; //esse id viria do serviço de autenticação
//         const token = jwt.sign({id}, process.env.SECRET, {
//             expiresIn: 300 //expira em 5 min
//         });
//         return res.json({auth:true, token: token});
//     }

//     res.status(500).json({message: "Login inválido!"});
// })

app.post("/logout", function(req, res){
    res.json({auth: false, token: null});
})

app.get('/usuarios', verifyJWT, (req, res, next)=>{
    usuariosServiceProxy(req, res, next);
})

app.get('/bois', verifyJWT, (req, res, next)=>{
    boisServiceProxy(req, res, next);
})


// Confiurações do app

var server = http.createServer(app);
server.listen(3000);
