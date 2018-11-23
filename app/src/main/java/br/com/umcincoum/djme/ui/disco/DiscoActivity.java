package br.com.umcincoum.djme.ui.disco;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.umcincoum.djme.R;
import br.com.umcincoum.djme.ui.disco.fragments.ChooseMusicFragment;
import br.com.umcincoum.djme.ui.disco.fragments.PlaylistDiscoFragment;

public class DiscoActivity extends AppCompatActivity {

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    private ChooseMusicFragment chooseMusicFragment;
    private PlaylistDiscoFragment playlistDiscoFragment;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_music:
                    chooseMusicFragment = new ChooseMusicFragment();
                    changeFragment(chooseMusicFragment);
                    return true;
                case R.id.navigation_playlist:
                    playlistDiscoFragment = new PlaylistDiscoFragment();
                    changeFragment(playlistDiscoFragment);
                    return true;
            }
            return false;
        }
    };

    private void changeFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerFragment, fragment);
        transaction.commit();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        mOnNavigationItemSelectedListener.onNavigationItemSelected(navigation.getMenu().getItem(0));
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        sp = getApplicationContext().getSharedPreferences("appSp", 0);
        editor = sp.edit();

        Log.e("User", sp.getString("user",null));
    }

}
