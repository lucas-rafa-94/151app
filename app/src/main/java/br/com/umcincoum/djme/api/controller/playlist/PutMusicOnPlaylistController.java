package br.com.umcincoum.djme.api.controller.playlist;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.umcincoum.djme.api.service.PlaylistService;
import br.com.umcincoum.djme.ui.disco.DiscoActivity;
import br.com.umcincoum.djme.ui.disco.MusicSelectedActivity;

public class PutMusicOnPlaylistController extends AsyncTask<Void,Void,String> {

    private MusicSelectedActivity musicSelectedActivity;

    public PutMusicOnPlaylistController(MusicSelectedActivity musicSelectedActivity) {
        this.musicSelectedActivity = musicSelectedActivity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return PlaylistService.putMusicOnPlaylist(this.musicSelectedActivity.event.getId() + this.musicSelectedActivity.playlist,this.musicSelectedActivity.trackSelectedModel);
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        if(string.equals("SUCESSO")){
            Toast.makeText(this.musicSelectedActivity, "Música adicionada com sucesso!", Toast.LENGTH_SHORT).show();
            Intent toDiscoMain = new Intent(this.musicSelectedActivity, DiscoActivity.class);
            this.musicSelectedActivity.startActivity(toDiscoMain);
;        }else {
            Toast.makeText(this.musicSelectedActivity, "Música já escolhida :( Fique a vontade para escolher outra!", Toast.LENGTH_SHORT).show();
        }
    }
}
