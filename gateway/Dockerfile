FROM openjdk:17-oracle
WORKDIR /app
COPY target/gateway.jar /app/gateway.jar
EXPOSE 8000
CMD ["java", "-jar", "gateway.jar"]