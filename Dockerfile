FROM maven:3.9.16-eclipse-temurin-17 AS build

WORKDIR /app-build

COPY pom.xml .
COPY src/ ./src

RUN mvn clean package -DskipTests

RUN cp target/*.jar application.jar


FROM eclipse-temurin:17-jre AS lancement

WORKDIR /app-lancement

COPY --from=build /app-build/application.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]