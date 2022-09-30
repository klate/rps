package com.github.klate.rps.controller;

import com.github.klate.rps.entity.GameResult;
import com.github.klate.rps.service.GameResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// controller for the game history
@RestController
public class GameHistoryController {

    // the background service for handling the game results
    private final GameResultService gameResultService;

    public GameHistoryController(GameResultService gameResultService){
        this.gameResultService = gameResultService;
    }

    @GetMapping("/api/history")
    public CompletableFuture<List<GameResult>> getAllGameResults() {
        return this.gameResultService.getAllGameResults();
    }

    // TODO: put param into url
    @GetMapping("/api/history/id")
    public CompletableFuture<GameResult> getGameResultById(@RequestParam(value = "id") int id) {
        return this.gameResultService.getGameResultById(id);
    }

}
