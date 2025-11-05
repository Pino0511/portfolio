import requests

r = requests.post("http://127.0.0.1:5000/generate_flashcards", json={"text": "Parlami di Python e Flask"})
print(r.json())
