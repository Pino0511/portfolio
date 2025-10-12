FROM eclipse-temurin:21-jdk-jammy

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

#docker build -t app .

#EXPOSE 3050
#docker run -p 3050:8080 app