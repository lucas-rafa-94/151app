package br.com.umcincoum.djme.api.model.spotify;

import java.io.Serializable;

public class TracksModel implements Serializable {

    private String name;
    private String photoUri;
    private String id;

    public TracksModel(String name, String photoUri, String id) {
        this.name = name;
        this.photoUri = photoUri;
        this.id = id;
    }

    public TracksModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

