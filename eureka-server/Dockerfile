FROM openjdk:17-oracle
WORKDIR /app
COPY target/eureka-server-1.0-SNAPSHOT.jar /app/app.jar
COPY src/main/resources/application.properties /app/application.properties

ENTRYPOINT ["java","-jar","/app/app.jar","--spring.config.location=/app/application.properties"]
