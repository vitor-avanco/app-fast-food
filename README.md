# Aplicação Fast Food
Tech Challenge POSTECH FIAP

**Diagrama**

https://app.diagrams.net/#G1jbIrDPWH3BY4NgY_mvSWEC9SoYqx3zQu

---
**Video do Projeto funcionando**

https://www.youtube.com/watch?v=uQ2c_eYHDds&ab_channel=VitorAvanco

---


## Stack
- Java 17
- Spring Boot
- Docker
- Maven
- Postgres
- Swagger
- K8S
    

## Tools
- Docker 
- Git
- Docker Compose


Para poder estar instalando essas ferramentas, siga o link de instalação.

- **Windows**
   - https://docs.docker.com/desktop/install/windows-install/ [Docker & Docker Composer]
   - https://git-scm.com/download/win [Git]
 - **Linux**
   - https://docs.docker.com/desktop/install/linux-install/ [Docker]
   - https://git-scm.com/book/pt-br/v2/Come%C3%A7ando-Instalando-o-Git [Git]
   - https://docs.docker.com/compose/install/linux/ [Docker Compose]
 - **Mac**
   - https://docs.docker.com/desktop/install/mac-install/ [Docker & Docker Composer]
   - https://git-scm.com/download/mac [Git]

---

### Como rodar a aplicação via kubernetes?

Para levantar a API junto com o banco de dados Postgres, execute na pasta raiz:

```bash
kubectl apply -f infra/kubernetes
```

**Importante: Caso utilize Docker Desktop é necessário habilitar o Kubernetes nas configurações.**

Depois de executada, a aplicação estará disponível para uso em: 

http://localhost:8080/swagger-ui/index.html#/

--

Endpoint de Health: 

http://localhost:8080/healthCheck

---

## Inicializando a aplicação com o container Docker

## Executando o projeto

### Build de Produção:

Para executar, é necessário estar dentro da raiz do projeto.
Execute a imagem com o jar compilado:

```sh
docker-compose -f ./infra/docker/docker-compose.yaml up
```

Depois de executada, a aplicação estará disponível para uso em: http://localhost:8080/swagger-ui/index.html#/

---

**Utilize a Collection ("Api AppFastFood.postman_collection.json") disponível na raíz do projeto para interagir com a aplicação**

- Cadatrar Clientes
- Verificar dados do cliente

- Listar Produtos
- Cadastrar Produto
- Editar Produto
- Excluir Produto

- Listar Pedido
- Criar Pedido
- Alterar Status do Pedido

- Verificar Status do Pagamento
- Weebhook para efetuar pagamento

- Healthcheck


**Não é possível criar um pedido, caso não exista produtos cadastrados.**
**Não é possível avançar o status de um pedido sem o pagamento estar confirmado.**

---

## Desenvolvedores
 - Caio Cezar Santos Rodrigues
 - Israel Sifoleli Junior
 - Jonas Aparecido Monteiro
 - José Vitor Alves Avanço
 - Raul Munhoz
 
