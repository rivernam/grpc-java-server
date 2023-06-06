package org.example;

import io.grpc.stub.StreamObserver;
import org.example.helloservice.HelloRequest;
import org.example.helloservice.HelloResponse;
import org.example.helloservice.HelloServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage("hello " + request.getIndex() + ", response time : " + LocalDateTime.now())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        logger.info("complete {}, request : {}, response : {}",
                request.getIndex(), request.getRequestTime(), response.getMessage());
    }
}
