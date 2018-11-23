package br.com.umcincoum.djme.api.controller.user;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.api.service.UserService;
import br.com.umcincoum.djme.ui.event.EventsActivity;
import br.com.umcincoum.djme.ui.user.LoginActivity;

public class LogInUserController extends AsyncTask<UserModel,Void,UserModel> {

    private LoginActivity loginActivity;

    public LogInUserController(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Override
    protected UserModel doInBackground(UserModel... userModels) {
        return UserService.loginUser(userModels[0]);
    }

    @Override
    protected void onPostExecute(UserModel userModel) {
        super.onPostExecute(userModel);
        if(userModel != null){
            if(!userModel.getEmail().equals("")){
                commitSp(userModel);
                Toast.makeText(this.loginActivity, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                toEvents();
                this.loginActivity.finish();
            }
        }else{
            Toast.makeText(this.loginActivity, "Usuário ou Senha Inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    public void toEvents(){
        Intent toEvents = new Intent(this.loginActivity, EventsActivity.class);
        this.loginActivity.startActivity(toEvents);
    }

    public void commitSp(UserModel userModel){
        Gson gson = new Gson();
        this.loginActivity.editor = this.loginActivity.sp.edit();
        this.loginActivity.editor.putString("user", gson.toJson(userModel));
        this.loginActivity.editor.putString("token", "loggedIn");
        this.loginActivity.editor.commit();
    }
}
