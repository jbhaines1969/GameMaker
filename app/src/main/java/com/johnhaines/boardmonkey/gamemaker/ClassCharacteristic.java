package com.johnhaines.boardmonkey.gamemaker;

import java.io.Serializable;

public class ClassCharacteristic implements Comparable<ClassCharacteristic>, Serializable {

    protected String name;
    protected String description;

    @Override
    public int compareTo(ClassCharacteristic another) {
        return this.name.compareTo(another.name);
    }
}
