/**
 * Represents a department manager in the HRMS (Human Resource Management System).
 * The class maps to the "dept_manager" table in the database and uses composite primary keys.
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
@Table(name = "dept_manager")
@IdClass(DepartmentManagerId.class)
public class DepartmentManager {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_no")
    private Employee employee;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_no")
    private Departments department;

    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "to_date", nullable = false)
    private Date toDate;

    /**
     * Constructs an empty DepartmentManager object.
     */
    public DepartmentManager() {
        super();
    }

    /**
     * Constructs a DepartmentManager object with the specified parameters.
     *
     * @param employee   the employee assigned as the department manager
     * @param department the department associated with the manager
     * @param fromDate   the start date of the manager's assignment
     * @param toDate     the end date of the manager's assignment
     */
    public DepartmentManager(Employee employee, Departments department, Date fromDate, Date toDate) {
        super();
        this.employee = employee;
        this.department = department;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Retrieves the employee assigned as the department manager.
     *
     * @return the employee assigned as the department manager
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee assigned as the department manager.
     *
     * @param employee the employee assigned as the department manager
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Retrieves the department associated with the manager.
     *
     * @return the department associated with the manager
     */
    public Departments getDepartment() {
        return department;
    }

    /**
     * Sets the department associated with the manager.
     *
     * @param department the department associated with the manager
     */
    public void setDepartment(Departments department) {
        this.department = department;
    }

    /**
     * Retrieves the start date of the manager's assignment.
     *
     * @return the start date of the manager's assignment
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Sets the start date of the manager's assignment.
     *
     * @param fromDate the start date of the manager's assignment
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Retrieves the end date of the manager's assignment.
     *
     * @return the end date of the manager's assignment
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Sets the end date of the manager's assignment.
     *
     * @param toDate the end date of the manager's assignment
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

}
