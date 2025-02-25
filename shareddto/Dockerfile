# Build stage
FROM maven:3.9-eclipse-temurin-22-alpine AS builder

WORKDIR /app
# Copy `shareddto` source files and `pom.xml` into the container
COPY . .

# Build the Maven project
RUN mvn clean install -DskipTests

# Runtime stage
FROM eclipse-temurin:22-jre-alpine AS runtime

VOLUME /libs
WORKDIR /app

# Copy the built JAR from the build stage to `/libs`
RUN mkdir -p /libs && chmod -R 777 /libs
COPY --from=builder /app/target/shareddto-1.0-SNAPSHOT.jar /libs/shareddto-1.0-SNAPSHOT.jar