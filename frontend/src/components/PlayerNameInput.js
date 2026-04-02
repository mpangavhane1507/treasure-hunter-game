import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { startGame } from '../store/gameSlice';
import './PlayerNameInput.css';

function PlayerNameInput({ onGameStart }) {
  const [name, setName] = useState('');
  const dispatch = useDispatch();
  const { loading, error } = useSelector((state) => state.game);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (name.trim()) {
      const result = await dispatch(startGame(name.trim()));
      if (!result.error) {
        onGameStart();
      }
    }
  };

  return (
    <div className="player-name-input">
      <h1>🏴‍☠️ Treasure Hunter</h1>
      <p className="subtitle">Find 3 hidden treasures in minimum turns!</p>
      
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter your name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          disabled={loading}
          autoFocus
        />
        <button type="submit" disabled={loading || !name.trim()}>
          {loading ? 'Starting...' : 'Start Game'}
        </button>
      </form>
      
      {error && <div className="error">{error}</div>}
      
      <div className="game-rules">
        <h3>How to Play:</h3>
        <ul>
          <li>Select up to 3 positions per turn</li>
          <li>Find all 3 treasures in minimum turns</li>
          <li>Proximity hints: 3 (close) → 2 (medium) → 1 (far)</li>
        </ul>
      </div>
    </div>
  );
}

export default PlayerNameInput;
