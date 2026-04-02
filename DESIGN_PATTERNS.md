# Design Patterns and Architecture

This document explains the design patterns and architectural decisions used in the Treasure Hunter game.

## Backend Design Patterns

### 1. Layered Architecture (MVC Pattern)

The backend follows a clean layered architecture:

```
Controller Layer (REST API)
    ↓
Service Layer (Business Logic)
    ↓
Model Layer (Domain Objects)
    ↓
Storage Layer (In-Memory)
```

**Benefits:**
- Separation of concerns
- Easy to test each layer independently
- Clear responsibility boundaries
- Maintainable and scalable

**Implementation:**
- `GameController` - Handles HTTP requests/responses
- `GameService` - Contains game logic and rules
- Model classes - Domain objects (GameSession, Coordinate, etc.)
- `ConcurrentHashMap` - Thread-safe in-memory storage

### 2. Data Transfer Object (DTO) Pattern

Separate DTOs for API communication:

```java
// Request DTOs
StartGameRequest
RevealPositionsRequest

// Response DTOs
StartGameResponse
RevealPositionsResponse
LeaderboardResponse
```

**Benefits:**
- Decouples internal models from API contracts
- Allows different representations for different clients
- Easier API versioning
- Prevents exposing internal structure

### 3. Service Layer Pattern

`GameService` encapsulates all business logic:

```java
@Service
public class GameService {
    public GameSession createOrResumeGame(String playerName)
    public RevealPositionsResult revealPositions(...)
    public int calculateProximity(...)
    public List<LeaderboardEntry> getTopScores(int limit)
}
```

**Benefits:**
- Centralized business logic
- Reusable across multiple controllers
- Easy to test without HTTP layer
- Transaction boundaries (if database added)

### 4. Repository Pattern (Simplified)

In-memory storage acts as a simple repository:

```java
private final Map<String, GameSession> sessions = new ConcurrentHashMap<>();
private final List<LeaderboardEntry> leaderboard = Collections.synchronizedList(new ArrayList<>());
```

**Benefits:**
- Abstraction over data storage
- Easy to swap for database implementation
- Thread-safe concurrent access
- Simple for MVP requirements

### 5. Value Object Pattern

`Coordinate` is an immutable value object:

```java
public class Coordinate {
    private final int row;
    private final int col;
    
    // Validation in constructor
    // Equals and hashCode based on values
    // No setters (immutable)
}
```

**Benefits:**
- Encapsulates validation logic
- Prevents invalid states
- Safe to use as map keys
- Thread-safe by design

### 6. Strategy Pattern (Implicit)

Proximity calculation could be extracted as a strategy:

```java
public interface ProximityCalculator {
    int calculate(Coordinate position, List<Coordinate> treasures);
}

// Current implementation is inline, but could be:
public class StandardProximityCalculator implements ProximityCalculator {
    // Current logic here
}
```

**Benefits:**
- Easy to add different proximity algorithms
- Testable in isolation
- Configurable at runtime

## Frontend Design Patterns

### 1. Flux Architecture (Redux)

Unidirectional data flow:

```
Action → Reducer → Store → View → Action
```

**Implementation:**
- Redux Toolkit for state management
- Slices for modular state (gameSlice, leaderboardSlice)
- Async thunks for API calls
- Immutable state updates

**Benefits:**
- Predictable state changes
- Time-travel debugging
- Easy to test
- Centralized state

### 2. Container/Presentational Component Pattern

**Container Components** (Smart):
- `GameContainer` - Manages game lifecycle
- Connected to Redux store
- Handle business logic

**Presentational Components** (Dumb):
- `Position` - Pure display component
- `Leaderboard` - Renders data
- Receive data via props
- No business logic

**Benefits:**
- Reusable presentational components
- Easy to test presentational components
- Clear separation of concerns
- Better performance (memo optimization)

### 3. Higher-Order Component Pattern (HOC)

Redux `connect` and hooks like `useSelector`:

```javascript
const { playerName, board } = useSelector((state) => state.game);
const dispatch = useDispatch();
```

**Benefits:**
- Inject Redux state into components
- Reusable connection logic
- Automatic re-renders on state changes

### 4. Compound Component Pattern

`GameBoard` composes multiple `Position` components:

```javascript
<div className="game-board">
  {board.map((position) => (
    <Position key={position.index} position={position} />
  ))}
</div>
```

**Benefits:**
- Flexible composition
- Reusable building blocks
- Clear component hierarchy

### 5. Custom Hooks Pattern (Potential)

Could extract reusable logic:

```javascript
// Custom hook for game logic
function useGameLogic() {
  const dispatch = useDispatch();
  const game = useSelector(state => state.game);
  
  const selectPosition = (index) => {
    dispatch(selectPositionAction(index));
  };
  
  return { game, selectPosition };
}
```

**Benefits:**
- Reusable stateful logic
- Cleaner components
- Easy to test

### 6. Facade Pattern

`gameApi.js` provides a simple interface to backend:

```javascript
export const startGame = async (playerName) => {
  const response = await apiClient.post('/game/start', { playerName });
  return response.data;
};
```

**Benefits:**
- Hides axios complexity
- Single place to change API calls
- Easy to mock for testing
- Consistent error handling

## Architectural Decisions

### 1. In-Memory Storage

**Decision:** Use `ConcurrentHashMap` instead of database

**Rationale:**
- Requirement: No database needed
- Simplifies deployment
- Fast access times
- Sufficient for MVP

**Trade-offs:**
- Data lost on restart (acceptable per requirements)
- Not suitable for production scale
- No persistence across deployments

### 2. RESTful API Design

**Decision:** Use REST instead of GraphQL or WebSockets

**Rationale:**
- Simple CRUD operations
- Standard HTTP methods
- Easy to test and debug
- Wide client support

**Endpoints:**
- `POST /api/game/start` - Create/resume game
- `POST /api/game/reveal` - Submit turn
- `GET /api/leaderboard` - Fetch scores

### 3. Redux for State Management

**Decision:** Use Redux Toolkit instead of Context API or MobX

**Rationale:**
- Complex state interactions
- Multiple components need game state
- Time-travel debugging useful
- Middleware for async actions

**Trade-offs:**
- More boilerplate than Context API
- Learning curve for Redux
- Overkill for very simple apps

### 4. Single API Call Per Turn

**Decision:** Submit all positions in one request

**Rationale:**
- Reduces network overhead
- Atomic turn operation
- Consistent turn counter
- Better user experience

**Implementation:**
```java
public RevealPositionsResult revealPositions(
    String playerName, 
    List<Coordinate> positions  // 1-3 positions
)
```

### 5. Session Identification by Name

**Decision:** Use player name as session key (no authentication)

**Rationale:**
- Requirement: Simple session persistence
- No security requirements specified
- Easy to implement
- Sufficient for game context

**Trade-offs:**
- No multi-device support
- Name collisions possible
- Not suitable for production

### 6. Proximity Algorithm

**Decision:** Distance-based with orthogonal/diagonal distinction

**Rationale:**
- Clear game mechanics
- Easy to understand for players
- Computationally simple
- Provides good gameplay

**Implementation:**
```java
// Proximity 3: Orthogonally adjacent
// Proximity 2: Diagonally adjacent
// Proximity 1: Everything else
```

## SOLID Principles

### Single Responsibility Principle (SRP)
- `GameController` - Only handles HTTP
- `GameService` - Only handles game logic
- Each component has one reason to change

### Open/Closed Principle (OCP)
- Can add new proximity algorithms without modifying existing code
- Can add new API endpoints without changing service layer

### Liskov Substitution Principle (LSP)
- DTOs can be substituted without breaking contracts
- Components can be swapped with compatible implementations

### Interface Segregation Principle (ISP)
- Small, focused interfaces
- Components only depend on what they use

### Dependency Inversion Principle (DIP)
- Controller depends on Service abstraction
- Service could depend on Repository interface (if added)

## Code Quality Practices

### 1. Immutability
- Redux state is immutable
- Coordinate objects are immutable
- Prevents accidental mutations

### 2. Validation
- Input validation in DTOs (`@NotBlank`, `@Size`)
- Coordinate bounds checking
- Position already revealed checking

### 3. Error Handling
- Try-catch in controller
- Meaningful error messages
- Proper HTTP status codes

### 4. Thread Safety
- `ConcurrentHashMap` for sessions
- `Collections.synchronizedList` for leaderboard
- Atomic operations in service layer

### 5. Separation of Concerns
- Clear layer boundaries
- No business logic in controllers
- No HTTP logic in services

## Testing Strategy

### Unit Tests
- Test each layer independently
- Mock dependencies
- Focus on business logic

### Integration Tests
- Test API endpoints
- Test Redux actions and reducers
- Test component interactions

### E2E Tests
- Test complete user flows
- Test backend + frontend together
- Verify game rules

## Future Improvements

### Backend
1. Add database (PostgreSQL, MongoDB)
2. Implement authentication (JWT)
3. Add WebSocket for real-time updates
4. Implement caching (Redis)
5. Add metrics and monitoring

### Frontend
1. Add animations and transitions
2. Implement sound effects
3. Add difficulty levels
4. Mobile-responsive design
5. Progressive Web App (PWA)

### Architecture
1. Microservices architecture
2. Event-driven architecture
3. CQRS pattern for game state
4. GraphQL API
5. Server-side rendering (SSR)
