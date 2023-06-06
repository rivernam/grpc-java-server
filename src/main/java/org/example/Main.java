package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.IOException;

public class Main implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @CommandLine.Option(names = {"-p", "--port"}, description = "grpc listen port", required = true)
    int port;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        Server server = ServerBuilder.forPort(port).addService(new HelloServiceImpl()).build();

        try {
            server.start();
            logger.info("server start on {}", port);
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}