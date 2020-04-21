# Execução do projeto

**OBS:** 
* Você deve ter o `Node JS` instalado em sua máquina;
* Você deve ter o `Apache Maven` instalado em sua máquina;
* Você deve ter o `Java JDK` instalado em sua máquina;
* Você deve ter o `Docker` instalado em sua máquina;

## 1º - Build dos projetos

### Frontend:

1. Vá até a pasta do projeto `${PATH_ATE}/selecao-java/frontend`;
2. Digite os comandos:

>`npm install`
>`ng build --prod`

### Backend:

1. Vá até a pasta do projeto `${PATH_ATE}/selecao-java/backend`;
2. Digite os comandos:

>`mvn -e clean package -U`

## 2º - Execução via CMD

### Frontend:

1. Vá até a pasta do projeto `${PATH_ATE}/selecao-java/frontend`;
2. Digite os comandos:

>`npm start`

**OBS:** Por padrão, o `Angular` executa a aplicação em `http://localhost:4200`

### Backend:

1. Vá até a pasta do projeto `${PATH_ATE}/selecao-java/backend`;
2. Digite os comandos:

>`java -jar backend.jar`

**OBS:** Por padrão, o `Spring Boot` executa a aplicação em `http://localhost:8080`

## 3º - Execução via Docker Compose

### Frontend:

1. Vá até a pasta do projeto `${PATH_ATE}/selecao-java/frontend`;
2. Digite os comandos:

>`docker build -t frontend .`
>`docker images`
>`docker tag [ID_IMAGEM_FRONTEND] frontend:0.0.1`

### Backend:

1. Vá até a pasta do projeto `${PATH_ATE}/selecao-java/backend`;
2. Digite os comandos:

>`docker build -t backend .`
>`docker images`
>`docker tag [ID_IMAGEM_BACKEND] backend:0.0.1`

### Executar docker-compose:

1. Vá até a pasta do projeto `${PATH_ATE}/selecao-java`;
2. Digite os comandos:

>`docker-compose up -d`

**OBS:** 
* A aplicação frontend esta sendo executada em `http://localhost:8082`
* A aplicação backend esta sendo executada em `http://localhost:8081`