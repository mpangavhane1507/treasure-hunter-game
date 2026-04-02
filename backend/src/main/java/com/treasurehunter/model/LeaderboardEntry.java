package com.treasurehunter.model;

import java.time.LocalDateTime;

public class LeaderboardEntry implements Comparable<LeaderboardEntry> {
    private String playerName;
    private int score;
    private LocalDateTime completedAt;
    
    public LeaderboardEntry() {}
    
    public LeaderboardEntry(String playerName, int score, LocalDateTime completedAt) {
        this.playerName = playerName;
        this.score = score;
        this.completedAt = completedAt;
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
    
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
    
    @Override
    public int compareTo(LeaderboardEntry other) {
        // Lower score is better
        int scoreComparison = Integer.compare(this.score, other.score);
        if (scoreComparison != 0) {
            return scoreComparison;
        }
        // If scores are equal, earlier completion is better
        return this.completedAt.compareTo(other.completedAt);
    }
}
