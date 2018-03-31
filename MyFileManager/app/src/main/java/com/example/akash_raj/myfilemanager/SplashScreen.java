package com.example.akash_raj.myfilemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by akash on 7/4/17.
 */

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        Thread thread=new Thread(){
            @Override
            public void start() {
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    Log.d("Sleeping",e.toString());
                }
                finally {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        };
        thread.run();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
