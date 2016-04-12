package com.boardmonkey.johnhaines.gamemaker;

public class CharFeature extends Characteristic {
	
	public CharFeature(String name) {
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

}
