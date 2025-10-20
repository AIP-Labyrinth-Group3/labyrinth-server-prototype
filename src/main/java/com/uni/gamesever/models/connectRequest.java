package com.uni.gamesever.models;

public class connectRequest {
    private String action;
    private String username;

    // Default constructor for JSON deserialization
    public connectRequest() {}

    public connectRequest(String action, String username) {
        this.action = action;
        this.username = username;
    }

    public String getAction() {
        return action;
    }
    public String getUsername() {
        return username;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
