package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dtos.MovieDTO;
import com.devsuperior.dsmovie.dtos.ScoreDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.entities.UserMovieScore;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO scoreDTO) {

        var user = userRepository.findByEmail(scoreDTO.getEmail());
        if(user == null) {

            user = User.builder()
                    .email(scoreDTO.getEmail())
                    .build();

            user = userRepository.saveAndFlush(user);
        }

        var movie = movieRepository.findById(scoreDTO.getMovieId()).get();

        var score = Score.builder()
                .id(UserMovieScore.builder()
                        .movie(movie)
                        .user(user)
                        .build())
                .value(scoreDTO.getScore()).build();

        score = scoreRepository.saveAndFlush(score);

        double sum = 0.0;
        for (Score s : movie.getScores()){
            sum = sum + s.getValue();
        }

        double avg = sum / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());

        movie = movieRepository.save(movie);

        var movieDTO = MovieDTO.builder().build();

        BeanUtils.copyProperties(movie, movieDTO);

        return movieDTO;
    }
}
