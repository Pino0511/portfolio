# 🧬 BioStack AI

> **Il tuo protocollo glow-up e longevity, ogni giorno.**

BioStack AI è un'applicazione web progettata per generare protocolli di benessere personalizzati. Tramite un rapido onboarding, l'app fornisce una routine quotidiana evidence-informed per ottimizzare skincare, sonno e postura, eliminando le complessità e suggerendo lo "stack" di prodotti ideale.

## ✨ Funzionalità Principali

*   **Generazione Protocollo Custom:** Routine basate sugli obiettivi specifici dell'utente (Skincare, Longevity, Focus, Postura).
*   **Daily Check-in:** Tracciamento delle abitudini mattutine e serali con un'interfaccia pulita e senza distrazioni.
*   **Autenticazione Social:** Accesso rapido e sicuro tramite Google grazie all'integrazione nativa con Supabase Auth.
*   **Privacy First:** I dati sensibili rimangono protetti, con un approccio che evita interrogatori inutili o claim medici.

## 🛠️ Tech Stack

Il progetto è costruito con tecnologie moderne per garantire performance e scalabilità:

*   **Frontend:** React, TypeScript, Vite
*   **Styling:** Tailwind CSS (con icone Lucide React)
*   **Backend & Auth:** Supabase
*   **Database:** PostgreSQL (via Supabase)

## 💻 Installazione Locale

Vuoi testare il progetto in locale? Segui questi passaggi:

1. **Clona la repository**
   ```bash
   git clone [https://github.com/Pino0511/biostack-ai.git](https://github.com/Pino0511/biostack-ai.git)
   cd biostack-ai
   ```

2. **Installa le dipendenze**
   ```bash
   npm install
   ```

3. **Configura le variabili d'ambiente**
   Crea un file `.env` nella root del progetto e inserisci le tue chiavi Supabase:
   ```env
   VITE_SUPABASE_URL=iltuourl.supabase.co
   VITE_SUPABASE_ANON_KEY=latuachiavesegreta
   ```

4. **Avvia il server di sviluppo**
   ```bash
   npm run dev
   ```

## ⚠️ Disclaimer

BioStack AI non è un dispositivo medico e non sostituisce in alcun modo il parere di un medico o di un dermatologo. I consigli forniti sono da considerarsi come supporto al benessere quotidiano generale.

---
**Sviluppato da [Jacopo Russo](https://github.com/Pino0511)**
