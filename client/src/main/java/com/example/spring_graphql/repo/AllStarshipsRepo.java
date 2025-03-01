package com.example.spring_graphql.repo;

import com.example.spring_graphql.entity.AllStarships;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllStarshipsRepo extends JpaRepository<AllStarships,Integer> {
}
