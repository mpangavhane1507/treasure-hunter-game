#!/bin/bash

echo "========================================"
echo "Treasure Hunter Game - Quick Start"
echo "========================================"
echo ""

echo "Checking prerequisites..."
echo ""

# Check Java
if ! command -v java &> /dev/null; then
    echo "[ERROR] Java is not installed or not in PATH"
    echo "Please install Java 17 or higher from https://adoptium.net/"
    exit 1
fi
echo "[OK] Java found"

# Check Maven
if ! command -v mvn &> /dev/null; then
    echo "[ERROR] Maven is not installed or not in PATH"
    echo "Please install Maven from https://maven.apache.org/download.cgi"
    exit 1
fi
echo "[OK] Maven found"

# Check Node
if ! command -v node &> /dev/null; then
    echo "[ERROR] Node.js is not installed or not in PATH"
    echo "Please install Node.js from https://nodejs.org/"
    exit 1
fi
echo "[OK] Node.js found"

echo ""
echo "All prerequisites satisfied!"
echo ""
echo "Starting backend server..."
echo ""

# Start backend in background
cd backend
mvn spring-boot:run > ../backend.log 2>&1 &
BACKEND_PID=$!
cd ..

echo "Backend PID: $BACKEND_PID"
echo "Waiting for backend to start (15 seconds)..."
sleep 15

echo ""
echo "Installing frontend dependencies..."
echo ""

cd frontend
if [ ! -d "node_modules" ]; then
    npm install
fi

echo ""
echo "Starting frontend..."
echo ""

# Start frontend
npm start > ../frontend.log 2>&1 &
FRONTEND_PID=$!
cd ..

echo ""
echo "========================================"
echo "Game is starting!"
echo "========================================"
echo ""
echo "Backend: http://localhost:8080 (PID: $BACKEND_PID)"
echo "Frontend: http://localhost:3000 (PID: $FRONTEND_PID)"
echo ""
echo "The game will open in your browser automatically."
echo ""
echo "To stop the game, run:"
echo "  kill $BACKEND_PID $FRONTEND_PID"
echo ""
echo "Or press Ctrl+C and run:"
echo "  pkill -f 'spring-boot:run'"
echo "  pkill -f 'react-scripts'"
echo ""

# Save PIDs to file for easy cleanup
echo "$BACKEND_PID" > .game-pids
echo "$FRONTEND_PID" >> .game-pids

echo "PIDs saved to .game-pids"
echo ""
