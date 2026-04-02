package com.treasurehunter.service;

import com.treasurehunter.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class GameService {
    
    private final Map<String, GameSession> sessions = new ConcurrentHashMap<>();
    private final List<LeaderboardEntry> leaderboard = Collections.synchronizedList(new ArrayList<>());
    private final Random random = new Random();
    
    public GameSession createOrResumeGame(String playerName) {
        GameSession session = sessions.get(playerName);
        
        if (session == null || session.isComplete()) {
            session = new GameSession(playerName);
            session.setTreasurePositions(generateTreasurePositions());
            sessions.put(playerName, session);
        }
        
        return session;
    }
    
    public GameSession getSession(String playerName) {
        return sessions.get(playerName);
    }
    
    public RevealPositionsResult revealPositions(String playerName, List<Coordinate> positions) {
        GameSession session = sessions.get(playerName);
        if (session == null) {
            throw new IllegalStateException("No active game session for player: " + playerName);
        }
        
        if (session.isComplete()) {
            throw new IllegalStateException("Game is already complete");
        }
        
        // Validate positions
        for (Coordinate pos : positions) {
            if (session.getRevealedPositions().containsKey(pos)) {
                throw new IllegalArgumentException("Position already revealed: " + pos);
            }
        }
        
        // Increment turn counter
        session.incrementTurnCount();
        
        // Process each position
        List<RevealedPosition> newlyRevealed = new ArrayList<>();
        for (Coordinate pos : positions) {
            RevealedPosition revealed = processPosition(pos, session.getTreasurePositions());
            session.getRevealedPositions().put(pos, revealed);
            newlyRevealed.add(revealed);
        }
        
        // Check if game is complete
        boolean isComplete = session.getTreasuresFound() == 3;
        session.setComplete(isComplete);
        
        Integer finalScore = null;
        if (isComplete) {
            finalScore = session.getTurnCount();
            recordScore(playerName, finalScore);
        }
        
        return new RevealPositionsResult(newlyRevealed, isComplete, finalScore);
    }
    
    private RevealedPosition processPosition(Coordinate position, List<Coordinate> treasures) {
        boolean isTreasure = treasures.contains(position);
        Integer proximityValue = null;
        
        if (!isTreasure) {
            proximityValue = calculateProximity(position, treasures);
        }
        
        return new RevealedPosition(position, isTreasure, proximityValue);
    }
    
    public int calculateProximity(Coordinate position, List<Coordinate> treasures) {
        // Check for orthogonal adjacency (proximity 3)
        for (Coordinate treasure : treasures) {
            if (position.isOrthogonallyAdjacentTo(treasure)) {
                return 3;
            }
        }
        
        // Check for diagonal adjacency (proximity 2)
        for (Coordinate treasure : treasures) {
            if (position.isDiagonallyAdjacentTo(treasure)) {
                return 2;
            }
        }
        
        // Default proximity is 1
        return 1;
    }
    
    private List<Coordinate> generateTreasurePositions() {
        Set<Coordinate> treasures = new HashSet<>();
        
        while (treasures.size() < 3) {
            int row = random.nextInt(5);
            int col = random.nextInt(5);
            treasures.add(new Coordinate(row, col));
        }
        
        return new ArrayList<>(treasures);
    }
    
    private void recordScore(String playerName, int score) {
        LeaderboardEntry entry = new LeaderboardEntry(playerName, score, LocalDateTime.now());
        leaderboard.add(entry);
        Collections.sort(leaderboard);
    }
    
    public List<LeaderboardEntry> getTopScores(int limit) {
        return leaderboard.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
    
    public static class RevealPositionsResult {
        private final List<RevealedPosition> newlyRevealed;
        private final boolean isComplete;
        private final Integer finalScore;
        
        public RevealPositionsResult(List<RevealedPosition> newlyRevealed, boolean isComplete, Integer finalScore) {
            this.newlyRevealed = newlyRevealed;
            this.isComplete = isComplete;
            this.finalScore = finalScore;
        }
        
        public List<RevealedPosition> getNewlyRevealed() {
            return newlyRevealed;
        }
        
        public boolean isComplete() {
            return isComplete;
        }
        
        public Integer getFinalScore() {
            return finalScore;
        }
    }
}
