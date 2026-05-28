const { GoogleGenerativeAI } = require('@google/generative-ai');
const dotenv = require('dotenv');

// Inicializa a SDK usando a sua variável de ambiente
const ai = new GoogleGenAI({ apiKey: process.env.GEMINI_API_KEY });

class GenerateService {

    // Chamada da IA Generativa do Gemini
    gerarTexto(textoPesquisa) {
        try {
            const response = ai.models.generateContent({
            model: 'gemini-2.5-flash', // modelo do Gemini
            contents: `Faça um resumo COERENTE e CURTO com o conteúdo: ${textoPesquisa}`,
            });
            
            return response.text;

        } catch (error) {
            return error("Erro ao gerar resumo:", error);
        }
    }


    resumirPesquisa (textoPesquisa){
        
        const resumo = this.gerarTexto(textoPesquisa);

        if (this.gerarTexto instanceof Error){
            return "O resumo não pôde ser gerado :( "
        }
        else {
            return resumo;
        }
    }

}

module.exports = {GenerateService};