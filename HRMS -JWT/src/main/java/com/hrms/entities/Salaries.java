/**
 * Represents an employee's salary in the HRMS (Human Resource Management System).
 * The class maps to the "salaries" table in the database and uses composite primary keys.
 */
package com.hrms.entities;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(SalaryId.class)
public class Salaries {

    @Id
    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "salary", nullable = false)
    private int salary;

    @Column(name = "to_date", nullable = false)
    private Date toDate;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_no")
    private Employee employee;

    /**
     * Retrieves the employee's salary.
     *
     * @return the employee's salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Sets the employee's salary.
     *
     * @param salary the employee's salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

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
     * Retrieves the end date of the salary.
     *
     * @return the end date of the salary
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Sets the end date of the salary.
     *
     * @param toDate the end date of the salary
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
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
     * Constructs an empty Salaries object.
     */
    public Salaries() {
        super();
    }

    /**
     * Constructs a Salaries object with the specified parameters.
     *
     * @param fromDate the start date of the salary
     * @param salary   the employee's salary
     * @param toDate   the end date of the salary
     * @param employee the employee associated with the salary
     */
    public Salaries(Date fromDate, int salary, Date toDate, Employee employee) {
        super();
        this.fromDate = fromDate;
        this.salary = salary;
        this.toDate = toDate;
        this.employee = employee;
    }

}
