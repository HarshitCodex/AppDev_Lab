package com.android.courseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PassChangeActivity extends AppCompatActivity {
    EditText newpassET,oldpassEt;
    String uname;
    Button changeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_change);
        Bundle bundle = getIntent().getExtras();
        uname = bundle.getString("Username");

        DbHelper helper = new DbHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        oldpassEt=(EditText)findViewById(R.id.etOldPass);
        newpassET = (EditText)findViewById(R.id.etNewPass);
        changeBtn = (Button)findViewById(R.id.btnChange);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpass = oldpassEt.getText().toString();
                Cursor cursor = database.rawQuery("SELECT * FROM Students WHERE USERNAME="+uname+" AND PASSWORD='"+oldpass+"'",null);
                String newpass = newpassET.getText().toString();
                if(cursor.getCount()>0) {
                    if (newpass.length() > 0) {
                        database.execSQL("UPDATE Students SET PASSWORD = '" + newpass + "' WHERE USERNAME = " + uname);
                        Toast.makeText(getApplicationContext(), "Password Changed Successfully! Please Login Again!", Toast.LENGTH_SHORT).show();
                        logoutUser();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Enter New Password", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Password! Please Enter Again!", Toast.LENGTH_SHORT).show();
                    clearAll();
                }
            }
        });

    }

    private void clearAll() {
        oldpassEt.setText("");
        newpassET.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itLogout:
                logoutUser();
                break;
            case R.id.itChngPass:
                changePass();
                break;
            case R.id.itExit:
                quitApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changePass() {
        Intent intentPass = new Intent(this,PassChangeActivity.class);
        intentPass.putExtra("Username",uname);
        startActivity(intentPass);
    }

    private void logoutUser() {
        Intent intentLogin = new Intent(this,MainActivity.class);
        startActivity(intentLogin);
        intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_LONG).show();
        finish();
//        clearAll();
    }

    private void quitApp() {
        finish();
        this.finishAffinity();
    }
}