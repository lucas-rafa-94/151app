package br.com.umcincoum.djme.api.controller.user;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.umcincoum.djme.ui.user.LoginActivity;
import br.com.umcincoum.djme.ui.user.RegisterActivity;
import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.api.model.canonical.ResponseCall;
import br.com.umcincoum.djme.api.service.UserService;

public class CreateUserController extends AsyncTask<UserModel,Void,ResponseCall> {

    private RegisterActivity registerActivity;

    public CreateUserController(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
    }

    @Override
    protected ResponseCall doInBackground(UserModel... userModels) {
       return UserService.createUser(userModels[0]);
    }


    @Override
    protected void onPostExecute(ResponseCall responseCall) {
        super.onPostExecute(responseCall);
        if(responseCall.getStatus().equals("Success")){
            Intent toLogin = new Intent(this.registerActivity, LoginActivity.class);
            Toast.makeText(this.registerActivity, responseCall.getDescription(), Toast.LENGTH_SHORT).show();
            this.registerActivity.startActivity(toLogin);
            this.registerActivity.finish();
        } else {
            Toast.makeText(this.registerActivity, responseCall.getDescription(), Toast.LENGTH_SHORT).show();
        }
    }
}
