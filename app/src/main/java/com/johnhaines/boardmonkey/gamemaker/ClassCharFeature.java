package com.johnhaines.boardmonkey.gamemaker;

public class ClassCharFeature extends ClassAbility {

    private int maxPoints = 0;

    private Class_S_F_T_Effect effectType = new Class_S_F_T_Effect(0);

    public ClassCharFeature(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof ClassCharFeature) {
            isEqual = this.name.equals(((ClassCharFeature) object).getName());
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

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Class_S_F_T_Effect getEffectType() {
        return this.effectType;
    }

    public void setEffectType(Class_S_F_T_Effect type) {
        this.effectType = type;
    }

}
