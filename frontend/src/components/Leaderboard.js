import React from 'react';
import { useSelector } from 'react-redux';
import './Leaderboard.css';

function Leaderboard() {
  const { scores, loading } = useSelector((state) => state.leaderboard);

  if (loading) {
    return <div className="leaderboard loading">Loading leaderboard...</div>;
  }

  if (scores.length === 0) {
    return (
      <div className="leaderboard empty">
        <h3>🏆 Leaderboard</h3>
        <p>No scores yet. Be the first!</p>
      </div>
    );
  }

  return (
    <div className="leaderboard">
      <h3>🏆 Top 10 Scores</h3>
      <div className="leaderboard-table">
        <div className="leaderboard-header">
          <span className="rank-col">Rank</span>
          <span className="name-col">Player</span>
          <span className="score-col">Turns</span>
        </div>
        {scores.map((entry) => (
          <div key={entry.rank} className="leaderboard-row">
            <span className="rank-col">
              {entry.rank === 1 && '🥇'}
              {entry.rank === 2 && '🥈'}
              {entry.rank === 3 && '🥉'}
              {entry.rank > 3 && `#${entry.rank}`}
            </span>
            <span className="name-col">{entry.playerName}</span>
            <span className="score-col">{entry.score}</span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Leaderboard;
