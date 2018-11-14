package br.com.umcincoum.djme.api.model;

import java.util.Date;
import java.util.List;

public class EventModel {

    private String id;
    private String name;
    private Date date;
    private String description;
    private String photoUri;
    private String passphrase;
    private List<String> playlists;
    private boolean avaiable;

    public EventModel(String id, String name, Date date, String description, String photoUri, String passphrase, List<String> playlists, boolean avaiable) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.photoUri = photoUri;
        this.passphrase = passphrase;
        this.playlists = playlists;
        this.avaiable = avaiable;
    }

    public EventModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public List<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<String> playlists) {
        this.playlists = playlists;
    }

    public boolean isAvaiable() {
        return avaiable;
    }

    public void setAvaiable(boolean avaiable) {
        this.avaiable = avaiable;
    }
}
