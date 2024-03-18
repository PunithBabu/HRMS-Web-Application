/**
 * An enumeration of genders.
 */
package com.hrms.entities;

public enum Gender {

    /**
     * Male gender.
     */
    M("M"),

    /**
     * Female gender.
     */
    F("F");

    private final String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the gender as a string.
     *
     * @return The gender as a string.
     */
    public String getGender() {
        return gender;
    }
}