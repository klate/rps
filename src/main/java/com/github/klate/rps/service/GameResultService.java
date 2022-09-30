package com.github.klate.rps.service;


import com.github.klate.rps.entity.GameResult;
import com.github.klate.rps.repository.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GameResultService {

    // the jpa repo for the game results
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
        // TODO: check if this is really non blocking
        result = gameResultRepository.save(result);
        return CompletableFuture.completedFuture(result);
    }

    /**
     * fetches a list of all the GameResults from the configured repository
     *
     * @return a list of all gameResults
     * */
    @Async
    public CompletableFuture<List<GameResult>> getAllGameResults(){
        // TODO: check to use webflux
        // TODO: add paging -> check repo technology first
        List<GameResult> results = this.gameResultRepository.findAll();
        return CompletableFuture.completedFuture(results);
    }

    /**
     * fetches a GameResult obj from the configured repository with the provided id
     *
     * @return a GameResult with the provided id
     * @throws EntityNotFoundException when the id was not found
     * */
    public CompletableFuture<GameResult> getGameResultById(int id) throws EntityNotFoundException {
        Optional<GameResult> result = this.gameResultRepository.findById(id);
        if(result.isPresent())
        {
            return CompletableFuture.completedFuture(result.get());
        }
        else throw new EntityNotFoundException();
    }


}
