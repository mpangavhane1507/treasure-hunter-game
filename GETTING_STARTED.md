# Getting Started with Treasure Hunter Game

Welcome! This guide will help you get the game running in minutes.

## What You're Building

A full-stack web game where players hunt for 3 hidden treasures on a 5x5 grid. Players get proximity hints and compete for the best score on the leaderboard.

## Prerequisites Check

Before starting, ensure you have:

1. **Java 17+** - Check with: `java -version`
2. **Maven 3.8+** - Check with: `mvn -version`
3. **Node.js 18+** - Check with: `node -version`

Don't have these? See [BUILD_AND_RUN.md](BUILD_AND_RUN.md) for installation links.

## Quick Start (3 Steps)

### Step 1: Start Backend

Open a terminal and run:

```bash
cd backend
mvn spring-boot:run
```

Wait for: `Started TreasureHunterApplication` message

### Step 2: Start Frontend

Open a NEW terminal and run:

```bash
cd frontend
npm install
npm start
```

The game will open automatically at http://localhost:3000

### Step 3: Play!

1. Enter your name
2. Click positions to select (up to 3)
3. Click "Reveal Positions"
4. Use proximity hints to find treasures
5. Complete the game and see the leaderboard!

## Even Faster Start

### Windows
Double-click `start-game.bat` or run:
```bash
start-game.bat
```

### Linux/Mac
```bash
chmod +x start-game.sh
./start-game.sh
```

This automatically starts both backend and frontend!

## What's Included

✅ Complete backend (Java Spring Boot)
✅ Complete frontend (React + Redux)
✅ All game features working
✅ Comprehensive documentation
✅ Quick start scripts
✅ Testing strategy

## Project Structure

```
treasure-hunter-game/
├── backend/          # Java Spring Boot REST API
├── frontend/         # React + Redux web app
├── README.md         # Project overview
├── BUILD_AND_RUN.md  # Detailed setup guide
├── TESTING.md        # Testing strategy
└── start-game.*      # Quick start scripts
```

## Game Features

- 🎮 5x5 interactive game board
- 💎 3 hidden treasures to find
- 🎯 Proximity hints (3=close, 2=medium, 1=far)
- 📊 Turn counter and scoring
- 🏆 Top 10 leaderboard
- 💾 Session persistence (resume after browser restart)
- ✨ Smooth animations

## API Endpoints

The backend provides these REST endpoints:

- `POST /api/game/start` - Start or resume game
- `POST /api/game/reveal` - Reveal selected positions
- `GET /api/game/state/{playerName}` - Get game state
- `GET /api/leaderboard` - Get top 10 scores

## Technology Stack

**Backend:**
- Java 17
- Spring Boot 3.2
- Maven
- In-memory storage

**Frontend:**
- React 18
- Redux Toolkit
- Axios
- CSS3

## Common Issues

### Port Already in Use

**Backend (8080):**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

**Frontend (3000):**
```bash
# Run on different port
PORT=3001 npm start
```

### Cannot Connect to Backend

1. Verify backend is running: http://localhost:8080/api/leaderboard
2. Check for CORS errors in browser console
3. Ensure backend started successfully (no errors in terminal)

### Build Failures

**Backend:**
```bash
cd backend
mvn clean install
```

**Frontend:**
```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
```

## Next Steps

### For Players
1. Start the game
2. Try to beat the leaderboard
3. Challenge friends to get better scores

### For Developers
1. Read [DESIGN_PATTERNS.md](DESIGN_PATTERNS.md) for architecture
2. Read [TESTING.md](TESTING.md) for testing strategy
3. Explore the code and make improvements

### For Deployment
1. Read [BUILD_AND_RUN.md](BUILD_AND_RUN.md) production section
2. Build backend JAR: `mvn clean package`
3. Build frontend: `npm run build`
4. Deploy to your server

## Documentation Guide

- **README.md** - Start here for overview
- **GETTING_STARTED.md** - This file (quick start)
- **BUILD_AND_RUN.md** - Detailed setup and deployment
- **TESTING.md** - Testing strategy and examples
- **DESIGN_PATTERNS.md** - Architecture and patterns
- **PROJECT_SUMMARY.md** - Complete project details
- **FILE_STRUCTURE.md** - All files explained

## Game Rules

### Objective
Find all 3 treasures in the minimum number of turns.

### How to Play
1. Each turn, select 1-3 positions
2. Click "Reveal Positions"
3. See what you found:
   - 💎 = Treasure found!
   - 3 = Very close (orthogonally adjacent)
   - 2 = Close (diagonally adjacent)
   - 1 = Far away

### Scoring
- Your score = total number of turns
- Lower score is better
- Top 10 scores shown on leaderboard

### Tips
- Use proximity hints strategically
- Select multiple positions per turn for efficiency
- Remember revealed positions to plan next moves

## Development Workflow

### Making Changes

**Backend:**
1. Edit Java files in `backend/src/main/java/`
2. Save (Maven auto-compiles)
3. Restart if needed

**Frontend:**
1. Edit files in `frontend/src/`
2. Save (hot-reload automatic)
3. See changes immediately

### Testing Changes

```bash
# Backend
cd backend
mvn test

# Frontend
cd frontend
npm test
```

## Getting Help

### Documentation
- Check relevant .md files in root directory
- See backend/README.md for API details
- See frontend/README.md for component details

### Troubleshooting
1. Check terminal for error messages
2. Check browser console (F12)
3. Verify all prerequisites installed
4. Try clean build (delete target/ and node_modules/)

### Common Questions

**Q: Can I change the board size?**
A: Yes, modify the constants in GameService.java and gameSlice.js

**Q: Can I add more treasures?**
A: Yes, change the treasure count in GameService.java

**Q: How do I deploy to production?**
A: See BUILD_AND_RUN.md production deployment section

**Q: Can I add a database?**
A: Yes, replace in-memory storage with JPA/Hibernate

## Success Checklist

✅ Prerequisites installed (Java, Maven, Node.js)
✅ Backend starts without errors
✅ Frontend starts and opens in browser
✅ Can enter name and start game
✅ Can select and reveal positions
✅ Proximity hints display correctly
✅ Can find all treasures
✅ Leaderboard shows scores
✅ Can start new game

## What's Next?

### Immediate
- Play the game and test all features
- Try to get on the leaderboard
- Test session persistence (close/reopen browser)

### Short Term
- Read the documentation
- Understand the architecture
- Run the tests
- Make small improvements

### Long Term
- Add new features (see PROJECT_SUMMARY.md)
- Improve UI/UX
- Add database persistence
- Deploy to production

## Support

This is a complete, working implementation of the Treasure Hunter game. All features are implemented and tested. If you encounter issues:

1. Check the documentation files
2. Verify prerequisites are installed correctly
3. Check terminal and browser console for errors
4. Try a clean build

## Project Status

✅ **COMPLETE AND READY**

- All requirements implemented
- Clean, working code
- Comprehensive documentation
- Quick start scripts
- Testing strategy
- Production-ready architecture

---

**Ready to play?** Run `start-game.bat` (Windows) or `./start-game.sh` (Linux/Mac)

**Need help?** Check [BUILD_AND_RUN.md](BUILD_AND_RUN.md) for detailed instructions

**Want to learn more?** Read [DESIGN_PATTERNS.md](DESIGN_PATTERNS.md) for architecture details

---

**Have fun hunting for treasures! 🏴‍☠️💎**
