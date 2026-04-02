import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const startGame = async (playerName) => {
  const response = await apiClient.post('/game/start', { playerName });
  return response.data;
};

export const revealPositions = async (playerName, positions) => {
  const response = await apiClient.post('/game/reveal', {
    playerName,
    positions,
  });
  return response.data;
};

export const getGameState = async (playerName) => {
  const response = await apiClient.get(`/game/state/${playerName}`);
  return response.data;
};

export const getLeaderboard = async () => {
  const response = await apiClient.get('/leaderboard');
  return response.data;
};
