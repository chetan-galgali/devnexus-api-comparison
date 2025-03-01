package com.insight.transform.clientproject.service;

import com.google.protobuf.Empty;
import com.insight.transform.AllStarshipData;
import com.insight.transform.AllStarshipInfoGrpc;
import com.insight.transform.StarshipID;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Log4j2
public class AllStarshipGrpcClient {
    @GrpcClient("local-grpc-server")
    private AllStarshipInfoGrpc.AllStarshipInfoBlockingStub allStarshipInfoBlockingStub;

    public List<AllStarshipData> getAllStarshipInfo(){
        List<AllStarshipData> returnData = new ArrayList<>();

        Empty empty = Empty.newBuilder()
                .build();
        Iterator<AllStarshipData> allStarshipInfo = allStarshipInfoBlockingStub.getAllStarshipInfo(empty);
        while(allStarshipInfo.hasNext()){
            AllStarshipData nextElement = allStarshipInfo.next();
            log.info("received = " + nextElement.getName());
            returnData.add(nextElement);
        }
        return returnData;
    }

    public AllStarshipData getStarshipById(Integer id){
        StarshipID starshipID = StarshipID.newBuilder().setId(id).build();
        AllStarshipData starshipInfoForId = allStarshipInfoBlockingStub.getStarshipInfoForId(starshipID);
        return starshipInfoForId;
    }
}
