const DataRequestModel = require("../models/DataRequest");
const DataResponseModel = require("../models/DataResponse");

// são exportados desestruturados, então dec=vem ser importados desestruturados tbm
const { SearchService } = require('../services/SearchService');
const { GenerateService } = require('../services/GenerateService');

class DataController {

    enviarTextoParaApp(req, res) {

        const { texto } = req.body;

        // modelo de texto recebido do app
        const textoApp = new DataRequestModel(texto);

        let respostaCompleta;
        let pesquisaResultado;

        // envia para service

        const pesquisaObjeto = new SearchService; // objeto de pesquisa
        pesquisaResultado = pesquisaObjeto.pesquisar(textoApp.texto); // pesquisa sobre o texto

        let resumoObjeto = new GenerateService; // objeto de resumo
        let resumoTexto;



        if (pesquisaResultado.length === 0) {
            resumoTexto = "Não há resumo";
            respostaCompleta = new DataResponseModel(resumoTexto, "não encontrado"); // modelo de resposta final
        } else {
            resumoTexto = resumoObjeto.resumirPesquisa(pesquisaResultado); // resume pesquisa e fontes
            respostaCompleta = new DataResponseModel(resumoTexto, "encontrado"); // modelo de resposta final
        }

        // devolve resposta
        res.status(200).json({
            respostaCompleta
        });
    }
}

module.exports = new DataController();

// ARRUMAR ESSE CONTROLLER