package com.sweng.claritas.socbox;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.Environment;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.widget.LinearLayout;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by zharif20 on 3/3/15.
 */
public class VideoActivity extends Activity implements SurfaceHolder.Callback, OnCompletionListener, OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener, OnInfoListener, OnErrorListener {

    Display currentDisplay;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    int videoWidth = 0;
    int videoHeight = 0;
    boolean readyToPlay = false;

    public final static String LOGTAG = "custom_video";
    public String vidUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
//    DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
//    int width = displayMetrics.widthPixels;
//    int height = displayMetrics.heightPixels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //get ref to the surfaceview from xml and ref to surfaceholder, to monitor what happens to the underlying surface
        surfaceView = (SurfaceView)this.findViewById(R.id.surfaceViewVideo);
        surfaceHolder = surfaceView.getHolder();
        //implement surfaceholder.callback
        surfaceHolder.addCallback(this);
        //push buffer surface
        //surfaceHolder.

        //constructing the mediaplayer
        mediaPlayer = new MediaPlayer();
        //specify the listener for various event
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnVideoSizeChangedListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnInfoListener(this);



        //set datasource on the mediaplayer
        try {
            mediaPlayer.setDataSource(vidUrl);

        } catch (IllegalArgumentException e) {
            Log.v(LOGTAG,e.getMessage());
            finish();
            //e.printStackTrace();
        }catch (IllegalStateException e) {
            Log.v(LOGTAG,e.getMessage());
            finish();
            //e.printStackTrace();
        } catch (IOException e) {
            Log.v(LOGTAG,e.getMessage());
            finish();
            //e.printStackTrace();
        }

        currentDisplay = getWindowManager().getDefaultDisplay();


    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.v(LOGTAG, "surfaceCreated called");
        mediaPlayer.setDisplay(holder);
        mediaPlayer.prepareAsync();
//        try {
//            mediaPlayer.prepare();
//        } catch (IllegalStateException e) {
//            Log.v(LOGTAG,e.getMessage());
//            finish();
//        } catch (IOException e) {
//            Log.v(LOGTAG,e.getMessage());
//            finish();
//        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.v(LOGTAG, "surfaceChanged called");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.v(LOGTAG, "surfaceDestroyed called");
    }

    public void onCompletion(MediaPlayer mp) {
        Log.v(LOGTAG, "onCompletion called");
        finish();
    }

    //media has enter the prepare state and ready to play
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.v(LOGTAG, "onPrepared called");
        surfaceHolder.setFixedSize(videoWidth, videoHeight);

        //set the size
        //videoWidth = mediaPlayer.getVideoWidth();
        //videoHeight = mediaPlayer.getVideoHeight();

        //if the video size is greater than displayed
//       if (videoWidth > currentDisplay.getSize(); || videoHeight > currentDisplay.getSize();) {
//            float heightRatio = (float)videoHeight/(float)currentDisplay.getHeight();
//            float widthRatio = (float)videoWidth/(float)currentDisplay.getWidth();
//
//            if (heightRatio > 1 || widthRatio > 1) {
//                //use whichever ratio is bigger and set vheight and vweight by dividng the video size by larger ratio
//                if (heightRatio > widthRatio) {
//                    videoHeight = (int)Math.ceil((float)videoHeight/(float)heightRatio);
//                    videoWidth = (int)Math.ceil((float)/videoWidth/(float)heightRatio);
//                }else{
//                    videoHeight = (int)Math.ceil((float)videoHeight/(float)widthRatio);
//                    videoWidth = (int)Math.ceil((float)videoWidth/(float)widthRatio);
//                }
//            }
//        }

        surfaceView.setLayoutParams(new LinearLayout.LayoutParams(videoWidth, videoHeight));

        mediaPlayer.start();

    }

    //seek listener
    public void onSeekComplete (MediaPlayer mediaPlayer) {
        Log.v(LOGTAG, "onSeekComplete called");
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        Log.v(LOGTAG, "onVideoSizeChanged called");
        videoWidth = width;
        videoHeight = height;
        Toast.makeText(getApplicationContext(),
                String.valueOf(videoWidth) + "x" + String.valueOf(videoHeight),
                Toast.LENGTH_SHORT).show();

        if (mediaPlayer.isPlaying()){
            surfaceHolder.setFixedSize(videoWidth, videoHeight);
        }
    }


    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
//        Log.v(LOGTAG,"onError Called");
//        if (whatError == MediaPlayer.MEDIA_ERROR_SERVER_DIED) {
//            Log.v(LOGTAG,"mediaS");
//            finish();
//        }
        return false;
    }
}
