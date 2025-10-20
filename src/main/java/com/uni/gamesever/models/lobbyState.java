package com.uni.gamesever.models;

public class lobbyState {
    private String type;
    private playerInfo[] players;

    public lobbyState(String type, playerInfo[] players) {
        this.type = type;
        this.players = players;
    }

    public String getType() {
        return type;
    }

    public playerInfo[] getPlayers() {
        return players;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPlayers(playerInfo[] players) {
        this.players = players;
    }
}
