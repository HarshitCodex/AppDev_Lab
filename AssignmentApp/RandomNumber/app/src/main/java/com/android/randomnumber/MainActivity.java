package com.android.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView resTv;
    Button randBtn;
    EditText lnumEt,rnumEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resTv = (TextView)findViewById(R.id.tvRes);
        lnumEt = (EditText)findViewById(R.id.etLnum);
        rnumEt=(EditText)findViewById(R.id.etRnum);
        randBtn=(Button)findViewById(R.id.btnRand);
        randBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sl = lnumEt.getText().toString();
                String sr = rnumEt.getText().toString();
                Log.i("DUCK","Range is "+sl+" to "+sr);
                if(sl.length()==0)
                {
                    Log.i("DUCK",sl);
                    sl="0";
                    lnumEt.setText(sl);
                }
                if(sr.length()==0)
                {
                    sr="100";
                    rnumEt.setText(sr);
                }
                Log.i("DUCK","Range is "+sl+" to "+sr);
                int lnum = Integer.valueOf(sl);
                int rnum = Integer.valueOf(sr);
                if(rnum<lnum)
                {
                    Toast.makeText(getApplicationContext(),"Invalid Range",Toast.LENGTH_SHORT).show();
                    return;
                }
                Random rand = new Random();
                int resval = lnum + rand.nextInt(rnum-lnum+1);
                resTv.setText(""+resval);
            }
        });
    }
}