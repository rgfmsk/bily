FROM openjdk:8-jdk-alpine
LABEL maintainer="emreozdemir89@gmail.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/assignment-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} assignment.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/assignment.jar"]