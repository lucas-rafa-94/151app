package br.com.umcincoum.djme.ui.disco.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.ui.disco.chooseArtist.ChooseArtistActivity;
import br.com.umcincoum.djme.ui.disco.fragments.listAdapter.ListDiscoMainAdapter;
import br.com.umcincoum.djme.utils.RecyclerViewClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseMusicFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    public EventModel event;

    public ChooseMusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_music, container, false);

        Gson gson = new Gson();
        sp = getContext().getSharedPreferences("appSp", 0);
        event = gson.fromJson(sp.getString("event",null), EventModel.class);

        recyclerView = view.findViewById(R.id.rvGenre);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    public void toEdit(String playlist){
        Intent toSearchArtist = new Intent(getContext(), ChooseArtistActivity.class);
        toSearchArtist.putExtra("playlist", playlist);
        startActivity(toSearchArtist);

    }


    public void loadData(){

        RecyclerViewClickListener listener = (view, position) -> {
            toEdit(event.getPlaylists().get(position));
        };

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListDiscoMainAdapter(event.getPlaylists(), listener, getContext());
        recyclerView.setAdapter(mAdapter);
    }


}
