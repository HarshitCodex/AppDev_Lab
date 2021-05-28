package com.android.courseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText unameEt,passEt;
    Button loginBtn;
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unameEt = (EditText)findViewById(R.id.etUName);
        passEt = (EditText)findViewById(R.id.etPass);
        loginBtn = (Button)findViewById(R.id.btnLogin);
        DbHelper helper = new DbHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unameinp = unameEt.getText().toString();
                String passinp = passEt.getText().toString();
                Cursor cursor = database.rawQuery("SELECT * FROM Students WHERE USERNAME="+unameinp+" AND PASSWORD='"+passinp+"'",null);
                Log.i("DUCK",cursor.getCount()+"");
                if(cursor.getCount()!=0)
                {
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    cursor.moveToFirst();
                    uname = unameinp;
                    int regBool = cursor.getInt(
                            cursor.getColumnIndexOrThrow("REG"));
                    if(regBool==0)
                    {
                        gotoCourses(uname);
                    }
                    else
                    {
                        gotoShow(uname);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Credentials",Toast.LENGTH_LONG).show();
                }
            }
        });


//        Cursor cursor = database.rawQuery("SELECT * FROM Students WHERE PASSWORD='Harshit'",null);
//        while(cursor.moveToNext()) {
//            String uname = cursor.getString(cursor.getColumnIndexOrThrow("USERNAME"));
//            Toast.makeText(getApplicationContext(), uname,Toast.LENGTH_SHORT).show();
//        }
    }

    private void gotoShow(String uname) {
        Intent intentShow = new Intent(this,ShowCoursesActivity.class);
        intentShow.putExtra("Username",uname);
        startActivity(intentShow);
    }

    private void gotoCourses(String uname) {
        Intent intentCourse = new Intent(this,CoursesActivity.class);
        intentCourse.putExtra("Username",uname);
        startActivity(intentCourse);
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
        clearAll();
    }

    private void clearAll() {
        unameEt.setText("");
        passEt.setText("");
    }

    private void quitApp() {
        finish();
        this.finishAffinity();
    }
}