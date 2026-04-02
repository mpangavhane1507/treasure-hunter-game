# Treasure Hunter Backend

Java Spring Boot REST API for the Treasure Hunter game.

## Prerequisites

- Java 17 or higher
- Maven 3.8+

## Building

```bash
mvn clean install
```

## Running

```bash
mvn spring-boot:run
```

The server will start on `http://localhost:8080`

## API Endpoints

### Start or Resume Game
```
POST /api/game/start
Content-Type: application/json

{
  "playerName": "John"
}

Response:
{
  "playerName": "John",
  "turnCount": 0,
  "revealedPositions": [],
  "isComplete": false
}
```

### Reveal Positions
```
POST /api/game/reveal
Content-Type: application/json

{
  "playerName": "John",
  "positions": [
    {"row": 0, "col": 0},
    {"row": 0, "col": 1}
  ]
}

Response:
{
  "turnCount": 1,
  "newlyRevealed": [
    {"row": 0, "col": 0, "isTreasure": false, "proximityValue": 1},
    {"row": 0, "col": 1, "isTreasure": true, "proximityValue": null}
  ],
  "isComplete": false,
  "finalScore": null
}
```

### Get Game State
```
GET /api/game/state/{playerName}

Response: Same as Start Game response
```

### Get Leaderboard
```
GET /api/leaderboard

Response:
{
  "topScores": [
    {"playerName": "Alice", "score": 5, "rank": 1},
    {"playerName": "Bob", "score": 7, "rank": 2}
  ]
}
```

## Testing

```bash
mvn test
```

## Architecture

- **Controller Layer**: REST endpoints (`GameController`)
- **Service Layer**: Business logic (`GameService`)
- **Model Layer**: Domain objects (`GameSession`, `Coordinate`, etc.)
- **DTO Layer**: Data transfer objects for API communication
- **Storage**: In-memory `ConcurrentHashMap` for sessions and leaderboard

## Game Logic

### Proximity Calculation
- **Proximity 3**: Orthogonally adjacent to treasure (up/down/left/right)
- **Proximity 2**: Diagonally adjacent to treasure
- **Proximity 1**: All other positions

### Session Management
- Sessions stored in memory keyed by player name
- Resumable until game completion
- Completing a game allows starting fresh with same name
- All data lost on server restart
