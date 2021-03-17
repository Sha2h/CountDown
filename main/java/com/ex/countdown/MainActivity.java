package com.ex.countdown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtDaysRemain, txtLongString;
    BackgroundSound bgSound = new BackgroundSound();


    @Override
    protected void onResume() {
        super.onResume();
        bgSound.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"montez.ttf",true);

        imageView = (ImageView)findViewById(R.id.   cakeView);
        ((AnimationDrawable)imageView.getDrawable()).start();
        txtDaysRemain = (TextView)findViewById(R.id.txtDaysRemain);
        txtLongString = (TextView)findViewById(R.id.txtLongString);

        startTimer();

    }

    private void startTimer() {
        long difference = getRemainDays();
        new CountDownTimer(difference,1000)
        {
            public void onTick(long millisUntilFinished){
               int days = (int)(millisUntilFinished/(1000*60*60*24));
               int hours = (int)((millisUntilFinished/(1000*60*60))%24);
                int mins = (int)((millisUntilFinished/(1000*60))%60);
                int sec = (int)((millisUntilFinished/(1000))%60);

                txtDaysRemain.setText(String.format("%d",days));
                txtLongString.setText(String.format("%d DAYS %02d:%02d:%02d",days,hours,mins,sec));
            }
            public void onFinish(){

            }
        }.start();
    }

    private long getRemainDays() {
        Date currentDate = new Date();
    Date endDate;
    if(currentDate.getMonth()<=6)
    {
        endDate = new Date(currentDate.getYear(),2,27);
    }
    else
    {
        endDate = new Date(currentDate.getYear()+1,2,27);
    }
    return endDate.getTime()-currentDate.getTime();
    }
class BackgroundSound extends AsyncTask<Void,Void,Void>
{

    @Override
    protected Void doInBackground(Void... voids) {
        MediaPlayer player;
        Random random = new Random(System.currentTimeMillis());
        int rd = random.nextInt(7-1)+1;
        if(rd==1)
            player= MediaPlayer.create(MainActivity.this,R.raw.aud);
         else if(rd==2)
            player= MediaPlayer.create(MainActivity.this,R.raw.aud1);
        else if(rd==3)
            player= MediaPlayer.create(MainActivity.this,R.raw.aud2);
        else
            player= MediaPlayer.create(MainActivity.this,R.raw.aud4);

        player.setLooping(true);
        player.setVolume(1.0f,1.0f);
        player.start();
        return null;

    }
}
}