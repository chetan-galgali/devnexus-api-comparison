package com.example.spring_graphql.controller.grpc;

import com.example.spring_graphql.entity.AllStarships;
import com.example.spring_graphql.entity.FilmsEntity;
import com.example.spring_graphql.repo.AllStarshipsRepo;
import com.google.protobuf.Empty;
import com.insight.transform.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
@Log4j2
public class AllStarshipInfoGRPCServer extends AllStarshipInfoGrpc.AllStarshipInfoImplBase {
    private final AllStarshipsRepo allStarshipsRepo;

    @Override
    public void getAllStarshipInfo(Empty request, StreamObserver<AllStarshipData> responseObserver) {
        List<AllStarships> all = allStarshipsRepo.findAll();
        all.stream()
                .map(this::getAllStarshipData)
                .forEach(item-> {
                    try {
                        //Thread.sleep(1000);
                        responseObserver.onNext(item);
                        log.info("Sent - " + item.getName());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        responseObserver.onCompleted();
    }

    @Override
    public void getStarshipInfoForId(StarshipID request, StreamObserver<AllStarshipData> responseObserver){
        Optional<AllStarships> optionalStarship = allStarshipsRepo.findById(request.getId());
        if(optionalStarship.isPresent()){
            AllStarshipData allStarshipData = getAllStarshipData(optionalStarship.get());
            responseObserver.onNext(allStarshipData);
        }
        responseObserver.onCompleted();
    }

    private AllStarshipData getAllStarshipData(AllStarships allStarships) {
        AllStarshipData returnObj = AllStarshipData.newBuilder()
                .setId(allStarships.getId())
                .setName(allStarships.getName())
                .setLength(allStarships.getLength())
                .setMaxSpeed(allStarships.getMaxSpeed())
                .addAllAllFilms(getAllFilms(allStarships))
                .build();
        return returnObj;
    }

    private List<films> getAllFilms(AllStarships allStarships) {
        List<films> returnObj = allStarships.getFilms().stream()
                .map(currentFilm -> {
                    films grpcFilm = films.newBuilder()
                            .setId(currentFilm.getId())
                            .setTitle(currentFilm.getTitle())
                            .addAllAllProducers(getAllProducers(currentFilm))
                            .build();
                    return grpcFilm;
                })
                .collect(Collectors.toList());
        return returnObj;
    }

    private List<producer> getAllProducers(FilmsEntity film){
        List<producer> returnObj = film.getProducers().stream()
                .map(currentProducer -> {
                    producer grpcProducer = producer.newBuilder()
                            .setId(currentProducer.getId())
                            .setName(currentProducer.getName())
                            .build();
                    return grpcProducer;
                })
                .collect(Collectors.toList());
        return returnObj;
    }
}
