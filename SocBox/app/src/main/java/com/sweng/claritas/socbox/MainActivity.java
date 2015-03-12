package com.sweng.claritas.socbox;

        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ImageButton;
        import java.util.ArrayList;


public class MainActivity extends ActionBarActivity{

    public ArrayList<String> musicURLs = new ArrayList<>();
    public AudioHandler audioHandler;
    public ImageButton playButton, muteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        musicURLs.add(0, "http://android.programmerguru.com/wp-content/uploads/2013/04/hosannatelugu.mp3");
        audioHandler = new AudioHandler(this, musicURLs);
    }

    public void displayPopUp(View view){audioHandler.displayPopUp(view);}

    public void dismissPopUp(View view){audioHandler.dismissPopUp();}

    public void backTrack(View view){audioHandler.backTrack();}

    public void forwardTrack(View view){audioHandler.forwardTrack();}

    public void playMusic(View view){audioHandler.playMusic();}

    public void mute(View view){audioHandler.mute();}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sound_control:
                Log.v("Sound c action selected", "sound control");
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}