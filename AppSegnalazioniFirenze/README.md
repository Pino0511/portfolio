# 📍 Firenze Urban Reports — Case Study & Showcase

> **Applicazione Mobile per le Segnalazioni Cittadine del Comune di Firenze**  
*Caso Studio e Progettazione UI/UX & Mobile Architecture sviluppata durante il tirocinio aziendale presso Links Management & Technology.*

[![React Native](https://img.shields.io/badge/React_Native-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)](https://reactnative.dev/)
[![Expo](https://img.shields.io/badge/Expo-000000?style=for-the-badge&logo=expo&logoColor=white)](https://expo.dev/)
[![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)
[![License: Case Study](https://img.shields.io/badge/Code-Proprietary%20%2F%20Confidential-red?style=for-the-badge)](#-nota-di-riservatezza-e-proprietà-intellettuale)

---

## 🔒 Nota di Riservatezza e Proprietà Intellettuale

> **Attenzione:** In ottemperanza agli accordi di riservatezza (NDA) e tutela della proprietà intellettuale di **Links Management & Technology** e del **Comune di Firenze**, il codice sorgente originale dell'applicazione è **riservato e non presente in questa repository**.  
> 
> Questa pagina ha lo scopo di documentare l'architettura software, le scelte tecnologiche, il workflow UI/UX e le competenze tecniche maturate durante lo sviluppo del progetto.

---

## 📌 Panoramica del Progetto

**Firenze Urban Reports** (SEGUID Mobile) è una soluzione software progettata per digitalizzare il flusso di segnalazione e tracciamento dei disservizi urbani (manto stradale, illuminazione pubblica, verde urbano, rifiuti) nella città di Firenze.

L'app fornisce ai cittadini una piattaforma moderna, veloce e geolocalizzata per interagire in modo trasparente con la Pubblica Amministrazione.

---

## ✨ Funzionalità e Flussi Utente

* **🔐 Authentication Flow & Identity (Mock):** Predisposizione dell'interfaccia per autenticazione SPID, CIE, Apple ID, Google ed e-mail.
* **📍 Mappa Interattiva & Geolocalizzazione:** Integrazione con GPS nativo per rilevare automaticamente la posizione dell'utente e tracciare i confini del territorio comunale.
* **📝 Creazione & Invio Segnalazione:** Form guidato multi-step per l'inserimento di categoria, descrizione del problema, punto preciso su mappa e allegati fotografici.
* **📋 Dashboard & Storico Ticket:** Monitoraggio in tempo reale dello stato delle segnalazioni inviate (*Aperto*, *In Lavorazione*, *Sospeso*, *Risolto*) con funzionalità di sollecito e cancellazione.

---

## 🛠️ Architettura e Scelte Tecniche

### 1. Perché Expo + React Native?
* **Cross-Platform nativo:** Sviluppo unico per iOS e Android mantenendo elevate prestazioni UI/UX.
* **Accesso alle API native:** Gestione fluida della fotocamera e della geolocalizzazione GPS tramite i moduli `expo-location` e `expo-image-picker`.
* **Prototipazione e Testing rapido:** Utilizzo del workflow Expo per il testing su dispositivi reali durante le fasi di review con l'azienda e il cliente.

### 2. Perché TypeScript?
* **Tipizzazione dei modelli dati:** Definizione chiara delle interfacce per le segnalazioni (`ReportTicket`), gli stati del flusso (`TicketStatus`) e le coordinate GPS (`LocationCoordinates`).
* **Manutenibilità Enterprise:** Riduzione dei bug a runtime durante la fase di integrazione e facilità di refactoring.

---

## 📸 Galleria Schermate (UI Showcase)

| Schermata Login | Dashboard / Lista | Mappa & Selezione | Nuova Segnalazione |
| :---: | :---: | :---: | :---: |
| ![Login](./screenshots/login.png) | ![Dashboard](./screenshots/dashboard.png) | ![Mappa](./screenshots/map.jpg) | ![Nuova](./screenshots/new.png) |

---

## 💻 Tech Stack Highlights

* **Core Framework:** React Native, Expo CLI
* **Language:** TypeScript
* **Maps & Geo:** `react-native-maps`, `expo-location`
* **Styling & UI:** Custom Component Design, Vector Icons (Lucide)
* **State & Data Handling:** React Hooks, Context API / Local State Management

---

## 👤 Autore

**Jacopo Russo**  
*Sviluppatore Software & Studente in Ingegneria Informatica*
