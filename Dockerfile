FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY target/*.jar dockerdemo.jar
ENTRYPOINT ["java", "-jar", "/dockerdemo.jar"]