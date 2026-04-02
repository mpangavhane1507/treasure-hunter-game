package com.treasurehunter.model;

import java.time.LocalDateTime;
import java.util.*;

public class GameSession {
    private String playerName;
    private List<Coordinate> treasurePositions;
    private Map<Coordinate, RevealedPosition> revealedPositions;
    private int turnCount;
    private boolean isComplete;
    private LocalDateTime createdAt;
    
    public GameSession() {
        this.revealedPositions = new HashMap<>();
        this.treasurePositions = new ArrayList<>();
        this.turnCount = 0;
        this.isComplete = false;
        this.createdAt = LocalDateTime.now();
    }
    
    public GameSession(String playerName) {
        this();
        this.playerName = playerName;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public List<Coordinate> getTreasurePositions() {
        return treasurePositions;
    }
    
    public void setTreasurePositions(List<Coordinate> treasurePositions) {
        this.treasurePositions = treasurePositions;
    }
    
    public Map<Coordinate, RevealedPosition> getRevealedPositions() {
        return revealedPositions;
    }
    
    public void setRevealedPositions(Map<Coordinate, RevealedPosition> revealedPositions) {
        this.revealedPositions = revealedPositions;
    }
    
    public int getTurnCount() {
        return turnCount;
    }
    
    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }
    
    public void incrementTurnCount() {
        this.turnCount++;
    }
    
    public boolean isComplete() {
        return isComplete;
    }
    
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public int getTreasuresFound() {
        return (int) revealedPositions.values().stream()
                .filter(RevealedPosition::isTreasure)
                .count();
    }
}
