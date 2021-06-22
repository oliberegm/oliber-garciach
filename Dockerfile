#LANZANDO LA APP
FROM adoptopenjdk/openjdk11:latest
MAINTAINER Oliber Garcia "oliber.garcia@gmail.com"
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw install -DskipTests
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /usr/local/lib/challen.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/challen.jar"]
