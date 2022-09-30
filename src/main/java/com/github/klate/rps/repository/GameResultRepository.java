package com.github.klate.rps.repository;

import com.github.klate.rps.entity.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: mayve use @RepositoryRestResource for data paging and streaming
public interface GameResultRepository extends JpaRepository<GameResult, Integer> {



}
