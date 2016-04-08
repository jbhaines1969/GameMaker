package com.boardmonkey.johnhaines.gamemaker;

public class CharSkill extends Characteristic {
	
	public CharSkill(String name) {
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

}
