package com.android.menuspapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActionBar;
import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class ControlActivity extends AppCompatActivity {
    Switch stbarSw,darkSw;
    Context context;
    int brightness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
//        try{
//        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
//        intent.setData(Uri.parse("package:" + this.getPackageName()));
//        startActivity(intent);}catch(Exception e){
//            Log.i("FUCK",e.getMessage());
//        }
        stbarSw = (Switch)findViewById(R.id.swStBar);
        darkSw=(Switch)findViewById(R.id.swDark);
        stbarSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleStbar(isChecked);
            }
        });
        darkSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleDarkMode(isChecked);
            }
        });

//        try {
//            changeBrightness();
//        }catch(Exception e) {
//            Log.i("FUCK",e.getMessage());
//        }
        SeekBar seekBar = findViewById(R.id.sbBrt);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        seekBar.setProgress((int) (layout.screenBrightness*255));
    }
//    private void changeBrightness() {
//        int brightness = 200;
//        Settings.System.putInt(this.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,brightness);
//    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int val = seekBar.getProgress();
            if(checkSystemWritePermission()) {
                ContentResolver cResolver = getApplicationContext().getContentResolver();
                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, val);
            }

        }
    };
    private boolean checkSystemWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(Settings.System.canWrite(getApplicationContext()))
                return true;
            else
                openAndroidPermissionsMenu();
        }
        return false;
    }

    private void openAndroidPermissionsMenu() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + this.getPackageName()));
            startActivity(intent);
        }
    }
    private void toggleDarkMode(boolean isChecked) {
        if(isChecked)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


    private void toggleStbar(boolean isChecked) {

// Hide the status bar.
        if(isChecked) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
}