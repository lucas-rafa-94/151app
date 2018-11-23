package br.com.umcincoum.djme.api.model.spotify;

import java.io.Serializable;

public class ArtistModel implements Serializable {
    private String id;
    private String name;
    private String photoUri;

    public ArtistModel(String id, String name, String photoUri) {
        this.id = id;
        this.name = name;
        this.photoUri = photoUri;
    }

    public ArtistModel() {
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

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
