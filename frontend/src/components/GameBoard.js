import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { revealPositions, selectPosition, deselectPosition } from '../store/gameSlice';
import Position from './Position';
import './GameBoard.css';

function GameBoard() {
  const dispatch = useDispatch();
  const { playerName, board, selectedPositions, turnCount, loading } = useSelector(
    (state) => state.game
  );

  const handlePositionClick = (index) => {
    if (board[index].revealed || loading) return;

    if (selectedPositions.includes(index)) {
      dispatch(deselectPosition(index));
    } else if (selectedPositions.length < 3) {
      dispatch(selectPosition(index));
    }
  };

  const handleReveal = async () => {
    if (selectedPositions.length === 0 || loading) return;

    const positions = selectedPositions.map((index) => ({
      row: Math.floor(index / 5),
      col: index % 5,
    }));

    await dispatch(revealPositions({ playerName, positions }));
  };

  return (
    <div className="game-board-container">
      <div className="game-header">
        <h2>🏴‍☠️ Treasure Hunter</h2>
        <div className="game-info">
          <span className="player-name">Player: {playerName}</span>
          <span className="turn-count">Turns: {turnCount}</span>
        </div>
      </div>

      <div className="game-board">
        {board.map((position) => (
          <Position
            key={position.index}
            position={position}
            isSelected={selectedPositions.includes(position.index)}
            onClick={() => handlePositionClick(position.index)}
          />
        ))}
      </div>

      <div className="game-controls">
        <div className="selection-info">
          Selected: {selectedPositions.length}/3
        </div>
        <button
          className="reveal-button"
          onClick={handleReveal}
          disabled={selectedPositions.length === 0 || loading}
        >
          {loading ? 'Revealing...' : 'Reveal Positions'}
        </button>
      </div>

      <div className="legend">
        <h4>Legend:</h4>
        <div className="legend-items">
          <span className="legend-item">
            <span className="legend-icon treasure">💎</span> Treasure
          </span>
          <span className="legend-item">
            <span className="legend-icon proximity-3">3</span> Very Close
          </span>
          <span className="legend-item">
            <span className="legend-icon proximity-2">2</span> Close
          </span>
          <span className="legend-item">
            <span className="legend-icon proximity-1">1</span> Far
          </span>
        </div>
      </div>
    </div>
  );
}

export default GameBoard;
