# Liferay Enterprise Portlets - Customer Support & Contact Management

Enterprise project developed during the internship on **Liferay 7.4**, including a series of portlets for managing business processes, such as a **corporate contact directory** and a **Customer Support Ticketing** system.

## 📋 Overview

The project was developed as part of an internship focused on enterprise development with Liferay 7.4, Java, and modular OSGi architectures. The goal was to create a complete solution for managing corporate contacts and customer support tickets, integrating data persistence, access control, and responsive interfaces.

## 🎯 Features

### Corporate Contact Directory
- Structured management of corporate contacts
- Contact viewing and organization
- Advanced search and filtering
- Responsive and user-friendly interface

### Customer Support Ticketing
- Complete system for managing support requests
- Ticket creation, editing, and deletion
- Assignment and tracking of request status
- Real-time notifications and updates

## 🛠️ Tech Stack

- **Backend:** Java, OSGi (modular architecture)
- **Platform:** Liferay 7.4
- **Persistence:** Service Builder, enterprise database integration (PostgreSQL/MySQL)
- **Architecture:** MVC pattern (Model-View-Controller)
- **Frontend:** JSP, Bootstrap for responsive interfaces
- **Security:** Granular access control and role management

## 🏗️ Architecture

The project is organized into separate OSGi modules, each with specific responsibilities:

- **Portlet Modules:** Components for managing user interfaces
- **Service Modules:** Business logic and services
- **API Modules:** Interfaces and contracts for integration

### Service Builder

Use of Service Builder for defining entities and services:
- Automatic generation of model, persistence, and service layer
- Integration with relational database
- Transactional management and caching

## 📸 Screenshots

### Contact Directory
<img width="1237" height="832" alt="Screenshot 2026-05-11 115509" src="https://github.com/user-attachments/assets/a51c408c-abf9-42c5-b411-3899b4a18ea9" />
<img width="1920" height="1080" alt="Internship Mobile and Enterprise (3)" src="https://github.com/user-attachments/assets/58a57c7c-5ced-4164-aa30-f3948944e7b0" />

### Customer Support Ticketing
<img width="1525" height="667" alt="Screenshot 2026-05-11 115831" src="https://github.com/user-attachments/assets/a2f318c1-0a78-417f-9cf9-82d5d7cae784" />
<img width="502" height="662" alt="Screenshot 2026-05-11 120347" src="https://github.com/user-attachments/assets/d2f3c92e-1ba2-425f-9262-e9f94b326aae" />

<img width="1470" height="707" alt="Screenshot 2026-05-11 115943" src="https://github.com/user-attachments/assets/b9c2a4d5-079c-4a48-910f-0565ecda9c0d" />
<img width="443" height="597" alt="Screenshot 2026-05-11 121423" src="https://github.com/user-attachments/assets/759ed17e-fa7e-4e51-884c-8abcda4782aa" />
<img width="1920" height="1080" alt="Internship Mobile and Enterprise (4)" src="https://github.com/user-attachments/assets/cb6a078e-0231-4038-bbc1-e921dd599dfb" />

## 🚀 Installation

### Prerequisites
- Java 8+
- Liferay 7.4 CE or DXP
- Database (PostgreSQL or MySQL)

### Build and Deploy

```bash
# Clone the repository
git clone <repository-url>

# Build with Gradle
./gradlew build

# Copy the jar to Liferay/deploy
cp modules/*.jar $LIFERAY_HOME/deploy

```
📄 License
This project was developed for educational and demonstrative purposes.

👨‍💻 Author
Developed by Jacopo Russo during the internship at LinksMT - January/April 2026
