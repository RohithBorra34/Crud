# Use official Java 17 image
FROM eclipse-temurin:17-jdk-jammy
# Set working directory
WORKDIR /app

# Copy jar file
COPY target/crud-1.0.0.jar app.jar

# Expose port
EXPOSE 8080

# Run application
ENTRYPOINT ["java","-jar","app.jar"]

