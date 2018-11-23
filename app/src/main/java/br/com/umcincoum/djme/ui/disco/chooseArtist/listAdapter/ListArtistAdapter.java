package br.com.umcincoum.djme.ui.disco.chooseArtist.listAdapter;

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
import br.com.umcincoum.djme.api.model.spotify.ArtistModel;
import br.com.umcincoum.djme.utils.RecyclerViewClickListener;

public class ListArtistAdapter extends RecyclerView.Adapter<ListArtistAdapter.ViewHolder>  {

    private List<ArtistModel> artistList;
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


    public ListArtistAdapter(List<ArtistModel> artists, RecyclerViewClickListener listener, Context context) {
        this.artistList = artists;
        mListener = listener;
        this.context = context;
    }


    @Override
    public ListArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evento, parent, false);

        return new ListArtistAdapter.ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ListArtistAdapter.ViewHolder holder, int position) {
        Picasso.get().load(artistList.get(position).getPhotoUri()).into(holder.ivFoto);
        holder.name.setText(artistList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }
}
