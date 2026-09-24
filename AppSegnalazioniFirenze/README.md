# 📍 Firenze Urban Reports — Case Study & Showcase

> **Mobile Application for Citizen Reports of the Municipality of Florence**  
*Case Study and UI/UX & Mobile Architecture developed during the corporate internship at Links Management & Technology.*

[![React Native](https://img.shields.io/badge/React_Native-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)](https://reactnative.dev/)
[![Expo](https://img.shields.io/badge/Expo-000000?style=for-the-badge&logo=expo&logoColor=white)](https://expo.dev/)
[![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)
[![License: Case Study](https://img.shields.io/badge/Code-Proprietary%20%2F%20Confidential-red?style=for-the-badge)](#-confidentiality-and-intellectual-property-notice)

---

## 📸 UI Showcase

### 🔐 Login & Dashboard

<p align="center">
  <img src="https://github.com/user-attachments/assets/b1dda8d3-5354-4449-b7f0-48b70b08cec7" width="420" alt="Login Screen" />
  &nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/17247dd9-ccb0-464f-8289-bfdf67264bbc" width="420" alt="Dashboard" />
</p>
<p align="center">
  <sub><b>Login Screen</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b>Dashboard / Reports List</b></sub>
</p>

### 📝 New Report & Interactive Map

<p align="center">
  <img src="https://github.com/user-attachments/assets/bbe30319-699c-4b35-a697-fd153172fd09" width="420" alt="New Report" />
  &nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/fcc87b2f-49b2-4009-9670-cb3d7156c18b" width="420" alt="Map Selection" />
</p>
<p align="center">
  <sub><b>New Report Form</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b>Map & Location Picker</b></sub>
</p>

### 🗺️ Map Detail

<p align="center">
  <img src="https://github.com/user-attachments/assets/aba500bb-5f2f-4126-b9cd-37321ccffd39" width="420" alt="Map Detail" />
</p>
<p align="center">
  <sub><b>Municipal Territory Boundaries</b></sub>
</p>

---

## 📌 Project Overview

**Firenze Urban Reports** (SEGUID Mobile) is a software solution designed to digitize the reporting and tracking of urban issues (road surface, public lighting, green areas, waste) in the city of Florence.

The app provides citizens with a modern, fast, and geolocated platform to interact transparently with the Public Administration.

---

## ✨ Features and User Flows

- **🔐 Authentication Flow & Identity (Mock):** Interface prepared for SPID, CIE, Apple ID, Google, and email authentication.
- **📍 Interactive Map & Geolocation:** Integration with native GPS to automatically detect the user's position and track the boundaries of the municipal territory.
- **📝 Report Creation & Submission:** Guided multi-step form for entering category, problem description, precise point on the map, and photo attachments.
- **📋 Dashboard & Ticket History:** Real-time monitoring of the status of submitted reports (*Open*, *In Progress*, *Suspended*, *Resolved*) with reminder and deletion functionality.

---

## 🔒 Confidentiality and Intellectual Property Notice

> **Warning:** In compliance with the confidentiality agreements (NDA) and the protection of the intellectual property of **Links Management & Technology** and the **Municipality of Florence**, the original source code of the application is **reserved and not included in this repository**.  
> 
> This page is intended to document the software architecture, technological choices, UI/UX workflow, and technical skills acquired during the development of the project.

---

## 🛠️ Architecture and Technical Choices

**Expo + React Native** — Native cross-platform development for iOS and Android with high UI/UX performance. Smooth handling of the camera and GPS geolocation through the `expo-location` and `expo-image-picker` modules. The Expo workflow enabled rapid prototyping and testing on real devices during review phases with the company and the client.

**TypeScript** — Clear definition of interfaces for reports (`ReportTicket`), flow states (`TicketStatus`), and GPS coordinates (`LocationCoordinates`). Enterprise-grade maintainability with reduced runtime bugs during integration and easier refactoring.

---

## 💻 Tech Stack Highlights

- **Core Framework:** React Native, Expo CLI
- **Language:** TypeScript
- **Maps & Geo:** `react-native-maps`, `expo-location`
- **Styling & UI:** Custom Component Design, Vector Icons (Lucide)
- **State & Data Handling:** React Hooks, Context API / Local State Management

---

## 👤 Author

**Jacopo Russo**  
*Software Developer & Computer Engineering Student*
