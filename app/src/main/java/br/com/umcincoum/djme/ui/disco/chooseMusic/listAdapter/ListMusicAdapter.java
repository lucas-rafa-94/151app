package br.com.umcincoum.djme.ui.disco.chooseMusic.listAdapter;

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
import br.com.umcincoum.djme.api.model.spotify.TracksModel;
import br.com.umcincoum.djme.utils.RecyclerViewClickListener;

public class ListMusicAdapter extends RecyclerView.Adapter<ListMusicAdapter.ViewHolder>  {

    private List<TracksModel> musicList;
    private RecyclerViewClickListener mListener;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public ImageView ivFoto;
        private RecyclerViewClickListener mListener;


        public ViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            ivFoto = v.findViewById(R.id.ivPhoto);
            name = v.findViewById(R.id.txtName);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }


    public ListMusicAdapter(List<TracksModel> musics, RecyclerViewClickListener listener, Context context) {
        this.musicList = musics;
        mListener = listener;
        this.context = context;
    }


    @Override
    public ListMusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evento, parent, false);

        return new ListMusicAdapter.ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ListMusicAdapter.ViewHolder holder, int position) {
        Picasso.get().load(musicList.get(position).getPhotoUri()).into(holder.ivFoto);
        holder.name.setText(musicList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }
}
