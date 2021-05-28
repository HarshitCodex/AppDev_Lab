package com.android.menuspapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView showTv;
    EditText nameEt, emailEt,mobEt;
    Button showBtn,saveBtn;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTv = (TextView)findViewById(R.id.tvShow);
        nameEt=(EditText)findViewById(R.id.etName);
        emailEt=(EditText)findViewById(R.id.etEmail);
        mobEt=(EditText)findViewById(R.id.etMob);
        showBtn=(Button)findViewById(R.id.btnShow);
        saveBtn=(Button)findViewById(R.id.btnSave);
        sharedPref=this.getPreferences(Context.MODE_PRIVATE);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });

    }

    private void saveInfo() {
        String name = nameEt.getText().toString();
        String email = emailEt.getText().toString();
        String mob = mobEt.getText().toString();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Name",name);
        editor.putString("Email",email);
        editor.putString("Mobile",mob);
        editor.apply();
        Toast.makeText(this,"Info Saved",Toast.LENGTH_SHORT).show();
    }

    private void showInfo() {
        String name = sharedPref.getString("Name","nil");
        String email = sharedPref.getString("Email","nil");
        String mob = sharedPref.getString("Mobile","nil");
        showTv.setText("Name: "+name+"\nEmail:"+email+"\nMobile: "+mob);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitems,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.itMyapp: openmyapp();
                break;
            case R.id.itSelfie:openselfie();
                break;
            case R.id.itExit:quitapp();
                break;
            case R.id.itControl:goControls();
                break;
            case R.id.itRingtone:goRingtone();

        }
        return super.onOptionsItemSelected(item);
    }

    private void goRingtone() {
        Intent iii = new Intent(getApplicationContext() , Preference.class);
        startActivity(iii);
    }

    private void goControls() {
        Intent intcont = new Intent(this,ControlActivity.class);
        startActivity(intcont);
    }

    private void openselfie() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra("android.intent.extras.CAMERA_FACING", 1);
        startActivityForResult(i,100);
    }

    private void quitapp() {
        finish();
        this.finishAffinity();
    }

    private void openmyapp() {
        Intent openint = getPackageManager().getLaunchIntentForPackage("com.example.fragmentlab");
        startActivity(openint);
    }
}