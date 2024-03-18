/**
 * Represents an employee's title in the HRMS (Human Resource Management System).
 * The class maps to the "titles" table in the database and uses composite primary keys.
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
import jakarta.persistence.Table;

@Entity
@Table(name = "titles")
@IdClass(TitlesId.class)
public class Titles {

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Id
    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_no")
    private Employee employee;

    /**
     * Retrieves the employee's title.
     *
     * @return the employee's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the employee's title.
     *
     * @param title the employee's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the start date of the title.
     *
     * @return the start date of the title
     */
    public Date getFromDate() {
        return fromDate;
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
     * Retrieves the end date of the title.
     *
     * @return the end date of the title
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Sets the end date of the title.
     *
     * @param toDate the end date of the title
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
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
     * Constructs an empty Titles object.
     */
    public Titles() {
        super();
    }

    /**
     * Constructs a Titles object with the specified parameters.
     *
     * @param title    the employee's title
     * @param fromDate the start date of the title
     * @param toDate   the end date of the title
     * @param employee the employee associated with the title
     */
    public Titles(String title, Date fromDate, Date toDate, Employee employee) {
        super();
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.employee = employee;
    }

}
