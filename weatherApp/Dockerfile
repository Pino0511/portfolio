# 1: Build dell'applicazione con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia prima il file pom.xml per scaricare le dipendenze (sfrutta la cache di Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia il codice sorgente e compila il JAR
COPY src ./src
RUN mvn package -DskipTests

# 2: Runtime leggero per la produzione
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia il JAR generato nello STAGE 1
COPY --from=build /app/target/*.jar app.jar

# Espone la porta standard di Spring Boot (8080)
EXPOSE 8080

# Esegue l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]
