package com.ex.countdown;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class FullscreenActivity extends AppCompatActivity {


    public static final int[] IMAGES;
    static {
        IMAGES = new int[]{

                R.drawable.c1,
                R.drawable.c2,
                R.drawable.c3,
                R.drawable.c4,
                R.drawable.c5,
                R.drawable.c6,
                R.drawable.c7,
                R.drawable.c8,
                R.drawable.c9,
                R.drawable.c10,
                R.drawable.c11


        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);


        Random random = new Random(System.currentTimeMillis());

        for(int image:IMAGES){
            imageView.setImageResource(IMAGES[random.nextInt(IMAGES.length-1)]);


        }

         new Handler().postDelayed(new MyHandler(),4000);

    }

    class MyHandler implements Runnable {
        public MyHandler() {
        }
        public void run(){
            gotoTransactionActivity();
        }
    }

    private void gotoTransactionActivity() {

        startActivity(new Intent(this,MainActivity.class));
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        super.finish();
    }
}