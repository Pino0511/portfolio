# Liferay Enterprise Portlets - Customer Support & Contact Management

Progetto enterprise sviluppato durante il tirocinio su **Liferay 7.4**, comprendente una serie di portlet per la gestione di processi aziendali, tra cui una **rubrica contatti aziendale** e un sistema di **Customer Support Ticketing**.

## 📋 Panoramica

Il progetto è stato sviluppato come parte di un percorso di tirocinio focalizzato sullo sviluppo enterprise con Liferay 7.4, Java e architetture modulari OSGi. L'obiettivo era realizzare una soluzione completa per la gestione di contatti aziendali e ticket di assistenza clienti, integrando funzionalità di persistenza dati, controllo accessi e interfacce responsive.

## 🎯 Funzionalità

### Rubrica Contatti Aziendale
- Gestione strutturata dei contatti aziendali
- Consultazione e organizzazione contatti
- Ricerca e filtraggio avanzato
- Interfaccia responsive e user-friendly

### Customer Support Ticketing
- Sistema completo per la gestione delle richieste di assistenza
- Creazione, modifica ed eliminazione ticket
- Assegnazione e tracking dello stato delle richieste
- Notifiche e aggiornamenti in tempo reale

## 🛠️ Stack Tecnologico

- **Backend:** Java, OSGi (architettura modulare)
- **Platform:** Liferay 7.4
- **Persistence:** Service Builder, integrazione database enterprise (PostgreSQL/MySQL)
- **Architecture:** Pattern MVC (Model-View-Controller)
- **Frontend:** JSP, Bootstrap per interfacce responsive
- **Security:** Controllo accessi granulare e gestione ruoli

## 🏗️ Architettura

Il progetto è organizzato in moduli OSGi separati, ciascuno con responsabilità specifiche:

- **Portlet Modules:** Componenti per la gestione delle interfacce utente
- **Service Modules:** Logica di business e servizi
- **API Modules:** Interfacce e contratti per l'integrazione

### Service Builder

Utilizzo di Service Builder per la definizione di entità e servizi:
- Generazione automatica di model, persistence e service layer
- Integrazione con database relazionale
- Gestione transazionale e caching

## 📸 Screenshot

### Rubrica Contatti
<img width="1237" height="832" alt="Screenshot 2026-05-11 115509" src="https://github.com/user-attachments/assets/a51c408c-abf9-42c5-b411-3899b4a18ea9" />
<img width="1920" height="1080" alt="Tirocinio Mobile e Enterprise (3)" src="https://github.com/user-attachments/assets/58a57c7c-5ced-4164-aa30-f3948944e7b0" />

### Customer Support Ticketing
<img width="1525" height="667" alt="Screenshot 2026-05-11 115831" src="https://github.com/user-attachments/assets/a2f318c1-0a78-417f-9cf9-82d5d7cae784" />
<img width="502" height="662" alt="Screenshot 2026-05-11 120347" src="https://github.com/user-attachments/assets/d2f3c92e-1ba2-425f-9262-e9f94b326aae" />

<img width="1470" height="707" alt="Screenshot 2026-05-11 115943" src="https://github.com/user-attachments/assets/b9c2a4d5-079c-4a48-910f-0565ecda9c0d" />
<img width="443" height="597" alt="Screenshot 2026-05-11 121423" src="https://github.com/user-attachments/assets/759ed17e-fa7e-4e51-884c-8abcda4782aa" />
<img width="1920" height="1080" alt="Tirocinio Mobile e Enterprise (4)" src="https://github.com/user-attachments/assets/cb6a078e-0231-4038-bbc1-e921dd599dfb" />

## 🚀 Installazione


### Prerequisiti
- Java 8+
- Liferay 7.4 CE o DXP
- Database (PostgreSQL o MySQL)

### Build e Deploy

```bash
# Clona il repository
git clone <repository-url>

# Build con Gradle
./gradlew build

# Copia il jar in Liferay/deploy
cp modules/*.jar $LIFERAY_HOME/deploy
```

## 📄 License

Questo progetto è stato sviluppato a scopo formativo e dimostrativo.

## 👨‍💻 Autore

Sviluppato da Jacopo Russo durante il tirocinio presso LinksMT - Gennaio/Aprile 2026
