FROM maven:3.8.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .

RUN cd Parcial && mvn clean package -DskipTests


FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/Parcial/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]