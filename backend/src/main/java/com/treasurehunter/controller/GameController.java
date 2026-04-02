package com.treasurehunter.controller;

import com.treasurehunter.dto.*;
import com.treasurehunter.model.GameSession;
import com.treasurehunter.model.LeaderboardEntry;
import com.treasurehunter.model.RevealedPosition;
import com.treasurehunter.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GameController {
    
    private final GameService gameService;
    
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    
    @PostMapping("/game/start")
    public ResponseEntity<StartGameResponse> startGame(@Valid @RequestBody StartGameRequest request) {
        GameSession session = gameService.createOrResumeGame(request.getPlayerName());
        
        List<RevealedPositionDTO> revealedPositions = session.getRevealedPositions().values().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        
        StartGameResponse response = new StartGameResponse(
                session.getPlayerName(),
                session.getTurnCount(),
                revealedPositions,
                session.isComplete()
        );
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/game/reveal")
    public ResponseEntity<RevealPositionsResponse> revealPositions(
            @Valid @RequestBody RevealPositionsRequest request) {
        
        try {
            GameService.RevealPositionsResult result = gameService.revealPositions(
                    request.getPlayerName(),
                    request.getPositions()
            );
            
            List<RevealedPositionDTO> newlyRevealed = result.getNewlyRevealed().stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
            
            GameSession session = gameService.getSession(request.getPlayerName());
            
            RevealPositionsResponse response = new RevealPositionsResponse(
                    session.getTurnCount(),
                    newlyRevealed,
                    result.isComplete(),
                    result.getFinalScore()
            );
            
            return ResponseEntity.ok(response);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/game/state/{playerName}")
    public ResponseEntity<StartGameResponse> getGameState(@PathVariable String playerName) {
        GameSession session = gameService.getSession(playerName);
        
        if (session == null) {
            return ResponseEntity.notFound().build();
        }
        
        List<RevealedPositionDTO> revealedPositions = session.getRevealedPositions().values().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        
        StartGameResponse response = new StartGameResponse(
                session.getPlayerName(),
                session.getTurnCount(),
                revealedPositions,
                session.isComplete()
        );
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/leaderboard")
    public ResponseEntity<LeaderboardResponse> getLeaderboard() {
        List<LeaderboardEntry> topScores = gameService.getTopScores(10);
        
        List<LeaderboardEntryDTO> dtos = topScores.stream()
                .map((entry) -> new LeaderboardEntryDTO(
                        entry.getPlayerName(),
                        entry.getScore(),
                        topScores.indexOf(entry) + 1
                ))
                .collect(Collectors.toList());
        
        LeaderboardResponse response = new LeaderboardResponse(dtos);
        return ResponseEntity.ok(response);
    }
    
    private RevealedPositionDTO toDTO(RevealedPosition revealed) {
        return new RevealedPositionDTO(
                revealed.getCoordinate().getRow(),
                revealed.getCoordinate().getCol(),
                revealed.isTreasure(),
                revealed.getProximityValue()
        );
    }
}
