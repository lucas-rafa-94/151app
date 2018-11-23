package br.com.umcincoum.djme.api.controller.spotify;

import android.os.AsyncTask;

import java.util.List;

import br.com.umcincoum.djme.api.model.spotify.TracksModel;
import br.com.umcincoum.djme.api.service.spotify.SpotifyCaller;
import br.com.umcincoum.djme.ui.disco.chooseMusic.ChooseMusicActivity;

public class GetTracksFromArtistController extends AsyncTask<String,Void,List<TracksModel>> {

    private ChooseMusicActivity chooseMusicActivity;

    public GetTracksFromArtistController(ChooseMusicActivity chooseMusicActivity) {
        this.chooseMusicActivity = chooseMusicActivity;
    }

    @Override
    protected List<TracksModel> doInBackground(String... strings) {
        return SpotifyCaller.getTopTracksFromArtists(strings[0], strings[1]);
    }

    @Override
    protected void onPostExecute(List<TracksModel> musicList) {
        super.onPostExecute(musicList);
        this.chooseMusicActivity.loadData(musicList);
    }
}