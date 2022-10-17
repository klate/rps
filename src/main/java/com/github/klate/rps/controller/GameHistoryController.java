package com.github.klate.rps.controller;

import com.github.klate.rps.entity.GameResult;
import com.github.klate.rps.service.GameResultService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
* REST controller for the game history of the played games
**/
@RestController
@RequestMapping(value = "/api/history")
public class GameHistoryController {

    // the background service for handling the game results
    private final GameResultService gameResultService;

    /**
     * constructs a new GameHistoryController
     * @param gameResultService the service for accessing the game results
     * */
    public GameHistoryController(GameResultService gameResultService){
        this.gameResultService = gameResultService;
    }

    /**
     * returns all existing GameResults
     * @return a future, that contains a list of gameresults
     * */
    @GetMapping
    @CrossOrigin("*")
    public Flux<GameResult> getAllGameResults() {
        return this.gameResultService.getAllGameResults();
    }

    /**
     * returns the GameResult with the given id
     * @param id the of the GameResult to be found
     * @return a future, that contains the game result with the given id
     * */
    @GetMapping("/{id}")
    @CrossOrigin("*")
    public Mono<GameResult> getGameResultById(@PathVariable String id) {
        return this.gameResultService.getGameResultById(id);
    }

    /**
     * returns all the GameResults, that were played the given username
     * @param username the username to search for
     * @return a list of GameResults, that were played by the given username
     * */
    @GetMapping("/username/{username}")
    @CrossOrigin("*")
    public Flux<GameResult> getGameResultsByUsername(@PathVariable String username) {
        return this.gameResultService.getGameResultsByUsername(username);
    }

    /**
     * returns all the GameResults, that were won the given winner
     * @param winner the winner to search for
     * @return a list of GameResults, that were wo by the given winner
     * */
    @GetMapping("/winner/{winner}")
    @CrossOrigin("*")
    public Flux<GameResult> getGameResultsByWinner(@PathVariable char winner) {
        return this.gameResultService.getGameResultsByWinner(winner);
    }
}
