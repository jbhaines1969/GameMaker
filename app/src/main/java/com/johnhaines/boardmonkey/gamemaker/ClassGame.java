package com.johnhaines.boardmonkey.gamemaker;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClassGame implements Serializable, Parcelable {

    private int maxTraitPoints;
    private int maxSkillPoints;
    private int maxFeatPoints;

    private String name = "";
    private String type = "";
    private String description = "";

    private ClassAdvancementMethod advancementMethod = new ClassAdvancementMethod();
    private ClassAttributeCreation attCreation = new ClassAttributeCreation();
    private ClassHitsType hitsType = new ClassHitsType();

    private ArrayList<ClassCharRace> races = new ArrayList<ClassCharRace>();
    private ArrayList<ClassCharClass> classes = new ArrayList<ClassCharClass>();
    private ArrayList<ClassCharAttribute> attributes = new ArrayList<ClassCharAttribute>();
    private ArrayList<ClassCharSkill> skills = new ArrayList<ClassCharSkill>();
    private ArrayList<ClassCharTrait> traits = new ArrayList<ClassCharTrait>();
    private ArrayList<ClassCharFeature> features = new ArrayList<ClassCharFeature>();

    public ClassGame(String name, String type) {
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

    public int getMaxTraitPoints() {
        return maxTraitPoints;
    }

    public void setMaxTraitPoints(int maxTraitPoints) {
        this.maxTraitPoints = maxTraitPoints;
    }

    public int getMaxSkillPoints() {
        return maxSkillPoints;
    }

    public void setMaxSkillPoints(int maxSkillPoints) {
        this.maxSkillPoints = maxSkillPoints;
    }

    public int getMaxFeatPoints() {
        return maxFeatPoints;
    }

    public void setMaxFeatPoints(int maxFeatPoints) {
        this.maxFeatPoints = maxFeatPoints;
    }

    public ArrayList<ClassCharRace> getRaces() {
        return races;
    }

    public ArrayList<ClassCharClass> getClasses() {
        return classes;
    }

    public ArrayList<ClassCharAttribute> getAttributes() {
        return attributes;
    }

    public ArrayList<ClassCharSkill> getSkills() {
        return skills;
    }

    public ArrayList<ClassCharTrait> getTraits() {
        return traits;
    }

    public ArrayList<ClassCharFeature> getFeatures() {
        return features;
    }

    public void addRace(ClassCharRace r) {
        races.add(r);
        sortList("Races");
    }

    public void removeRace(ClassCharRace r) {
        races.remove(r);
    }

    public void removeRace(int index) {
        races.remove(index);
    }

    public void addClass(ClassCharClass c) {
        classes.add(c);
        sortList("Classes");
    }

    public void removeClass(ClassCharClass c) {
        classes.remove(c);
    }

    public void removeClass(int index) {
        classes.remove(index);
    }

    public void addAttribute(ClassCharAttribute a) {
        attributes.add(a);
        sortList("Attributes");
    }

    public void removeAttribute(ClassCharAttribute a) {
        attributes.remove(a);
    }

    public void removeAttribute(int index) {
        attributes.remove(index);
    }

    public void addSkill(ClassCharSkill s) {
        skills.add(s);
        sortList("Skills");
    }

    public void removeSkill(ClassCharSkill s) {
        skills.remove(s);
    }

    public void removeSkill(int index) {
        skills.remove(index);
    }

    public void addTrait(ClassCharTrait t) {
        traits.add(t);
        sortList("Traits");
    }

    public void removeTrait(ClassCharTrait t) {
        traits.remove(t);
    }

    public void removeTrait(int index) {
        traits.remove(index);
    }

    public void addFeature(ClassCharFeature f) {
        features.add(f);
        sortList("Features");
    }

    public void removeFeature(ClassCharFeature f) {
        features.remove(f);
    }

    public void removeFeature(int index) {
        features.remove(index);
    }

    public ClassAttributeCreation getAttCreation() {
        return this.attCreation;
    }

    public ClassHitsType getHitsType() {
        return this.hitsType;
    }

    public ClassAdvancementMethod getAdvancementMethod() {
        return advancementMethod;
    }

    public void sortList(String listType) {

        /*  sorts ArrayLists in ascending Alphabetical by name */

        if (listType.equals("Races")) {

            Collections.sort(races, new Comparator<ClassCharRace>() {
                @Override
                public int compare(ClassCharRace item1, ClassCharRace item2) {
                    return item1.getName().compareTo(item2.getName());
                }
            });
        }

        if (listType.equals("Attributes")) {

            Collections.sort(attributes, new Comparator<ClassCharAttribute>() {
                @Override
                public int compare(ClassCharAttribute item1, ClassCharAttribute item2) {
                    return item1.getName().compareTo(item2.getName());
                }
            });
        }

        if (listType.equals("Classes")) {

            Collections.sort(classes, new Comparator<ClassCharClass>() {
                @Override
                public int compare(ClassCharClass item1, ClassCharClass item2) {
                    return item1.getName().compareTo(item2.getName());
                }
            });
        }

        if (listType.equals("Skills")) {

            Collections.sort(skills, new Comparator<ClassCharSkill>() {
                @Override
                public int compare(ClassCharSkill item1, ClassCharSkill item2) {
                    return item1.getName().compareTo(item2.getName());
                }
            });
        }

        if (listType.equals("Traits")) {

            Collections.sort(traits, new Comparator<ClassCharTrait>() {
                @Override
                public int compare(ClassCharTrait item1, ClassCharTrait item2) {
                    return item1.getName().compareTo(item2.getName());
                }
            });
        }

        if (listType.equals("Features")) {

            Collections.sort(features, new Comparator<ClassCharFeature>() {
                @Override
                public int compare(ClassCharFeature item1, ClassCharFeature item2) {
                    return item1.getName().compareTo(item2.getName());
                }
            });
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
