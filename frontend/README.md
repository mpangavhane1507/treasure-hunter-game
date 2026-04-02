# Treasure Hunter Frontend

React + Redux web application for the Treasure Hunter game.

## Prerequisites

- Node.js 18 or higher
- npm or yarn

## Installation

```bash
npm install
```

## Running

```bash
npm start
```

The application will start on `http://localhost:3000`

Make sure the backend is running on `http://localhost:8080` before starting the frontend.

## Building for Production

```bash
npm run build
```

This creates an optimized production build in the `build/` directory.

## Project Structure

```
src/
├── api/              # API client for backend communication
├── components/       # React components
│   ├── GameContainer.js      # Root game component
│   ├── PlayerNameInput.js    # Name entry screen
│   ├── GameBoard.js          # Main game board
│   ├── Position.js           # Individual grid cell
│   ├── GameComplete.js       # Completion screen
│   └── Leaderboard.js        # Top scores display
├── store/            # Redux store and slices
│   ├── store.js              # Store configuration
│   ├── gameSlice.js          # Game state management
│   └── leaderboardSlice.js   # Leaderboard state
├── App.js            # Main app component
└── index.js          # Entry point
```

## Features

- Player name registration
- Interactive 5x5 game board
- Position selection (up to 3 per turn)
- Real-time proximity hints
- Treasure discovery animations
- Game completion screen
- Top 10 leaderboard
- Session persistence (resume game after browser restart)

## State Management

The application uses Redux Toolkit for state management:

- **Game State**: Player info, board state, turn count, selections
- **Leaderboard State**: Top scores and rankings

## API Integration

The frontend communicates with the backend via REST API:

- `POST /api/game/start` - Start or resume game
- `POST /api/game/reveal` - Reveal selected positions
- `GET /api/leaderboard` - Fetch top scores

## Testing

```bash
npm test
```

## Technologies

- React 18
- Redux Toolkit
- Axios for HTTP requests
- CSS3 for styling
