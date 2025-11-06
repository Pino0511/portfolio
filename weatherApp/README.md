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
![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat&logo=docker&logoColor=white)

**Key Dependencies:**
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Cloud OpenFeign
- Springdoc OpenAPI UI
- SQLite JDBC Driver
- ModelMapper

---
## 📦 Prerequisites

- Java 21 or higher
- Maven 3.6+ (or use included wrapper)
- Docker (optional)

---

## 🚀 Getting Started

### Build and Run

Clone repository
git clone <repository-url>
cd weatherApp

Build project
./mvnw clean install

Run application
./mvnw spring-boot:run


### Access Application

- **Application**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

---

## 📖 API Documentation

Interactive API documentation available at: http://localhost:8080/swagger-ui.html

**Example Endpoints:**
GET /api/weather/current?lat={latitude}&lon={longitude}
GET /api/weather/forecast?lat={latitude}&lon={longitude}
POST /api/weather/location
GET /api/weather/history

---

## 🐳 Docker

Build image
docker build -t weather-dashboard .

Run container
docker run -p 8080:8080 weather-dashboard


---


## ⚙️ Configuration

Edit `src/main/resources/application.properties`:

server.port=8080
spring.datasource.url=jdbc:sqlite:post.db
spring.jpa.hibernate.ddl-auto=update


---


## 👤 Author

**Jacopo Russo**
- GitHub: [@Pino0511](https://github.com/Pino0511)
- LinkedIn: [Jacopo Russo](https://linkedin.com/in/jacopo-russo)

---

## 🙏 Acknowledgments

- [OpenMeteo API](https://open-meteo.com/) - Weather data provider
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Springdoc OpenAPI](https://springdoc.org/)

</div>