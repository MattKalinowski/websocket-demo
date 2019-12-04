package com.example.websocketdemo.controller;

import com.example.websocketdemo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


/**
 * Responsible for receiving messages from one client and then broadcasting it to others.
 *
 * Configured in com.example.websocketdemo.config.WebSocketConfig
 * (all the messages sent from clients with a destination starting with /app
 * will be routed to these message handling methods annotated with @MessageMapping)
 * */
@Controller
public class ChatController {

    /**
    * For example, a message with destination /app/chat.sendMessage will be routed to the sendMessage()
    * */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    /**
     * For example, a message with destination /app/chat.addUser will be routed to the addUser()
     * */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}