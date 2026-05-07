const express = require("express");
const cors = require("cors");

const routes = require("./routes/route"); // pega as rotas

const app = express();

app.use(cors());
app.use(express.json());

app.use(routes);

const PORT = 3000; // porta que a api está rodando

app.listen(PORT, () => { //liga o servidor e espera algo
    console.log(`Servidor rodando na porta ${PORT}`);
});