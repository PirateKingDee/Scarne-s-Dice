package com.googleappliedandroid.scarnedice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button roll;
    private Button hold;
    private Button reset;
    private TextView userScore;
    private TextView computerScore;
    private TextView userScore_tv;
    private TextView computerScore_tv;
    private ImageView dicePicture;
    private TextView userTurnScore_tv;
    private TextView computerTurnScore_tv;

    private int userOverallScore;
    private int userTurnScore;
    private int computerOverallScore;
    private int computerTurnScore;
    private Random rand;
    private Handler timerHandle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dicePicture = (ImageView)findViewById(R.id.dicePicture);
        roll = (Button)findViewById(R.id.roll_button);
        hold = (Button)findViewById(R.id.hold_button);
        reset = (Button)findViewById(R.id.reset_button);
        userScore = (TextView)findViewById(R.id.your_score);
        computerScore = (TextView)findViewById(R.id.computer_score);
        computerScore_tv = (TextView)findViewById(R.id.computer_score_text);
        userScore_tv = (TextView)findViewById(R.id.your_score_text);
        userTurnScore_tv = (TextView)findViewById(R.id.user_turn_score);
        computerTurnScore_tv = (TextView)findViewById(R.id.computer_turn_score);
        userTurnScore = 0;
        computerTurnScore = 0;
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOverallScore = 0;
                userTurnScore = 0;
                computerOverallScore = 0;
                computerTurnScore = 0;
                updateScoreText();
                roll.setEnabled(true);
                hold.setEnabled(true);
                computerTurnScore_tv.setText("0");
                computerTurnScore_tv.setTextSize(36);
                userTurnScore_tv.setText("0");
                userTurnScore_tv.setTextSize(36);
            }
        });

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rand = new Random();
                int diceNumber = rand.nextInt(6)+1;
                Toast.makeText(getApplicationContext(),Integer.toString(diceNumber),Toast.LENGTH_SHORT).show();
                updateDicePicture(diceNumber);
                if(diceNumber == 1){
                    userTurnScore = 0;
                    computerTurn();
                }
                else{
                    userTurnScore += diceNumber;
                }
                userTurnScore_tv.setText(Integer.toString(userTurnScore));
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOverallScore += userTurnScore;
                updateScoreText();
                userTurnScore = 0;
                if(checkWin(userOverallScore)){
                    userTurnScore_tv.setTextSize(16);
                    userTurnScore_tv.setText("User Win\n"+Integer.toString(userOverallScore));
                }
                else{
                    computerTurn();
                }
            }
        });
    }
    public void computerTurn(){

        timerHandle = new Handler();
        Runnable timerRunnable = new Runnable() {

            @Override
            public void run() {

                rand = new Random();
                roll.setEnabled(false);
                hold.setEnabled(false);
                int diceNumber = rand.nextInt(6)+1;
                if(diceNumber == 1){
                    computerTurnScore = 0;
                }
                else{
                    computerTurnScore += diceNumber;
                }
                Toast.makeText(getApplicationContext(),"Bob rolled "+Integer.toString(diceNumber),Toast.LENGTH_SHORT).show();
                updateDicePicture(diceNumber);
                computerTurnScore_tv.setText(Integer.toString(computerTurnScore));
                if(diceNumber == 1 || computerTurnScore > 20){
                    timerHandle.removeCallbacks(this);
                    computerOverallScore += computerTurnScore;
                    updateScoreText();
                    roll.setEnabled(true);
                    hold.setEnabled(true);
                    computerTurnScore = 0;
                    if(checkWin(computerOverallScore)){
                        computerTurnScore_tv.setTextSize(16);
                        computerTurnScore_tv.setText("Computer Win\n"+Integer.toString(computerOverallScore));
                    }
                }
                else{
                    timerHandle.postDelayed(this, 1000);
                }
            }
        };

        timerHandle.postDelayed(timerRunnable, 1000);

    }

    public void updateDicePicture(int dice){
        switch (dice){
            case 1: dicePicture.setBackgroundResource(R.drawable.dice1);
                    //dicePicture.getResources().getDrawable(R.drawable.dice1);
                break;
            case 2: dicePicture.setBackgroundResource(R.drawable.dice2);
                    //dicePicture.getResources().getDrawable(R.drawable.dice2);
                break;
            case 3: dicePicture.setBackgroundResource(R.drawable.dice3);
                //dicePicture.getResources().getDrawable(R.drawable.dice3);
                break;
            case 4: dicePicture.setBackgroundResource(R.drawable.dice4);
                //dicePicture.getResources().getDrawable(R.drawable.dice4);
                break;
            case 5: dicePicture.setBackgroundResource(R.drawable.dice5);
                //dicePicture.getResources().getDrawable(R.drawable.dice5);
                break;
            case 6: dicePicture.setBackgroundResource(R.drawable.dice6);
                //dicePicture.getResources().getDrawable(R.drawable.dice6);
                break;
            default:break;
        }
    }
    public void updateScoreText(){
        String overAllScore = Integer.toString(userOverallScore);
        userScore.setText(overAllScore);
        String cs = Integer.toString(computerOverallScore);
        computerScore.setText(cs);
    }
    public boolean checkWin(int score){
        if(score >= 100){
            roll.setEnabled(false);
            hold.setEnabled(false);
            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT);
            return true;
        }
        return false;
    }
}
