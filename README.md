# Aplicação Fast Food
Tech Challenge POSTECH FIAP

## Stack
- Java 17
- Spring Boot
- Docker
- Maven
- Postgres
- Swagger
    

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

## Inicializando a aplicação com o container Docker

**Atenção!**: Verifique se as variáveis de ambiente estão em mãos para poder inicializar corretamente a aplicação para cada ambiente.

Certifique-se de inserir o arquivo .env dentro da **/infra/prod**

Verifique no arquivo **app/mvnw**, se EOL está configurado como "LF". 

## Executando o projeto

### Build de Produção:

Para executar, é necessário estar dentro da raiz do projeto.
Execute a imagem com o jar compilado:

```sh
docker-compose -f ./infra/prod/docker-compose.yaml up
```

Depois de executada, a aplicação estará disponível para uso em: http://localhost:8080/swagger-ui/index.html#/

---

**Utilize a Collection ("AppFastFood.postman_collection") disponível na raíz do projeto para interagir com a aplicação**

*Inserir informações na aplicação:*

- Criar Clientes
- Cadastrar Produtos
- Criar Pedidos

**Não é possível criar um pedido, caso não exista produtos cadastrados.**

---

### Build de Desenvolvimento
Para executar, é necessário estar dentro da raiz do projeto.

Esse comando compila o código java, executa o jar do monolito e gera uma imagem nova, usem quando forem testar alteracoes do codigo:

```sh
docker-compose -f ./infra/dev/docker-compose.yaml up 
```

### Inicializando a aplicação sem o container

Para poder estar rodando em maquina local sem o container docker e sem o Postgres, pois estará utiliza o banco H2. Deve realizar a instalação das seguintes ferramentas:
- Amazon Corretto 17 JDK
- Maven
- Lombok

---

#### Baixando as dependência
Comando para baixar as dependências do Maven:
```sh
mvn clean install 
```

Após o sucesso a da instalação, poderá inicializar a aplicação!

---

## Desenvolvedores
 - Caio Cezar Santos Rodrigues
 - Israel Sifoleli Junior
 - Jonas Aparecido Monteiro
 - José Vitor Alves Avanço
 - Raul Munhoz
 