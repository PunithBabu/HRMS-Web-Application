/**
 * Represents the composite primary key for the DepartmentManager class in the HRMS (Human Resource Management System).
 * The class is used to uniquely identify a department manager assignment.
 */
package com.hrms.entities;

import java.io.Serializable;

public class DepartmentManagerId implements Serializable {

    private Departments department;
    private Employee employee;

    /**
     * Retrieves the department associated with the department manager assignment.
     *
     * @return the department associated with the department manager assignment
     */
    public Departments getDepartment() {
        return department;
    }

    /**
     * Sets the department associated with the department manager assignment.
     *
     * @param department the department associated with the department manager assignment
     */
    public void setDepartment(Departments department) {
        this.department = department;
    }

    /**
     * Retrieves the employee associated with the department manager assignment.
     *
     * @return the employee associated with the department manager assignment
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee associated with the department manager assignment.
     *
     * @param employee the employee associated with the department manager assignment
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Constructs a DepartmentManagerId object with the specified parameters.
     *
     * @param department the department associated with the department manager assignment
     * @param employee   the employee associated with the department manager assignment
     */
    public DepartmentManagerId(Departments department, Employee employee) {
        this.department = department;
        this.employee = employee;
    }

    /**
     * Constructs an empty DepartmentManagerId object.
     */
    public DepartmentManagerId() {
    }

}
