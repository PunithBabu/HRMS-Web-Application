/**
 * Converts the Gender enumeration to a string representation for database persistence and vice versa.
 * The conversion is performed using the AttributeConverter interface provided by the Jakarta Persistence API.
 * This converter is applied automatically to all attributes of type Gender.
 */
package com.hrms.entities;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    /**
     * Converts the Gender enumeration value to a string representation for database storage.
     *
     * @param attribute the Gender enumeration value
     * @return the string representation of the Gender value
     */
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getGender();
    }

    /**
     * Converts the string value from the database to the corresponding Gender enumeration value.
     *
     * @param dbData the string value from the database
     * @return the corresponding Gender enumeration value
     * @throws IllegalArgumentException if the database value does not match any Gender enumeration value
     */
    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(Gender.values())
                .filter(g -> g.getGender().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
