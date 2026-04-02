import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchLeaderboard } from '../store/leaderboardSlice';
import { resetGame } from '../store/gameSlice';
import Leaderboard from './Leaderboard';
import './GameComplete.css';

function GameComplete({ onNewGame }) {
  const dispatch = useDispatch();
  const { turnCount, playerName } = useSelector((state) => state.game);

  useEffect(() => {
    dispatch(fetchLeaderboard());
  }, [dispatch]);

  const handleNewGame = () => {
    dispatch(resetGame());
    onNewGame();
  };

  return (
    <div className="game-complete">
      <div className="completion-message">
        <h1>🎉 Congratulations!</h1>
        <p className="player-name">{playerName}</p>
        <p className="score-message">
          You found all treasures in <span className="score">{turnCount}</span> turns!
        </p>
      </div>

      <Leaderboard />

      <button className="new-game-button" onClick={handleNewGame}>
        Play Again
      </button>
    </div>
  );
}

export default GameComplete;
