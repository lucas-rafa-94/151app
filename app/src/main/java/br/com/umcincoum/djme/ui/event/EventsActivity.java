package br.com.umcincoum.djme.ui.event;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.event.GetAllEventsController;
import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.ui.disco.DiscoActivity;
import br.com.umcincoum.djme.ui.event.listAdapter.ListEventAdapter;
import br.com.umcincoum.djme.utils.RecyclerViewClickListener;

public class EventsActivity extends AppCompatActivity {

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    GetAllEventsController getAllEventsController;

    public List<EventModel> listEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        recyclerView = findViewById(R.id.rvEventos);
        sp = getApplicationContext().getSharedPreferences("appSp", 0);
        editor = sp.edit();

        Log.e("User", sp.getString("user",null));

        init();
    }

    public void toEdit(EventModel event){
        UserModel user = new UserModel();
        Gson gson = new Gson();

        user = gson.fromJson(sp.getString("user",null), UserModel.class);

        if(user.getEvents() != null){
            boolean hasIt = false;
            for(int i = 0; i < user.getEvents().size(); i++){
                if(user.getEvents().get(i).getId().equals(event.getId())){
                    hasIt = true;
                }
            }
            if(hasIt){
                toDiscoMain();
                editor.putString("event", gson.toJson(event));
                editor.commit();
            }else{
                toEventDetails(event);
            }
        }else {
            toEventDetails(event);
        }

    }

    public void init(){
        listEvents = new ArrayList<>();
        getAllEventsController = new GetAllEventsController(this);
        getAllEventsController.execute();
    }

    public void loadData(List<EventModel> listEvent){

        RecyclerViewClickListener listener = (view, position) -> {
            toEdit(listEvent.get(position));
        };

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListEventAdapter(listEvent, listener, this);
        recyclerView.setAdapter(mAdapter);
    }

    public void toEventDetails(EventModel event){
        Intent toEventDetails = new Intent(this, EventDetailsActivity.class);
        toEventDetails.putExtra("event", event);
        startActivity(toEventDetails);
    }

    public void toDiscoMain(){
        Intent toDiscoMain = new Intent(this, DiscoActivity.class);
        startActivity(toDiscoMain);
    }

    @Override
    public void onBackPressed() {

    }
}
