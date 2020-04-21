FROM 			openjdk:8-jdk-alpine

MAINTAINER		Felipe Rudolfe <feliperudolfe@outlook.com>

WORKDIR 		/opt/app

COPY 			target/backend.jar app.jar

EXPOSE 			8080

CMD 			["java", "-jar", "app.jar"]