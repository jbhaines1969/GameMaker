package com.johnhaines.boardmonkey.gamemaker;

import java.util.ArrayList;
import java.util.TreeMap;

public class ClassCharClass extends ClassCharacteristic {

    private TreeMap<ClassCharAttribute, Integer> minAttributes = new TreeMap<>();
    private TreeMap<ClassCharAttribute, Integer> maxAttributes = new TreeMap<>();
    private ArrayList<ClassCharSkill> knownSkills = new ArrayList<ClassCharSkill>();
    private ArrayList<ClassCharTrait> classTraits = new ArrayList<ClassCharTrait>();
    private ArrayList<ClassCharFeature> classFeats = new ArrayList<ClassCharFeature>();

    private TreeMap<Integer, String> levelNameMap = new TreeMap<>();

    private int startingSkillPoints;
    private int startingTraitPoints;
    private int startingFeatPoints;

    private int levelsForAttributePoints;
    private int attributePointsPerLevelUp;

    private int levelsForSkillPoints;
    private int skillPointsPerLevelUp;

    private int levelsForTraitPoints;
    private int traitPointsPerLevelUp;

    private int levelsForFeatPoints;
    private int featPointsPerLevelUp;


    public ClassCharClass(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof ClassCharClass) {
            isEqual = this.name.equals(((ClassCharClass) object).getName());
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

    public TreeMap<ClassCharAttribute, Integer> getMinAttributes() {
        return minAttributes;
    }

    public TreeMap<ClassCharAttribute, Integer> getMaxAttributes() {
        return maxAttributes;
    }

    public void setMinAttributes(TreeMap<ClassCharAttribute, Integer> minAttributes) {
        this.minAttributes = minAttributes;
    }

    public void setMaxAttributes(TreeMap<ClassCharAttribute, Integer> maxAttributes) {
        this.maxAttributes = maxAttributes;
    }

    public int getStartingSkillPoints() {
        return startingSkillPoints;
    }

    public void setStartingSkillPoints(int startingSkillPoints) {
        this.startingSkillPoints = startingSkillPoints;
    }

    public int getStartingTraitPoints() {
        return startingTraitPoints;
    }

    public void setStartingTraitPoints(int startingTraitPoints) {
        this.startingTraitPoints = startingTraitPoints;
    }

    public int getStartingFeatPoints() {
        return startingFeatPoints;
    }

    public void setStartingFeatPoints(int startingFeatPoints) {
        this.startingFeatPoints = startingFeatPoints;
    }

    public int getLevelsForSkillPoints() {
        return levelsForSkillPoints;
    }

    public void setLevelsForSkillPoints(int levelsForSkillPoints) {
        this.levelsForSkillPoints = levelsForSkillPoints;
    }

    public int getSkillPointsPerLevelUp() {
        return skillPointsPerLevelUp;
    }

    public void setSkillPointsPerLevelUp(int skillPointsPerLevelUp) {
        this.skillPointsPerLevelUp = skillPointsPerLevelUp;
    }

    public int getLevelsForTraitPoints() {
        return levelsForTraitPoints;
    }

    public void setLevelsForTraitPoints(int levelsForTraitPoints) {
        this.levelsForTraitPoints = levelsForTraitPoints;
    }

    public int getTraitPointsPerLevelUp() {
        return traitPointsPerLevelUp;
    }

    public void setTraitPointsPerLevelUp(int traitPointsPerLevelUp) {
        this.traitPointsPerLevelUp = traitPointsPerLevelUp;
    }

    public int getLevelsForFeatPoints() {
        return levelsForFeatPoints;
    }

    public void setLevelsForFeatPoints(int levelsForFeatPoints) {
        this.levelsForFeatPoints = levelsForFeatPoints;
    }

    public int getFeatPointsPerLevelUp() {
        return featPointsPerLevelUp;
    }

    public void setFeatPointsPerLevelUp(int featPointsPerLevelUp) {
        this.featPointsPerLevelUp = featPointsPerLevelUp;
    }

    public int getLevelsForAttributePoints() {
        return levelsForAttributePoints;
    }

    public void setLevelsForAttributePoints(int levelsForAttributePoints) {
        this.levelsForAttributePoints = levelsForAttributePoints;
    }

    public int getAttributePointsPerLevelUp() {
        return attributePointsPerLevelUp;
    }

    public void setAttributePointsPerLevelUp(int attributePointsPerLevelUp) {
        this.attributePointsPerLevelUp = attributePointsPerLevelUp;
    }

    public void addTrait(ClassCharTrait trait) {
        classTraits.add(trait);
    }

    public void removeTrait(ClassCharTrait trait) {
        classTraits.remove(trait);
    }

    public ArrayList<ClassCharTrait> getTraits() {
        return classTraits;
    }

    public void clearClassTraits() {
        classTraits.clear();
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

    public void addClassFeat(ClassCharFeature feature) {
        classFeats.add(feature);
    }

    public void removeClassFeature(ClassCharFeature feature) {
        classFeats.remove(feature);
    }

    public void clearClassFeatures() {
        classFeats.clear();
    }

    public ArrayList<ClassCharFeature> getClassFeats() {
        return classFeats;
    }

    public TreeMap<Integer, String> getLevelNameList() {
        return this.levelNameMap;
    }

    public void addLevelName(Integer key, String name) {
        levelNameMap.put(key, name);
    }

    public void removeLevelName(Integer key) {
        levelNameMap.remove(key);
    }
}
