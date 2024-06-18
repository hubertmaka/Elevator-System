FROM openjdk
LABEL authors="hubertmaka"

WORKDIR /app

COPY target/Elevator-System-1.0-SNAPSHOT.jar /app/Elevator-System.jar

ENTRYPOINT ["java"]

CMD ["-jar", "Elevator-System.jar"]