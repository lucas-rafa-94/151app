package br.com.umcincoum.djme.ui.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.user.LogInUserController;
import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.ui.event.EventsActivity;

public class LoginActivity extends AppCompatActivity {

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    EditText edtEmail, edtPassword;
    Button btnLogIn, btnRegister;

    TextView txtRegister;

    LogInUserController logInUserController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getApplicationContext().getSharedPreferences("appSp", 0);

        init();
        txtRegister = findViewById(R.id.txtRegister);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnRegister = findViewById(R.id.btnRegister);

        String str_cadastro = getString(R.string.cadastro);

        txtRegister.setText(Html.fromHtml(str_cadastro));

    }

    public void toRegister(View v){
        Intent toRegister = new Intent(this, RegisterActivity.class);
        startActivity(toRegister);
    }

    public void init(){
        if(sp.getString("token", null) != null){
            if(sp.getString("token", null).equals("loggedIn")){
                Intent toEvents= new Intent(this, EventsActivity.class);
                startActivity(toEvents);
            }
        }
    }

    public void loginUser(View v){
        UserModel userModel = new UserModel();
        logInUserController = new LogInUserController(this);

        userModel.setEmail(edtEmail.getText().toString());
        userModel.setPassword(edtPassword.getText().toString());

        logInUserController.execute(userModel);
    }
}
