package br.com.umcincoum.djme.ui.event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.model.EventModel;

public class EventDetailsActivity extends AppCompatActivity {
    EventModel event;

    TextView lblName, lblDescription, lblDate, lblAvaiable;
    Button btnDiscotecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);


        lblName = findViewById(R.id.lblName);
        lblDescription = findViewById(R.id.lblDescription);
        lblDate = findViewById(R.id.lblDate);
        lblAvaiable = findViewById(R.id.lblAvaiable);


        if((EventModel) getIntent().getSerializableExtra("event") != null ) {
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
            Intent toConfirmEvent = new Intent(this, ConfirmEventActivity.class);
            toConfirmEvent.putExtra("event", event);
            startActivity(toConfirmEvent);
        }else{
            Toast.makeText(this, "Evento não liberado para discotecagem ainda :(", Toast.LENGTH_SHORT).show();
        }
    }
}
