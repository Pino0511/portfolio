# 🧬 BioStack AI

> **Your daily glow-up and longevity protocol.**

BioStack AI is a web application designed to generate personalized wellness protocols. Through a quick onboarding process, the app delivers a daily evidence-informed routine to optimize skincare, sleep, and posture — removing complexity and suggesting the ideal product "stack".

## 🛠️ Tech Stack

- **Frontend:** React, Vite, TypeScript, Tailwind CSS
- **Backend / BaaS:** Supabase (Auth, Database, Edge Functions)
- **Authentication:** Supabase Auth with Google OAuth
- **Hosting:** Vercel / Netlify (suggested)
- **Tooling:** ESLint, Prettier, npm

## 📸 Demo

> 📹 **Video Demo**: [Watch the full application demo](https://www.loom.com/share/b023472755134a5cbf96c960d0f900ed)

## ✨ Key Features

- 🎯 **Custom Protocol Generation:** Routines based on the user's specific goals (Skincare, Longevity, Focus, Posture).
- 📅 **Daily Check-in:** Tracking of morning and evening habits with a clean, distraction-free interface.
- 🔐 **Social Authentication:** Quick and secure login via Google thanks to native integration with Supabase Auth.
- 🔒 **Privacy First:** Sensitive data remains protected, with an approach that avoids unnecessary questioning or medical claims.

## 💻 Local Installation

To test the project locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Pino0511/biostack-ai.git
   cd biostack-ai
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Configure environment variables**:
   Create a `.env` file in the project root and add your Supabase credentials:
   ```env
   VITE_SUPABASE_URL=https://yoururl.supabase.co
   VITE_SUPABASE_ANON_KEY=yoursecretkey
   ```

4. **Start the development server**:
   ```bash
   npm run dev
   ```

5. Open your browser at `http://localhost:5173` 🚀

## ⚠️ Disclaimer

BioStack AI is **not a medical device** and does not replace the advice of a doctor or dermatologist in any way. The suggestions provided are intended as support for general daily wellness.

**Developed by [Jacopo Russo](https://github.com/Pino0511)**
