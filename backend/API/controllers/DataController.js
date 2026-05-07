const DataRequestModel = require("../models/DataRequest");
const textoService = require("../services/SearchService");

class DataController {

    enviarTexto(req, res) {

        const { texto } = req.body;

        // cria objeto model
        const textoTesteUsuario = new DataRequestModel(texto);

        // envia para service
        const resposta = textoService.processarTexto(textoTesteUsuario.texto);

        // devolve resposta
        res.status(200).json({
            resposta: resposta
        });
    }
}

module.exports = new DataController();