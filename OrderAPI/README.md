# 🛍️ Order API

A .NET 8 REST API application for managing orders, customers and users with JSON file persistence.

## 📋 Overview

Order API is a .NET application that provides a complete order management system with authentication and authorization. Built with clean architecture principles and JSON file-based persistence for data storage.

**Key Features:**

- 👥 Customer management
- 📦 Order processing and tracking
- 🔐 User authentication and authorization
- 💾 JSON file-based data persistence
- 📊 RESTful API endpoints
- 🔄 Basic Authentication implementation
- 🏗️ Clean Architecture design

---

## 🛠️ Tech Stack

![.NET 8](https://img.shields.io/badge/-.NET_8-512BD4?style=flat&logo=.net&logoColor=white)
![C#](https://img.shields.io/badge/-C%23-239120?style=flat&logo=c-sharp&logoColor=white)
![REST API](https://img.shields.io/badge/-REST_API-009688?style=flat&logo=fastapi&logoColor=white)
![JSON](https://img.shields.io/badge/-JSON-000000?style=flat&logo=json&logoColor=white)

**Key Features:**

- .NET 8 Web API
- Basic Authentication
- JSON File Storage
- Clean Architecture
- Dependency Injection
- DTO Pattern

---

## 📦 Prerequisites

- .NET 8 SDK or higher
- Visual Studio 2022 or VS Code

---

## 🚀 Getting Started

### Build and Run

```bash
# Clone repository
git clone <repository-url>
cd OrderAPI

# Build project
dotnet build

# Run application
dotnet run --project Esame_OrderAPI/Esame_OrderAPI.csproj
```

### Access Application

The API endpoints can be accessed and tested through Swagger UI at: [http://localhost:5001/swagger](http://localhost:5001/swagger)

The API documentation provides an interactive interface where you can:

- View all available endpoints
- Test API operations directly
- View request/response schemas
- Authenticate using the login endpoint

---

## 📖 API Documentation

**Available Endpoints:**

### Authentication

```http
POST /api/login - Authenticate user
```

### Customers

```http
GET /api/customers - Get all customers
GET /api/customers/{id} - Get customer by ID
POST /api/customers - Create new customer
PUT /api/customers/{id} - Update customer
DELETE /api/customers/{id} - Delete customer
```

### Orders

```http
GET /api/orders - Get all orders
GET /api/orders/{id} - Get order by ID
POST /api/orders - Create new order
PUT /api/orders/{id} - Update order
DELETE /api/orders/{id} - Delete order
```

### Users

```http
GET /api/users - Get all users
GET /api/users/{id} - Get user by ID
POST /api/users - Create new user
PUT /api/users/{id} - Update user
DELETE /api/users/{id} - Delete user
```

---

## 🏗️ Project Structure

```text
Esame_OrderAPI/
├── Esame_OrderAPI/ (API Layer)
│   ├── Controllers/
│   ├── Security/
│   └── Program.cs
├── OrderAPI.BL/ (Business Layer)
│   └── Services/
├── OrderAPI.DL/ (Data Layer)
│   └── Repositories/
└── OrderAPI.Models/ (Domain Layer)
    ├── Dto/
    ├── Enum/
    └── Configurations/
```

---

## ⚙️ Configuration

Data files location in `Esame_OrderAPI/Data/`:

- CustomersFile.json
- OrdersFile.json
- UserFile.json

---

## 👤 Author

### Jacopo Russo

- GitHub: [@Pino0511](https://github.com/Pino0511)
- LinkedIn: [Jacopo Russo](https://linkedin.com/in/jacopo-russo)

---
