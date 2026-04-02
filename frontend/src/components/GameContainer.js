import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import PlayerNameInput from './PlayerNameInput';
import GameBoard from './GameBoard';
import GameComplete from './GameComplete';
import './GameContainer.css';

function GameContainer() {
  const { playerName, isComplete } = useSelector((state) => state.game);
  const [gameStarted, setGameStarted] = useState(false);

  const handleGameStart = () => {
    setGameStarted(true);
  };

  const handleNewGame = () => {
    setGameStarted(false);
  };

  if (!gameStarted) {
    return <PlayerNameInput onGameStart={handleGameStart} />;
  }

  if (isComplete) {
    return <GameComplete onNewGame={handleNewGame} />;
  }

  return <GameBoard />;
}

export default GameContainer;
