# ============================================
# BUILD STAGE
# ============================================
# Use Ubuntu-based image for Java 25 (Alpine might not have Java 25 yet)
FROM maven:3.9.6-eclipse-temurin-25 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline || mvn dependency:resolve
COPY src ./src
RUN mvn clean package -DskipTests

# ============================================
# RUNTIME STAGE
# ============================================
# Use Ubuntu/Debian base for Java 25 availability
FROM eclipse-temurin:25-jre

# Install SSL certificates (different command for Debian/Ubuntu)
RUN apt-get update && apt-get install -y ca-certificates openssl && \
    update-ca-certificates && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Create non-root user (different commands for Debian/Ubuntu)
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# Copy JAR
COPY --from=builder /app/target/LFP-*.jar app.jar

# Environment variables
ENV SPRING_PROFILES_ACTIVE=prod

# SSL fix for TiDB (truststore path is different on Ubuntu)
ENV JAVA_OPTS="-Djavax.net.ssl.trustStore=/etc/ssl/certs/java/cacerts \
               -Djavax.net.ssl.trustStorePassword=changeit"

EXPOSE 8081

# Health check
HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8081/ || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]