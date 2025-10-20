package com.uni.gamesever.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.uni.gamesever.classes.GameLogic;
import com.uni.gamesever.controller.SocketConnectionHandlerActions;
import com.uni.gamesever.controller.SocketConnectionHandlerBroadcast;
import com.uni.gamesever.services.SocketBroadcastService;

@Configuration
@EnableWebSocket
public class WebSocketConfig
    implements WebSocketConfigurer {

    private final SocketBroadcastService socketBroadcastService;
    private final GameLogic gameLogic;

    public WebSocketConfig(SocketBroadcastService socketBroadcastService, GameLogic gameLogic) {
        this.socketBroadcastService = socketBroadcastService;
        this.gameLogic = gameLogic;
    }

    @Override
    public void registerWebSocketHandlers(
        WebSocketHandlerRegistry webSocketHandlerRegistry)
    {
        webSocketHandlerRegistry
            .addHandler(new SocketConnectionHandlerActions(socketBroadcastService, gameLogic),"/client/actions")
            .setAllowedOrigins("*");
        
        webSocketHandlerRegistry
        .addHandler(new SocketConnectionHandlerBroadcast(socketBroadcastService),"/server/broadcast")
        .setAllowedOrigins("*");
    }
}
