# Projeto de Atendimento de Clientes

Este projeto é uma API para exemplo de abertura de atedimentos usando RabbitMQ.

## Conteudos

- [Introdução](#introdução)
- [Pré-requisitos](#prerequisitos)
- [Setup](#setup)
- [Executando a Aplicação](#executando-a-aplicacao)
- [Endpoints](#endpoints)
- [Descrição dos Endpoints](#descricao-dos-endpoints)


## Introdução

RabbitMQ é um intermediário de mensagens que permite que aplicações se comuniquem entre si usando mensagens. Ele suporta vários protocolos de mensagens e fornece recursos como entrega confiável, roteamento e clustering.

Este projeto demonstra uma configuração simples de produtor-consumidor usando Spring Boot e RabbitMQ.

## Pré-requisitos

- Java 11 or later
- Maven 3.6.0 or later
- RabbitMQ server

## Setup

1. **Clone the repository**:
    ```sh
    git clone https://github.com/MariliaNeves/api-atendimento.git
    cd api-atendimento
    ```

2. **Start RabbitMQ server**:
    - Se você tiver o Docker instalado, pode iniciar o RabbitMQ usando o seguinte comando:
      ```sh
      docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
      ```
    - Alternativamente, você pode baixar e instalar o RabbitMQ a partir [desse link](https://www.rabbitmq.com/download.html).



## Executando a Aplicação

1. **Build the project**:
    ```sh
    mvn clean install
    ```

2. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```


## Endpoints

| Método HTTP | Rota            | Descrição                                              |
|-------------|------------------|--------------------------------------------------------|
| POST        | /serviceRequest  | Cria uma nova solicitação de serviço                   |
| POST        | /attendant       | Cria um novo atendente                                 |
| GET         | /attendants      | Recupera a lista de todos os atendentes                |
| GET         | /serviceRequests | Recupera a lista de todas as solicitações de serviço   |


## Descrição dos Endpoints

### POST /serviceRequest
**Descrição**: Cria uma nova solicitação de serviço.

**Request Body**:
```json
{
  "subject": "PROBLEMAS_COM_CARTAO",
  "description": "teste teste teste ..."
}
```

### POST /attendant
**Descrição**: Cria um novo atendente.

**Request Body**:
```json
{
  "name": "Maria",
  "team": "CARTOES"
}
```

