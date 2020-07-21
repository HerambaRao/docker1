FROM openjdk:8
EXPOSE 8080
COPY target/*.jar productcatalog.jar
ENTRYPOINT ["java", "-jar", "/productcatalog.jar"]