package com.treasurehunter.dto;

import java.util.List;

public class RevealPositionsResponse {
    private int turnCount;
    private List<RevealedPositionDTO> newlyRevealed;
    private boolean isComplete;
    private Integer finalScore;
    
    public RevealPositionsResponse() {}
    
    public RevealPositionsResponse(int turnCount, List<RevealedPositionDTO> newlyRevealed, 
                                  boolean isComplete, Integer finalScore) {
        this.turnCount = turnCount;
        this.newlyRevealed = newlyRevealed;
        this.isComplete = isComplete;
        this.finalScore = finalScore;
    }
    
    public int getTurnCount() {
        return turnCount;
    }
    
    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }
    
    public List<RevealedPositionDTO> getNewlyRevealed() {
        return newlyRevealed;
    }
    
    public void setNewlyRevealed(List<RevealedPositionDTO> newlyRevealed) {
        this.newlyRevealed = newlyRevealed;
    }
    
    public boolean isComplete() {
        return isComplete;
    }
    
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
    
    public Integer getFinalScore() {
        return finalScore;
    }
    
    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }
}
