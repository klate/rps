package com.github.klate.rps.service;


import com.github.klate.rps.entity.GameResult;
import com.github.klate.rps.repositories.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GameResultService {

    @Autowired
    private GameResultRepository gameResultRepository;

    /**
     * persists the given game result object inside the configured repository
     * @param result the game result object
     *
     * @return a completable future, that wraps the saved object
     * */
    @Async
    public CompletableFuture<GameResult> saveGameResult(GameResult result){
        result = gameResultRepository.save(result);
        return  CompletableFuture.completedFuture(result);
    }

    /**
     * fetches a list of all the GameResults from the configured repository
     *
     * @return a list of all gameResults
     * */
    @Async
    public CompletableFuture<List<GameResult>> getAllGameResults(){
        // TODO: check to use webflux
        // TODO: add paging
        List<GameResult> results = gameResultRepository.findAll();
        return CompletableFuture.completedFuture(results);
    }


}
