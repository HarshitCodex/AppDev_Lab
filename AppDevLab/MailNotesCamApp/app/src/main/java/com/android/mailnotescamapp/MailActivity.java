package com.android.mailnotescamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MailActivity extends AppCompatActivity {

    EditText toEt,subEt,bodyEt;
    Button sendmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        toEt = (EditText)findViewById(R.id.etMail);
        subEt=(EditText)findViewById(R.id.etSubject);
        bodyEt=(EditText)findViewById(R.id.etBody);
        sendmail=(Button)findViewById(R.id.btnMail);
        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMail();
            }
        });
    }

    private void SendMail() {
        String recv = toEt.getText().toString();
        String sub = subEt.getText().toString();
        String body = bodyEt.getText().toString();
        Intent mail = new Intent(Intent.ACTION_SEND);
        mail.setData(Uri.parse("mailto:"));
        mail.putExtra(Intent.EXTRA_EMAIL,new String[]{recv});
        mail.putExtra(Intent.EXTRA_SUBJECT, sub);
        mail.putExtra(Intent.EXTRA_TEXT, body);
        mail.setType("message/rfc822");
        startActivity(Intent.createChooser(mail, "Choose an Email client :"));
    }
}