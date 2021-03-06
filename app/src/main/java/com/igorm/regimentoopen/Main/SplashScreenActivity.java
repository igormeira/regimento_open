package com.igorm.regimentoopen.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.igorm.regimentoopen.R;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timerThread = new Thread() {
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
