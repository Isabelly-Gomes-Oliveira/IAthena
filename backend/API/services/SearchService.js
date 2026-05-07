class SearchService {

    pesquisar(textoUsuario) { // pega o texto vindo do app
        // Apenas um teste simples
        // finge que aqui tem um processamento de pesquisa sobre o textoUsuario e que guarda a resposta da pesquisa
        const textoPesquisa = "A pesquisa sobre " + textoUsuario + " foi feita"
        return textoPesquisa; // retorna uma mensagem
    }
    
}

module.exports = {SearchService};

/*
    PLANEJAMENTO DO PROCESSAMENTO ORIGINAL DO PROJETO:
    - A SearchService.js pega o texto vindo do app e guarda, chama a IA que realiza pesquisas, passa o texto guardado e passa para a IA, guarda a resposta retornada pela IA e a retorna.
    - O DataController.js chama primeiro a função do SearchService.js e guarda em uma variável e, em outra variável chama a função do GenerateService.js e passa a variável da search como parâmentro.
    - A GenerateService.js pega a resposta da IA de pesquisa (passada no parâmetro pelo controller), chama a IA gnerativa, envia essa resposta da pesquisa para a IA generativa fazer um resumo e o retorna.
*/