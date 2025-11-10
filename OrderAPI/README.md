🛍️ Order API
=============

A .NET 8 REST API application for managing orders, customers and users with JSON file persistence.

📋 Overview
-----------

Order API is a .NET application that provides a complete order management system with authentication and authorization. Built with clean architecture principles and JSON file-based persistence for data storage.

**Key Features:**

- 👥 Customer management
- 📦 Order processing and tracking
- 🔐 User authentication and authorization
- 💾 JSON file-based data persistence
- 📊 RESTful API endpoints
- 🔄 Basic Authentication implementation
- 🏗️ Clean Architecture design

🛠️ Tech Stack
--------------

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

📦 Prerequisites
----------------

- .NET 8 SDK or higher
- Visual Studio 2022 or VS Code

🚀 Getting Started
------------------

### Build and Run

# Clone repository
git clone <repository-url>
cd OrderAPI

# Build project
dotnet build

# Run application
dotnet run --project Esame_OrderAPI/Esame_OrderAPI.csproj

### Access Application

The API endpoints can be accessed and tested through Swagger UI at: http://localhost:5001/swagger

The API documentation provides an interactive interface where you can:

- View all available endpoints
- Test API operations directly
- View request/response schemas
- Authenticate using the login endpoint

Screenshot
----------

![OrderAPI screenshot](OrderAPI/screenshot.png)

📖 API Documentation
--------------------

**Available Endpoints:**

### Authentication

POST /api/login - Authenticate user

### Customers

GET /api/customers - Get all customers
GET /api/customers/{id} - Get customer by ID
POST /api/customers - Create new customer
PUT /api/customers/{id} - Update customer
DELETE /api/customers/{id} - Delete customer

### Orders

GET /api/orders - Get all orders
GET /api/orders/{id} - Get order by ID
POST /api/orders - Create new order
PUT /api/orders/{id} - Update order
DELETE /api/orders/{id} - Delete order

### Users

GET /api/users - Get all users
GET /api/users/{id} - Get user by ID
POST /api/users - Create new user
PUT /api/users/{id} - Update user
DELETE /api/users/{id} - Delete user

🏗️ Project Structure
---------------------

```
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

⚙️ Configuration
----------------

Data files location in `Esame_OrderAPI/Data/`:

- CustomersFile.json
- OrdersFile.json
- UserFile.json

## ❓ Troubleshooting

### Application won't start
- Ensure .NET 8 SDK is installed: `dotnet --version`
- Check that port 5001 is not in use
- Try cleaning and rebuilding: `dotnet clean && dotnet build`
- Verify all NuGet packages are restored: `dotnet restore`

### Swagger UI not loading
- Verify application is running on `http://localhost:5001`
- Check that Swagger is configured in `Program.cs`
- Clear browser cache and try again
- Check browser console for JavaScript errors (F12)

### Authentication fails
- Verify credentials are correct
- Check that user exists in `UserFile.json`
- Ensure Basic Authentication header is properly formatted
- Restart the application after modifying user data

### Database file errors
- Verify JSON data files exist in `Esame_OrderAPI/Data/` folder
- Check file permissions allow read/write operations
- Ensure JSON formatting is valid (use JSON validator)
- Restart application if data files were manually modified

### CORS errors
- Configure CORS in `Program.cs` if calling from different domain
- Verify frontend URL is whitelisted in CORS policy
- Check browser console for specific CORS error details
- Clear browser cache

### Port 5001 already in use
- Check what application is using port 5001: `netstat -ano | findstr :5001` (Windows)
- Kill the process or use a different port
- Change port in `Program.cs` if needed: `builder.WebHost.UseUrls("http://localhost:5002")`

👤 Author
---------

### Jacopo Russo

- GitHub: [@Pino0511](https://github.com/Pino0511)
- LinkedIn: [Jacopo Russo](https://linkedin.com/in/jacopo-russo)
