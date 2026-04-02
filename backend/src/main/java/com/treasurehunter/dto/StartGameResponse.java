package com.treasurehunter.dto;

import java.util.List;

public class StartGameResponse {
    private String playerName;
    private int turnCount;
    private List<RevealedPositionDTO> revealedPositions;
    private boolean isComplete;
    
    public StartGameResponse() {}
    
    public StartGameResponse(String playerName, int turnCount, 
                           List<RevealedPositionDTO> revealedPositions, boolean isComplete) {
        this.playerName = playerName;
        this.turnCount = turnCount;
        this.revealedPositions = revealedPositions;
        this.isComplete = isComplete;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public int getTurnCount() {
        return turnCount;
    }
    
    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }
    
    public List<RevealedPositionDTO> getRevealedPositions() {
        return revealedPositions;
    }
    
    public void setRevealedPositions(List<RevealedPositionDTO> revealedPositions) {
        this.revealedPositions = revealedPositions;
    }
    
    public boolean isComplete() {
        return isComplete;
    }
    
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
