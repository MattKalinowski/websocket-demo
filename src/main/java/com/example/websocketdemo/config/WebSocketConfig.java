package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * Enables a simple in-memory message broker.
 *
 * An alternative for RabbitMQ, ActiveMQ or any other full-featured message broker.
 * */
@Configuration
@EnableWebSocketMessageBroker // this @ is used to enable our WebSocket server
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Registers a WebSocket endpoint that the clients will use to connect to our WebSocket server.
     *
     * SockJS is used to enable fallback options for browsers that don’t support WebSocket.
     *
     * This method comes from Spring frameworks STOMP implementation.
     * STOMP is needed because WebSocket itself is just a communication protocol.
     * It does not define things like - How to send a message only to users who are subscribed to a particular topic,
     * or how to send a message to a particular user.
     * */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }


    /**
     * Configures a message broker that will be used to route messages from one client to another.
     *
     * The first line defines that the messages whose destination starts with “/app”
     * should be routed to message-handling methods.
     *
     * The second line defines that the messages whose destination starts with “/topic”
     * should be routed to the message broker. Message broker broadcasts messages to all the connected clients
     * who are subscribed to a particular topic.
     * */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}