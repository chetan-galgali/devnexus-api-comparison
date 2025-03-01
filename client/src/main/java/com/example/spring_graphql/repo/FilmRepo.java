package com.example.spring_graphql.repo;

import com.example.spring_graphql.entity.FilmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepo extends JpaRepository<FilmsEntity,Integer> {
}
