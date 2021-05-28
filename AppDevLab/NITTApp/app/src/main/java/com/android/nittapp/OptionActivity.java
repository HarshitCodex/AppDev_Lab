package com.android.nittapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.security.cert.PKIXRevocationChecker;

public class OptionActivity extends AppCompatActivity {

    Button goBtn,backBtn;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Toast.makeText(OptionActivity.this, "Welcome to Services Page", Toast.LENGTH_LONG).show();
        rg = findViewById(R.id.rgServices);
        goBtn = (Button)findViewById(R.id.btnGo);
        backBtn = (Button)findViewById(R.id.btnBack);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDesiredIntent();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMainActivity();
            }
        });
    }

    private void OpenMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void OpenDesiredIntent() {
        int id = rg.getCheckedRadioButtonId();
        switch (id){
            case R.id.rbCall:
                Uri u = Uri.parse("tel:04312503000");
                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case R.id.rbYt:
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/channel/UCEPOEe5azp3FbUjvMwttPqw"));
                try {
                    startActivity(webIntent);
                } catch (SecurityException s)
                {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case R.id.rbWeb:
                Intent webNitt= new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.nitt.edu"));
                try {
                    startActivity(webNitt);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }
                break;
        }
    }
}