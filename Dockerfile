# Maven
# Maven
FROM maven:3.8.1-openjdk-11-slim AS builder
WORKDIR /app
COPY mu-server-demo/pom.xml .

COPY mu-server-demo/src ./src
RUN mvn clean package -Dmaven.test.skip=true


# RTSDK Java
FROM amazoncorretto:17.0.7-alpine
WORKDIR /app
COPY --from=builder /app/target/mu-server-demo-1.0-SNAPSHOT-jar-with-dependencies.jar .

EXPOSE 8080
#COPY run.sh ./run.sh #comment the COPY command
ENTRYPOINT ["java", "-jar", "./mu-server-demo-1.0-SNAPSHOT-jar-with-dependencies.jar"]

