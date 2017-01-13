package com.johnhaines.boardmonkey.gamemaker;

import java.io.Serializable;

/**
 * Created by Terri on 12/7/2016.
 */

public class ClassCharacteristicPointRanges implements Serializable {

    public ClassCharacteristicPointRanges() {

    }

    private int minSkillPoints = 0;
    private int maxSkillPoints = 0;
    private int minTraitPoints = 0;
    private int maxTraitPoints = 0;
    private int minFeatPoints = 0;
    private int maxFeatPoints = 0;

    public int getMinSkillPoints() {
        return minSkillPoints;
    }

    public void setMinSkillPoints(int minSkillPoints) {
        this.minSkillPoints = minSkillPoints;
    }

    public int getMaxSkillPoints() {
        return maxSkillPoints;
    }

    public void setMaxSkillPoints(int maxSkillPoints) {
        this.maxSkillPoints = maxSkillPoints;
    }

    public int getMinTraitPoints() {
        return minTraitPoints;
    }

    public void setMinTraitPoints(int minTraitPoints) {
        this.minTraitPoints = minTraitPoints;
    }

    public int getMaxTraitPoints() {
        return maxTraitPoints;
    }

    public void setMaxTraitPoints(int maxTraitPoints) {
        this.maxTraitPoints = maxTraitPoints;
    }

    public int getMinFeatPoints() {
        return minFeatPoints;
    }

    public void setMinFeatPoints(int minFeatPoints) {
        this.minFeatPoints = minFeatPoints;
    }

    public int getMaxFeatPoints() {
        return maxFeatPoints;
    }

    public void setMaxFeatPoints(int maxFeatPoints) {
        this.maxFeatPoints = maxFeatPoints;
    }
}
