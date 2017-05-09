package edu.slcc.jamesfullmer.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Runnable waitTime = new Runnable()
        {
            public void run()
            {
                nextActivity();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(waitTime, 3500);


    }

    public void nextActivity()
    {
        Intent intent = new Intent(this, mainMenu.class);
        startActivity(intent);
    }

}
