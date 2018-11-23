package br.com.umcincoum.djme.api.controller.playlist;

import android.os.AsyncTask;
import android.widget.Toast;

import br.com.umcincoum.djme.api.service.PlaylistService;
import br.com.umcincoum.djme.ui.disco.playlist.VoteMusicActivity;

public class VoteMusicByPlaylistController extends AsyncTask<Void,Void,String> {
    private VoteMusicActivity voteMusicActivity;

    public VoteMusicByPlaylistController(VoteMusicActivity voteMusicActivity) {
        this.voteMusicActivity = voteMusicActivity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return PlaylistService.vote(this.voteMusicActivity.trackSelectedModel.getMusic(), this.voteMusicActivity.email, this.voteMusicActivity.playlist);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s.equals("Sucesso")){
            Toast.makeText(this.voteMusicActivity, "Votado com sucesso :)", Toast.LENGTH_SHORT).show();
            this.voteMusicActivity.toMenu();
        }else{
            Toast.makeText(this.voteMusicActivity, "Erro ao votar :(", Toast.LENGTH_SHORT).show();
        }
    }
}
