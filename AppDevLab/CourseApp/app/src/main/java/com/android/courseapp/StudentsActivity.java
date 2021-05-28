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
import android.widget.TextView;
import android.widget.Toast;

public class StudentsActivity extends AppCompatActivity {

    String uname;
    TextView studTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Bundle bundle = getIntent().getExtras();
        uname = bundle.getString("Username");
        studTv = (TextView)findViewById(R.id.tvStud);
        DbHelper helper = new DbHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM Students WHERE USERNAME="+uname,null);
        cursor.moveToFirst();
        int OSval = 0,DSval=0,ALval=0,APPval=0;
        OSval = cursor.getInt(cursor.getColumnIndexOrThrow("OS"));
        DSval = cursor.getInt(
                cursor.getColumnIndexOrThrow("DS"));
        ALval = cursor.getInt(
                cursor.getColumnIndexOrThrow("AL"));
        APPval = cursor.getInt(
                cursor.getColumnIndexOrThrow("APP"));

        Cursor cursor2 = database.rawQuery("SELECT * FROM Students WHERE OS="+OSval+" AND DS="+DSval+" AND AL="+ALval+" AND APP="+APPval,null);
        String res="Students with the Same Courses : \n";
        int cnt=1;
        while(cursor2.moveToNext())
        {
            String studName;
            studName = cursor2.getString(
                    cursor2.getColumnIndexOrThrow("USERNAME"));
            res+=studName+"\n";
            cnt++;
        }
        studTv.setText(res);
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