FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/seoul-0.0.1-SNAPSHOT.jar seoul.jar
ENTRYPOINT ["java","-jar","seoul.jar"]