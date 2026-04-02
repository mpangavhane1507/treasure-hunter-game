# Requirements Document

## Introduction

The Treasure Hunter Game is a full-stack web browser game where players hunt for 3 hidden treasures on a 5x5 grid board. Players reveal positions strategically to find all treasures in the minimum number of turns. The game features a proximity system that guides players toward treasures, session persistence for resuming games, and a leaderboard showing top scores.

## Glossary

- **Game_System**: The complete treasure hunter application including frontend and backend
- **Frontend**: React + Redux web application that renders the game interface
- **Backend**: Java REST API that manages game state and logic
- **Game_Board**: A 5x5 grid containing 3 hidden treasure positions
- **Turn**: A single game action where the player reveals 1-3 positions
- **Position**: A specific cell on the Game_Board identified by coordinates
- **Treasure**: A hidden item placed on the Game_Board that players must find
- **Proximity_Value**: A numeric indicator (1-3) showing distance to nearest Treasure
- **Score**: The total number of Turns taken to find all 3 Treasures
- **Game_Session**: A persistent game state associated with a player name
- **Leaderboard**: A ranked list of the top 10 Scores

## Requirements

### Requirement 1: Player Registration

**User Story:** As a player, I want to enter my name at game start, so that my game session and score are tracked.

#### Acceptance Criteria

1. WHEN the game starts, THE Frontend SHALL prompt the player to enter a name
2. THE Frontend SHALL send the player name to the Backend via API call
3. THE Backend SHALL store the player name associated with the Game_Session
4. THE Backend SHALL validate that the player name is not empty
5. IF the player name is empty, THEN THE Backend SHALL return a validation error

### Requirement 2: Game Board Generation

**User Story:** As a player, I want a new game board created when I start, so that I can begin hunting for treasures.

#### Acceptance Criteria

1. WHEN a new game is created, THE Backend SHALL generate a 5x5 Game_Board
2. THE Backend SHALL randomly place exactly 3 Treasures on distinct Positions
3. THE Backend SHALL ensure no two Treasures occupy the same Position
4. THE Backend SHALL keep Treasure Positions hidden from the Frontend
5. THE Backend SHALL initialize the Turn counter to zero

### Requirement 3: Position Selection

**User Story:** As a player, I want to select up to 3 positions per turn, so that I can efficiently search for treasures.

#### Acceptance Criteria

1. WHEN a Turn begins, THE Frontend SHALL allow the player to select between 1 and 3 Positions
2. THE Frontend SHALL prevent selection of more than 3 Positions per Turn
3. THE Frontend SHALL prevent selection of already-revealed Positions
4. THE Frontend SHALL visually indicate which Positions are selected
5. THE Frontend SHALL provide a submit action to complete the Turn

### Requirement 4: Position Revelation

**User Story:** As a player, I want to reveal selected positions in a single API call, so that I can see what I found.

#### Acceptance Criteria

1. WHEN the player submits selected Positions, THE Frontend SHALL send all selected Positions in a single API call
2. THE Backend SHALL process all submitted Positions in the same Turn
3. THE Backend SHALL increment the Turn counter by one
4. FOR EACH submitted Position, THE Backend SHALL determine if it contains a Treasure
5. FOR EACH submitted Position that does not contain a Treasure, THE Backend SHALL calculate the Proximity_Value to the nearest Treasure
6. THE Backend SHALL return results for all submitted Positions in the API response

### Requirement 5: Proximity Calculation

**User Story:** As a player, I want to see proximity hints for non-treasure positions, so that I can strategically find treasures.

#### Acceptance Criteria

1. WHEN a Position without a Treasure is revealed, THE Backend SHALL calculate the distance to the nearest Treasure
2. THE Backend SHALL assign Proximity_Value 3 for Positions orthogonally adjacent to a Treasure
3. THE Backend SHALL assign Proximity_Value 2 for Positions diagonally adjacent to a Treasure or orthogonally adjacent to a Proximity_Value 3 Position
4. THE Backend SHALL assign Proximity_Value 1 for all other Positions
5. THE Backend SHALL return the Proximity_Value with the Position result

### Requirement 6: Board State Persistence

**User Story:** As a player, I want revealed positions to stay visible, so that I can use previous information to make decisions.

#### Acceptance Criteria

1. WHEN a Position is revealed, THE Frontend SHALL display the result (Treasure or Proximity_Value)
2. THE Frontend SHALL keep all revealed Positions visible until the game ends
3. THE Backend SHALL maintain the state of all revealed Positions in the Game_Session
4. WHEN the Frontend requests game state, THE Backend SHALL return all previously revealed Positions

### Requirement 7: Game Completion Detection

**User Story:** As a player, I want the game to end when I find all treasures, so that I can see my final score.

#### Acceptance Criteria

1. WHEN all 3 Treasures are found, THE Backend SHALL mark the game as complete
2. THE Backend SHALL record the final Score (total number of Turns)
3. THE Backend SHALL return game completion status in the API response
4. THE Frontend SHALL display a game completion screen
5. THE Frontend SHALL prevent further Position selections after game completion

### Requirement 8: Score Tracking and Leaderboard

**User Story:** As a player, I want to see the top 10 scores, so that I can compare my performance with others.

#### Acceptance Criteria

1. WHEN a game is completed, THE Backend SHALL store the Score with the player name
2. THE Backend SHALL maintain a Leaderboard of completed games
3. THE Backend SHALL rank Scores in ascending order (lower is better)
4. WHEN the Leaderboard is requested, THE Backend SHALL return the top 10 Scores
5. THE Frontend SHALL display the Leaderboard on the game completion screen

### Requirement 9: Session Persistence

**User Story:** As a player, I want to resume my game after closing the browser, so that I don't lose my progress.

#### Acceptance Criteria

1. WHEN a player returns with the same name, THE Backend SHALL retrieve the existing Game_Session
2. THE Backend SHALL restore the Game_Board state including all revealed Positions
3. THE Backend SHALL restore the current Turn counter
4. THE Frontend SHALL display the restored game state
5. IF no Game_Session exists for the player name, THEN THE Backend SHALL create a new game

### Requirement 10: API Design

**User Story:** As a developer, I want a clean REST API, so that the frontend and backend communicate efficiently.

#### Acceptance Criteria

1. THE Backend SHALL provide an API endpoint to create or resume a game by player name
2. THE Backend SHALL provide an API endpoint to submit selected Positions for a Turn
3. THE Backend SHALL provide an API endpoint to retrieve current game state
4. THE Backend SHALL provide an API endpoint to retrieve the Leaderboard
5. THE Backend SHALL return responses in JSON format
6. THE Backend SHALL use appropriate HTTP status codes for success and error conditions

### Requirement 11: Frontend State Management

**User Story:** As a developer, I want Redux to manage application state, so that the UI remains consistent and predictable.

#### Acceptance Criteria

1. THE Frontend SHALL use Redux to store game state
2. THE Frontend SHALL use Redux to store the Game_Board state
3. THE Frontend SHALL use Redux to store revealed Positions
4. THE Frontend SHALL use Redux to store the current Turn counter
5. THE Frontend SHALL dispatch actions for all state changes

### Requirement 12: Build and Deployment

**User Story:** As a developer, I want clear build and run instructions, so that I can set up the application easily.

#### Acceptance Criteria

1. THE Game_System SHALL include documentation for building the Frontend
2. THE Game_System SHALL include documentation for building the Backend
3. THE Game_System SHALL include documentation for running the Frontend
4. THE Game_System SHALL include documentation for running the Backend
5. THE documentation SHALL specify all required dependencies and versions

### Requirement 13: Testing Strategy

**User Story:** As a developer, I want a testing approach, so that I can verify the game works correctly.

#### Acceptance Criteria

1. THE Game_System SHALL include a testing strategy document
2. THE testing strategy SHALL cover Backend game logic testing
3. THE testing strategy SHALL cover API endpoint testing
4. THE testing strategy SHALL cover Frontend component testing
5. THE testing strategy SHALL cover proximity calculation verification

### Requirement 14: In-Memory State Management

**User Story:** As a developer, I want game state stored in memory, so that the system remains simple without database dependencies.

#### Acceptance Criteria

1. THE Backend SHALL store all Game_Sessions in memory
2. THE Backend SHALL store the Leaderboard in memory
3. WHEN the Backend restarts, THE Backend SHALL clear all Game_Sessions
4. WHEN the Backend restarts, THE Backend SHALL clear the Leaderboard
5. THE Backend SHALL not require any external database or persistence layer

### Requirement 15: Round-Trip Game State Serialization

**User Story:** As a developer, I want game state properly serialized, so that API communication is reliable.

#### Acceptance Criteria

1. THE Backend SHALL serialize Game_Session state to JSON format
2. THE Frontend SHALL deserialize JSON responses into application state
3. FOR ALL valid Game_Session objects, serializing then deserializing SHALL produce an equivalent object
4. THE Backend SHALL include all necessary game state fields in JSON responses
5. IF JSON serialization fails, THEN THE Backend SHALL return an error response

## Notes

- The proximity system uses a distance-based metric where orthogonal adjacency (up, down, left, right) has higher proximity than diagonal adjacency
- The game does not require graphical design evaluation; functional correctness is the priority
- The method of board generation (hardcoded vs random) is not evaluated, but random placement is recommended for replayability
- Session persistence is achieved through player name lookup, not browser cookies or tokens
