# Treasure Hunter Game

A full-stack web browser game where players hunt for 3 hidden treasures on a 5x5 grid board.

## Project Structure

```
treasure-hunter-game/
├── backend/          # Java Spring Boot REST API
└── frontend/         # React + Redux web application
```

## Quick Start

### Automated Start (Recommended)

**Windows:**
```bash
start-game.bat
```

**Linux/Mac:**
```bash
chmod +x start-game.sh
./start-game.sh
```

This will automatically:
1. Check prerequisites (Java, Maven, Node.js)
2. Start the backend server
3. Install frontend dependencies
4. Start the frontend
5. Open the game in your browser

### Manual Start

**Prerequisites:**
- Java 17 or higher
- Node.js 18 or higher
- Maven 3.8+

**Terminal 1 - Backend:**
```bash
cd backend
mvn spring-boot:run
```
Backend will start on http://localhost:8080

**Terminal 2 - Frontend:**
```bash
cd frontend
npm install
npm start
```
Frontend will start on http://localhost:3000

### Stopping the Game

**Linux/Mac:**
```bash
./stop-game.sh
```

**Windows:**
Close the terminal windows or press Ctrl+C in each

## Game Rules

- Board size: 5x5 grid
- Objective: Find 3 hidden treasures in minimum number of turns
- Each turn: Reveal up to 3 positions
- Proximity hints guide you to treasures:
  - **3**: Orthogonally adjacent to treasure
  - **2**: Diagonally adjacent to treasure
  - **1**: Further away
- Score: Total number of turns taken
- Leaderboard: Top 10 scores displayed at game end

## Features

- Player name registration and session tracking
- Session persistence (resume game after browser restart)
- Real-time proximity calculations
- Top 10 leaderboard
- In-memory state management (no database required)

## API Endpoints

- `POST /api/game/start` - Start or resume game
- `POST /api/game/reveal` - Reveal selected positions
- `GET /api/game/state/{playerName}` - Get current game state
- `GET /api/leaderboard` - Get top 10 scores

## Testing

See individual README files in `backend/` and `frontend/` directories for testing instructions.
