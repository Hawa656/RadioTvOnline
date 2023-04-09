package com.example.radiotvolnline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RadioActivity2 extends AppCompatActivity {
    private Button btn;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio2);
        btn = (Button) findViewById(R.id.radio);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        progressDialog = new ProgressDialog(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playPause){
                    btn.setText("pause");
                    if (initialStage){
                        new Player().execute("http://radio.net.pk/play/suno-urdu-live/");
                    }else {
                        if (!mediaPlayer.isPlaying())
                            mediaPlayer.start();
                    }
                    playPause = true;
                }else {
                    btn.setText("play");
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }
                    playPause = false;

                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer !=null){
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
    class Player extends AsyncTask<String,Void, Boolean>{
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean prepared = false;
            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        initialStage=true;
                        playPause = false;
                        btn.setText("LAuncher streaming");
                        mediaPlayer.stop();
                        mediaPlayer.reset();

                    }
                });
                mediaPlayer.prepare();
                prepared=true;

            }catch (Exception e){
                Log.e("MonAudio", e.getMessage());
                prepared = false;
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (progressDialog.isShowing()){
                progressDialog.cancel();
            }
            mediaPlayer.start();
            initialStage = false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Buffering...");
            progressDialog.show();
        }
    }
}