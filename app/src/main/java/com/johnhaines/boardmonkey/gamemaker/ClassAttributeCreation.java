package com.johnhaines.boardmonkey.gamemaker;

import java.io.Serializable;

/**
 * Created by Terri on 12/7/2016.
 */
public class ClassAttributeCreation implements Serializable {

    public ClassAttributeCreation() {

    }

    private int creationType = 0;
    /*  Straight Dice
        1) Dice minus Lowest Roll
        2) Point Assignment
        3) Base Points
        4) Base + Dice
        5) Dice + Point Assignment
        6) Base + Point Assignment
     */
    private int baseScore = 0;
    private int diceSides = 0;
    private int diceRolls = 0;
    private int points = 0;

    public int getCreationType() {
        return creationType;
    }

    public void setCreationType(int creationType) {
        this.creationType = creationType;
    }

    public int getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }

    public int getDiceSides() {
        return diceSides;
    }

    public void setDiceSides(int diceSides) {
        this.diceSides = diceSides;
    }

    public int getDiceRolls() {
        return diceRolls;
    }

    public void setDiceRolls(int diceRolls) {
        this.diceRolls = diceRolls;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
