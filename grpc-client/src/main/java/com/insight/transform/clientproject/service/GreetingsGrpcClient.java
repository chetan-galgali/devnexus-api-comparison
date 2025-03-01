package com.insight.transform.clientproject.service;

import com.insight.transform.GreeterGrpc;
import com.insight.transform.HelloReply;
import com.insight.transform.HelloRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class GreetingsGrpcClient {
    @GrpcClient("local-grpc-server")
    private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

    public String receiveGreeting(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        HelloReply helloReply = greeterBlockingStub.sayHello(request);
        String message = helloReply.getMessage();
        return message;

    }
}
