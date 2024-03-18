/**
 * Represents an exception indicating a resource not found in the HRMS (Human Resource Management System).
 * This exception is thrown when a requested resource cannot be found.
 */
package com.hrms.exceptions;

public class NotFoundException extends RuntimeException {

    /**
     * Constructs a NotFoundException with the specified error message.
     *
     * @param message the error message indicating the resource not found
     */
    public NotFoundException(String message) {
        super(message);
    }

}
