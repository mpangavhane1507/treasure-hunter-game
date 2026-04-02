# Build and Run Instructions

Complete guide to building and running the Treasure Hunter game.

## Prerequisites

### Required Software

1. **Java Development Kit (JDK) 17 or higher**
   - Download from: https://adoptium.net/
   - Verify installation: `java -version`

2. **Apache Maven 3.8+**
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation: `mvn -version`

3. **Node.js 18 or higher**
   - Download from: https://nodejs.org/
   - Verify installation: `node -version` and `npm -version`

## Quick Start

### Option 1: Run Both Services Separately

**Terminal 1 - Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Terminal 2 - Frontend:**
```bash
cd frontend
npm install
npm start
```

Then open http://localhost:3000 in your browser.

### Option 2: Build and Run Production

**Build Backend:**
```bash
cd backend
mvn clean package
java -jar target/treasure-hunter-backend-1.0.0.jar
```

**Build Frontend:**
```bash
cd frontend
npm install
npm run build
# Serve the build folder with any static server
npx serve -s build -p 3000
```

## Detailed Instructions

### Backend Setup

1. **Navigate to backend directory:**
   ```bash
   cd backend
   ```

2. **Install dependencies (automatic with Maven):**
   ```bash
   mvn clean install
   ```

3. **Run in development mode:**
   ```bash
   mvn spring-boot:run
   ```

4. **Build JAR file:**
   ```bash
   mvn clean package
   ```
   This creates `target/treasure-hunter-backend-1.0.0.jar`

5. **Run JAR file:**
   ```bash
   java -jar target/treasure-hunter-backend-1.0.0.jar
   ```

6. **Verify backend is running:**
   - Open http://localhost:8080/api/leaderboard
   - Should return: `{"topScores":[]}`

### Frontend Setup

1. **Navigate to frontend directory:**
   ```bash
   cd frontend
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```
   This downloads all required packages from package.json

3. **Run in development mode:**
   ```bash
   npm start
   ```
   - Opens http://localhost:3000 automatically
   - Hot-reload enabled (changes reflect immediately)

4. **Build for production:**
   ```bash
   npm run build
   ```
   - Creates optimized build in `build/` directory
   - Minified and optimized for performance

5. **Serve production build:**
   ```bash
   npx serve -s build -p 3000
   ```
   Or use any static file server (nginx, Apache, etc.)

## Configuration

### Backend Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
# Change server port
server.port=8080

# CORS configuration (if frontend runs on different port)
spring.web.cors.allowed-origins=http://localhost:3000
```

### Frontend Configuration

Edit `frontend/src/api/gameApi.js`:

```javascript
// Change backend URL if needed
const API_BASE_URL = 'http://localhost:8080/api';
```

## Troubleshooting

### Backend Issues

**Problem: Port 8080 already in use**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

**Problem: Maven build fails**
- Ensure Java 17+ is installed: `java -version`
- Clear Maven cache: `mvn clean`
- Delete `~/.m2/repository` and rebuild

**Problem: CORS errors**
- Verify CORS configuration in `application.properties`
- Check `CorsConfig.java` allows frontend origin

### Frontend Issues

**Problem: Port 3000 already in use**
- Kill process using port 3000
- Or run on different port: `PORT=3001 npm start`

**Problem: npm install fails**
- Clear npm cache: `npm cache clean --force`
- Delete `node_modules` and `package-lock.json`
- Run `npm install` again

**Problem: Cannot connect to backend**
- Verify backend is running on port 8080
- Check `API_BASE_URL` in `gameApi.js`
- Check browser console for CORS errors

**Problem: Blank page after build**
- Check browser console for errors
- Verify all dependencies installed correctly
- Try `npm run build` again

## Development Workflow

### Making Changes

**Backend changes:**
1. Edit Java files
2. Maven auto-recompiles (if using `mvn spring-boot:run`)
3. Restart if needed

**Frontend changes:**
1. Edit React/JS files
2. Changes reflect immediately (hot-reload)
3. No restart needed

### Testing Changes

**Backend:**
```bash
cd backend
mvn test
```

**Frontend:**
```bash
cd frontend
npm test
```

## Production Deployment

### Backend Deployment

1. Build JAR:
   ```bash
   mvn clean package -DskipTests
   ```

2. Deploy JAR to server:
   ```bash
   scp target/treasure-hunter-backend-1.0.0.jar user@server:/app/
   ```

3. Run on server:
   ```bash
   java -jar /app/treasure-hunter-backend-1.0.0.jar
   ```

4. Use process manager (systemd, PM2, etc.) for production

### Frontend Deployment

1. Build production bundle:
   ```bash
   npm run build
   ```

2. Deploy `build/` folder to web server:
   ```bash
   scp -r build/* user@server:/var/www/html/
   ```

3. Configure web server (nginx example):
   ```nginx
   server {
       listen 80;
       server_name yourdomain.com;
       root /var/www/html;
       index index.html;
       
       location / {
           try_files $uri /index.html;
       }
       
       location /api {
           proxy_pass http://localhost:8080;
       }
   }
   ```

## Environment-Specific Configuration

### Development
- Backend: http://localhost:8080
- Frontend: http://localhost:3000
- CORS enabled for localhost

### Production
- Backend: https://api.yourdomain.com
- Frontend: https://yourdomain.com
- Update API_BASE_URL in frontend
- Configure proper CORS origins
- Use HTTPS

## Performance Optimization

### Backend
- Increase JVM heap size: `java -Xmx512m -jar app.jar`
- Enable GC logging for monitoring
- Use connection pooling if adding database

### Frontend
- Production build is already optimized
- Enable gzip compression on web server
- Use CDN for static assets
- Implement caching headers

## Monitoring

### Backend Health Check
```bash
curl http://localhost:8080/api/leaderboard
```

### Frontend Health Check
```bash
curl http://localhost:3000
```

### Logs

**Backend logs:**
- Console output when running with `mvn spring-boot:run`
- Configure logging in `application.properties`

**Frontend logs:**
- Browser console (F12)
- Network tab for API calls

## Additional Resources

- Spring Boot Documentation: https://spring.io/projects/spring-boot
- React Documentation: https://react.dev/
- Redux Toolkit Documentation: https://redux-toolkit.js.org/
- Maven Documentation: https://maven.apache.org/guides/
