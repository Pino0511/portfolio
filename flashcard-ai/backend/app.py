from flask import Flask, request, jsonify
import google.generativeai as genai
import json
import re
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

API_KEY = "AIzaSyAEzQ8SCR3uXBptiYW8KvTtPWFOxp0fWFU"

genai.configure(api_key=API_KEY)
@app.route('/')
def index():
    return "Server Gemini attivo!"

@app.route('/generate_flashcards', methods=['POST'])
def generate_flashcards():
    data = request.json
    testo = data['text']
    lang = data.get('lang', 'en')
    if lang == "it":
        prompt_lang = "in Italian"
    else:
        prompt_lang = "in English"
    prompt = (
        f"Generate 5 question/answer flashcards in {prompt_lang}. "
        f"Use ONLY {prompt_lang} for both the questions and answers! Return the result as a single JSON array of objects in this format: "
        f'[{{"question": "...", "answer": "..."}}]. '
        f"Use this content:\n{testo}"
    )
    try:
        model = genai.GenerativeModel('gemini-2.5-pro')
        response = model.generate_content(prompt)
        contenuto = response.text
        clean = re.sub(r'``````', '', contenuto, flags=re.IGNORECASE)
        # Trova solo tutto il blocco tra [ e ]
        match = re.search(r'\[.*\]', clean, flags=re.DOTALL)
        if match:
            clean = match.group(0)
        elif clean.strip().startswith("{"):
            clean = "[" + clean.strip() + "]"
        else:
            clean = clean.strip()
        try:
            flashcards = json.loads(clean)
        except Exception:
            flashcards = [{"question": "Errore AI", "answer": clean}]
    except Exception as e:
        flashcards = [{"question": "Errore generazione Gemini", "answer": str(e)}]

    return jsonify({"flashcards": flashcards})

if __name__ == '__main__':
    app.run(debug=True)
