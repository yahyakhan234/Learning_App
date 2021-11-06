package com.challenge.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context context;
    TextView tv,tv2;
    boolean isPaused=false;
    String[] myStrings=new String[2];
    public static final String TV_KEY="tv1";
    public static final String TV2_KEY="tv2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        AppCompatActivity compatActivity=this;
        getApplicationContext();
        tv=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        findViewById(R.id.bt2).setOnClickListener(vi->{
            startActivity(new Intent(this, ImageAct.class));
        });
        findViewById(R.id.button).setOnClickListener(v -> {
            myStrings[0]=tv.getText().toString();
            myStrings[1]=tv2.getText().toString();
            startActivity(new Intent(MainActivity.this,sec_act.class).putExtra(TV_KEY,myStrings[0]).putExtra(TV2_KEY,myStrings[1]));

        });


        }

        protected void onStart() {
            super.onStart();
            Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show();
        }

        protected void onResume() {
            super.onResume();
            Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show();
        }

        protected void onPause() {

            isPaused=true;
            super.onPause();
            Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show();
        }

        protected void onStop() {
            super.onStop();
            Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show();
        }
        protected void onDestroy() {
            super.onDestroy();
            Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
        }

    }