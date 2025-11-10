# ☀️ Weather Dashboard

A Spring Boot REST API application for retrieving and managing weather data using the OpenMeteo API with SQLite persistence.

## 📋 Overview

Weather Dashboard is a Spring Boot application that fetches real-time weather data from OpenMeteo API and stores it in a local SQLite database. Built with clean architecture principles and OpenFeign for external API communication.

**Key Features:**

- 🌤️ Real-time weather data retrieval from OpenMeteo API
- 📍 Location-based weather queries
- 💾 SQLite database for data persistence
- 📊 RESTful API with OpenAPI/Swagger documentation
- 🐳 Docker containerization support
- 🔄 Automatic DTO mapping with ModelMapper

---

## Screenshots

![Screenshot 1](Screenshot.png)

---

## 🛠️ Tech Stack

![Spring Boot](https://img.shields.io/badge/-Spring_Boot_3.5.3-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![Java 21](https://img.shields.io/badge/-Java_21-007396?style=flat&logo=java&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/-Spring_Data_JPA-6DB33F?style=flat&logo=spring&logoColor=white)
![SQLite](https://img.shields.io/badge/-SQLite-003B57?style=flat&logo=sqlite&logoColor=white)
![OpenFeign](https://img.shields.io/badge/-OpenFeign-6DB33F?style=flat&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/-Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)

---

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- Git

### Installation

1. Clone the repository:

```bash
git clone <repository-url>
cd weatherApp
```

2. Build the project:

```bash
mvn clean install
```

3. Run the application:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Access Swagger UI

Once the application is running, access the API documentation at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/v3/api-docs`

---

## ❓ Troubleshooting

### Application fails to start / Port 8080 already in use
- Check if another application is using port 8080: `netstat -ano | findstr :8080` (Windows) or `lsof -i :8080` (Mac/Linux)
- Kill the process using the port or change the port in `application.properties`: `server.port=8081`
- Restart the application with the new port

### Maven build failures
- Clear Maven cache: `mvn clean`
- Delete `target/` directory and rebuild: `rm -rf target && mvn clean install`
- Check Java version matches requirement: `java -version` (should be Java 21+)
- Update Maven: `mvn -v` and upgrade if necessary
- Check pom.xml for dependency conflicts

### SQLite database connection errors
- Verify SQLite is properly configured in `application.properties`
- Check database file path and permissions: database should be in the project root
- Clear old database file and let the application create a new one on startup
- Enable SQL debugging: add `logging.level.org.hibernate.SQL=DEBUG` to `application.properties`

### OpenFeign API calls failing / External API unreachable
- Verify OpenMeteo API is accessible: `curl https://api.open-meteo.com/v1/forecast`
- Check internet connection and firewall rules
- Verify API endpoints in the Feign client configuration
- Enable debug logging: `logging.level.org.springframework.cloud.openfeign=DEBUG`
- Check if the OpenMeteo API has rate limiting (may require backoff strategy)

### Swagger UI not loading at /swagger-ui.html
- Verify Spring Boot and Springdoc OpenAPI dependencies are in pom.xml
- Clear browser cache: `Ctrl+F5` or open in incognito/private mode
- Check application logs for initialization errors
- Ensure the application started successfully on correct port
- Try accessing via `/v3/api-docs` to test API documentation endpoint

### 404 errors on REST endpoints
- Verify controller mappings are correct with `@RestController` and `@RequestMapping`
- Check if the endpoint path matches the request URL
- Enable debug logging: `logging.level.org.springframework.web=DEBUG`
- Verify no global error handler is returning 404 for valid endpoints
- Check application startup logs for any mapping issues

### Database migrations or schema issues
- Verify Hibernate is configured correctly for SQLite
- Check `spring.jpa.hibernate.ddl-auto` setting (usually `create-drop` for development)
- Manual schema creation: remove current database file and restart application
- Check entity annotations: `@Entity`, `@Table`, and column definitions
- Review Hibernate logs for schema generation errors

### Memory issues or high CPU usage
- Increase heap size: `mvn spring-boot:run -Dspring-boot.run.arguments="--Xmx512m"`
- Profile the application to identify memory leaks
- Check for inefficient database queries or N+1 problems
- Implement query result pagination for large datasets
- Monitor thread count and database connection pool

### Docker container startup issues
- Build Docker image: `docker build -t weather-app .`
- Run container with port mapping: `docker run -p 8080:8080 weather-app`
- Check container logs: `docker logs <container_id>`
- Verify Dockerfile exposes correct port (8080)
- Ensure Docker has sufficient resources allocated

### Configuration property not being recognized
- Check property name spelling in `application.properties` or `application.yml`
- Verify custom properties use correct prefix (e.g., `app.weather.api-key`)
- Create `@ConfigurationProperties` class for type-safe access
- Enable configuration processor in pom.xml for IDE autocomplete support
- Restart IDE and rebuild project after configuration changes
