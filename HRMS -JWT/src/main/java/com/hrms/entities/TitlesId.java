/**
 * Represents the composite primary key for the Titles class in the HRMS (Human Resource Management System).
 * The class is used to uniquely identify an employee's title record.
 */
package com.hrms.entities;

import java.io.Serializable;
import java.sql.Date;

public class TitlesId implements Serializable {

    private Date fromDate;
    private Employee employee;

    /**
     * Retrieves the start date of the title.
     *
     * @return the start date of the title
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Retrieves the employee associated with the title.
     *
     * @return the employee associated with the title
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee associated with the title.
     *
     * @param employee the employee associated with the title
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Sets the start date of the title.
     *
     * @param fromDate the start date of the title
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Constructs a TitlesId object with the specified parameters.
     *
     * @param fromDate the start date of the title
     * @param employee the employee associated with the title
     */
    public TitlesId(Date fromDate, Employee employee) {
        super();
        this.fromDate = fromDate;
        this.employee = employee;
    }

    /**
     * Constructs an empty TitlesId object.
     */
    public TitlesId() {
        super();
    }

}
