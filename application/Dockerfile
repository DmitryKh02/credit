FROM openjdk:17-oracle
WORKDIR /app
COPY target/application.jar /app/application.jar
EXPOSE 8001
CMD ["java", "-jar", "application.jar"]