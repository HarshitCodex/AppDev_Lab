package com.android.courseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class CoursesActivity extends AppCompatActivity {

    CheckBox osCB,dsCB,alCB,appCB;
    Button submitBtn;
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        osCB = (CheckBox)findViewById(R.id.cbOS);
        dsCB = (CheckBox)findViewById(R.id.cbOS);
        alCB = (CheckBox)findViewById(R.id.cbAL);
        appCB = (CheckBox)findViewById(R.id.cbAPP);
        submitBtn=(Button)findViewById(R.id.btnSubmit);

        Bundle bundle = getIntent().getExtras();
        uname = bundle.getString("Username");

        DbHelper helper = new DbHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            int OSupd=0,DSupd = 0,ALupd=0,APPupd=0;
            int cnt=0;
            @Override
            public void onClick(View v) {
                if(osCB.isChecked())
                {
                    OSupd=1;
                    cnt++;
                }
                if(dsCB.isChecked())
                {
                    DSupd=1;
                    cnt++;
                }
                if(alCB.isChecked())
                {
                    ALupd=1;
                    cnt++;
                }
                if(appCB.isChecked())
                {
                    APPupd=1;
                    cnt++;
                }
                if(cnt>=1) {
                    database.execSQL("UPDATE Students SET OS=" + OSupd + " ,DS=" + DSupd + " ,AL=" + ALupd + " ,APP=" + APPupd + " ,REG=1 WHERE USERNAME =" + uname);
//                    ContentValues values = new ContentValues();
//                    values.put("DS",DSupd);
//                    values.put("OS",OSupd);
//                    values.put("AL",ALupd);
//                    values.put("APP",APPupd);
//                    database.update("Student")
                    Log.i("DUCK","Executed SQL");
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ShowCoursesActivity.class);
                    intent.putExtra("Username",uname);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Select atleast 1 course", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentLogin);
        Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_LONG).show();
        finish();
//        clearAll();
    }

    private void quitApp() {
        finish();
        this.finishAffinity();
    }
}