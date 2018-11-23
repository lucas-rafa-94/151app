package br.com.umcincoum.djme.api.controller.playlist;

import android.os.AsyncTask;

import java.util.List;

import br.com.umcincoum.djme.api.model.PlaylistModel;
import br.com.umcincoum.djme.api.service.PlaylistService;
import br.com.umcincoum.djme.ui.disco.playlist.ViewPlaylistActivity;

public class GetPlaylistByIdController extends AsyncTask<String,Void,PlaylistModel> {

    private ViewPlaylistActivity viewPlaylistActivity;

    public GetPlaylistByIdController(ViewPlaylistActivity viewPlaylistActivity) {
        this.viewPlaylistActivity = viewPlaylistActivity;
    }

    @Override
    protected PlaylistModel doInBackground(String... strings) {
        return PlaylistService.getPlaylistById(strings[0]);
    }

    @Override
    protected void onPostExecute(PlaylistModel playlistModel) {
        super.onPostExecute(playlistModel);
        if(playlistModel.getTrackSelectedModelList() != null){
            this.viewPlaylistActivity.loadData(playlistModel.getTrackSelectedModelList());
        }
    }
}
