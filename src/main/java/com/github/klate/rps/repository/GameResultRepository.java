package com.github.klate.rps.repository;

import com.github.klate.rps.entity.GameResult;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * Java Persistance Api repository, that contains the game results (GameResult)
 * */
public interface GameResultRepository extends ReactiveMongoRepository<GameResult, String> {

    /**
    * fetches all the game results, that were played by the given user
    * */
    Flux<GameResult> findByUserName(String userName);

    /**
     * fetches all the game results, that were won by the given winner
     * */
    Flux<GameResult> findByWinner(char winner);
}
