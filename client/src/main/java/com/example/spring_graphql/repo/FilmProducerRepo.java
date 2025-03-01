package com.example.spring_graphql.repo;

import com.example.spring_graphql.entity.FilmsProducers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmProducerRepo extends JpaRepository<FilmsProducers,Integer> {
}
