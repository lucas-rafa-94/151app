package br.com.umcincoum.djme.api.service.spotify;

import android.util.Log;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import br.com.umcincoum.djme.api.model.spotify.ArtistModel;
import br.com.umcincoum.djme.api.model.spotify.TracksModel;
import br.com.umcincoum.djme.utils.AppUtils;

public class SpotifyCaller {

    private static ClientResponse response = null;
    private static WebResource webResource = null;
    private static Client client = null;

    public static List<ArtistModel> getArtists(String artist){
        List<ArtistModel> artists = new ArrayList<>();

        Gson gson = new Gson();
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "search-music?artists=" + artist.replace(" ", "-"));
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .get(ClientResponse.class);
            artists = Arrays.asList(gson.fromJson(String.valueOf(response.getEntity(String.class)), ArtistModel[].class));
            Log.e("GetAllArtists", String.valueOf(response.getStatus()) + " - " + String.valueOf(response.getEntity(String.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artists;
    }

    public static List<TracksModel> getTopTracksByArtists(String artist){
        List<TracksModel> musicList = new ArrayList<>();

        Gson gson = new Gson();
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "/search-music/top-tracks/" + artist);
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .get(ClientResponse.class);
            musicList = Arrays.asList(gson.fromJson(String.valueOf(response.getEntity(String.class)), TracksModel[].class));
            Log.e("GetAllTopTracks", String.valueOf(response.getStatus()) + " - " + String.valueOf(response.getEntity(String.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicList;
    }


    public static List<TracksModel> getTopTracksFromArtists(String artist, String music){
        List<TracksModel> musicList = new ArrayList<>();

        Gson gson = new Gson();
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "search-music/artist?name=" + artist.replace(" ", "-") + "&track=" + music.replace(" ", "-") );
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .get(ClientResponse.class);
            musicList = Arrays.asList(gson.fromJson(String.valueOf(response.getEntity(String.class)), TracksModel[].class));
            Log.e("GetAllTracks", String.valueOf(response.getStatus()) + " - " + String.valueOf(response.getEntity(String.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicList;
    }
}
