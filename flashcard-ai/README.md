# Flashcard AI Web App

This is a simple full-stack AI-powered flashcard generation web application built with a React frontend and Flask backend.  
It allows you to input text, automatically generate flashcards using OpenAI/Gemini APIs, and study interactively with flipping card animations and multilingual support.

## Features

- Generate flashcards from custom text using AI (OpenAI, Gemini)
- Flip and navigate flashcards in a modern, responsive UI
- Multilingual flashcard creation
- Error handling for user input and API responses
- REST API backend (Flask), React user interface
- Sample multilingual cards and easy customization

## Screenshots

![Screenshot 1](screenREADME/Screenshot%202025-10-16%20195217.png) ![Screenshot 2](screenREADME/Screenshot%202025-10-16%20195312.png) ![Screenshot 3](screenREADME/Screenshot%202025-10-16%20195333.png) ![Screenshot 4](screenREADME/Screenshot%202025-10-16%20195344.png)

## Getting Started

### Prerequisites

- [Node.js](https://nodejs.org/) >= 16
- [Python](https://www.python.org/) >= 3.9
- [pip](https://pip.pypa.io/en/stable/) (Recommend creating a virtual environment)

### Installation

1. Clone the repository:
    ```
    git clone <repository-url>
    cd flashcard-ai
    ```
2. Install backend dependencies:
    ```
    cd backend
    python -m venv venv
    source venv/bin/activate
    pip install -r requirements.txt
    python app.py
    ```
3. Install frontend dependencies:
    ```
    cd ../frontend/flashcard-frontend
    npm install
    npm start
    ```
4. Access via `http://localhost:3000` in your browser.

## Project Structure

- `backend/` — Flask API for flashcard generation
- `frontend/flashcard-frontend/` — React UI for displaying and creating cards

## API

This app uses OpenAI & Gemini APIs for automatic flashcard generation.  
Create a `.env` file in the `backend/` folder and add your API keys:

