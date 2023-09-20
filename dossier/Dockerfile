FROM openjdk:17-oracle
WORKDIR /app
COPY target/dossier.jar /app/dossier.jar
EXPOSE 8003
CMD ["java", "-jar", "dossier.jar"]