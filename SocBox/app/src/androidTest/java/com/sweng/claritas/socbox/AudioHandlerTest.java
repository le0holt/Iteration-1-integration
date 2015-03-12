package com.sweng.claritas.socbox;

import android.app.Activity;
import android.media.MediaPlayer;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import java.util.ArrayList;


/**
 *
 *
 * Created by Andy on 03/03/2015.
 */
public class AudioHandlerTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public Activity activity;
    public AudioHandlerTest() {
        super(MainActivity.class);
    }
    boolean check = false;


    protected void setUp() throws Exception{
        super.setUp();

        setActivityInitialTouchMode(false);

        activity = getActivity();
    }

    @SmallTest
    public void testURL(){
        ArrayList<String> songs = new ArrayList<>();

        songs.add("a");

        AudioHandler audioHandler = new AudioHandler(activity,songs);

        assertEquals("a", audioHandler.URLs.get(0));
    }

    public void testURLs(){
        ArrayList<String> songs = new ArrayList<>();
        songs.add("a");
        songs.add("b");

        AudioHandler audioHandler = new AudioHandler(activity,songs);

        assertEquals("b", audioHandler.URLs.get(1));
    }

    public void testCurrentSong(){
        ArrayList<String> songs = new ArrayList<>();
        songs.add("a");
        songs.add("b");

        AudioHandler audioHandler = new AudioHandler(activity,songs);

        audioHandler.CurrentSong = 1;

        assertEquals("b", audioHandler.URLs.get(audioHandler.CurrentSong));
    }

    public void testChangePlaylist(){
        ArrayList<String> songs = new ArrayList<>();
        songs.add("a");
        songs.add("b");

        AudioHandler audioHandler = new AudioHandler(activity,songs);

        songs.add(2,"c");
        songs.add(3,"b");

        audioHandler.setPlaylist(songs);

        assertEquals(4, audioHandler.URLs.size());
    }

    public void testPause(){

        ArrayList<String> songs = new ArrayList<>();
        songs.add("a");

        AudioHandler audioHandler = new AudioHandler(activity, songs);

        audioHandler.mediaPlayer.start();

        audioHandler.playMusic();

        assertEquals(false,audioHandler.mediaPlayer.isPlaying());

    }

    public void testPauseToPlay() throws InterruptedException {

        ArrayList<String> songs = new ArrayList<>();



        songs.add("http://android.programmerguru.com/wp-content/uploads/2013/04/hosannatelugu.mp3");

        AudioHandler audioHandler = new AudioHandler(activity, songs);


        while(!audioHandler.AudioReady) {}


        audioHandler.mediaPlayer.start();


        wait(4000);

        assertEquals(true,audioHandler.mediaPlayer.isPlaying());

//
//        if (audioHandler.AudioReady) {
//            audioHandler.mediaPlayer.start();
//            assertEquals(true, audioHandler.mediaPlayer.isPlaying());
//            check = true;
//        } else {
//            assertEquals(false, audioHandler.mediaPlayer.isPlaying());
//            check = true;
//        }







//        audioHandler.playMusic();
//
//        audioHandler.playMusic();





    }

    protected void tearDown() throws Exception{super.tearDown();}
}
