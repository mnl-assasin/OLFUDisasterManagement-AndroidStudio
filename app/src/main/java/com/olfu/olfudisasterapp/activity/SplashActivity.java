package com.olfu.olfudisasterapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.data.EZSharedPreferences;

public class SplashActivity extends TranslucentActivity {

    private static final int DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(runnable, DELAY);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (EZSharedPreferences.isLogin(SplashActivity.this)) {
                // USER LOG IN;
                // GET USER INFO;
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }
    };
}
