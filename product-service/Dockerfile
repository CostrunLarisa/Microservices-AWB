FROM openjdk:17-oracle
WORKDIR /app
COPY src/main/resources/application.properties /app/application.properties
COPY target/product-service-1.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar","--spring.config.location=/app/application.properties"]
