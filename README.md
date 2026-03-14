Desafio Técnico Itaú - Backend Júnior
Este projeto é uma API REST desenvolvida em Java com Spring Boot para o desafio técnico de Engenharia de Software (vaga Júnior) do Itaú Unibanco. O sistema recebe transações financeiras e calcula estatísticas em tempo real com base nas transações ocorridas nos últimos 60 segundos.

Em total conformidade com os requisitos do desafio, a aplicação não utiliza banco de dados externo ou sistemas de cache (como Redis), armazenando e processando todos os dados exclusivamente na memória da aplicação.

Tecnologias Utilizadas:
Java 21 (ou a versão que você estiver utilizando)

Spring Boot 3 (Web, Validation)

Swagger / Springdoc OpenAPI para documentação

Lombok para redução de boilerplate

Decisões Arquiteturais e Boas Práticas:
Para garantir que a aplicação fosse não apenas funcional, mas também robusta e escalável, as seguintes decisões de engenharia foram tomadas:

Thread-Safety e Concorrência: Como o Spring Boot lida com múltiplas requisições simultâneas em diferentes threads, o armazenamento em memória foi implementado utilizando uma ConcurrentLinkedQueue. Isso previne condições de corrida e a temida ConcurrentModificationException durante picos de acessos simultâneos no recebimento de transações.

Gerenciamento de Memória (Escalonamento Automático): Para evitar vazamentos de memória (memory leaks) em tempo de execução, foi implementado um job assíncrono utilizando a anotação @Scheduled na camada de Serviço. Essa rotina expurga automaticamente as transações mais antigas que 60 segundos, mantendo a fila sempre com o tamanho mínimo necessário.

Tratamento Global de Exceções: A validação de dados de entrada (como valores negativos ou datas no futuro) foi delegada ao Jakarta Bean Validation (@PositiveOrZero, @PastOrPresent). As quebras de contrato são interceptadas globalmente por um @RestControllerAdvice, que mapeia as falhas e devolve automaticamente os status codes exigidos (422 Unprocessable Entity e 400 Bad Request), mantendo os Controllers extremamente limpos.

Documentação Interface-Driven: As anotações verbosas do Swagger/OpenAPI foram isoladas em interfaces (ex: TransacaoControllerDoc). Os Controllers concretos apenas implementam essas interfaces, separando estritamente a responsabilidade de roteamento HTTP da responsabilidade de documentação.



Endpoints e Contratos:
A API estará disponível em http://localhost:8080.

A API consome e produz dados estritamente no formato JSON.

1. Criar Transação
   Rota: POST /transacao

Descrição: Recebe e registra uma transação caso o valor seja maior ou igual a zero e a data não esteja no futuro.

Body esperado:

JSON
{
"valor": 150.50,
"dataHora": "2024-03-14T15:30:00.000-03:00"
}
Retornos: 201 Created (Sucesso), 422 Unprocessable Entity (Regra de negócio violada), 400 Bad Request (JSON malformado).

2. Apagar Transações
   Rota: DELETE /transacao

Descrição: Limpa todo o armazenamento em memória.

Retorno: 200 OK

3. Obter Estatísticas
   Rota: GET /estatistica

Descrição: Calcula e retorna métricas referentes apenas às transações validadas nos últimos 60 segundos.

Retorno esperado (200 OK):

JSON
{
"count": 10,
"sum": 1234.56,
"avg": 123.456,
"min": 12.34,
"max": 123.56
}
(Nota: Caso não haja transações na janela de tempo, todos os campos retornam 0 de forma segura)

📄 Documentação da API
Com a aplicação em execução, acesse o Swagger UI para interagir visualmente com a API:
👉 http://localhost:8080/swagger-ui.html