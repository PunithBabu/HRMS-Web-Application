/**
 * Represents an error response for the HRMS (Human Resource Management System) API.
 * The class contains information about the status code, error message, and timestamp of the error.
 */
package com.hrms.exceptions;

import java.util.Date;

public class ErrorResponse {

    private int status;
    private String message;
    private Date timeStamp;

    /**
     * Retrieves the status code of the error.
     *
     * @return the status code of the error
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status code of the error.
     *
     * @param status the status code of the error
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Retrieves the error message.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     *
     * @param message the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the timestamp of the error.
     *
     * @return the timestamp of the error
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the timestamp of the error.
     *
     * @param date the timestamp of the error
     */
    public void setTimeStamp(Date date) {
        this.timeStamp = date;
    }
}
