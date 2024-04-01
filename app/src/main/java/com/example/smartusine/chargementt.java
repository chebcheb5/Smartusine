package com.example.smartusine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



public class chargementt extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargementt);
        new Handler().postDelayed(() -> {

            Intent intent = new Intent(chargementt.this, signIn.class);
            startActivity(intent);

            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}