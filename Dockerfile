FROM openjdk:21-ea-24-oracle

WORKDIR /app

COPY target/servicio-auth-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_BD_FULLSTACK /app/oracle_wallet/
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]