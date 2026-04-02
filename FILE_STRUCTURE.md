# Complete File Structure

This document lists all files in the Treasure Hunter Game project.

## Root Directory

```
treasure-hunter-game/
├── .gitignore                    # Git ignore rules
├── README.md                     # Project overview and quick start
├── BUILD_AND_RUN.md             # Detailed build and deployment guide
├── TESTING.md                    # Testing strategy and guidelines
├── DESIGN_PATTERNS.md           # Architecture and design patterns
├── PROJECT_SUMMARY.md           # Complete project summary
├── FILE_STRUCTURE.md            # This file
├── start-game.bat               # Windows quick start script
├── start-game.sh                # Linux/Mac quick start script
└── stop-game.sh                 # Linux/Mac stop script
```

## Backend (Java Spring Boot)

```
backend/
├── pom.xml                                          # Maven configuration
├── README.md                                        # Backend documentation
│
├── src/main/java/com/treasurehunter/
│   ├── TreasureHunterApplication.java              # Spring Boot main class
│   │
│   ├── config/
│   │   └── CorsConfig.java                         # CORS configuration
│   │
│   ├── controller/
│   │   └── GameController.java                     # REST API endpoints
│   │
│   ├── service/
│   │   └── GameService.java                        # Business logic
│   │
│   ├── model/
│   │   ├── Coordinate.java                         # Position value object
│   │   ├── GameSession.java                        # Game state model
│   │   ├── RevealedPosition.java                   # Revealed cell model
│   │   └── LeaderboardEntry.java                   # Score entry model
│   │
│   └── dto/
│       ├── StartGameRequest.java                   # Start game request DTO
│       ├── StartGameResponse.java                  # Start game response DTO
│       ├── RevealPositionsRequest.java             # Reveal request DTO
│       ├── RevealPositionsResponse.java            # Reveal response DTO
│       ├── RevealedPositionDTO.java                # Position result DTO
│       ├── LeaderboardResponse.java                # Leaderboard response DTO
│       └── LeaderboardEntryDTO.java                # Score entry DTO
│
└── src/main/resources/
    └── application.properties                       # Application configuration
```

### Backend File Count: 15 files

## Frontend (React + Redux)

```
frontend/
├── package.json                                     # NPM dependencies
├── README.md                                        # Frontend documentation
│
├── public/
│   └── index.html                                   # HTML template
│
└── src/
    ├── index.js                                     # Application entry point
    ├── index.css                                    # Global styles
    ├── App.js                                       # Root component
    ├── App.css                                      # App styles
    │
    ├── api/
    │   └── gameApi.js                              # Backend API client
    │
    ├── store/
    │   ├── store.js                                # Redux store configuration
    │   ├── gameSlice.js                            # Game state slice
    │   └── leaderboardSlice.js                     # Leaderboard state slice
    │
    └── components/
        ├── GameContainer.js                         # Root game component
        ├── GameContainer.css                        # Container styles
        ├── PlayerNameInput.js                       # Name entry component
        ├── PlayerNameInput.css                      # Name input styles
        ├── GameBoard.js                            # Game board component
        ├── GameBoard.css                           # Board styles
        ├── Position.js                             # Grid cell component
        ├── Position.css                            # Cell styles
        ├── GameComplete.js                         # Completion screen
        ├── GameComplete.css                        # Completion styles
        ├── Leaderboard.js                          # Leaderboard component
        └── Leaderboard.css                         # Leaderboard styles
```

### Frontend File Count: 22 files

## Specification Files

```
.kiro/specs/treasure-hunter-game/
├── .config.kiro                                     # Spec configuration
├── requirements.md                                  # Requirements document
├── design.md                                        # Design document
└── tasks.md                                         # Implementation tasks
```

### Spec File Count: 4 files

## Total Project Statistics

- **Total Files**: 52
- **Backend Files**: 15
- **Frontend Files**: 22
- **Documentation Files**: 7
- **Spec Files**: 4
- **Scripts**: 3
- **Configuration**: 1 (.gitignore)

## File Types Breakdown

### Java Files (13)
- 1 Main application class
- 1 Configuration class
- 1 Controller class
- 1 Service class
- 4 Model classes
- 7 DTO classes

### JavaScript/JSX Files (14)
- 1 Entry point
- 1 App component
- 1 API client
- 3 Redux files (store + 2 slices)
- 6 React components
- 1 Test setup (if added)

### CSS Files (8)
- 1 Global styles
- 1 App styles
- 6 Component styles

### Configuration Files (4)
- 1 Maven POM (pom.xml)
- 1 NPM package (package.json)
- 1 Spring properties (application.properties)
- 1 HTML template (index.html)

### Documentation Files (7)
- README.md (main)
- BUILD_AND_RUN.md
- TESTING.md
- DESIGN_PATTERNS.md
- PROJECT_SUMMARY.md
- FILE_STRUCTURE.md
- backend/README.md
- frontend/README.md

### Scripts (3)
- start-game.bat (Windows)
- start-game.sh (Linux/Mac)
- stop-game.sh (Linux/Mac)

## Lines of Code Estimate

### Backend
- Java code: ~1,200 lines
- Configuration: ~50 lines
- **Total**: ~1,250 lines

### Frontend
- JavaScript/JSX: ~1,000 lines
- CSS: ~600 lines
- Configuration: ~50 lines
- **Total**: ~1,650 lines

### Documentation
- Markdown: ~2,500 lines

### Grand Total: ~5,400 lines

## Key Directories

### Source Code
- `backend/src/main/java/com/treasurehunter/` - All Java source code
- `frontend/src/` - All React source code

### Configuration
- `backend/src/main/resources/` - Backend configuration
- `frontend/public/` - Frontend static files

### Documentation
- Root directory - All documentation files
- `backend/README.md` - Backend-specific docs
- `frontend/README.md` - Frontend-specific docs

### Specifications
- `.kiro/specs/treasure-hunter-game/` - Requirements, design, tasks

## File Naming Conventions

### Backend (Java)
- Classes: PascalCase (e.g., `GameController.java`)
- Packages: lowercase (e.g., `com.treasurehunter.service`)

### Frontend (JavaScript)
- Components: PascalCase (e.g., `GameBoard.js`)
- Utilities: camelCase (e.g., `gameApi.js`)
- Styles: Match component name (e.g., `GameBoard.css`)

### Documentation
- All caps with underscores for guides (e.g., `BUILD_AND_RUN.md`)
- README.md for overview files

## Important Files to Review

### For Setup
1. `README.md` - Start here
2. `BUILD_AND_RUN.md` - Detailed setup
3. `start-game.bat` or `start-game.sh` - Quick start

### For Development
1. `backend/src/main/java/com/treasurehunter/service/GameService.java` - Core logic
2. `frontend/src/store/gameSlice.js` - State management
3. `DESIGN_PATTERNS.md` - Architecture guide

### For Testing
1. `TESTING.md` - Testing strategy
2. `backend/src/test/` - Backend tests (to be added)
3. `frontend/src/**/*.test.js` - Frontend tests (to be added)

### For API Integration
1. `backend/src/main/java/com/treasurehunter/controller/GameController.java` - API endpoints
2. `frontend/src/api/gameApi.js` - API client
3. `backend/README.md` - API documentation

## Missing Files (Intentional)

These files are not included but could be added:

### Testing
- `backend/src/test/java/**/*Test.java` - Unit and integration tests
- `frontend/src/**/*.test.js` - Component tests
- `frontend/src/setupTests.js` - Test configuration

### Build Artifacts (Generated)
- `backend/target/` - Maven build output
- `frontend/build/` - React production build
- `frontend/node_modules/` - NPM dependencies

### IDE Files
- `.idea/` - IntelliJ IDEA
- `.vscode/` - VS Code (may exist)
- `*.iml` - IntelliJ module files

### Environment
- `.env` - Environment variables
- `.env.local` - Local overrides

## File Dependencies

### Backend Dependencies (pom.xml)
- spring-boot-starter-web
- spring-boot-starter-validation
- spring-boot-starter-test

### Frontend Dependencies (package.json)
- react
- react-dom
- react-redux
- @reduxjs/toolkit
- axios
- react-scripts

## Build Outputs

### Backend
- `backend/target/treasure-hunter-backend-1.0.0.jar` - Executable JAR

### Frontend
- `frontend/build/` - Production-ready static files
  - `index.html`
  - `static/js/*.js` - Bundled JavaScript
  - `static/css/*.css` - Bundled CSS

## Version Control

### Tracked Files
- All source code
- All documentation
- Configuration files
- Scripts

### Ignored Files (.gitignore)
- Build artifacts (target/, build/)
- Dependencies (node_modules/)
- IDE files (.idea/, *.iml)
- Logs (*.log)
- OS files (.DS_Store)

---

**Last Updated**: 2026-04-01
**Total Files**: 52
**Project Status**: Complete and Ready
