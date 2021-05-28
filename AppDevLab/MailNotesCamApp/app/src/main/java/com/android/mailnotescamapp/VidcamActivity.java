package com.android.mailnotescamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class VidcamActivity extends AppCompatActivity {

    Button vidBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidcam);
        vidBtn = (Button)findViewById(R.id.btnCam);
        vidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCamera();
            }
        });
    }

    private void OpenCamera() {
        Intent vidintent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivity(vidintent);
    }
}