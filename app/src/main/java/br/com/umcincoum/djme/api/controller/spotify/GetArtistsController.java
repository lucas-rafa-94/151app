package br.com.umcincoum.djme.api.controller.spotify;

import android.os.AsyncTask;

import java.util.List;

import br.com.umcincoum.djme.api.model.spotify.ArtistModel;
import br.com.umcincoum.djme.api.service.spotify.SpotifyCaller;
import br.com.umcincoum.djme.ui.disco.chooseArtist.ChooseArtistActivity;

public class GetArtistsController extends AsyncTask<String,Void,List<ArtistModel>> {

    private ChooseArtistActivity chooseArtistActivity;

    public GetArtistsController(ChooseArtistActivity chooseArtistActivity) {
        this.chooseArtistActivity = chooseArtistActivity;
    }

    @Override
    protected List<ArtistModel> doInBackground(String... strings) {
        return SpotifyCaller.getArtists(strings[0]);
    }

    @Override
    protected void onPostExecute(List<ArtistModel> artistModels) {
        super.onPostExecute(artistModels);
        this.chooseArtistActivity.loadData(artistModels);
    }
}
