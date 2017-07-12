package com.marvin.splashedink.ui.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;
import com.marvin.splashedink.R;
import com.marvin.splashedink.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SimpleDraweeView splash_image = (SimpleDraweeView) findViewById(R.id.splash_image);
        splash_image.setImageResource(R.drawable.splash);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
