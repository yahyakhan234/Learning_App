package com.challenge.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImageAct extends AppCompatActivity {

    String str;
    TextView tv;
    int c=0;
    ArrayList<String> myStringLists=new ArrayList<>();
    Map<String,Object> myMap=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        EditText myEditText;
        myEditText =findViewById(R.id.enterTextET);
        LinearLayout l1=findViewById(R.id.AddTVLinearLayout);
        findViewById(R.id.addtextButton).setOnClickListener(v->{
            str=myEditText.getText().toString();
            myMap.put("item"+c,str);
            View child = getLayoutInflater().inflate(R.layout.textviewmine, null);
            l1.addView(child);
            tv=child.findViewById(R.id.textViewInflated);
            tv.setText(str);
            tv.setId(666+c);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setVisibility(View.GONE);
                }
            });
            c++;
        });




        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String st= pref.getString("path", null); // getting String
        Bitmap bitmap = BitmapFactory.decodeFile(st);
        ImageView iv=findViewById(R.id.myImage);
        iv.setImageBitmap(bitmap);
    }
}