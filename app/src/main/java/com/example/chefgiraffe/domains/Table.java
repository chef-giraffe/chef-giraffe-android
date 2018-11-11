package com.example.chefgiraffe.domains;

public class Table {
    private String id;
    private String friendlyName;

    public Table(String id, String friendlyName) {
        this.id = id;
        this.friendlyName = friendlyName;
    }

    public String getId() {
        return id;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
