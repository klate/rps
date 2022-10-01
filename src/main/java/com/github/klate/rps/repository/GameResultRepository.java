package com.github.klate.rps.repository;

import com.github.klate.rps.entity.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Java Persistance Api repository, that contains the game results (GameResult)
 * */
public interface GameResultRepository extends JpaRepository<GameResult, Integer> {

    /**
    * fetches all the game results, that were played by the given user
    * */
    List<GameResult> findByUserName(String userName);

    /**
     * fetches all the game results, that were won by the given winner
     * */
    List<GameResult> findByWinner(char winner);
}
