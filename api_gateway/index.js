require("dotenv-safe").config();

const jwt = require("jsonwebtoken");
const express = require("express");
const httpProxy = require("express-http-proxy");
const helmet = require("helmet");
const cors = require('cors');
const app = express();

var http = require("http");
var cookieParser = require("cookie-parser");
var bodyParser = require("body-parser");
var logger = require('morgan');

const urlencodedParser = bodyParser.urlencoded({ extended: false });
app.use(bodyParser.urlencoded({extended: false}))

//parse application/json
app.use(bodyParser.json())

app.use(logger("dev"));
app.use(helmet());
app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(cookieParser());
app.use(cors())

const authServiceProxy = httpProxy("http://micro-auth:8080", {
    proxyReqBodyDecorator: function(bodyContent, srcReq){
        console.log(bodyContent.user);
        console.log(bodyContent.password);
        try {
            retBody = {};
            retBody.email = bodyContent.user;
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
                expiresIn: 5000
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


const proxyConta = httpProxy('http://micro-conta:8080', {});
const proxyCliente = httpProxy('http://micro-cliente:8080', {});

const proxySaga = httpProxy('http://saga:8080', {});


// const usuariosServiceProxy = httpProxy("http://localhost:5000");
// const boisServiceProxy = httpProxy("http://localhost:5001");

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

//login
app.post("/login", (req, res, next)=>{
    authServiceProxy(req, res, next);
});


//deposito
app.post('/conta/deposito/:id', verifyJWT, (req, res, next) => {
    req.url = `/conta/deposito/${req.params.id}`;
    proxyConta(req, res, next);
});

//saque
app.post('/conta/saque/:id', verifyJWT, (req, res, next) => {
    req.url = `/conta/saque/${req.params.id}`;
    proxyConta(req, res, next);
});


//transferencia
app.post('/conta/transferencia/:id', verifyJWT, (req, res, next) => {
    req.url = `/conta/transferencia/${req.params.id}`;
    proxyConta(req, res, next);
});

//extrato
app.get('/conta/extrato/:id', verifyJWT, (req, res, next) => {
    req.url = `/conta/extrato/${req.params.id}`;
    proxyConta(req, res, next);
});

//conta por id
app.get("/conta/:id", verifyJWT,  (req, res, next) => {
    req.url = `/conta/${req.params.id}`;
    proxyConta(req, res, next);
});

//pendestens
app.get("/conta/gerente/:id", verifyJWT,  (req, res, next) => {
    req.url = `/conta/gerente/${req.params.id}`;
    proxyConta(req, res, next);
});



//cliente por id
app.get("/clientes/:id", verifyJWT, (req, res, next) => {
    req.url = `/clientes/${req.params.id}`;
    proxyCliente(req, res, next);
});

app.put("/saga/clientes/:id" , verifyJWT ,(req, res, next) =>{
    req.url = `/clientes/${req.params.id}`;
    proxySaga(req, res, next);
});

app.post("/saga/registrar", (req, res, next) =>{
    req.url = `/registrar`;
    proxySaga(req, res, next);
});


app.put("/saga/clientes/aprovacao/:id" , verifyJWT ,(req, res, next) =>{
    req.url = `/clientes/aprovacao/${req.params.id}`;
    console.log(req.body);
    proxySaga(req, res, next);
});

app.put("/saga/clientes/reprovacao/:id" , verifyJWT ,(req, res, next) =>{
    req.url = `/clientes/reprovacao/${req.params.id}`;
    console.log(req.body);
    proxySaga(req, res, next);
});




app.post("/logout", function(req, res){
    res.json({auth: false, token: null});
});


// Confiurações do app
var server = http.createServer(app);
server.listen(3000, () =>{
    console.log("Ouvinte");
});
