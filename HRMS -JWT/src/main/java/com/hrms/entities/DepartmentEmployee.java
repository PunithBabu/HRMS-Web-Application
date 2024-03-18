/**
 * Represents a department employee in the HRMS (Human Resource Management System).
 * The class maps to the "dept_emp" table in the database and uses composite primary keys.
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
@Table(name = "dept_emp")
@IdClass(DepartmentEmployeeId.class)
public class DepartmentEmployee {

    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "to_date", nullable = false)
    private Date toDate;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_no")
    private Employee employee;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_no")
    private Departments department;

    /**
     * Retrieves the start date of the department assignment for the employee.
     *
     * @return the start date of the department assignment
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Sets the start date of the department assignment for the employee.
     *
     * @param fromDate the start date of the department assignment
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Retrieves the end date of the department assignment for the employee.
     *
     * @return the end date of the department assignment
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Sets the end date of the department assignment for the employee.
     *
     * @param toDate the end date of the department assignment
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * Retrieves the employee associated with the department assignment.
     *
     * @return the employee associated with the department assignment
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee associated with the department assignment.
     *
     * @param employee the employee associated with the department assignment
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Retrieves the department associated with the employee assignment.
     *
     * @return the department associated with the employee assignment
     */
    public Departments getDepartment() {
        return department;
    }

    /**
     * Sets the department associated with the employee assignment.
     *
     * @param department the department associated with the employee assignment
     */
    public void setDepartment(Departments department) {
        this.department = department;
    }

    /**
     * Constructs a DepartmentEmployee object with the specified parameters.
     *
     * @param fromDate   the start date of the department assignment
     * @param toDate     the end date of the department assignment
     * @param employee   the employee associated with the department assignment
     * @param department the department associated with the employee assignment
     */
    public DepartmentEmployee(Date fromDate, Date toDate, Employee employee, Departments department) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.employee = employee;
        this.department = department;
    }

    /**
     * Constructs an empty DepartmentEmployee object.
     */
    public DepartmentEmployee() {
    }

}
