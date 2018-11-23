package br.com.umcincoum.djme.ui.disco.chooseMusic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.spotify.GetArtistsController;
import br.com.umcincoum.djme.api.controller.spotify.GetTopTracksController;
import br.com.umcincoum.djme.api.controller.spotify.GetTracksFromArtistController;
import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.api.model.spotify.ArtistModel;
import br.com.umcincoum.djme.api.model.spotify.TracksModel;
import br.com.umcincoum.djme.ui.disco.MusicSelectedActivity;
import br.com.umcincoum.djme.ui.disco.chooseArtist.listAdapter.ListArtistAdapter;
import br.com.umcincoum.djme.ui.disco.chooseMusic.listAdapter.ListMusicAdapter;
import br.com.umcincoum.djme.utils.RecyclerViewClickListener;

public class ChooseMusicActivity extends AppCompatActivity {

    public ArtistModel artist;

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    public EditText edtSearch;
    public Button btnSearch;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public String playlist;

    public List<ArtistModel> artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_music);

        edtSearch = findViewById(R.id.edtSearchBarMusic);
        btnSearch = findViewById(R.id.btnSearchMusic);
        recyclerView = findViewById(R.id.rvMusic);

        if((ArtistModel) getIntent().getSerializableExtra("artist") != null ) {
            artist = (ArtistModel) getIntent().getSerializableExtra("artist");
            GetTopTracksController getTopTracksController = new GetTopTracksController(this);
            getTopTracksController.execute(artist.getId());
        }

        if((String) getIntent().getStringExtra("playlist") != null ) {
            playlist = (String) getIntent().getStringExtra("playlist");
        }


    }

    public void searchMusic(View v){
        GetTracksFromArtistController getTopTracksController = new GetTracksFromArtistController(this);
        getTopTracksController.execute(artist.getName(), edtSearch.getText().toString());
    }

    public void toEdit(TracksModel music){
        Intent toMusicSelected = new Intent(this, MusicSelectedActivity.class);
        toMusicSelected.putExtra("music", music);
        toMusicSelected.putExtra("artist", artist);
        toMusicSelected.putExtra("playlist", playlist);
        startActivity(toMusicSelected);
    }

    public void loadData(List<TracksModel> musicList){

        RecyclerViewClickListener listener = (view, position) -> {
                toEdit(musicList.get(position));
        };

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListMusicAdapter(musicList, listener, this);
        recyclerView.setAdapter(mAdapter);
    }
}
