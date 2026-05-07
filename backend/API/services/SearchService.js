const textoUsuario = require('../models/DataRequest');
// const textoResposta = require('../models/DataResponse')

class SearchService {

    processarTexto(textoUsuario) {
        // Apenas um teste simples
        return `API recebeu o texto :) : ${textoUsuario}`;
    }
}

module.exports = new SearchService();