# MODELAGEM API

## O QUE A API FAZ?
A API faz a conexão entre o frontend e a I.A, isto é:

1. A aplicação analisa o texto contido na tela do usuário e guarda esse texto;
2. Passa o texto para a API;
3. A API chama a I.A e envia o texto;
4. A I.A faz a pesquisa e um texto resumindo o que foi encontrado, as fontes e um status (encontrado ou não encontrado);
5. A API passa os dados para o frontend (app);
6. O aplicativo mostra as infromações para o usuário.

Resumindo, o trajeto de informações é:
***aplicativo -> api -> ia -> api -> aplicativo -> usuário***

## ENDPOINTS / ROTAS:
1. ***POST*** - **recebe** informações do app (texto, chama a I.A, pega direto as informações da I.A e devolve pro frontend).

**OBS**: Dados tratados em ***JSON***.

## STATUS HTTPS:

### Status de Sucesso:
200 OK
- Tudo funcionou normalmente
- Ex: IA respondeu e você devolveu o resumo + fontes

202 Accepted
- A requisição foi aceita, mas ainda está sendo processada
- Útil se a IA demorar (processamento assíncrono)

204 No Content
- Funcionou, mas não há conteúdo para retornar
- Ex: IA não encontrou nada relevante

### Erros do cliente (app)
400 Bad Request
- O frontend mandou algo errado ou incompleto
- Ex: texto vazio ou inválido

401 Unauthorized
- Se você decidir usar autenticação futuramente

403 Forbidden
- Acesso negado (ex: limite de uso atingido)

404 Not Found
- Rota não existe
- Ex: endpoint errado

422 Unprocessable Entity
- Dados válidos, mas não fazem sentido
- Ex: texto muito curto ou sem contexto suficiente

### Erros do servidor/IA
500 Internal Server Error
- Erro inesperado na sua API

502 Bad Gateway
- Sua API tentou falar com a IA e deu erro
- Muito comum nesse tipo de projeto

503 Service Unavailable
- IA fora do ar ou sobrecarregada

504 Gateway Timeout
- IA demorou demais pra responder

## MODELO: MVC EM JS

![Texto Alternativo](https://arquivo.devmedia.com.br/REVISTAS/easyjava/imagens/9/3/image001.jpg)

### ESTRUTURA:

📁 controllers
   - analiseController.js

📁 models
   - AnaliseRequest.js
   - AnaliseResponse.js

📁 services
   - fakeNewsService.js

📄 app.js

### EXEMPLO:

### 🧱 MODEL (dados)

```
// Classe que representa o que o usuário envia
class AnaliseRequest {
    constructor(texto) {
        // Texto que será analisado
        this.texto = texto;
    }
}

// Exporta a classe para uso em outros arquivos
module.exports = AnaliseRequest;
```

```
// Classe que representa a resposta da API
class AnaliseResponse {
    constructor(resultado, confianca) {
        // Resultado: Fake, Verdadeiro ou Duvidoso
        this.resultado = resultado;

        // Nível de confiança (ex: 0.85)
        this.confianca = confianca;
    }
}

// Exporta a classe
module.exports = AnaliseResponse;
```

### ⚙️ SERVICE (lógica da aplicação + IA)

```
// Importa o model de resposta
const AnaliseResponse = require('../models/AnaliseResponse');

// Classe responsável pela lógica da aplicação
class FakeNewsService {

    // Método que analisa o texto
    analisarTexto(texto) {

        // Verifica se o texto contém "milagre" (simulação de IA)
        if (texto.toLowerCase().includes("milagre")) {

            // Retorna resultado "Duvidoso"
            return new AnaliseResponse("Duvidoso", 0.55);
        }

        // Caso contrário, retorna "Fake"
        return new AnaliseResponse("Fake", 0.80);
    }
}

// Exporta uma instância do serviço
module.exports = new FakeNewsService();
```

### 🎮 CONTROLLER (entrada da API)
```
// Importa o service
const fakeNewsService = require('../services/fakeNewsService');

// Função que será chamada quando fizer POST
const analisar = (req, res) => {

    // Pega o texto do corpo da requisição
    const { texto } = req.body;

    // Validação: verifica se veio texto
    if (!texto) {
        // Retorna erro 400
        return res.status(400).json({ erro: "Texto é obrigatório" });
    }

    // Chama o service para analisar
    const resultado = fakeNewsService.analisarTexto(texto);

    // Retorna o resultado em JSON
    return res.status(200).json(resultado);
};

// Exporta a função
module.exports = { analisar };
```

### 📄 app.js
```
// Importa o Express
const express = require('express');

// Cria a aplicação
const app = express();

// Permite receber JSON no body
app.use(express.json());

// Importa o controller
const analiseController = require('./controllers/analiseController');

// Define a rota POST /api/analise
app.post('/api/analise', analiseController.analisar);

// Define a porta
const PORT = 3000;

// Inicia o servidor
app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});
```
