const DataRequestModel = require("../models/DataRequest");
const DataResponseModel = require("../models/DataResponse");

// são exportados desestruturados, então devem ser importados desestruturados tbm
const { SearchService } = require('../services/SearchService');
const { GenerateService } = require('../services/GenerateService');

class DataController {

    async enviarTextoParaApp(req, res) {
        try {
            const { texto } = req.body;

            const textoApp = new DataRequestModel(texto);

            const pesquisaObjeto = new SearchService();
            const pesquisaResultado = pesquisaObjeto.pesquisar(textoApp.texto);

            const resumoObjeto = new GenerateService();
            let resumoTexto;
            let respostaCompleta;

            if (pesquisaResultado.length === 0) {
                resumoTexto = "Não há resumo";
                respostaCompleta = new DataResponseModel(resumoTexto, "não encontrado");
            } else {
                resumoTexto = await resumoObjeto.resumirPesquisa(pesquisaResultado);
                respostaCompleta = new DataResponseModel(resumoTexto, "encontrado");
            }

            res.status(200).json({ respostaCompleta });

        } catch (error) {
            console.error("Erro no controller:", error);
            res.status(500).json({ erro: "Erro interno no servidor" });
        }
    }
}

module.exports = new DataController();