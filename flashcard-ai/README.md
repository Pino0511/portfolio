- Flip and navigate flashcards in a modern, responsive UI
- Multilingual flashcard creation
- Error handling for user input and API responses
- REST API backend (Flask), React user interface
- Sample multilingual cards and easy customization

## Screenshots

![Screenshot 1](flashcard-ai/screenREADME/Screenshot%202025-10-16%20195217.png)
![Screenshot 2](flashcard-ai/screenREADME/Screenshot%202025-10-16%20195312.png)
![Screenshot 3](flashcard-ai/screenREADME/Screenshot%202025-10-16%20195333.png)
![Screenshot 4](flashcard-ai/screenREADME/Screenshot%202025-10-16%20195344.png)

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

This app uses OpenAI & Gemini APIs for automatic flashcard generation. Create a `.env` file in the `backend/` folder and add your API keys:

## ❓ Troubleshooting

### Backend doesn't start
- Ensure Python 3.9+ is installed: `python --version`
- Verify virtual environment is activated: `source venv/bin/activate` (Linux/Mac) or `venv\Scripts\activate` (Windows)
- Check all dependencies are installed: `pip install -r requirements.txt`
- Verify port 5000 is not in use
- Check that `.env` file exists in `backend/` folder with API keys

### Frontend doesn't start
- Ensure Node.js 16+ is installed: `node --version`
- Try clearing npm cache: `npm cache clean --force`
- Delete `node_modules` and reinstall: `rm -rf node_modules && npm install`
- Check that port 3000 is not in use

### API errors when generating flashcards
- Verify API keys are correctly set in `.env` file
- Check API key has valid permissions in OpenAI/Gemini dashboard
- Verify internet connection
- Check API rate limits haven't been exceeded
- Restart both backend and frontend after adding/changing API keys

### CORS errors
- Ensure Flask-CORS is installed: `pip install flask-cors`
- Verify backend is running on `http://localhost:5000`
- Clear browser cache and cookies
- Try opening in incognito/private window

### Button click not working
- Check browser console for JavaScript errors (F12)
- Verify both frontend and backend are running
- Try hard refresh: `Ctrl+Shift+R` (Cmd+Shift+R on Mac)
- Check that all dependencies are properly installed
