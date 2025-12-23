// api/index.js - This forces Vercel to build
export default function handler(request, response) {
  response.status(200).json({
    message: "Java Spring Boot API",
    status: "Running via Docker"
  });
}