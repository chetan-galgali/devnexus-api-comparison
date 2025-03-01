package com.example.spring_graphql.repo;

import com.example.spring_graphql.entity.AllFilms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllFilmsRepo extends JpaRepository<AllFilms,Integer> {
}
