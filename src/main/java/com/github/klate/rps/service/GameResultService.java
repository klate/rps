package com.github.klate.rps.service;


import com.github.klate.rps.entity.GameResult;
import com.github.klate.rps.repository.GameResultRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<GameResult> saveGameResult(GameResult result){
        // TODO: check if this is really non blocking
        result = gameResultRepository.save(result);
        return CompletableFuture.completedFuture(result);
    }

    /**
     * fetches a list of all the GameResults from the configured repository
     *
     * @return a future with the list of all gameResults
     * */
    @Async
    public CompletableFuture<List<GameResult>> getAllGameResults() {
        return CompletableFuture.completedFuture(this.gameResultRepository.findAll());
    }

    /**
     * fetches a GameResult obj from the configured repository with the provided id
     *
     * @return a future with a GameResult, that is identified the provided id
     * @throws EntityNotFoundException when the id was not found
     * */
    @Async
    public CompletableFuture<GameResult> getGameResultById(int id) throws EntityNotFoundException {
        Optional<GameResult> result = this.gameResultRepository.findById(id);

        if(result.isEmpty())
            throw new EntityNotFoundException();

        return CompletableFuture.completedFuture(result.get());
    }

    /**
     * fetches all the GameResults, that were played by the given username
     *
     * @param username the username
     * @return a future with a list of GameResult, that were played by the given username
     * @throws EntityNotFoundException when the username was not found
     * */
    @Async
    public CompletableFuture<List<GameResult>> getGameResultsByUsername(String username) throws EntityNotFoundException {
        List<GameResult> results = this.gameResultRepository.findByUserName(username);

        if (results.isEmpty())
            throw new EntityNotFoundException();

        return CompletableFuture.completedFuture(results);
    }

    /**
     * fetches all the GameResults, that were won by the given winner ()
     *
     * @param winner the winner of the game
     * @return a future with a list of GameResult, that were won by the given winner
     * @throws EntityNotFoundException when the winner was not found
     * */
    @Async
    public CompletableFuture<List<GameResult>> getGameResultsByWinner(char winner) throws EntityNotFoundException {
        List<GameResult> results = this.gameResultRepository.findByWinner(winner);

        if (results.isEmpty())
            throw new EntityNotFoundException();

        return CompletableFuture.completedFuture(results);
    }

}
