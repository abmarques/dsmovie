package com.devsuperior.dsmovie.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_score")
public class Score {

    @EmbeddedId
    private UserMovieScore id;
    private Double value;

}
