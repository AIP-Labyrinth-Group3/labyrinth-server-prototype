package com.uni.gamesever.models;

import java.util.UUID;

public class playerInfo {
    private String id;
    private String name;

    public playerInfo() {}
    public playerInfo(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
