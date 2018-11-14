package br.com.umcincoum.djme.api.service;

import android.util.Log;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.api.model.canonical.ResponseCall;
import br.com.umcincoum.djme.utils.AppUtils;

public class UserService {

    private static ClientResponse response = null;
    private static WebResource webResource = null;
    private static Client client = null;

    public static ResponseCall createUser(UserModel user){

        ResponseCall responseCall = new ResponseCall();

        Gson gson = new Gson();
        String json = gson.toJson(user).toString();
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "user");
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .post(ClientResponse.class, json);
            responseCall = gson.fromJson(String.valueOf(response.getEntity(String.class)), ResponseCall.class);
            Log.e("CreateUser", String.valueOf(response.getStatus()) + " - " + gson.fromJson(String.valueOf(response.getEntity(String.class)), ResponseCall.class).getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseCall;
    }

    public static UserModel loginUser(UserModel user){
        UserModel userModel = new UserModel();

        Gson gson = new Gson();
        String json = gson.toJson(user).toString();
        client = Client.create();
        try{
            webResource = client.
                    resource(AppUtils.URI + "user/login");
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).header("Content-type","application/json")
                    .post(ClientResponse.class, json);
            userModel = gson.fromJson(String.valueOf(response.getEntity(String.class)), UserModel.class);
            Log.e("LoginUser", String.valueOf(response.getStatus()) + " - " + gson.fromJson(String.valueOf(response.getEntity(String.class)), UserModel.class).getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userModel;
    }
}
