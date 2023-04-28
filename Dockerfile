FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/loanApp-0.0.1-SNAPSHOT.jar loan-app-1.0.0.jar
ENTRYPOINT ["java","-jar","/loan-app-1.0.0.jar"]