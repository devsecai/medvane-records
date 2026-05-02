# MED-IAC-004: runs as root.
FROM eclipse-temurin:17-jre

WORKDIR /app
COPY target/medvane-records-0.1.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
