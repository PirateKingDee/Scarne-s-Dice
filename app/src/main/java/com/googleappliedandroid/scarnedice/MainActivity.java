package com.googleappliedandroid.scarnedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private boolean playerTurn;
    private Button roll;
    private Button hold;
    private Button reset;
    private TextView userScore;
    private TextView computerScore;
    private TextView userScore_tv;
    private TextView computerScore_tv;
    private ImageView dicePicture;

    private int userOverallScore;
    private int userTurnScore;
    private int computerOverallScore;
    private int computerTurnScore;
    private Dice user;
    private Dice computer;
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

        playerTurn = true;
        user = new Dice();
        computer = new Dice();

        userOverallScore = user.getScore();
        computerOverallScore = user.getScore();

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int diceNumber = user.rollDice();
                updateDicePicture(diceNumber);

            }
        });
    }
    public void updateDicePicture(int dice){
        switch (dice){
            case 1: dicePicture.setBackgroundResource(R.drawable.dice1);
                break;
            case 2: dicePicture.setBackgroundResource(R.drawable.dice2);
                break;
            case 3: dicePicture.setBackgroundResource(R.drawable.dice3);
                break;
            case 4: dicePicture.setBackgroundResource(R.drawable.dice4);
                break;
            case 5: dicePicture.setBackgroundResource(R.drawable.dice5);
                break;
            case 6: dicePicture.setBackgroundResource(R.drawable.dice6);
                break;
            default:break;
        }

    }
}
