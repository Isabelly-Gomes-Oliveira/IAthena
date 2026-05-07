class GenerateService {

    resumirPesquisa (textoPesquisa){
        // Apenas um teste simples
        // Finge que aqui há um processamento da pesquisa na ia e depois o resultado é guardado em "resumo"
        const resumo = "O resumo da pesquisa: " + textoPesquisa + " foi feito"
        return resumo;
    }
}

module.exports = {GenerateService};