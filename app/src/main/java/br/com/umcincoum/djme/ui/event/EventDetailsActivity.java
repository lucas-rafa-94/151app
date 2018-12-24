package br.com.umcincoum.djme.ui.event;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.user.UpdateUserEventController;
import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.ui.disco.DiscoActivity;

public class EventDetailsActivity extends AppCompatActivity {
    public EventModel event;

    TextView lblName, lblDescription, lblDate, lblAvaiable;
    EditText edtPassowrd;
    PopupWindow popupWindow;
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    public UpdateUserEventController updateUserEventController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        sp = getApplicationContext().getSharedPreferences("appSp", 0);

        lblName = findViewById(R.id.lblName);
        lblDescription = findViewById(R.id.lblDescription);
        lblDescription.setMovementMethod(new ScrollingMovementMethod());
        lblDate = findViewById(R.id.lblDate);
        lblAvaiable = findViewById(R.id.lblAvaiable);


        if(getIntent().getSerializableExtra("event") != null ) {
            event = (EventModel) getIntent().getSerializableExtra("event");
            loadData();
        }

    }

    public void loadData(){
        lblName.setText(event.getName());
        lblDate.setText(event.getDate().substring(0,10));
        if(event.isAvaiable()){
            lblAvaiable.setText("Disponível");
        }else{
            lblAvaiable.setText("Indísponível");
        }

        lblDescription.setText(event.getDescription());
    }

    public void toConfirmEvent(View v) {
        if(event.isAvaiable()){
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.popup_layout,(ViewGroup) findViewById(R.id.popupLayout));
            popupWindow = new PopupWindow(layout,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
            popupWindow.showAtLocation(v,Gravity.CENTER,0,0);

            Button confirmBtn = layout.findViewById(R.id.btnConfirmEvent);

            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edtPassowrd = layout.findViewById(R.id.edtPassword);

                    confirmEvent(view, edtPassowrd);
                }
            });
            //Intent toConfirmEvent = new Intent(this, ConfirmEventActivity.class);
            //toConfirmEvent.putExtra("event", event);
            //startActivity(toConfirmEvent);
        }else{
            Toast.makeText(this, "Evento não liberado para discotecagem ainda :(", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmEvent(View v, EditText editText){
        if(event.getPassphrase().equals(editText.getText().toString())){
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

            Toast.makeText(getApplicationContext(), "Senha correta!", Toast.LENGTH_SHORT).show();
            toDiscoMain();

        }else{
            Toast.makeText(getApplicationContext(), "Senha Incorreta!", Toast.LENGTH_SHORT).show();
        }
    }

    public void toDiscoMain(){
        popupWindow.dismiss();
        Intent toDiscoMain = new Intent(this, DiscoActivity.class);
        startActivity(toDiscoMain);
    }
}
