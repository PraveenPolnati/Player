# Use an official OpenJDK image as the base image
FROM openjdk:23-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the local system to the container
COPY target/Player-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot app will run on (default: 8080)
EXPOSE 8080

# Command to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
