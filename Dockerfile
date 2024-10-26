FROM maven:3.9.9-amazoncorretto-17-al2023

WORKDIR /app

COPY mvnw mvnw.cmd pom.xml ./

COPY src ./src

RUN mvn clean package -DskipTests=true

CMD ["mvn", "spring-boot:run"]
