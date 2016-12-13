package com.adaldosso.spendykt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import static com.adaldosso.spendykt.login.LoginActivity.SESSION_ID_KEY;
import static com.adaldosso.spendykt.login.LoginActivity.SPENDY_PREFERENCES_NAME;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                start();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    private void start() {
        SharedPreferences prefs = getSharedPreferences(SPENDY_PREFERENCES_NAME, MODE_PRIVATE);
        String sessionID = prefs.getString(SESSION_ID_KEY, null);
        if (sessionID != null) {
            doLogin();
        }
        else {
            doMain();
        }
    }

    private void doMain() {
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }

    private void doLogin() {
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

}
