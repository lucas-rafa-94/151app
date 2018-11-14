package br.com.umcincoum.djme.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.CreateUserController;
import br.com.umcincoum.djme.api.model.UserModel;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword;
    Button btnRegister;

    CreateUserController createUserController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

    }

    public void createUser(View v){
        createUserController = new CreateUserController(this);

        UserModel userModel = new UserModel();
        userModel.setEmail(edtEmail.getText().toString());
        userModel.setName(edtName.getText().toString());
        userModel.setPassword(edtPassword.getText().toString());

        createUserController.execute(userModel);
    }


}
