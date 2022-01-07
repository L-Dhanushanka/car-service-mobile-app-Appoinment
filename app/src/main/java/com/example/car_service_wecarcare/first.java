package com.example.car_service_wecarcare;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

    public class first extends AppCompatActivity {

        private final int SPLASH_DISPLAY_LENGTH = 6000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.firstpg);

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(first.this,home.class);
                    first.this.startActivity(mainIntent);
                    first.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }

