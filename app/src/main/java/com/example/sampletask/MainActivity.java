package com.example.sampletask;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView animatedImage;
    private Button btnsubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animatedImage = findViewById(R.id.img_icon);
        btnsubmit = findViewById(R.id.btn_sumbit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsubmit.setEnabled(false);
                Random r = new Random();
                int random = r.nextInt(10 - 2) + 2;
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute(String.valueOf(random));
                Toast.makeText(MainActivity.this,"Please wait " +random + "  Seconds ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startAnimation() {
        Animation rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
        rotation.setFillAfter(true);
        animatedImage.startAnimation(rotation);
    }

    private void stopAnimation() {
        animatedImage.clearAnimation();
    }


    @SuppressLint("StaticFieldLeak")
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String resp;
            try {
                int time = Integer.parseInt(params[0]) * 1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String result) {
            stopAnimation();
            btnsubmit.setEnabled(true);
        }
        @Override
        protected void onPreExecute() {
            startAnimation();
        }

        @Override
        protected void onProgressUpdate(String... text) {
        }
    }

}
