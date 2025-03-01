package com.example.spring_graphql.repo;

import com.example.spring_graphql.entity.Starships;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarshipRepo extends JpaRepository<Starships,Integer> {
}
