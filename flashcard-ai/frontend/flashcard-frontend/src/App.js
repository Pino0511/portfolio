import React, { useState } from 'react';
import { FaSyncAlt, FaArrowRight, FaArrowLeft, FaRandom } from "react-icons/fa";
import './App.css';

function FlipCard({ question, answer, flipped, onFlip }) {
  return (
    <div className={`flip-card${flipped ? " flipped" : ""}`} onClick={onFlip}>
      <div className="flip-card-inner">
        <div className="flip-card-front">
          <div className="card-label">Question</div>
          <div className="card-content">{question}</div>
          <div className="card-tip">Click to reveal the answer</div>
        </div>
        <div className="flip-card-back">
          <div className="card-label">Answer</div>
          <div className="card-content">{answer}</div>
          <div className="card-tip">Click to go back</div>
        </div>
      </div>
    </div>
  );
}

function Loader() {
  return (
    <div className="loader">
      <span role="img" aria-label="robot">🤖</span>
      <span>Generating flashcards...</span>
    </div>
  );
}

function shuffleArray(array) {
  return [...array].sort(() => Math.random() - 0.5);
}

function App() {
  const [input, setInput] = useState('');
  const [flashcards, setFlashcards] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [current, setCurrent] = useState(0);
  const [flipped, setFlipped] = useState(false);
  const [lang, setLang] = useState("en");

  async function handleSubmit(e) {
    e.preventDefault();
    setLoading(true);
    setError('');
    setFlashcards([]);
    setCurrent(0);
    setFlipped(false);
    try {
      const res = await fetch('http://localhost:5000/generate_flashcards', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ text: input, lang })
      });
      const data = await res.json();
      if (data.flashcards && Array.isArray(data.flashcards) && data.flashcards.length > 0) {
        setFlashcards(data.flashcards);
      } else {
        setError("No flashcards generated.");
      }
    } catch (err) {
      setError('Unable to connect to backend.');
    }
    setLoading(false);
  }

  function nextCard() {
    setCurrent(c => {
      setFlipped(false);
      return Math.min(flashcards.length - 1, c + 1);
    });
  }
  function prevCard() {
    setCurrent(c => {
      setFlipped(false);
      return Math.max(0, c - 1);
    });
  }
  function handleReset() {
    setInput('');
    setFlashcards([]);
    setError('');
    setCurrent(0);
    setFlipped(false);
  }
  function handleFlip() {
    setFlipped(f => !f);
  }
  function handleShuffle() {
    setFlashcards(shuffleArray(flashcards));
    setCurrent(0);
    setFlipped(false);
  }

  return (
    <div className="root-container">
      <h1 className="title">
        <span role="img" aria-label="graduation cap">🎓</span> AI Flashcard Generator
      </h1>
      <form className="input-form" onSubmit={handleSubmit}>
        <label htmlFor="lang-select" className="lang-label">Flashcard language:</label>
        <select
          id="lang-select"
          value={lang}
          onChange={e => setLang(e.target.value)}
          className="lang-select"
        >
          <option value="en">English</option>
          <option value="it">Italian</option>
        </select>

        <textarea
          className="text-area"
          value={input}
          onChange={e => setInput(e.target.value)}
          placeholder="Enter your topic or text to generate flashcards..."
        />
        <button type="submit" disabled={loading || !input.trim()} className="gen-btn">
          {loading ? <Loader /> : <><FaSyncAlt /> Generate Flashcards</>}
        </button>
        {(flashcards.length > 0 || error) && (
          <button type="button" className="reset-btn" onClick={handleReset}>Reset</button>
        )}
      </form>
      <div className="desc" style={{ textAlign: "center", margin: "1.4em auto 0 auto", color: "#4b6e7a", fontSize: "1.07em" }}>
        Write your study topic or paste any text. The AI will create smart Q/A flashcards!
      </div>
      {error && <div className="error-msg">{error}</div>}
      {flashcards.length > 0 && !loading && (
        <div className='flashcard-section'>
          <div className="navigation">
            <button onClick={prevCard} disabled={current === 0} title="Previous card"><FaArrowLeft /></button>
            <span>{current + 1} / {flashcards.length}</span>
            <button onClick={nextCard} disabled={current === flashcards.length - 1} title="Next card"><FaArrowRight /></button>
            <button onClick={handleShuffle} title="Shuffle flashcards" className="shuffle-btn"><FaRandom /></button>
          </div>
          <FlipCard
            question={flashcards[current].question}
            answer={flashcards[current].answer}
            flipped={flipped}
            onFlip={handleFlip}
          />
          <div className="hint">Click on the flashcard to flip. Use arrows to change card. Shuffling helps for active recall!</div>
        </div>
      )}
    </div>
  );
}

export default App;
