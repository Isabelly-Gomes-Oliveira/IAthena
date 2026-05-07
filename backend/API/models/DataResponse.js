class DataResponse {

    constructor (textoResumoFontes, status){
        this.textoResultado = textoResumoFontes; // texto que passou pelo processamento de pesquisa (SearchService.js) e pelo resumo (GenerateService.js)
        this.status = status; // encontrado ou não encontrado
    }

}

module.exports = DataResponse; // permite exportar essa classe