package com.boardmonkey.johnhaines.gamemaker;

import java.util.HashMap;

public class CharClass extends Characteristic {
	
	private HashMap<CharAttribute, Integer> minAttributes;
	private HashMap<CharAttribute, Integer> maxAttributes;

	private int startingSkillPoints;
	private int startingTraitPoints;
	private int startingFeatPoints;

	private int levelsForSkillPoints;
	private int skillPointsPerLevelUp;

	private int levelsForTraitPoints;
	private int traitPointsPerLevelUp;

	private int levelsForFeatPoints;
	private int featPointsPerLevel;
	
	
	public CharClass(String name) {
		this.name = name;
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
	
	public String toString() {
		return this.name;
	}
	
	public void addMinAttribute(CharAttribute c, int min) {
		minAttributes.put(c, min);
	}
	
	public void addMaxAttribute(CharAttribute c, int max) {
		maxAttributes.put(c, max);
	}
	
	public void removeMinAttribute(CharAttribute c) {
		minAttributes.remove(c);
	}
	
	public void removeMaxAttribute(CharAttribute c) {
		maxAttributes.remove(c);
	}

	public HashMap<CharAttribute, Integer> getMinAttributes() {
		return minAttributes;
	}

	public HashMap<CharAttribute, Integer> getMaxAttributes() {
		return maxAttributes;
	}

	public void setMinAttributes(HashMap<CharAttribute, Integer> minAttributes) {
		this.minAttributes = minAttributes;
	}

	public void setMaxAttributes(HashMap<CharAttribute, Integer> maxAttributes) {
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

	public int getFeatPointsPerLevel() {
		return featPointsPerLevel;
	}

	public void setFeatPointsPerLevel(int featPointsPerLevel) {
		this.featPointsPerLevel = featPointsPerLevel;
	}
}
