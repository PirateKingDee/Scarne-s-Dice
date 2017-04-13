package com.googleappliedandroid.scarnedice;

/**
 * Created by andyliang on 4/13/17.
 */

import java.util.Random;

public class Dice {

    int score;
    Random rand;
    public Dice(){
        score = 0;
        rand = new Random();
    }
    public int rollDice(){
        int rolledDice = rand.nextInt()%6+1;
        if(rolledDice == 0){
            resetScore();
            return 0;
        }
        score = score + rolledDice;
        return rolledDice;
    }
    public int getScore(){
        return score;
    }
    public void resetScore(){
        score = 0;
    }
}

