# MODELAGEM API

## O QUE A API FAZ?
A API faz a conexão entre o frontend e a I.A, isto é:

1. A aplicação analisa o texto contido na tela do usuário e guarda esse texto;
2. Passa o texto para a API;
3. A API envia para a I.A;
4. A I.A faz a pesquisa e devolve para a API as fontes e um texto resumindo o que foi encontrado;
5. A API passa os dados para o frontend (app);
6. O aplicativo mostra as infromações para o usuário.

Resumindo, o trajeto de informações é:
***aplicativo -> api -> ia -> api -> aplicativo -> usuário***