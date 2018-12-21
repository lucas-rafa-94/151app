package br.com.umcincoum.djme.ui.disco.playlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.controller.playlist.GetPlaylistByIdController;
import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.api.model.TrackSelectedModel;
import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.ui.disco.playlist.listAdapter.ListViewPlaylistAdapter;
import br.com.umcincoum.djme.utils.RecyclerViewClickListener;

public class ViewPlaylistActivity extends AppCompatActivity {

    public TextView txtPlaylist;

    public SharedPreferences sp;

    public String playlist;
    public EventModel event;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_playlist);

        txtPlaylist = findViewById(R.id.lblPlaylist);
        recyclerView = findViewById(R.id.rvPlaylist);

        sp = getApplicationContext().getSharedPreferences("appSp", 0);

        if((String) getIntent().getStringExtra("playlist") != null ) {
            playlist = (String) getIntent().getStringExtra("playlist");
            txtPlaylist.setText(playlist);
        }

        init();
    }

    public void init(){
        event = new EventModel();
        event = new Gson().fromJson(sp.getString("event",null), EventModel.class);
        GetPlaylistByIdController getPlaylistByIdController = new GetPlaylistByIdController(this);
        getPlaylistByIdController.execute(event.getId()+playlist);
    }

    public void toEdit(TrackSelectedModel trackSelectedModel){
        UserModel user = new UserModel();
        user = new Gson().fromJson(sp.getString("user",null), UserModel.class);

        boolean voted = false;
        if(trackSelectedModel.getUsersVoted() != null){
            for (int i = 0; i < trackSelectedModel.getUsersVoted().size(); i++){
                if(user.getEmail().equals(trackSelectedModel.getUsersVoted().get(i))){
                    voted = true;
                }
            }
        }
        if(voted){
            Toast.makeText(this, "Você já deu like nessa música :)", Toast.LENGTH_SHORT).show();
        }else {
            Intent toVote = new Intent(this, VoteMusicActivity.class);
            toVote.putExtra("playlist", event.getId() + playlist);
            toVote.putExtra("email", user.getEmail());
            toVote.putExtra("track", trackSelectedModel);
            startActivity(toVote);
        }

    }

    public void refresh (View v){
        GetPlaylistByIdController getPlaylistByIdController = new GetPlaylistByIdController(this);
        getPlaylistByIdController.execute(event.getId()+playlist);
    }

    public void loadData(List<TrackSelectedModel> musicList){

        RecyclerViewClickListener listener = (view, position) -> {
            toEdit(musicList.get(position));
        };
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListViewPlaylistAdapter(musicList, listener, this);
        recyclerView.setAdapter(mAdapter);
    }
}
