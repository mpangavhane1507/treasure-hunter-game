package com.treasurehunter.dto;

import com.treasurehunter.model.Coordinate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RevealPositionsRequest {
    @NotBlank(message = "Player name is required")
    private String playerName;
    
    @NotEmpty(message = "At least one position is required")
    @Size(min = 1, max = 3, message = "Must reveal between 1 and 3 positions per turn")
    private List<Coordinate> positions;
    
    public RevealPositionsRequest() {}
    
    public RevealPositionsRequest(String playerName, List<Coordinate> positions) {
        this.playerName = playerName;
        this.positions = positions;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public List<Coordinate> getPositions() {
        return positions;
    }
    
    public void setPositions(List<Coordinate> positions) {
        this.positions = positions;
    }
}
