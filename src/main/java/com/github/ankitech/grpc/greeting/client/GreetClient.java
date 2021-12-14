package com.github.ankitech.grpc.greeting.client;

import com.proto.greet.Greeting;
import com.proto.greet.GreetingRequest;
import com.proto.greet.GreetingResponse;
import com.proto.greet.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 51015)
                .usePlaintext()
                .build();

        System.out.println("creating stub");

        GreetingServiceGrpc.GreetingServiceBlockingStub greetClient = GreetingServiceGrpc.newBlockingStub(channel);

        Greeting greeting = Greeting.newBuilder()
                .setFirstName("kumar")
                .setLastName("ankit")
                .build();

        GreetingRequest greetingRequest = GreetingRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        GreetingResponse greetingResponse = greetClient.greet(greetingRequest);

        System.out.println(greetingResponse.getResult());

        System.out.println("shutting down channel");
        channel.shutdown();
    }
}
