#!/bin/bash

echo "Stopping Treasure Hunter Game..."
echo ""

# Try to read PIDs from file
if [ -f ".game-pids" ]; then
    echo "Reading PIDs from .game-pids..."
    BACKEND_PID=$(sed -n '1p' .game-pids)
    FRONTEND_PID=$(sed -n '2p' .game-pids)
    
    if [ ! -z "$BACKEND_PID" ]; then
        echo "Stopping backend (PID: $BACKEND_PID)..."
        kill $BACKEND_PID 2>/dev/null || echo "Backend already stopped"
    fi
    
    if [ ! -z "$FRONTEND_PID" ]; then
        echo "Stopping frontend (PID: $FRONTEND_PID)..."
        kill $FRONTEND_PID 2>/dev/null || echo "Frontend already stopped"
    fi
    
    rm .game-pids
else
    echo "No .game-pids file found, trying to kill by process name..."
    pkill -f 'spring-boot:run' && echo "Backend stopped" || echo "Backend not running"
    pkill -f 'react-scripts' && echo "Frontend stopped" || echo "Frontend not running"
fi

echo ""
echo "Game stopped!"
