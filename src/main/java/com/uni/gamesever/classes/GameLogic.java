package com.uni.gamesever.classes;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni.gamesever.models.connectRequest;
import com.uni.gamesever.models.playerInfo;
import com.uni.gamesever.services.SocketBroadcastService;

@Service
public class GameLogic {
    private final SocketBroadcastService socketBroadcastService;
    private static final int MAX_PLAYERS = 4;
    private playerInfo[] players = new playerInfo[MAX_PLAYERS];

    public GameLogic(SocketBroadcastService socketBroadcastService) {
        this.socketBroadcastService = socketBroadcastService;
    }

    public int handleClientMessage(String message, String userId) throws JsonMappingException, JsonProcessingException {
        //parsing the client message into a connectRequest object
        System.out.println("Received message from user " + userId + ": " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        connectRequest request;
        try {
             request = objectMapper.readValue(message, connectRequest.class);
        } catch (Exception e) {
            System.err.println("Failed to parse message from user " + userId + ": " + e.getMessage());
            return -1;
        }

        //check if the action is "CONNECT"
        if ("CONNECT".equals(request.getAction())) {
           //check if there is space for a new player
           if (players[MAX_PLAYERS - 1] != null) {
                  System.out.println("Connection attempt by " + request.getUsername() + " rejected. Game is full.");
                  socketBroadcastService.broadcastMessage("Connection rejected for " + request.getUsername() + ". Game is full.");
                    return -1;
                } else {
              for (int i = 0; i < MAX_PLAYERS; i++) {
                if (players[i] == null) {
                     players[i] = new playerInfo(request.getUsername());
                     //create a lobbyState object and parse it to JSON
                     String lobbyStateJson = objectMapper.writeValueAsString(
                        new com.uni.gamesever.models.lobbyState("LOBBY_STATE", players)
                     );

                     socketBroadcastService.broadcastMessage(lobbyStateJson);
                     break;
                }
              }
            }           
        }
        return 1;
    }

    public void processGameAction(String action, String userId) {
        // Implement game action processing logic here
        System.out.println("Processing action: " + action + " for user: " + userId);
    }


}

