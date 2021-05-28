package com.android.cgpaapp;

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

public class EditStudent extends AppCompatActivity {

    EditText rollEt,nameEt,cgEt;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        rollEt = (EditText)findViewById(R.id.etRollE);
        nameEt=(EditText)findViewById(R.id.etNameE);
        cgEt=(EditText)findViewById(R.id.etCGE);
        addBtn = (Button)findViewById(R.id.btnEdit);
        DbHelper helper=new DbHelper(this);
        SQLiteDatabase database=helper.getWritableDatabase();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sroll = rollEt.getText().toString();
                String sname = nameEt.getText().toString();
                String scg = cgEt.getText().toString();
                if(sroll.length()==0||sname.length()==0||scg.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please Enter All Fields",Toast.LENGTH_LONG).show();
                    return;
                }
                Cursor cursor = database.rawQuery("SELECT * FROM Students WHERE ROLLNUMBER="+sroll,null);
                if(cursor.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Valid RollNumber",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    helper.updateDetails(sroll,sname,scg);
                    Toast.makeText(getApplicationContext(),"Updated!",Toast.LENGTH_LONG).show();
                    rollEt.setText("");
                    nameEt.setText("");
                    cgEt.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itDisp:
                dispAll();
                break;
            case R.id.itEdit:
                editStudent();
                break;
            case R.id.itHome:
                goHome();
                break;
            case R.id.itSearch:
                Toast.makeText(getApplicationContext(),"Not Available Here",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void dispAll() {
        Intent dispintent = new Intent(getApplicationContext(),StudentActivity.class);
        startActivity(dispintent);
    }

    private void editStudent() {
        Intent edintent = new Intent(getApplicationContext(),EditStudent.class);
        startActivity(edintent);
    }
}