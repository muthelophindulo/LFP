# ============================================
# BUILD STAGE
# ============================================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# ============================================
# RUNTIME STAGE
# ============================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port 8081 (matches your application.properties)
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]