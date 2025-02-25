# Sets up maven to compile
FROM maven:3.9-eclipse-temurin-22-alpine AS builder
LABEL authors="pablomiranda"
ENV SPRING_PROFILES_ACTIVE=docker
# Set the working directory
WORKDIR /app
COPY . .

# Compile the application
RUN mvn clean install -DskipTests

FROM eclipse-temurin:22-jdk-alpine
WORKDIR /app

# Copy the JAR file to the container
COPY --from=builder /app/target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver-0.0.1-SNAPSHOT.jar

# Run the application
ENTRYPOINT [ "java","-jar","eurekaserver-0.0.1-SNAPSHOT.jar" ]