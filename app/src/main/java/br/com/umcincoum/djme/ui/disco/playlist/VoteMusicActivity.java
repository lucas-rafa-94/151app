package br.com.umcincoum.djme.ui.disco.playlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.playlist.VoteMusicByPlaylistController;
import br.com.umcincoum.djme.api.model.TrackSelectedModel;
import br.com.umcincoum.djme.ui.disco.DiscoActivity;

public class VoteMusicActivity extends AppCompatActivity {

    public TrackSelectedModel trackSelectedModel;

    public String email;

    public String playlist;

    TextView txtArtist, txtMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_music);

        txtArtist = findViewById(R.id.txtArtist);
        txtMusic = findViewById(R.id.txtMusic);

        if((String) getIntent().getStringExtra("playlist") != null ) {
            playlist = (String) getIntent().getStringExtra("playlist");
        }

        if((String) getIntent().getStringExtra("email") != null ) {
            email = (String) getIntent().getStringExtra("email");
        }

        if((TrackSelectedModel) getIntent().getSerializableExtra("track") != null ) {
            trackSelectedModel = (TrackSelectedModel) getIntent().getSerializableExtra("track");
            txtArtist.setText(trackSelectedModel.getArtist());
            txtMusic.setText(trackSelectedModel.getName());
        }
    }

    public void vote(View v){
        VoteMusicByPlaylistController voteMusicByPlaylistController = new VoteMusicByPlaylistController(this);
        voteMusicByPlaylistController.execute();
    }

    public void toMenu(){
        Intent toMenu = new Intent(this, DiscoActivity.class);
        startActivity(toMenu);
    }
}
