package br.com.umcincoum.djme.ui.disco.playlist.listAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.api.model.TrackSelectedModel;

import br.com.umcincoum.djme.utils.RecyclerViewClickListener;

public class ListViewPlaylistAdapter extends RecyclerView.Adapter<ListViewPlaylistAdapter.ViewHolder>  {

    private List<TrackSelectedModel> listTracks;
    private RecyclerViewClickListener mListener;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView artist;
        public TextView music;
        public TextView votes;
        public ImageView ivFoto;
        private RecyclerViewClickListener mListener;


        public ViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            ivFoto = v.findViewById(R.id.ivPhoto);
            artist = v.findViewById(R.id.txtName);
            music = v.findViewById(R.id.txtDescription);
            votes = v.findViewById(R.id.txtAvaiable);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }


    public ListViewPlaylistAdapter(List<TrackSelectedModel> musics, RecyclerViewClickListener listener, Context context) {
        this.listTracks = musics;
        mListener = listener;
        this.context = context;
    }


    @Override
    public ListViewPlaylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evento, parent, false);

        return new ListViewPlaylistAdapter.ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ListViewPlaylistAdapter.ViewHolder holder, int position) {
        Picasso.get().load(listTracks.get(position).getPhotoUri()).into(holder.ivFoto);
        holder.artist.setText(listTracks.get(position).getArtist());
        holder.music.setText(listTracks.get(position).getName());
        holder.votes.setText(String.valueOf(listTracks.get(position).getLikes()));

    }

    @Override
    public int getItemCount() {
        return listTracks.size();
    }
}
