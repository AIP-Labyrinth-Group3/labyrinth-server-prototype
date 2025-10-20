package com.uni.gamesever.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.uni.gamesever.classes.GameLogic;
import com.uni.gamesever.services.SocketBroadcastService;

// Socket-Connection Configuration class
public class SocketConnectionHandlerActions extends TextWebSocketHandler {
    private final SocketBroadcastService socketBroadcastService;
    private final GameLogic gameLogic;

    public SocketConnectionHandlerActions(SocketBroadcastService socketBroadcastService, GameLogic gameLogic) {
        this.socketBroadcastService = socketBroadcastService;
        this.gameLogic = gameLogic;
    }
    List<WebSocketSession> webSocketSessions
        = Collections.synchronizedList(new ArrayList<>());

   
    @Override
    public void
    afterConnectionEstablished(WebSocketSession session)
        throws Exception
    {

        super.afterConnectionEstablished(session);
        System.out.println(session.getId() + " Connected");

        webSocketSessions.add(session);
    }

    
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                          CloseStatus status)throws Exception
    {
        super.afterConnectionClosed(session, status);
        System.out.println(session.getId()
                           + " DisConnected");

       
        webSocketSessions.remove(session);
    }

    
    @Override
    public void handleMessage(WebSocketSession session,
                              WebSocketMessage<?> message)
        throws Exception
    {

        super.handleMessage(session, message);
        System.out.println("Message Received from user " + session.getId() + ": " + message.getPayload());

        //the whole game logic goes here
        if(gameLogic.handleClientMessage(message.getPayload().toString(), session.getId()) != 1) {
            // we return a error message to the client
            
        }
    }
}
