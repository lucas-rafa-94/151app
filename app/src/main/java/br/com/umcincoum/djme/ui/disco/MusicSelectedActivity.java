package br.com.umcincoum.djme.ui.disco;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.playlist.PutMusicOnPlaylistController;
import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.api.model.TrackSelectedModel;
import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.api.model.spotify.ArtistModel;
import br.com.umcincoum.djme.api.model.spotify.TracksModel;

public class MusicSelectedActivity extends AppCompatActivity {

    public SharedPreferences sp;

    TextView txtPlaylist, txtArtist, txtMusic;

    public String playlist;
    public TracksModel music;
    public ArtistModel artist;
    public UserModel user;
    public EventModel event;

    public TrackSelectedModel trackSelectedModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_selected);

        sp = getApplicationContext().getSharedPreferences("appSp", 0);

        txtPlaylist = findViewById(R.id.txtPlaylist);
        txtArtist = findViewById(R.id.txtArtist);
        txtMusic = findViewById(R.id.txtMusic);

        if((String) getIntent().getStringExtra("playlist") != null ) {
            playlist = (String) getIntent().getStringExtra("playlist");
            txtPlaylist.setText(playlist);
        }

        if((ArtistModel) getIntent().getSerializableExtra("artist") != null ) {
            artist = (ArtistModel) getIntent().getSerializableExtra("artist");
            txtArtist.setText(artist.getName());
        }


        if((TracksModel) getIntent().getSerializableExtra("music") != null ) {
            music = (TracksModel) getIntent().getSerializableExtra("music");
            txtMusic.setText(music.getName());
        }
    }

    public void confirmTrack(View v){
        trackSelectedModel = new TrackSelectedModel();
        trackSelectedModel.setArtist(artist.getName());
        trackSelectedModel.setLikes(0);
        trackSelectedModel.setName(music.getName());
        trackSelectedModel.setMusic(music.getId());
        trackSelectedModel.setPhotoUri(music.getPhotoUri());

        UserModel userModel = new UserModel();
        userModel.setEmail(new Gson().fromJson(sp.getString("user",null), UserModel.class).getEmail());
        userModel.setName(new Gson().fromJson(sp.getString("user",null), UserModel.class).getName());

        event = new Gson().fromJson(sp.getString("event",null), EventModel.class);

        trackSelectedModel.setUserModel(userModel);

        PutMusicOnPlaylistController putMusicOnPlaylistController = new PutMusicOnPlaylistController(this);
        putMusicOnPlaylistController.execute();
    }
}
