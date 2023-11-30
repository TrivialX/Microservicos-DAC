db = db.getSiblingDB('auth');
db.createCollection('auths');


var userData = [
    {
        nome: "Maria Olivera",
        email: "maria@example.com",
        senha: "23b8db3766a1ca8715cd40dbd4458286e07905db1bc00d319c09dd1b92d88d58",
        tipoUser: "CLIENTE",
        usuario: 1,
        salt: "9d90b0e2f901d386"
    },
    {
        nome: "Carlos Pereira",
        email: "carlos@example.com",
        senha: "2e42863c78d4359b97e6fed7514eb05645910890f01463c929f442fa139ec7a1",
        tipoUser: "CLIENTE",
        usuario: 2,
        salt: "c906e3dfbfa68757"
    },
    {
        nome: "Ana Souza",
        email: "ana@example.com",
        senha: "582fd6e4998ba2624ad19c1eab56a1cff07e12eedfcee9574dfa6919dfaea7a1",
        tipoUser: "CLIENTE",
        usuario: 3,
        salt: "20dadcba21fd3e03"
    },
    {
        nome: "Gabriel Russo",
        email: "gabriel.yan.russo@gmail.com",
        senha: "569472bc3acd8de134a9949e7010318f52e3c1f0e4e390bd1efd74a41d626fab",
        tipoUser: "GERENTE",
        usuario: 3,
        salt: "umS@ltUnic0"
    }
];

userData.forEach(function (user) {
    db.auths.insert(user);
});
