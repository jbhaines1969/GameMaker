package com.johnhaines.boardmonkey.gamemaker;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Terri on 12/12/2016.
 */

public class ClassHitsType implements Serializable {

    private int type = 0;
    /* Determines method for determining hit points
        1: dice roll (uses hitDiceSids and hitDiceRolls)
        2: attribute (uses an attribute score (single value in attList Array) + modifiers attTimes and attPlus
            attTimes is a multiplier, attPlus is an additive(or subtractive) mod.
        3: accessory based (uses isModifiedByAtts if Attributes will modify health)
     */

    private int hitDiceSides = 0;
    private int hitDiceRolls = 0;

    private int attTimes = 1;
    private int attPlus = 0;

    private boolean isModifiedByAtts;

    private ArrayList<ClassCharAttribute> attList = new ArrayList<>();

    public ClassHitsType() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHitDiceSides() {
        return hitDiceSides;
    }

    public void setHitDiceSides(int hitDiceSides) {
        this.hitDiceSides = hitDiceSides;
    }

    public int getHitDiceRolls() {
        return hitDiceRolls;
    }

    public void setHitDiceRolls(int hitDiceRolls) {
        this.hitDiceRolls = hitDiceRolls;
    }

    public int getAttTimes() {
        return attTimes;
    }

    public void setAttTimes(int attTimes) {
        this.attTimes = attTimes;
    }

    public int getAttPlus() {
        return attPlus;
    }

    public void setAttPlus(int attPlus) {
        this.attPlus = attPlus;
    }

    public ArrayList<ClassCharAttribute> getAttList() {
        return attList;
    }

    public void setAttList(ArrayList<ClassCharAttribute> attList) {
        this.attList = attList;
    }

    public void addAttribute(ClassCharAttribute att) {
        this.attList.add(att);
    }

    public boolean isModifiedByAtts() {
        return isModifiedByAtts;
    }

    public void setModifiedByAtts(boolean modifiedByAtts) {
        isModifiedByAtts = modifiedByAtts;
    }


}
