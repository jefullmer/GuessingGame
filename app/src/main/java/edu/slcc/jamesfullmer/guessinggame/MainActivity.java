package edu.slcc.jamesfullmer.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNum = 0;
    int level = 0;
    int randMax = 10;
    Random rand = new Random();
    private TextView countDownText;
    private EditText guessedText;
    private Button submitBut;



    public final static String EXTRA_MESSAGE = "something or other";

    public void submitClick(View view)
    {
        TextView levelTxt = (TextView) findViewById(R.id.LevelText);
        TextView guessLimitTxt = (TextView) findViewById(R.id.guessLimit);
        String message = "Houston we have a problem!";

        if (guessedText.length() != 0)
        {
            String guessedString = guessedText.getText().toString();
            int guessedNum = Integer.parseInt(guessedString);


            ImageView hint = (ImageView) findViewById(R.id.helpPic);

            if (guessedNum > randomNum) {
                message = "Too High!";
                hint.setImageResource(R.drawable.sign_high);
            } else if (guessedNum < randomNum) {
                message = "Too Low!";
                hint.setImageResource(R.drawable.sign_low);
            } else {
                message = "Next";
                level++;
                levelTxt.setText("Level: " + level);
                randMax += 10;
                randomNum = rand.nextInt(randMax);
                if (randomNum == 0)
                    randomNum++;
                guessLimitTxt.setText("Between 1 - " + randMax);
                hint.setImageResource(R.drawable.sign_next);
            }
        }
        else
        {
            message = "Please enter a number.";
        }
        guessedText.setText("");

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        countDownText = (TextView) findViewById(R.id.countDownLabel);
        guessedText = (EditText) findViewById(R.id.guessInput);
        submitBut = (Button) findViewById(R.id.submitBut);

        guessedText.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    submitBut.performClick();
                    return true;
                }
                return false;
            }
        });


        randomNum = rand.nextInt(randMax);
        if (randomNum == 0)
            randomNum++;
        System.out.println("random Number: " + randomNum);

        //countDownLabel.setText("test");
        final Intent intent = new Intent(this, gameOver.class);
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                countDownText.setText("Time Left: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                String message = String.valueOf(level);
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, mainMenu.class);
            startActivity(intent);
        }
        if (id == R.id.action_refresh)
        {
            recreate();
        }

        return super.onOptionsItemSelected(item);
    }
}
