package com.example.radiotvolnline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TvActivity extends AppCompatActivity {
    Button tf1,mtv,disney,koortv;
    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        tf1 = (Button) findViewById(R.id.tf1);
        mtv = (Button) findViewById(R.id.mtv);
        disney = (Button) findViewById(R.id.disney);
        koortv = (Button) findViewById(R.id.kooratv);

        tf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ortmtv1method();
            }
        });
        mtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ortmtv2method();
            }
        });
        koortv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ortmtv3method();
            }
        });
        disney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ortmtv4method();
            }
        });
    }
    public void ortmtv1method() {
        Intent intent = new Intent(TvActivity.this,Webview.class);
        intent.putExtra("links","https://www.tf1.fr/");
        startActivity(intent);
    }
    public void ortmtv2method() {
        Intent intent = new Intent(TvActivity.this,Webview.class);
        intent.putExtra("links","https://www.mtv.com");
        startActivity(intent);
    }
    public void ortmtv3method() {
        Intent intent = new Intent(TvActivity.this,Webview.class);
        intent.putExtra("links","https://cool.live-kooora-tv.com/");
        startActivity(intent);
    }
    public void ortmtv4method() {
        Intent intent = new Intent(TvActivity.this,Webview.class);
        intent.putExtra("links","https://www.disney.fr/");
        startActivity(intent);
    }
}