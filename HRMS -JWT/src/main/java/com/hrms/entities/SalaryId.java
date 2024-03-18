/**
 * Represents the composite primary key for the Salaries class in the HRMS (Human Resource Management System).
 * The class is used to uniquely identify an employee's salary record.
 */
package com.hrms.entities;

import java.io.Serializable;
import java.sql.Date;

public class SalaryId implements Serializable {

    private Date fromDate;
    private Employee employee;

    /**
     * Retrieves the start date of the salary.
     *
     * @return the start date of the salary
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Sets the start date of the salary.
     *
     * @param fromDate the start date of the salary
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Retrieves the employee associated with the salary.
     *
     * @return the employee associated with the salary
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee associated with the salary.
     *
     * @param employee the employee associated with the salary
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Constructs a SalaryId object with the specified parameters.
     *
     * @param fromDate the start date of the salary
     * @param employee the employee associated with the salary
     */
    public SalaryId(Date fromDate, Employee employee) {
        super();
        this.fromDate = fromDate;
        this.employee = employee;
    }

    /**
     * Constructs an empty SalaryId object.
     */
    public SalaryId() {
        super();
    }

}
