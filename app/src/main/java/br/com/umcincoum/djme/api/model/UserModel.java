package br.com.umcincoum.djme.api.model;

import java.util.List;

public class UserModel {


    private String email;
    private String name;
    private String password;
    private List<EventModel> events;

    public UserModel(String email, String name, String password, List<EventModel> events) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.events = events;
    }

    public UserModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }
}

