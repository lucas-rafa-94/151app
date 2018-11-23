package br.com.umcincoum.djme.ui.disco.chooseArtist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.List;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.spotify.GetArtistsController;
import br.com.umcincoum.djme.api.controller.spotify.GetTopTracksController;
import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.api.model.spotify.ArtistModel;
import br.com.umcincoum.djme.ui.disco.chooseArtist.listAdapter.ListArtistAdapter;
import br.com.umcincoum.djme.ui.disco.chooseMusic.ChooseMusicActivity;
import br.com.umcincoum.djme.ui.event.listAdapter.ListEventAdapter;
import br.com.umcincoum.djme.utils.RecyclerViewClickListener;

public class ChooseArtistActivity extends AppCompatActivity {

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    public EditText edtSearch;
    public Button btnSearch;

    public String playlist;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_artist);

        edtSearch = findViewById(R.id.edtSearchBarArtist);
        btnSearch = findViewById(R.id.btnSearchArtist);
        recyclerView = findViewById(R.id.rvArtist);

        if((String) getIntent().getStringExtra("playlist") != null ) {
            playlist = (String) getIntent().getStringExtra("playlist");
        }


    }

    public void searchArtists(View v){
        GetArtistsController getArtistsController = new GetArtistsController(this);
        getArtistsController.execute(edtSearch.getText().toString());
    }

    public void toEdit(ArtistModel artist){
        Intent toChooseMusic = new Intent(this, ChooseMusicActivity.class);
        toChooseMusic.putExtra("artist", artist);
        toChooseMusic.putExtra("playlist", playlist);
        startActivity(toChooseMusic);

    }

    public void loadData(List<ArtistModel> artistModels){

        RecyclerViewClickListener listener = (view, position) -> {
            toEdit(artistModels.get(position));
        };

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListArtistAdapter(artistModels, listener, this);
        recyclerView.setAdapter(mAdapter);
    }
}
