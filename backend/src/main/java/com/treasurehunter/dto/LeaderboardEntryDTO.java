package com.treasurehunter.dto;

public class LeaderboardEntryDTO {
    private String playerName;
    private int score;
    private int rank;
    
    public LeaderboardEntryDTO() {}
    
    public LeaderboardEntryDTO(String playerName, int score, int rank) {
        this.playerName = playerName;
        this.score = score;
        this.rank = rank;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
}
