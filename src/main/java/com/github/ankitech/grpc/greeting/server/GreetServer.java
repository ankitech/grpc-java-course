package com.github.ankitech.grpc.greeting.server;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Welcome to grpc");

        BindableService bindableService;
        Server server = ServerBuilder
                .forPort(51015)
                .addService(new GreetServiceImpl())
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    System.out.println("Received shutdown request");
                    server.shutdown();
                    System.out.println("Server stopped successfully");
                }
        ));

        server.awaitTermination();
    }
}
