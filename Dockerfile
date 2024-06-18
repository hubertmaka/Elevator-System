FROM maven:3.8.5-openjdk AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B package --file pom.xml

FROM openjdk

WORKDIR /app

COPY --from=build /app/target/Elevator-System-1.0-SNAPSHOT.jar /app/Elevator-System.jar

ENTRYPOINT ["java"]

CMD ["-jar", "Elevator-System.jar"]