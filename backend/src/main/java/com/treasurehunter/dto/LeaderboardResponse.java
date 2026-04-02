package com.treasurehunter.dto;

import java.util.List;

public class LeaderboardResponse {
    private List<LeaderboardEntryDTO> topScores;
    
    public LeaderboardResponse() {}
    
    public LeaderboardResponse(List<LeaderboardEntryDTO> topScores) {
        this.topScores = topScores;
    }
    
    public List<LeaderboardEntryDTO> getTopScores() {
        return topScores;
    }
    
    public void setTopScores(List<LeaderboardEntryDTO> topScores) {
        this.topScores = topScores;
    }
}
