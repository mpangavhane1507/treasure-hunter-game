package com.treasurehunter.dto;

import jakarta.validation.constraints.NotBlank;

public class StartGameRequest {
    @NotBlank(message = "Player name is required")
    private String playerName;
    
    public StartGameRequest() {}
    
    public StartGameRequest(String playerName) {
        this.playerName = playerName;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
