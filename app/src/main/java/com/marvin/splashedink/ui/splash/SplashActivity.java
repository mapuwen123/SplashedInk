package com.marvin.splashedink.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.marvin.splashedink.R;
import com.marvin.splashedink.ui.kotlin.KotlinActivity;
import com.marvin.splashedink.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView splash_image = (ImageView) findViewById(R.id.splash_image);
        Glide.with(this)
                .load(R.drawable.splash)
                .into(splash_image);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
//            Intent intent = new Intent(this, KotlinActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
