@echo off
echo ========================================
echo Treasure Hunter Game - Quick Start
echo ========================================
echo.

echo Checking prerequisites...
echo.

REM Check Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java is not installed or not in PATH
    echo Please install Java 17 or higher from https://adoptium.net/
    pause
    exit /b 1
)
echo [OK] Java found

REM Check Maven
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Maven is not installed or not in PATH
    echo Please install Maven from https://maven.apache.org/download.cgi
    pause
    exit /b 1
)
echo [OK] Maven found

REM Check Node
node -v >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Node.js is not installed or not in PATH
    echo Please install Node.js from https://nodejs.org/
    pause
    exit /b 1
)
echo [OK] Node.js found

echo.
echo All prerequisites satisfied!
echo.
echo Starting backend server...
echo.

REM Start backend in new window
start "Treasure Hunter Backend" cmd /k "cd backend && mvn spring-boot:run"

echo Waiting for backend to start (15 seconds)...
timeout /t 15 /nobreak >nul

echo.
echo Installing frontend dependencies...
echo.

cd frontend
if not exist node_modules (
    call npm install
)

echo.
echo Starting frontend...
echo.

REM Start frontend in new window
start "Treasure Hunter Frontend" cmd /k "npm start"

echo.
echo ========================================
echo Game is starting!
echo ========================================
echo.
echo Backend: http://localhost:8080
echo Frontend: http://localhost:3000
echo.
echo The game will open in your browser automatically.
echo.
echo To stop the game:
echo 1. Close both terminal windows
echo 2. Or press Ctrl+C in each window
echo.
pause
