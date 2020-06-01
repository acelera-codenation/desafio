# codenation-desafio project

## Salve seu token numa variável de ambiente
```
export $TOKEN_CODENATION=?????
```

## Executar app em modo dev

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Empacotar a aplicação

```
./mvnw package
```

Vai criar o arquivo `codenation-desafio-1.0-SNAPSHOT-runner.jar` no diretório `/target`.

Para executar a aplicação:

```
java -jar target/codenation-desafio-1.0-SNAPSHOT-runner.jar
```

## Creating a native executable

```
./mvnw package -Pnative
```

 Se não tem GraalVM instalado:
 
```
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

Para executar: 

```
./target/codenation-desafio-1.0-SNAPSHOT-runner
```


Para enviar o desafio, executar o serviço e fazer um get para:

http://{host}:{porta}/desafio-cesar/enviar

