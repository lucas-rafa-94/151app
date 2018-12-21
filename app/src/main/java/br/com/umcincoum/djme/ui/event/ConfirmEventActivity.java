package br.com.umcincoum.djme.ui.event;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.user.UpdateUserEventController;
import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.ui.disco.DiscoActivity;

public class ConfirmEventActivity extends AppCompatActivity {

    EditText edtPassowrd;

    public EventModel event;

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    public UpdateUserEventController updateUserEventController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_event);

        sp = getApplicationContext().getSharedPreferences("appSp", 0);

        edtPassowrd = findViewById(R.id.edtPassword);

        if((EventModel) getIntent().getSerializableExtra("event") != null ) {
            event = (EventModel) getIntent().getSerializableExtra("event");
        }

    }

    public void confirmEvent(View v){
        if(event.getPassphrase().equals(edtPassowrd.getText().toString())){
            List<EventModel> listEvent = new ArrayList<>();
            Gson gson = new Gson();

            UserModel user = new UserModel();
            user = gson.fromJson(sp.getString("user",null), UserModel.class);

            editor = sp.edit();

            if (user.getEvents() != null){
                listEvent = user.getEvents();
                listEvent.add(event);
                user.setEvents(listEvent);

                updateUserEventController = new UpdateUserEventController(this);
                updateUserEventController.execute(user);

                Log.e("listEvents", "Entrou");
            }else {
                listEvent.add(event);
                user.setEvents(listEvent);
                updateUserEventController = new UpdateUserEventController(this);
                updateUserEventController.execute(user);
                Log.e("listEvents", "Nao entrou");
            }


            editor.putString("event", gson.toJson(event));
            editor.putString("user", gson.toJson(user));
            editor.commit();

            Toast.makeText(this, "Senha correta!", Toast.LENGTH_SHORT).show();
            //toDiscoMain();

        }else{
            Toast.makeText(this, "Senha Incorreta!", Toast.LENGTH_SHORT).show();
        }
    }

    public void toDiscoMain(){
        Intent toDiscoMain = new Intent(this, DiscoActivity.class);
        startActivity(toDiscoMain);
    }
}
