package br.com.umcincoum.djme.ui.event;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.GetAllEventsController;
import br.com.umcincoum.djme.api.model.EventModel;

public class EventsActivity extends AppCompatActivity {

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    GetAllEventsController getAllEventsController;

    public List<EventModel> listEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_events);

        sp = getApplicationContext().getSharedPreferences("appSp", 0);
        editor = sp.edit();

        Log.e("User", sp.getString("user",null));

        init();
    }

    public void init(){
        listEvents = new ArrayList<>();
        getAllEventsController = new GetAllEventsController(this);
        getAllEventsController.execute();
    }
}
