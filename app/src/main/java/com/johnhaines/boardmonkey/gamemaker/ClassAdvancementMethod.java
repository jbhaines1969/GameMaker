package com.johnhaines.boardmonkey.gamemaker;

import java.io.Serializable;

/**
 * Created by John on 1/15/2017.
 */

public class ClassAdvancementMethod implements Serializable {

    public ClassAdvancementMethod() {

    }

    private int type = 1;

    // 1: levels based on earned experience (set number of ability points / level)
    // 2: earned incremental advancement points based on earned experience
    // 3: event triggered advancement (determined by GM)

    private int attPointCost = 0;
    private int attNonPrefPenalty = 0;
    private float attCostMultiplier = 1;
    private int maxAttPoints;
    private int skillPointCost = 0;
    private int skillNonPrefPenalty = 0;
    private float skillCostMultiplier = 1;
    private int maxSkillPoints = 1;
    private int traitPointCost = 0;
    private int traitNonPrefPenalty = 0;
    private float traitCostMultiplier = 1;
    private int maxTraitPoints = 1;
    private int featPointCost = 0;
    private int featNonPreferredPenalty = 0;
    private float featCostMultiplier = 1;
    private int maxFeatPoints = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAttPointCost() {
        return attPointCost;
    }

    public void setAttPointCost(int attPointCost) {
        this.attPointCost = attPointCost;
    }

    public int getAttNonPrefPenalty() {
        return attNonPrefPenalty;
    }

    public void setAttNonPrefPenalty(int attNonPrefPenalty) {
        this.attNonPrefPenalty = attNonPrefPenalty;
    }

    public float getAttCostMultiplier() {
        return attCostMultiplier;
    }

    public void setAttCostMultiplier(float attCostMultiplier) {
        this.attCostMultiplier = attCostMultiplier;
    }

    public int getMaxAttPoints() {
        return maxAttPoints;
    }

    public void setMaxAttPoints(int maxAttPoints) {
        this.maxAttPoints = maxAttPoints;
    }

    public int getSkillPointCost() {
        return skillPointCost;
    }

    public void setSkillPointCost(int skillPointCost) {
        this.skillPointCost = skillPointCost;
    }

    public int getSkillNonPrefPenalty() {
        return skillNonPrefPenalty;
    }

    public void setSkillNonPrefPenalty(int skillNonPrefPenalty) {
        this.skillNonPrefPenalty = skillNonPrefPenalty;
    }

    public float getSkillCostMultiplier() {
        return skillCostMultiplier;
    }

    public void setSkillCostMultiplier(float skillCostMultiplier) {
        this.skillCostMultiplier = skillCostMultiplier;
    }

    public int getMaxSkillPoints() {
        return maxSkillPoints;
    }

    public void setMaxSkillPoints(int maxSkillPoints) {
        this.maxSkillPoints = maxSkillPoints;
    }

    public int getTraitPointCost() {
        return traitPointCost;
    }

    public void setTraitPointCost(int traitPointCost) {
        this.traitPointCost = traitPointCost;
    }

    public int getTraitNonPrefPenalty() {
        return traitNonPrefPenalty;
    }

    public void setTraitNonPrefPenalty(int traitNonPrefPenalty) {
        this.traitNonPrefPenalty = traitNonPrefPenalty;
    }

    public float getTraitCostMultiplier() {
        return traitCostMultiplier;
    }

    public void setTraitCostMultiplier(float traitCostMultiplier) {
        this.traitCostMultiplier = traitCostMultiplier;
    }

    public int getMaxTraitPoints() {
        return maxTraitPoints;
    }

    public void setMaxTraitPoints(int maxTraitPoints) {
        this.maxTraitPoints = maxTraitPoints;
    }

    public int getFeatPointCost() {
        return featPointCost;
    }

    public void setFeatPointCost(int featPointCost) {
        this.featPointCost = featPointCost;
    }

    public int getFeatNonPreferredPenalty() {
        return featNonPreferredPenalty;
    }

    public void setFeatNonPreferredPenalty(int featNonPreferredPenalty) {
        this.featNonPreferredPenalty = featNonPreferredPenalty;
    }

    public float getFeatCostMultiplier() {
        return featCostMultiplier;
    }

    public void setFeatCostMultiplier(float featCostMultiplier) {
        this.featCostMultiplier = featCostMultiplier;
    }

    public int getMaxFeatPoints() {
        return maxFeatPoints;
    }

    public void setMaxFeatPoints(int maxFeatPoints) {
        this.maxFeatPoints = maxFeatPoints;
    }

}
