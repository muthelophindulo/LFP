# ===============================
# Stage 1: Build (Optimized)
# ===============================
FROM maven:3.9.9-eclipse-temurin-25 AS build

WORKDIR /app

# 1. Copy only pom.xml first (better layer caching)
COPY pom.xml .

# 2. Download dependencies with retry for network resilience
RUN mvn dependency:go-offline -B || \
    (echo "Retrying with simpler resolve..." && mvn dependency:resolve -B)

# 3. Copy source code
COPY src ./src

# 4. Build application with production settings
RUN mvn clean package -DskipTests \
    -Dmaven.test.skip=true \
    -Dmaven.javadoc.skip=true


# ===============================
# Stage 2: Runtime (Optimized)
# ===============================
FROM eclipse-temurin:25-jre-jammy

# Create non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring

WORKDIR /app

# Copy jar from build stage
COPY --from=build --chown=spring:spring /app/target/*.jar app.jar

# Switch to non-root user
USER spring:spring

# Expose your application port (8081 as in your original)
EXPOSE 8081

# Add JVM options for better container performance
ENV JAVA_OPTS=""

# Use shell form to allow JAVA_OPTS
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]