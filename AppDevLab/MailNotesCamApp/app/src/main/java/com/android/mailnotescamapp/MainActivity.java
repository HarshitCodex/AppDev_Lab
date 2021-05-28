package com.android.mailnotescamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button goBtn;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Welcome to Applications Page", Toast.LENGTH_LONG).show();
        rg = findViewById(R.id.rgApps);
        goBtn = (Button)findViewById(R.id.btnGo);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDesiredActvity();
            }
        });
    }

    private void OpenDesiredActvity() {
        int id = rg.getCheckedRadioButtonId();
        switch (id){
            case R.id.rbMail:
                Intent maili = new Intent(this, MailActivity.class);
                try
                {
                    startActivity(maili);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case R.id.rbNote:
                Intent notei = new Intent(this, NoteActivity.class);

                try {
                    startActivity(notei);
                } catch (SecurityException s)
                {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case R.id.rbVid:
                Intent vidi = new Intent(this, VidcamActivity.class);
                try {
                    startActivity(vidi);
                } catch (SecurityException s)
                {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }
                break;
        }
    }
}