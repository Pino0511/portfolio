# 🎬 Film Filter App

A simple, responsive web app to browse, filter, and manage a list of movies using a public API.

## ✨ Features

- ✅ **Fetch movies by genre** from an open API
- 🔍 **Search** movies by title
- 👁️ **Mark as seen / unseen** (saved via cookies)
- 🗑️ **Remove movies** from the list
- ➕ **Add custom movies**
- 🔃 **Sort A-Z / Z-A**
- 💻 **Responsive layout** (desktop, tablet, mobile)
- 🕒 Automatically removes the first "seen" movie every 2 minutes (observable)

## 📦 Tech Stack

- HTML5
- CSS3 (custom properties, responsive design)
- JavaScript (DOM, fetch, cookie handling)
- API: [sampleapis.com/movies](https://sampleapis.com/api-list/movies)

## 🧠 How It Works

- Movies are fetched by genre using query parameters (`?categoria=comedy`)
- Search and filter operations update the visible movie list
- Seen state is stored in browser cookies (`visti`)
- New movies added manually stay visible until the page reloads
