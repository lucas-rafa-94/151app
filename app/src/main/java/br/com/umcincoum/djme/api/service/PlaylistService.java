package br.com.umcincoum.djme.api.service;

import android.util.Log;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import br.com.umcincoum.djme.api.model.PlaylistModel;
import br.com.umcincoum.djme.api.model.TrackSelectedModel;
import br.com.umcincoum.djme.api.model.canonical.ResponseCall;
import br.com.umcincoum.djme.utils.AppUtils;

public class PlaylistService {

    private static ClientResponse response = null;
    private static WebResource webResource = null;
    private static Client client = null;

    public static List<PlaylistModel> getAllPlaylists( String event){
        List<PlaylistModel> playlists = new ArrayList<>();

        Gson gson = new Gson();
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "playlist?event=" + event);
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .get(ClientResponse.class);
            playlists = Arrays.asList(gson.fromJson(String.valueOf(response.getEntity(String.class)), PlaylistModel[].class));
            Log.e("GetAllPlaylists", String.valueOf(response.getStatus()) + " - " + String.valueOf(response.getEntity(String.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlists;
    }

    public static String putMusicOnPlaylist(String playlist, TrackSelectedModel trackSelectedModel){

        ResponseCall responseCall = new ResponseCall();
        Gson gson = new Gson();
        String json = gson.toJson(trackSelectedModel);
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "playlist?playlist=" + playlist);
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .post(ClientResponse.class, json);
            if(response.getStatus() == 200){
                responseCall = gson.fromJson(String.valueOf(response.getEntity(String.class)), ResponseCall.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseCall.getStatus();
    }

    public static PlaylistModel getPlaylistById( String id){
        PlaylistModel playlist = new PlaylistModel();

        Gson gson = new Gson();
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "playlist/" +  id);
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .get(ClientResponse.class);
            playlist = gson.fromJson(String.valueOf(response.getEntity(String.class)), PlaylistModel.class);
            Log.e("GetAllPlaylists", String.valueOf(response.getStatus()) + " - " + String.valueOf(response.getEntity(String.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlist;
    }

    public static String vote( String music, String email, String playlist){
        String str = "Falha";
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "/playlist/"+playlist+"/vote?email=" + email + "&music=" + music);
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .post(ClientResponse.class);
            if(response.getStatus() == 200){
                str = "Sucesso";
            }else{
                str = "Falha";
            }
            Log.e("GetAllPlaylists", String.valueOf(response.getStatus()) + " - " + String.valueOf(response.getEntity(String.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
