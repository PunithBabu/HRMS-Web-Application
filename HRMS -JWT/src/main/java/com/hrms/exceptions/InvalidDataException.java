/**
 * Represents an exception indicating invalid data in the HRMS (Human Resource Management System).
 * This exception is thrown when there is an issue with the provided data that does not meet the required constraints.
 */
package com.hrms.exceptions;

public class InvalidDataException extends RuntimeException {

    /**
     * Constructs an InvalidDataException with the specified error message.
     *
     * @param message the error message describing the invalid data issue
     */
    public InvalidDataException(String message) {
        super(message);
    }
}
