package com.johnhaines.boardmonkey.gamemaker;

import java.io.Serializable;

/**
 * Created by Terri on 12/11/2016.
 */

public class Class_S_F_T_Effect implements Serializable {

    public Class_S_F_T_Effect(Integer type) {
        this.effectType = type;
    }

    private int effectType = 0;
    /* 1 = gamePlay(bonus in description),
        2 = roll mod (set which roll, and mod amount)
        3 = characteristic mod (set which characteristic and mod amount)
     */

    private ClassCharacteristic charModded;

    private String rollModded = "";

    private int modAmount = 0;


    public int getEffectType() {
        return this.effectType;
    }

    public void setEffectType(int type) {
        this.effectType = type;
    }

    public ClassCharacteristic getCharModded() {
        return charModded;
    }

    public void setCharModded(ClassCharacteristic charModded) {
        this.charModded = charModded;
    }

    public String getRollModded() {
        return rollModded;
    }

    public void setRollModded(String rollModded) {
        this.rollModded = rollModded;
    }

    public int getRollModAmount() {
        return modAmount;
    }

    public void setRollModAmount(int rollModAmount) {
        this.modAmount = rollModAmount;
    }
}
