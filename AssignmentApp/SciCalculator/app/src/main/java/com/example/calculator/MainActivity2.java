package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        ArrayList<String> history=new ArrayList<String>();
        try{
            history = (ArrayList<String>) getIntent().getSerializableExtra("history");
        }catch (Exception e)
        {
            Log.i("DUCK",""+e);
        }
        int n = history.size();

        ArrayList<HistoryItem> calcList = new ArrayList<>();

        for (int i=0; i<n; i++) {
            calcList.add(new HistoryItem(history.get(i)));
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        HistAdaptor mAdapter = new HistAdaptor(calcList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}