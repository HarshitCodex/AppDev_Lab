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
import android.widget.TextView;
import android.widget.Toast;

public class ShowCoursesActivity extends AppCompatActivity {

    String uname;
    TextView showTv;
    Button studBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_courses);
        Bundle bundle = getIntent().getExtras();
        uname = bundle.getString("Username");

        DbHelper helper = new DbHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        showTv = (TextView)findViewById(R.id.tvShow);
        studBtn = (Button)findViewById(R.id.btnStud);
        Cursor cursor = database.rawQuery("SELECT * FROM Students WHERE USERNAME="+uname,null);
        cursor.moveToFirst();
        String res = "Your Courses are : \n";
        int cnt=1;
        int OSval = 0,DSval=0,ALval=0,APPval=0;
        OSval = cursor.getInt(cursor.getColumnIndexOrThrow("OS"));
        DSval = cursor.getInt(
                cursor.getColumnIndexOrThrow("DS"));
        ALval = cursor.getInt(
                cursor.getColumnIndexOrThrow("AL"));
        APPval = cursor.getInt(
                cursor.getColumnIndexOrThrow("APP"));
        if(OSval==1)
        {
            res+=cnt + ". Operating Systems\n";
            cnt++;
        }
        if(DSval==1)
        {
            res+=cnt + ". Data Structures\n";
            cnt++;
        }
        if(ALval==1)
        {
            res+=cnt + ". Algorithms\n";
            cnt++;
        }
        if(APPval==1)
        {
            res+=cnt + ". App Development\n";
            cnt++;
        }
        showTv.setText(res);
        studBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoStudents();
            }
        });
    }

    private void gotoStudents() {
            Intent intentStud = new Intent(this,StudentsActivity.class);
            intentStud.putExtra("Username",uname);
            startActivity(intentStud);
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