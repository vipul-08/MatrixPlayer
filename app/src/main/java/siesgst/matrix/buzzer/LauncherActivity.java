package siesgst.matrix.buzzer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class LauncherActivity extends AppCompatActivity {

    ImageView launcherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        launcherImage = findViewById(R.id.launcherImage);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launcherImage.setImageResource(R.drawable.impre);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LauncherActivity.this,MainActivity.class));
                    }
                },
                1000);
            }
        }, 1000);
    }
}