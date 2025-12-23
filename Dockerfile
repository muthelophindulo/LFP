# Multi-stage build for Vercel
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Create a minimal runtime
FROM node:18-alpine

WORKDIR /app

# Copy the built JAR
COPY --from=builder /app/target/*.jar app.jar

# Create a simple Node.js server to run Java
RUN echo 'const { exec } = require("child_process");' > server.js
RUN echo 'const http = require("http");' >> server.js
RUN echo 'const server = http.createServer((req, res) => {' >> server.js
RUN echo '  exec("java -jar app.jar", (error) => {' >> server.js
RUN echo '    res.writeHead(500);' >> server.js
RUN echo '    res.end("Java app failed: " + error.message);' >> server.js
RUN echo '  });' >> server.js
RUN echo '});' >> server.js
RUN echo 'server.listen(8081);' >> server.js

EXPOSE 8081
CMD ["node", "server.js"]