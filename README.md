# 👨‍💻 Jacopo Russo | Full-Stack Web Developer

<div align="center">

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=flat&logo=linkedin)](https://linkedin.com/in/jacopo-russo)

**Building innovative digital solutions with modern technologies**

</div>

---

## 🚀 About Me

Welcome to my portfolio! I'm **Jacopo Russo**, an Italian web and mobile developer passionate about creating elegant, functional, and user-centered applications.

🎓 **Background:**
- Computer Science Diploma
- Currently studying Digital Technologies at ITS
- Continuous learner with a growth mindset

💡 **What I Do:**
- Design and develop responsive web applications
- Build cross-platform mobile apps
- Create clean, maintainable, and scalable code
- Focus on UI/UX design and best practices
- Data analysis and backend architecture

---

## 🛠️ Tech Stack

### Frontend
![HTML5](https://img.shields.io/badge/-HTML5-E34F26?style=flat&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/-CSS3-1572B6?style=flat&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/-JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black)
![React](https://img.shields.io/badge/-React-61DAFB?style=flat&logo=react&logoColor=black)
![React Native](https://img.shields.io/badge/-React_Native-61DAFB?style=flat&logo=react&logoColor=black)

### Backend & Tools
![Node.js](https://img.shields.io/badge/-Node.js-339933?style=flat&logo=node.js&logoColor=white)
![Git](https://img.shields.io/badge/-Git-F05032?style=flat&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/-GitHub-181717?style=flat&logo=github&logoColor=white)
![REST API](https://img.shields.io/badge/-REST_API-009688?style=flat&logo=fastapi&logoColor=white)
![Spring Boot](https://img.shields.io/badge/-Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/-Java-007396?style=flat&logo=java&logoColor=white)
![Python](https://img.shields.io/badge/-Python-3776AB?style=flat&logo=python&logoColor=white)
![Flask](https://img.shields.io/badge/-Flask-000000?style=flat&logo=flask&logoColor=white)

---

## 💼 Featured Projects


### 🛍️ Order API

![.NET](https://img.shields.io/badge/-.NET_8-512BD4?style=flat&logo=.net&logoColor=white) ![C#](https://img.shields.io/badge/-C%23-239120?style=flat&logo=c-sharp&logoColor=white) ![REST API](https://img.shields.io/badge/-REST_API-009688?style=flat&logo=fastapi&logoColor=white)

> .NET 8 REST API for managing orders, customers and users

**Description:** A comprehensive REST API built with .NET 8 and C# that provides a complete order management system with authentication and authorization. Features clean architecture design, JSON file-based persistence, and full CRUD operations for orders, customers, and users.

**✨ Key Features:**
- 👥 Complete customer management system
- 📦 Order processing and tracking
- 🔐 User authentication with Basic Auth
- 💾 JSON file-based data persistence
- 📊 RESTful API with Swagger documentation
- 🏗️ Clean Architecture design pattern

**🔧 Tech Stack:** `.NET 8` • `C#` • `REST API` • `JSON` • `Swagger/OpenAPI` • `Basic Authentication`

**📝 Setup Instructions:**
```bash
# Clone and navigate to project
cd OrderAPI/Esame_OrderAPI

# Restore dependencies
dotnet restore

# Run the application
dotnet run

# Access Swagger UI at http://localhost:5001/swagger
```

**🔑 Environment Variables:**
- No external API keys required
- Data is stored in JSON files in `Data/` directory

📂 **[View Source Code](./OrderAPI)**

---


### 🎲 Flashcard AI Web App

![React](https://img.shields.io/badge/-React-61DAFB?style=flat&logo=react&logoColor=black) ![Flask](https://img.shields.io/badge/-Flask-000000?style=flat&logo=flask&logoColor=white) ![Python](https://img.shields.io/badge/-Python-3776AB?style=flat&logo=python&logoColor=white) ![JavaScript](https://img.shields.io/badge/-JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black)
> AI-powered flashcard generation with multilingual support

**Description:**  
A full-stack AI-powered flashcard generation web application that leverages OpenAI/Gemini APIs to automatically create educational flashcards 
from custom text. Features an interactive React frontend with smooth flipping animations and a Flask REST API backend.

**✨ Key Features:**
- 🤖 AI-generated flashcards using OpenAI/Gemini APIs
- 🌍 Multilingual flashcard creation support
- 🔄 Interactive card flipping with smooth animations
- 🎨 Modern and responsive user interface
- ⚡ Error handling for API responses and user input
- 💾 Sample cards with easy customization

**🔧 Tech Stack:**  
`React` • `Flask` • `Python` • `OpenAI API` • `Gemini API` • `REST API` • `JavaScript`

📂 **[View Source Code](./flashcard-ai/)**


**📝 Setup Instructions:**
```bash
# Backend Setup
cd flashcard-ai/backend

# Create virtual environment
python -m venv venv

# Activate virtual environment
# Windows:
venv\Scripts\activate
# Linux/Mac:
source venv/bin/activate

# Install dependencies
pip install flask flask-cors google-generativeai

# Run backend
python app.py

# Frontend Setup (in a new terminal)
cd flashcard-ai/frontend/flashcard-frontend

# Install dependencies
npm install

# Run frontend
npm start
```

**🔑 Environment Variables:**
Create a `.env` file in the backend directory or set API key directly in `app.py`:
- `API_KEY`: Your OpenAI or Gemini API key
  - Get OpenAI key: https://platform.openai.com/api-keys
  - Get Gemini key: https://makersuite.google.com/app/apikey


---

### ⚔️ War of Races - Fantasy Battle System
> Spring Boot REST API for managing fantasy creatures and battles

**Description:**  
A comprehensive Spring Boot application that provides a RESTful API for managing fantasy creatures and races in a battle system. Features CRUD operations for creatures, race management with unique characteristics, and a battle simulation system with damage calculations.

**✨ Key Features:**
- 💉 Complete CRUD operations for creatures and races
- ⚔️ Battle simulation system with complex damage calculations
- 🎯 Race-specific bonuses and special abilities
- 📊 Health, attack, defense stats management
- 🛡️ Predefined fantasy races (Elf, Dwarf, Orc)
- 💾 SQLite database integration

**🔧 Tech Stack:**  
`Spring Boot` • `Java` • `REST API` • `SQLite` • `Maven` • `JPA/Hibernate`

📂 **[View Source Code](./creature-api/warofraces/)**

---


### 🕹️ Pokedex Mobile App
> A comprehensive Pokédex application for iOS and Android

**Description:**  
A cross-platform mobile app built with React Native that displays and searches through Pokémon data retrieved from public APIs. Features an intuitive interface with smooth animations and real-time data fetching.

**✨ Key Features:**
- 📱 Full Pokémon catalog with real-time API integration
- 🔍 Advanced search and filtering capabilities
- 🎨 Modern, responsive UI/UX design
- ⚡ Fast performance on both iOS and Android
- 📊 Detailed Pokémon stats and information

**🔧 Tech Stack:**  
`React Native` • `JavaScript (ES6+)` • `RESTful APIs` • `Async/Await` • `React Hooks`

📂 **[View Source Code](./pokedex/)**

---

### 🎬 Film Filter App
> Smart movie browsing with advanced filtering

**Description:**  
An interactive web application that allows users to browse, filter, and discover movies using a public film API. Features responsive design and smooth user experience across all devices.

**✨ Key Features:**
- 🎯 Real-time filtering by genre, year, and language
- 📱 Fully responsive design for all screen sizes
- ⚡ Asynchronous data fetching with error handling
- 🎨 Clean and intuitive user interface
- 🔄 Dynamic content loading

**🔧 Tech Stack:**  
`HTML5` • `CSS3` • `JavaScript (ES6+)` • `Fetch API` • `Responsive Design`

📂 **[View Source Code](./film-filter-app/)**

---

### ☀️ Weather Dashboard
> Real-time weather forecasting application

**Description:**  
A weather application that displays real-time meteorological data using the OpenMeteo API. Features 7-day forecasts, current conditions, and location-based weather information.

**✨ Key Features:**
- 🌤️ 7-day weather forecasts with hourly details
- 📍 Geolocation-based automatic weather detection
- 🌡️ Temperature, humidity, and wind data visualization
- 💨 Clean and user-friendly interface
- 📊 Weather charts and visual indicators

**🔧 Tech Stack:**  
`HTML5` • `JavaScript` • `REST API` • `Geolocation API` • `Chart.js`

📂 **[View Source Code](./weatherApp/)**

---

## 📊 GitHub Stats

<div align="center">

![Top Languages](https://github-readme-stats.vercel.app/api/top-langs/?username=Pino0511&layout=compact&theme=radical)

</div>

---

## 🎯 What I'm Currently Working On

- 🤖 Integrating more AI-powered features in web applications
- ☘️ Building enterprise-level Spring Boot microservices
- 📊 Exploring data visualization and analytics dashboards
- 🚀 Learning Docker and Kubernetes for containerization
- 👯 Looking to collaborate on full-stack open-source projectsologies and DevOps

---

## 📫 Get In Touch

<div align="center">

**Let's connect and build something amazing together!**

[![GitHub](https://img.shields.io/badge/-GitHub-181717?style=for-the-badge&logo=github)](https://github.com/Pino0511)
[![LinkedIn](https://img.shields.io/badge/-LinkedIn-0A66C2?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/jacopo-russo)
[![Email](https://img.shields.io/badge/-Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:your.email@example.com)

</div>

---

<div align="center">

**💡 Open to opportunities | 🚀 Always learning | 🌟 Passionate about code**

*Thanks for visiting my portfolio!*

</div>
