package com.example.radiotvolnline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button radiOnline,TvOnline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiOnline = (Button) findViewById(R.id.buttonRadio);
        TvOnline = (Button) findViewById(R.id.buttonTv);


        radiOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radiOnlinemethod();
            }
        });
        TvOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TvOnlinemethod();
            }
        });
    }

    public  void radiOnlinemethod(){
        Intent intent = new Intent(MainActivity.this,RadioActivity2.class);

        startActivity(intent);
    }
    public  void TvOnlinemethod(){
        Intent intent = new Intent(MainActivity.this,TvActivity.class);
        startActivity(intent);
    }
}