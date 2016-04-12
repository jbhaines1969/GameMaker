package com.boardmonkey.johnhaines.gamemaker;

import java.util.ArrayList;
import java.util.HashMap;


public class CharRace extends Characteristic{
	
	private int baseMoveSpeed = 0;
	private int baseFlySpeed = 0;
	private int baseSwimSpeed = 0;

	private ArrayList<CharClass> classesAllowed = new ArrayList<CharClass>();
	private ArrayList<String> raceAbilities = new ArrayList<String>();
	private ArrayList<CharSkill> knownSkills = new ArrayList<CharSkill>();
	private ArrayList<CharFeature> autoFeats = new ArrayList<CharFeature>();
	private HashMap<CharAttribute, Integer> raceAttributeModMale = new HashMap<CharAttribute, Integer>();
	private HashMap<CharAttribute, Integer> raceAttributeModFemale = new HashMap<CharAttribute, Integer>();

	private HashMap<CharAttribute, Integer> minAttributes = new HashMap<CharAttribute, Integer>();
	private HashMap<CharAttribute, Integer> maxAttributes = new HashMap<CharAttribute, Integer>();	
	
	public CharRace(String name) {
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

	public void addClass(CharClass c) {
		classesAllowed.add(c);
	}
	
	public ArrayList<CharClass> getAllowedClasses() {
		return classesAllowed;
	}
	
	public void removeClass(CharClass c) {
		classesAllowed.remove(c);
	}
	
	public void addRaceAbility(String bonus) {
		raceAbilities.add(bonus);
	}
	
	public void removeRaceAbility(String bonus) {
		raceAbilities.remove(bonus);
	}
	
	public ArrayList<String> getRaceAbilities() {
		return raceAbilities;
	}

	public void addKnownSkill(CharSkill skill) {
		knownSkills.add(skill);
	}

	public void removeKnownSkill(CharSkill skill) {
		knownSkills.remove(skill);
	}

	public ArrayList<CharSkill> getKnownSkills() {
		return knownSkills;
	}

	public void addAutoFeat(CharFeature feature) {
		autoFeats.add(feature);
	}

	public void removeAutoFeature(CharFeature feature) {
		autoFeats.remove(feature);
	}

	public ArrayList<CharFeature> getAutoFeats() {
		return autoFeats;
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
	
	public void addAttributeModMale(CharAttribute c, int mod) {
		raceAttributeModMale.put(c, mod);
	}
	
	public void removeAttributeModMale(CharAttribute c) {
		raceAttributeModMale.remove(c);
	}

	public HashMap<CharAttribute, Integer> getRaceAttributeModsMale() {
		return raceAttributeModMale;
	}

	public void addAttributeModFemale(CharAttribute c, int mod) {
		raceAttributeModFemale.put(c, mod);
	}

	public void removeAttributeModFemale(CharAttribute c) {
		raceAttributeModFemale.remove(c);
	}

	public HashMap<CharAttribute, Integer> getRaceAttributeModsFemale() {
		return raceAttributeModFemale;
	}

	public HashMap<CharAttribute, Integer> getMinAttributes() {
		return minAttributes;
	}

	public HashMap<CharAttribute, Integer> getMaxAttributes() {
		return maxAttributes;
	}
	
	
}
