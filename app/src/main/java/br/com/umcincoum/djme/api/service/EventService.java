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

import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.utils.AppUtils;

public class EventService {

    private static ClientResponse response = null;
    private static WebResource webResource = null;
    private static Client client = null;

    public static List<EventModel> getAllEvents(){
        List<EventModel> events = new ArrayList<>();

        Gson gson = new Gson();
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "event");
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .get(ClientResponse.class);
            events = Arrays.asList(gson.fromJson(String.valueOf(response.getEntity(String.class)), EventModel[].class));
            Log.e("GetAllEvents", String.valueOf(response.getStatus()) + " - " + String.valueOf(response.getEntity(String.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
}
