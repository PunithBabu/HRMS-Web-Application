/**
 * Handles custom exceptions and provides appropriate error responses for the HRMS (Human Resource Management System) API.
 * The class is annotated with @ControllerAdvice to handle exceptions globally across all controllers.
 */
package com.hrms.exceptions;

import java.util.Date;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles the NotFoundException and returns an appropriate error response.
     *
     * @param nfe the NotFoundException to handle
     * @return the ResponseEntity containing the error response
     */
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NotFoundException nfe) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(nfe.getMessage());
        employeeErrorResponse.setTimeStamp(new Date());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the InvalidDataException and returns an appropriate error response.
     *
     * @param ide the InvalidDataException to handle
     * @return the ResponseEntity containing the error response
     */
    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleException(InvalidDataException ide) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(ide.getMessage());
        employeeErrorResponse.setTimeStamp(new Date());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the DataIntegrityViolationException and returns an appropriate error response.
     *
     * @param dive the DataIntegrityViolationException to handle
     * @return the ResponseEntity containing the error response
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException dive) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(dive.getMessage());
        employeeErrorResponse.setTimeStamp(new Date());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the HttpMessageNotReadableException and returns an appropriate error response.
     *
     * @param hmnre the HttpMessageNotReadableException to handle
     * @return the ResponseEntity containing the error response
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException hmnre) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage("Validation Failed: Inputs in Wrong datatype");
        employeeErrorResponse.setTimeStamp(new Date());

        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

}
