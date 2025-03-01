package com.example.spring_graphql.repo;

import com.example.spring_graphql.entity.StarshipFilms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarshipFilmsRepo extends JpaRepository<StarshipFilms,Integer> {
}
