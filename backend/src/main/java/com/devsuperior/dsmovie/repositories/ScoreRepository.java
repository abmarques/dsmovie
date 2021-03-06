package com.devsuperior.dsmovie.repositories;

import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.UserMovieScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, UserMovieScore> {

}
