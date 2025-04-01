# Use a Maven image to build the project
FROM maven:3.9.9-amazoncorretto-17 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven configuration and source code
COPY pom.xml .
COPY src ./src

# Build the project and create a JAR file
RUN mvn clean install -DskipTests -Dflyway.skip=true

# Use a minimal base image for the final stage
FROM amazoncorretto:17.0.13

# Set the working directory
WORKDIR /app

# Expose the application port
EXPOSE 8080

# Copy the JAR file from the build stage
COPY --from=build /app/target/seek-challenge-0.0.1-SNAPSHOT.jar seek-challenge-0.0.1-SNAPSHOT.jar

# Define the entry point to run the application
ENTRYPOINT ["java", "-jar", "seek-challenge-0.0.1-SNAPSHOT.jar"]