package com.insight.transform.clientproject.controller;

import com.insight.transform.AllStarshipData;
import com.insight.transform.clientproject.service.AllStarshipGrpcClient;
import com.insight.transform.clientproject.service.GreetingsGrpcClient;
import com.insight.transform.clientproject.value.Films;
import com.insight.transform.clientproject.value.Producer;
import com.insight.transform.clientproject.value.Starships;
import com.insight.transform.films;
import com.insight.transform.producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GrpcClientController {
    private final AllStarshipGrpcClient allStarshipGrpcClient;


    @GetMapping("/grpc/starships")
    public List<Starships> getAllStarshipData(){
        List<AllStarshipData> allStarshipInfo = allStarshipGrpcClient.getAllStarshipInfo();
        List<Starships> collect = allStarshipInfo.stream()
                .map(this::getStarships)
                .collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/grpc/starships/{id}/films")
    public Starships getStarshipById(@PathVariable("id") Integer id){
        AllStarshipData starshipById = allStarshipGrpcClient.getStarshipById(id);
        return getStarships(starshipById);
    }

    private Starships getStarships(AllStarshipData allStarshipData) {
        Starships build = Starships.builder()
                .id(allStarshipData.getId())
                .name(allStarshipData.getName())
                .length(allStarshipData.getLength())
                .maxSpeed(allStarshipData.getMaxSpeed())
                .films(getFilms(allStarshipData.getAllFilmsList()))
                .build();
        return build;
    }

    private List<Films> getFilms(List<films> allFilmsList) {
        return allFilmsList.stream()
                .map(item -> {
                    return Films.builder()
                            .id(item.getId())
                            .title(item.getTitle())
                            .producers(getProducers(item.getAllProducersList()))
                            .build();
                })
                .collect(Collectors.toList());

    }

    private List<Producer> getProducers(List<producer> allProducersList) {
        return allProducersList.stream()
                .map(item -> {
                    return Producer.builder()
                            .name(item.getName())
                            .id(item.getId())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
