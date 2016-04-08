package com.boardmonkey.johnhaines.gamemaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Game {
	
	private String name;
	private String type;
	private String description;
	
	private int creationType;
	/* Type 1: Straight Dice
	 * Type 2: Dice - lowest roll;
	 * Type 3: Dice + Points
	 * Type 4: Base + Dice
	 * Type 5: Point assignment;
	 * Type 6: Base + Points;
	 * Type 7: Base only;
	 * Type 8: Base + Dice + Points
	 */
	private int baseScore;
	private int diceSides;
	private int diceRolls;
	private int points;
	
	private ArrayList<CharRace> races = new ArrayList<CharRace>();
	private ArrayList<CharClass> classes = new ArrayList<CharClass>();
	private ArrayList<CharAttribute> attributes = new ArrayList<CharAttribute>();
	private ArrayList<CharSkill> skills = new ArrayList<CharSkill>();
	private ArrayList<CharTrait> traits = new ArrayList<CharTrait>();
	private ArrayList<CharFeature> features = new ArrayList<CharFeature>();
	
	public Game(String name, String type) {
		this.name = name;
		this.type = type;

	}
	
	public String toString() {
		return this.name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<CharRace> getRaces() {
		return races;
	}
	public ArrayList<CharClass> getClasses() {
		return classes;
	}
	public ArrayList<CharAttribute> getAttributes() {
		return attributes;
	}
	public ArrayList<CharSkill> getSkills() {
		return skills;
	}
	public ArrayList<CharTrait> getTraits() {
		return traits;
	}
	public ArrayList<CharFeature> getFeatures() {
		return features;
	}
	
	public void addRace(CharRace r) {
		races.add(r);
		Collections.sort(races, new Comparator<CharRace>() {
			@Override
			public int compare(CharRace item1, CharRace item2) {
				return item1.getName().compareTo(item2.getName());
			}
		});
	}
	
	public void removeRace(CharRace r) {
		races.remove(r);
	}
	
	public void addClass(CharClass c) {
		classes.add(c);
		Collections.sort(classes, new Comparator<CharClass>() {
			@Override
			public int compare(CharClass item1, CharClass item2) {
				return item1.getName().compareTo(item2.getName());
			}
		});
	}
	
	public void removeClass(CharClass c) {
		classes.remove(c);
	}
	
	public void addAttribute (CharAttribute a) {
		attributes.add(a);
		Collections.sort(attributes, new Comparator<CharAttribute>() {
			@Override
			public int compare(CharAttribute item1, CharAttribute item2) {
				return item1.getName().compareTo(item2.getName());
			}
		});
	}
	
	public void removeAttribute (CharAttribute a) {
		attributes.remove(a);
	}
	
	public void addSkill (CharSkill s ) {
		skills.add(s);
		Collections.sort(skills, new Comparator<CharSkill>() {
			@Override
			public int compare(CharSkill item1, CharSkill item2) {
				return item1.getName().compareTo(item2.getName());
			}
		});
	}
	
	public void removeSkill (CharSkill s) {
		skills.remove(s);
	}
	
	public void addTrait (CharTrait t) {
		traits.add(t);
		Collections.sort(traits, new Comparator<CharTrait>() {
			@Override
			public int compare(CharTrait item1, CharTrait item2) {
				return item1.getName().compareTo(item2.getName());
			}
		});
	}
	
	public void removeTrait (CharTrait t) {
		traits.remove(t);
	}
	
	public void addFeature (CharFeature f) {
		features.add(f);
		Collections.sort(features, new Comparator<CharFeature>(){
			@Override
			public int compare(CharFeature item1, CharFeature item2) {
				return item1.getName().compareTo(item2.getName());
			}
		});
	}
	
	public void removeFeature (CharFeature f) {
		features.remove(f);
	}

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
