package com.boardmonkey.johnhaines.gamemaker;

public class CharAttribute extends Characteristic {
	
	private int minAttribute;
	private int maxAttribute;
	
	
	public String toString() {
		return this.name;
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
	
	public CharAttribute(String name) {
		this.name = name;
	}

	public int getMinAttribute() {
		return minAttribute;
	}

	public void setMinAttribute(int minAttribute) {
		this.minAttribute = minAttribute;
	}

	public int getMaxAttribute() {
		return maxAttribute;
	}

	public void setMaxAttribute(int maxAttribute) {
		this.maxAttribute = maxAttribute;
	}

	
	
	

}
