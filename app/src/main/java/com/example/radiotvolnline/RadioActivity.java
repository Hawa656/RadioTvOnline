package com.example.radiotvolnline;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class RadioActivity extends AppCompatActivity {
    Button Play;
    String stream = "http://radio.net.pk/play/suno-urdu-live/";
    MediaPlayer mediaPlayer;
    boolean prepared = false;
    boolean started = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        Play = (Button) findViewById(R.id.radio);
        Play.setEnabled(false);
        Play.setText("Laoding Please wait... ");

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        new PlayTask().execute(stream);

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (started){
                    started=false;
                    mediaPlayer.start();
                    Play.setText("PLAY");
                }else {
                    started = true;
                    mediaPlayer.start();
                    Play.setText("PAUSE");
                }
            }
        });
    }
    private class PlayTask extends AsyncTask<String,Void,Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try{
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
            } catch (IOException e){
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Play.setEnabled(true);
            Play.setText("PLAY");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (started){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (started){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prepared){
            mediaPlayer.release();
        }
    }
}