package br.com.umcincoum.djme.api.controller.playlist;

import android.os.AsyncTask;

import java.util.List;

import br.com.umcincoum.djme.api.model.PlaylistModel;

public class GetAllPlaylistsController extends AsyncTask<Void,Void,List<PlaylistModel>> {
    @Override
    protected List<PlaylistModel> doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(List<PlaylistModel> playlistModels) {
        super.onPostExecute(playlistModels);
    }
}
