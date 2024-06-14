# Projeto de Atendimento de Clientes

Este projeto é uma API para gerenciar solicitações de serviço e atendentes.

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

