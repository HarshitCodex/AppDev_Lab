package com.android.mailnotescamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    EditText headEt,noteEt;
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        headEt = (EditText)findViewById(R.id.etHead);
        noteEt=(EditText)findViewById(R.id.etNote);
        saveBtn=(Button)findViewById(R.id.btnNote);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveNote();
            }
        });
    }

    private void SaveNote() {
        String heading = headEt.getText().toString();
        String notes = noteEt.getText().toString();
        Intent noteintent = new Intent(Intent.ACTION_SEND);
        noteintent.putExtra(Intent.EXTRA_SUBJECT,heading);
        noteintent.putExtra(Intent.EXTRA_TEXT,notes);
        noteintent.setType("text/plain");
        startActivity(Intent.createChooser(noteintent,"Choose a Notes App"));
    }
}