# 🌍 Country Weather Service

REST API Spring Boot che integra due servizi esterni (**REST Countries API** e **OpenMeteo API**) tramite **Spring Cloud OpenFeign** per recuperare, aggregare e memorizzare informazioni geografiche e dati meteo in tempo reale.

---

## 📋 Overview

Il servizio accetta il nome di un paese, ne recupera i dettagli geografici (capitale, popolazione, valuta, bandiera), estrae le coordinate geografiche della capitale e le utilizza per interrogare il servizio meteo. I dati aggregati vengono persistiti a DB tramite Spring Data JPA per consentire aggiornamenti successivi (stato di visita, note personali e rating).

## 🌟 Key Features

- 🌍 **Country Data Retrieval**: Estrazione dati geografici e demografici da REST Countries API.
- 🌤️ **Real-time Weather**: Query meteo in base alle coordinate della capitale tramite OpenMeteo API.
- 🔄 **OpenFeign Integration**: Client HTTP dichiarativi per la comunicazione tra microservizi/API esterne.
- 💾 **Data Persistence**: Salvataggio ed elaborazione dei dati aggregati tramite Spring Data JPA.
- 📊 **Swagger / OpenAPI 3**: Interfaccia interattiva per il testing e la documentazione degli endpoint REST.

---

## 📸 Demo & Screenshots

![Swagger API Demo](Screenshot.png)

---

## 🛠️ Tech Stack

![Spring Boot](https://img.shields.io/badge/-Spring_Boot_3-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![Java 17+](https://img.shields.io/badge/-Java_17+-007396?style=flat&logo=java&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/-Spring_Data_JPA-6DB33F?style=flat&logo=spring&logoColor=white)
![OpenFeign](https://img.shields.io/badge/-OpenFeign-6DB33F?style=flat&logo=spring&logoColor=white)
![Swagger/OpenAPI](https://img.shields.io/badge/-Swagger-85EA2D?style=flat&logo=swagger&logoColor=black)
![Maven](https://img.shields.io/badge/-Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)

---

## 🚀 Getting Started

### Prerequisites

- **Java 17** o superiore
- **Maven 3.6+**
- **Git**

### Configuration

Verifica la configurazione del database nel file `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/weather_db
spring.datasource.username=tuo_utente
spring.datasource.password=tua_password
spring.jpa.hibernate.ddl-auto=update
```

### Installation & Run

Clona il repository:

```bash
git clone <repository-url>
cd weatherApp
```

Compila ed esegui l'applicazione:

Su Windows (Prompt dei Comandi):

```dos
mvnw spring-boot:run
```

Su Linux / macOS / PowerShell:

```bash
./mvnw spring-boot:run
```

Accedi a Swagger UI:

Con l'applicazione avviata su `http://localhost:8080`:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs JSON: `http://localhost:8080/v3/api-docs`

---

## 🔗 REST Endpoints

| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/country-weather/{country}` | Recupera dati geografici e meteo del paese, persistendo il risultato a DB. |
| PUT | `/country-weather/{country}` | Aggiorna lo stato di visita (`visited`), le note e il rating del paese specificato. |
| GET | `/country-weather/all` | Restituisce la lista completa dei paesi e meteo salvati nel database. |
