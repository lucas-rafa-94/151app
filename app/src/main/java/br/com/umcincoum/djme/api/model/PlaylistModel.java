package br.com.umcincoum.djme.api.model;

import java.util.List;

public class PlaylistModel {

    private String playlist;
    private EventModel eventModel;
    private String genre;
    private List<TrackSelectedModel> trackSelectedModelList;

    public PlaylistModel(String playlist, EventModel eventModel, String genre, List<TrackSelectedModel> trackSelectedModelList) {
        this.playlist = playlist;
        this.eventModel = eventModel;
        this.genre = genre;
        this.trackSelectedModelList = trackSelectedModelList;
    }

    public PlaylistModel() {
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<TrackSelectedModel> getTrackSelectedModelList() {
        return trackSelectedModelList;
    }

    public void setTrackSelectedModelList(List<TrackSelectedModel> trackSelectedModelList) {
        this.trackSelectedModelList = trackSelectedModelList;
    }
}

