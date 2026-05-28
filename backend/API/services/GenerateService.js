const { GoogleGenAI } = require('@google/genai');
const dotenv = require('dotenv');
dotenv.config();

const ai = new GoogleGenAI({ apiKey: process.env.GEMINI_API_KEY });

class GenerateService {

    async gerarTexto(textoPesquisa) {
    try {
        const response = await ai.models.generateContent({
            model: 'gemini-2.5-flash',
            contents: `Faça um resumo COERENTE e CURTO com o conteúdo: ${textoPesquisa}`,
        });

        const texto = response.candidates[0].content.parts[0].text;
        return texto;

    } catch (error) {
        console.error("Erro ao gerar resumo:", error);
        return new Error("Erro ao gerar resumo");
    }
}

    async resumirPesquisa(textoPesquisa) {
        const resumo = await this.gerarTexto(textoPesquisa);

        if (resumo instanceof Error) {
            return "O resumo não pôde ser gerado :( ";
        }

        return resumo;
    }
}

module.exports = { GenerateService };