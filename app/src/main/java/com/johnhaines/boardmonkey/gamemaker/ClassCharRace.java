package com.johnhaines.boardmonkey.gamemaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


public class ClassCharRace extends ClassCharacteristic {

    private int baseMoveSpeed = 0;
    private int baseFlySpeed = 0;
    private int baseSwimSpeed = 0;

    private ArrayList<ClassCharClass> classesAllowed = new ArrayList<ClassCharClass>();
    private ArrayList<ClassCharTrait> raceTraits = new ArrayList<ClassCharTrait>();
    private ArrayList<ClassCharSkill> knownSkills = new ArrayList<ClassCharSkill>();
    private ArrayList<ClassCharFeature> autoFeats = new ArrayList<ClassCharFeature>();
    private TreeMap<ClassCharAttribute, Integer> raceAttributeModMale = new TreeMap<>();
    private TreeMap<ClassCharAttribute, Integer> raceAttributeModFemale = new TreeMap<ClassCharAttribute, Integer>();

    private HashMap<ClassCharAttribute, Integer> minAttributes = new HashMap<ClassCharAttribute, Integer>();
    private HashMap<ClassCharAttribute, Integer> maxAttributes = new HashMap<ClassCharAttribute, Integer>();

    public ClassCharRace(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof ClassCharRace) {
            isEqual = this.name.equals(((ClassCharRace) object).getName());
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.name;
    }

    public void setMoveSpeed(int speed) {
        this.baseMoveSpeed = speed;
    }

    public int getMoveSpeed() {
        return baseMoveSpeed;
    }

    public void setFlySpeed(int speed) {
        this.baseFlySpeed = speed;
    }

    public int getFlySpeed() {
        return baseFlySpeed;
    }

    public void setSwimSpeed(int speed) {
        this.baseSwimSpeed = speed;
    }

    public int getSwimSpeed() {
        return baseSwimSpeed;
    }

    public void addClass(ClassCharClass c) {
        classesAllowed.add(c);
    }

    public ArrayList<ClassCharClass> getAllowedClasses() {
        return classesAllowed;
    }

    public void removeClass(ClassCharClass c) {
        classesAllowed.remove(c);
    }

    public void clearClassesAllowed() {
        classesAllowed.clear();
    }

    public void addTrait(ClassCharTrait trait) {
        raceTraits.add(trait);
    }

    public void removeTrait(ClassCharTrait trait) {
        raceTraits.remove(trait);
    }

    public ArrayList<ClassCharTrait> getTraits() {
        return raceTraits;
    }

    public void clearRaceTraits() {
        raceTraits.clear();
    }

    public void addKnownSkill(ClassCharSkill skill) {
        knownSkills.add(skill);
    }

    public void removeKnownSkill(ClassCharSkill skill) {
        knownSkills.remove(skill);
    }

    public void clearKnownSkills() {
        knownSkills.clear();
    }

    public ArrayList<ClassCharSkill> getKnownSkills() {
        return knownSkills;
    }

    public void addAutoFeat(ClassCharFeature feature) {
        autoFeats.add(feature);
    }

    public void removeAutoFeature(ClassCharFeature feature) {
        autoFeats.remove(feature);
    }

    public void clearAutoFeatures() {
        autoFeats.clear();
    }

    public ArrayList<ClassCharFeature> getAutoFeats() {
        return autoFeats;
    }

    public void addMinAttribute(ClassCharAttribute c, int min) {
        minAttributes.put(c, min);
    }

    public void addMaxAttribute(ClassCharAttribute c, int max) {
        maxAttributes.put(c, max);
    }

    public void removeMinAttribute(ClassCharAttribute c) {
        minAttributes.remove(c);
    }

    public void removeMaxAttribute(ClassCharAttribute c) {
        maxAttributes.remove(c);
    }

    public void addAttributeModMale(ClassCharAttribute c, int mod) {
        raceAttributeModMale.put(c, mod);
    }

    public void removeAttributeModMale(ClassCharAttribute c) {
        raceAttributeModMale.remove(c);
    }

    public void clearAttributeModMale() {
        raceAttributeModMale.clear();
    }

    public TreeMap<ClassCharAttribute, Integer> getRaceAttributeModsMale() {
        return raceAttributeModMale;
    }

    public void addAttributeModFemale(ClassCharAttribute c, int mod) {
        raceAttributeModFemale.put(c, mod);
    }

    public void removeAttributeModFemale(ClassCharAttribute c) {
        raceAttributeModFemale.remove(c);
    }

    public void clearAttributeModFemale() {
        raceAttributeModFemale.clear();
    }

    public TreeMap<ClassCharAttribute, Integer> getRaceAttributeModsFemale() {
        return raceAttributeModFemale;
    }

    public HashMap<ClassCharAttribute, Integer> getMinAttributes() {
        return minAttributes;
    }

    public HashMap<ClassCharAttribute, Integer> getMaxAttributes() {
        return maxAttributes;
    }


}
