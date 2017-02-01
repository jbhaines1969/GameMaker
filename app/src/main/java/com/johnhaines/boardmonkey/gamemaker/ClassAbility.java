package com.johnhaines.boardmonkey.gamemaker;

import java.util.ArrayList;

/**
 * Created by John on 1/16/2017.
 */

public class ClassAbility extends ClassCharacteristic {

    protected int effectType = 0;

    /* 1: bonus to roll
       2: ability level acts as success roll
       3: level acts as number of dice for roll
       4: gameplay (determined by Game Master)
     */

    protected int maxPoints = 0;

    protected boolean restrictToPrimaryClasses = false;
    protected boolean restrictToPrimaryRaces = false;

    protected ArrayList<ClassCharClass> preferredClasses = new ArrayList<>();
    protected ArrayList<ClassCharRace> preferredRaces = new ArrayList<>();

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public boolean isRestrictToPrimaryClasses() {
        return restrictToPrimaryClasses;
    }

    public void setRestrictToPrimaryClasses(boolean restrictToPrimaryClasses) {
        this.restrictToPrimaryClasses = restrictToPrimaryClasses;
    }

    public boolean isRestrictToPrimaryRaces() {
        return restrictToPrimaryRaces;
    }

    public void setRestrictToPrimaryRaces(boolean restrictToPrimaryRaces) {
        this.restrictToPrimaryRaces = restrictToPrimaryRaces;
    }

    public ArrayList<ClassCharClass> getPreferredClasses() {
        return preferredClasses;
    }

    public void setPreferredClasses(ArrayList<ClassCharClass> preferredClasses) {
        this.preferredClasses = preferredClasses;
    }

    public void addPreferredClass(ClassCharClass c) {
        preferredClasses.add(c);
    }

    public void removePreferredClass(ClassCharClass c) {
        preferredClasses.remove(c);
    }

    public void removePreferredClass(Integer index) {
        preferredClasses.remove(index);
    }

    public ArrayList<ClassCharRace> getPreferredRaces() {
        return preferredRaces;
    }

    public void setPreferredRaces(ArrayList<ClassCharRace> preferredRaces) {
        this.preferredRaces = preferredRaces;
    }

    public void addPreferredRace(ClassCharRace r) {
        preferredRaces.add(r);
    }

    public void removePrefferedRace(ClassCharRace r) {
        preferredRaces.remove(r);
    }

    public void removePreferredRace(Integer index) {
        preferredRaces.remove(index);
    }
}
