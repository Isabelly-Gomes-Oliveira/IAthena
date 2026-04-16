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
1. ***POST*** - **recebe** informações do app (texto, chama a I.A, pega direto as informações da I.A e devolve pro frontend.

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
