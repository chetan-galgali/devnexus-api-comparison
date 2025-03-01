package com.example.spring_graphql.controller.graphql;

import com.example.spring_graphql.entity.AllFilms;
import com.example.spring_graphql.entity.AllStarships;
import com.example.spring_graphql.repo.AllFilmsRepo;
import com.example.spring_graphql.repo.AllStarshipsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StarshipGraphQL {
    private final AllStarshipsRepo allStarshipsRepo;
    private final AllFilmsRepo allFilmsRepo;

    @QueryMapping
    public List<AllStarships> allStarships(){
        return allStarshipsRepo.findAll();
    }

    @QueryMapping
    public List<AllFilms> allFilms(){
        return allFilmsRepo.findAll();
    }

    @QueryMapping
    public AllStarships starshipById(@Argument Integer starshipId){
        Optional<AllStarships> byId = allStarshipsRepo.findById(starshipId);
        if(byId.isEmpty()){
            throw new IllegalArgumentException("Starship starshipId is not valid - " + starshipId);
        }
        return byId.get();
    }

    @QueryMapping
    public AllFilms filmsById(@Argument Integer filmId){
        Optional<AllFilms> byId = allFilmsRepo.findById(filmId);
        if(byId.isEmpty()){
            throw new IllegalArgumentException("Film filmId is not valid - " + filmId);
        }
        return byId.get();
    }

    @MutationMapping
    public List<AllStarships> createStarship(@Argument Integer id, @Argument String name, @Argument Integer maxSpeed, @Argument Float length){
        AllStarships starshipEntity = new AllStarships();
        starshipEntity.setId(id);
        starshipEntity.setName(name);
        starshipEntity.setMaxSpeed(maxSpeed);
        starshipEntity.setLength(length);
        allStarshipsRepo.save(starshipEntity);
        return allStarshipsRepo.findAll();
    }

    @MutationMapping
    public List<AllStarships> createStarshipPojo(@Argument(name = "starship") AllStarships starshipInput){
        allStarshipsRepo.save(starshipInput);
        return allStarshipsRepo.findAll();
    }
}
