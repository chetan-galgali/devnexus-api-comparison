package com.example.spring_graphql.controller.rest;

import com.example.spring_graphql.entity.*;
import com.example.spring_graphql.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class StarshipController {
    private final StarshipFilmsRepo starshipFilmsRepo;
    private final StarshipRepo starshipRepo;
    private final FilmProducerRepo filmProducerRepo;

    @GetMapping("/starships")
    public List<Starships> getAllStarships(){
        return starshipRepo.findAll();
    }

    @GetMapping("/starships/{id}/films")
    public StarshipFilms getFilmsForStarshipId(@PathVariable("id") Integer id){
        Optional<StarshipFilms> byId = starshipFilmsRepo.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        else {
            return new StarshipFilms();
        }
    }

    @GetMapping("/films/{id}/producers")
    public FilmsProducers getProducersForFilmId(@PathVariable("id") Integer id){
        Optional<FilmsProducers> byId = filmProducerRepo.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        else {
            return new FilmsProducers();
        }
    }
}
