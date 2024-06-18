FROM openjdk:21-jdk-slim AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven

RUN mvn clean install

FROM openjdk:21-slim

WORKDIR /app

COPY --from=build /app/target/Elevator-System-1.0-SNAPSHOT.jar /app/Elevator-System.jar

CMD ["java", "-jar", "Elevator-System.jar"]
