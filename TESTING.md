# Testing Strategy

## Overview

This document outlines the testing approach for the Treasure Hunter game.

## Backend Testing

### Unit Tests

Test individual components in isolation:

#### GameService Tests
- `testGenerateTreasurePositions()` - Verify 3 unique treasures are generated
- `testCreateOrResumeGame()` - Test session creation and resumption
- `testCalculateProximity()` - Verify proximity algorithm correctness
  - Orthogonal adjacency returns 3
  - Diagonal adjacency returns 2
  - Other positions return 1
- `testRevealPositions()` - Test position revelation logic
- `testGameCompletion()` - Verify game completes when 3 treasures found
- `testLeaderboardRanking()` - Verify scores are sorted correctly

#### Model Tests
- `testCoordinateValidation()` - Test coordinate bounds (0-4)
- `testCoordinateAdjacency()` - Test orthogonal and diagonal adjacency methods
- `testLeaderboardEntryComparison()` - Test sorting logic

### Integration Tests

Test API endpoints:

```java
@SpringBootTest
@AutoConfigureMockMvc
class GameControllerIntegrationTest {
    
    @Test
    void testStartGameEndpoint() {
        // POST /api/game/start with player name
        // Verify 200 OK and valid response structure
    }
    
    @Test
    void testRevealPositionsEndpoint() {
        // Start game, then reveal positions
        // Verify turn increment and correct results
    }
    
    @Test
    void testLeaderboardEndpoint() {
        // Complete a game, then fetch leaderboard
        // Verify score appears in top 10
    }
}
```

### Manual Testing

1. Start backend: `mvn spring-boot:run`
2. Use curl or Postman to test endpoints:

```bash
# Start game
curl -X POST http://localhost:8080/api/game/start \
  -H "Content-Type: application/json" \
  -d '{"playerName":"TestPlayer"}'

# Reveal positions
curl -X POST http://localhost:8080/api/game/reveal \
  -H "Content-Type: application/json" \
  -d '{
    "playerName":"TestPlayer",
    "positions":[{"row":0,"col":0},{"row":0,"col":1}]
  }'

# Get leaderboard
curl http://localhost:8080/api/leaderboard
```

## Frontend Testing

### Component Tests

Test React components using React Testing Library:

```javascript
// PlayerNameInput.test.js
test('renders name input and start button', () => {
  render(<PlayerNameInput />);
  expect(screen.getByPlaceholderText('Enter your name')).toBeInTheDocument();
  expect(screen.getByText('Start Game')).toBeInTheDocument();
});

test('disables button when name is empty', () => {
  render(<PlayerNameInput />);
  expect(screen.getByText('Start Game')).toBeDisabled();
});

// GameBoard.test.js
test('renders 5x5 grid', () => {
  render(<GameBoard />);
  const positions = screen.getAllByRole('button');
  expect(positions).toHaveLength(25);
});

test('allows selecting up to 3 positions', () => {
  // Click positions and verify selection state
});

// Position.test.js
test('displays treasure icon when revealed', () => {
  const position = { revealed: true, isTreasure: true };
  render(<Position position={position} />);
  expect(screen.getByText('💎')).toBeInTheDocument();
});

test('displays proximity value when revealed', () => {
  const position = { revealed: true, isTreasure: false, proximityValue: 3 };
  render(<Position position={position} />);
  expect(screen.getByText('3')).toBeInTheDocument();
});
```

### Redux Tests

Test Redux slices:

```javascript
// gameSlice.test.js
test('selectPosition adds position to selection', () => {
  const state = gameReducer(initialState, selectPosition(5));
  expect(state.selectedPositions).toContain(5);
});

test('prevents selecting more than 3 positions', () => {
  let state = initialState;
  state = gameReducer(state, selectPosition(0));
  state = gameReducer(state, selectPosition(1));
  state = gameReducer(state, selectPosition(2));
  state = gameReducer(state, selectPosition(3));
  expect(state.selectedPositions).toHaveLength(3);
});
```

### Integration Tests

Test full user flows:

1. Enter player name and start game
2. Select positions and reveal
3. Find all treasures
4. View leaderboard

### Manual Testing

1. Start frontend: `npm start`
2. Test user flows:
   - Enter name and start game
   - Select 1-3 positions per turn
   - Verify proximity hints display correctly
   - Find all 3 treasures
   - Verify game completion screen
   - Check leaderboard displays correctly
   - Close browser and reopen - verify game resumes

## End-to-End Testing

### Full System Test

1. Start backend on port 8080
2. Start frontend on port 3000
3. Complete full game flow:
   - Player A starts game and finds treasures in 8 turns
   - Player B starts game and finds treasures in 6 turns
   - Verify leaderboard shows Player B ranked higher
   - Player A starts new game (old session replaced)
   - Verify Player A can resume if browser refreshed

### Proximity Verification

Create a test with known treasure positions and verify proximity calculations:

```
Board with treasures at (0,0), (2,2), (4,4):

Expected proximity values:
- (0,1): 3 (orthogonal to (0,0))
- (1,0): 3 (orthogonal to (0,0))
- (1,1): 2 (diagonal to (0,0))
- (2,3): 3 (orthogonal to (2,2))
- (3,3): 2 (diagonal to (2,2))
- (0,4): 1 (far from all treasures)
```

## Performance Testing

### Backend
- Test concurrent game sessions (multiple players)
- Verify thread-safe session storage
- Test leaderboard with 100+ entries

### Frontend
- Test board rendering performance
- Verify Redux state updates are efficient
- Test with slow network conditions

## Test Coverage Goals

- Backend: 80%+ code coverage
- Frontend: 70%+ code coverage
- All critical paths must be tested
- All API endpoints must have integration tests

## Running Tests

### Backend
```bash
cd backend
mvn test
mvn test -Dtest=GameServiceTest  # Run specific test
```

### Frontend
```bash
cd frontend
npm test
npm test -- --coverage  # With coverage report
```

## Continuous Integration

Recommended CI pipeline:
1. Run backend tests
2. Run frontend tests
3. Build backend JAR
4. Build frontend production bundle
5. Run E2E tests
6. Generate coverage reports
