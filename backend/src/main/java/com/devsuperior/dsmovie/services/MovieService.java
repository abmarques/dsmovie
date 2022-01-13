package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dtos.MovieDTO;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> findAll(Pageable pageable) {
        var result = repository.findAll(pageable);

        return result.map(x -> MovieDTO.builder()
                .id(x.getId())
                .title(x.getTitle())
                .image(x.getImage())
                .score(x.getScore())
                .build()
        );
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        var movie = repository.findById(id).get();

        return MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .image(movie.getImage())
                .score(movie.getScore())
                .build();
    }
}
