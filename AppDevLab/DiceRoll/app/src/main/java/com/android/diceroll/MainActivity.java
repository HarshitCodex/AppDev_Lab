package com.android.diceroll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rnd = new Random();
    TextView p1ScoreText ,p2ScoreText;
    int p1score=0,p2score=0;
    int turn=1;
    Button rollBtn,resetBtn;
    ImageView diePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diePic = (ImageView)findViewById(R.id.imgDice);
        p1ScoreText=(TextView)findViewById(R.id.tvp1);
        p2ScoreText=(TextView)findViewById(R.id.tvp2);
        rollBtn=(Button)findViewById((R.id.btnRoll));
        rollBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                diceRotate();
            }
        });
        resetBtn=(Button)findViewById(R.id.btnReset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });
    }

    private void resetAll() {
        p1score=0;
        p2score=0;
        p1ScoreText.setText("P1 = 0");
        p2ScoreText.setText("P2 = 0");
        diePic.setImageResource(R.drawable.fulldice);
        rollBtn.setText("P1 Roll");
        rollBtn.setEnabled(true);

    }

    private void diceRotate() {
        int num = rnd.nextInt(6)+1;
        Animation rolling = AnimationUtils.loadAnimation(this,R.anim.rotate);
        diePic.setImageResource(R.drawable.fulldice);
        rolling.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                diePic.setImageResource(R.drawable.fulldice);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch (num){
                    case 1:
                        diePic.setImageResource(R.drawable.die_1);
                        break;
                    case 2:
                        diePic.setImageResource(R.drawable.die_2);
                        break;
                    case 3:
                        diePic.setImageResource(R.drawable.die_3);
                        break;
                    case 4:
                        diePic.setImageResource(R.drawable.die_4);
                        break;
                    case 5:
                        diePic.setImageResource(R.drawable.die_5);
                        break;
                    case 6:
                        diePic.setImageResource(R.drawable.die_6);
                        break;
                }
                if(turn==1)
                {
                    p1score+=num;
                    String p1text = "P1 = "+Integer.toString(p1score);
                    p1ScoreText.setText(p1text);
                    turn*=-1;
                    rollBtn.setText("P2 Roll");
                    if(p1score>=25)
                    {
                        Toast.makeText(MainActivity.this, "Player 1 Won with score "+p1score, Toast.LENGTH_SHORT).show();
                        rollBtn.setText("Press Reset");
                        rollBtn.setEnabled(false);
                    }
                }
                else if(turn==-1)
                {
                    p2score+=num;
                    String p2text = "P2 = "+Integer.toString(p2score);
                    p2ScoreText.setText(p2text);
                    turn*=-1;
                    rollBtn.setText("P1 Roll");
                    if(p2score>=25)
                    {
                        Toast.makeText(MainActivity.this, "Player 2 Won with score "+p2score, Toast.LENGTH_SHORT).show();
                        rollBtn.setText("Press Reset");
                        rollBtn.setEnabled(false);
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        diePic.startAnimation((rolling));
        

    }
}