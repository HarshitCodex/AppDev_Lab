package com.android.cgpaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private StudentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<StudentItem> studentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
//        ArrayList<StudentItem> studentList = new ArrayList<>();
        DbHelper helper = new DbHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Students",null);
        while(cursor.moveToNext())
        {
            String name;
            int roll;
            double cg;
            roll = cursor.getInt(cursor.getColumnIndexOrThrow("ROLLNUMBER"));
            name = cursor.getString(cursor.getColumnIndexOrThrow("USERNAME"));
            cg = cursor.getDouble(cursor.getColumnIndexOrThrow("CGPA"));
            String info = "Name : "+name+"\nRollNumber : "+roll+"\nCGPA : "+cg;
            studentList.add(new StudentItem(info));
        }
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new StudentAdapter(studentList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.action_menu,menu);

        MenuItem item = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setQueryHint("Search using RollNumber(Id)");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<StudentItem> newstudentList = new ArrayList<>();
                for(StudentItem s:studentList)
                {
                    if(s.getTextResource().contains(newText))
                    {
                        newstudentList.add(s);
                    }
                }
                mAdapter.updateList(newstudentList);
                return false;
            }
        });
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