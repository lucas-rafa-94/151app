package br.com.umcincoum.djme.ui.event.listAdapter;

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
import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.utils.RecyclerViewClickListener;

public class ListEventAdapter extends RecyclerView.Adapter<ListEventAdapter.ViewHolder> {

    private List<EventModel> eventList;
    private RecyclerViewClickListener mListener;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        public TextView name, description, date, avaiable;
        public ImageView ivFoto;
        private RecyclerViewClickListener mListener;


        public ViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;

            ivFoto = v.findViewById(R.id.ivPhoto);
            name = v.findViewById(R.id.txtName);
            description = v.findViewById(R.id.txtDescription);
            date = v.findViewById(R.id.txtDate);
            avaiable = v.findViewById(R.id.txtAvaiable);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }


    public ListEventAdapter(List<EventModel> eventList, RecyclerViewClickListener listener, Context context) {
        this.eventList = eventList;
        mListener = listener;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evento, parent, false);

        return new ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EventModel event = eventList.get(position);
        Picasso.get().load("https://scontent.fcgh11-1.fna.fbcdn.net/v/t1.0-9/45488611_2737691129589348_6474835693196541952_o.jpg?_nc_cat=108&_nc_ht=scontent.fcgh11-1.fna&oh=e46cd20af05f2c55887d50205ac8b33c&oe=5C68A495").into(holder.ivFoto);
        holder.name.setText(event.getName());
        holder.description.setText(event.getDate().substring(0,10));
//        holder.description.setText(event.getDescription());
        if(event.isAvaiable()){
            holder.avaiable.setText("Disponível");
        }else {
            holder.avaiable.setText("Indisponível");
        }


    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
