package com.challenge.learningapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class sec_act extends AppCompatActivity {
    TextView t1,t2;
    String s1,s2;
    public static String pathToPhoto;
    Bitmap photo;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        s1=getIntent().getStringExtra(MainActivity.TV_KEY);
        s2=getIntent().getStringExtra(MainActivity.TV2_KEY);
        t1=findViewById(R.id.tv1);
        t2=findViewById(R.id.tv2);
        t1.setText(s1);
        t2.setText(s2);
        findViewById(R.id.saveImage).setOnClickListener(v->{
           // saveImageToGallery();
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("path",pathToPhoto); // Storing string
            editor.commit();
            startActivity(new Intent(this,ImageAct.class));
        });
        findViewById(R.id.saveImgInter).setOnClickListener(vi->{
            saveImageToDisk();
        });
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 66);
        }
        else
        {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 66);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 66)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 66);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
            if (grantResults[1]==PackageManager.PERMISSION_GRANTED&&grantResults[2]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Mill Gaye Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 66 && resultCode == Activity.RESULT_OK) {
          photo = (Bitmap) data.getExtras().get("data");

         //   imageView.setImageBitmap(photo);
        }
    }
    void saveImageToGallery(){
        String path = Environment.getExternalStorageDirectory().toString()+"/My Learning/";
        OutputStream fOut = null;
        int counter = 0;
        File file = new File(path, "MyRoom.jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap pictureBitmap = photo; // obtaining the Bitmap
        pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
        try {
            fOut.flush();
            fOut.close();
            Toast.makeText(this, "Image Saved In Gallery", Toast.LENGTH_SHORT).show();// Not really required
        } catch (IOException e) {
            e.printStackTrace();
        }
        // do not forget to close the stream
        //TODO DO this
    }
    void saveImageToDisk(){
        String path = sec_act.this.getFilesDir().toString();
        OutputStream fOut = null;
        int counter = 0;
        File file = new File(path, "MyRoom.jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap pictureBitmap = photo; // obtaining the Bitmap
         // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
        try {
            pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush(); // Not really required
            fOut.close();
            Toast.makeText(this, "Image Saved In Disk", Toast.LENGTH_SHORT).show();
            pathToPhoto=path+"/MyRoom.jpg";
        } catch (IOException e) {
            e.printStackTrace();
        }

         // do not forget to close the stream

    }
}