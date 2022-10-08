package com.github.klate.rps.service;


import com.github.klate.rps.entity.GameResult;
import com.github.klate.rps.repository.GameResultRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class GameResultService {

    // the jpa repo containing the game results
    private final GameResultRepository gameResultRepository;

    /**
     * creates a new gameresult service
     * @param gameResultRepository the repository, that stores the game result data
     * */
    public GameResultService(GameResultRepository gameResultRepository){
        this.gameResultRepository = gameResultRepository;
    }

    /**
     * persists the given game result object inside the configured repository
     * @param result the game result object
     *
     * @return a future with the saved object
     * */
    @Async
    public CompletableFuture<GameResult> create(GameResult result) throws ExecutionException, InterruptedException {
        return gameResultRepository.save(result).toFuture();
    }

    /**
     * fetches a list of all the GameResults from the configured repository
     *
     * @return a future with the list of all gameResults
     * */
    public Flux<GameResult> getAllGameResults() {
        return this.gameResultRepository.findAll();
    }

    /**
     * fetches a GameResult obj from the configured repository with the provided id
     *
     * @return a future with a GameResult, that is identified the provided id
     * */
    public Mono<GameResult> getGameResultById(String id) {
        return this.gameResultRepository.findById(id);
    }

    /**
     * fetches all the GameResults, that were played by the given username
     *
     * @param username the username
     * @return a future with a list of GameResult, that were played by the given username
     * */
    public Flux<GameResult> getGameResultsByUsername(String username) {
        return this.gameResultRepository.findByUserName(username);
    }

    /**
     * fetches all the GameResults, that were won by the given winner ()
     *
     * @param winner the winner of the game
     * @return a future with a list of GameResult, that were won by the given winner
     * */
    public Flux<GameResult> getGameResultsByWinner(char winner) {
        return this.gameResultRepository.findByWinner(winner);
    }

}
