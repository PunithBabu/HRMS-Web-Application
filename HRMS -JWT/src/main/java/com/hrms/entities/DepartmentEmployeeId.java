/**
 * Represents the composite primary key for the DepartmentEmployee class in the HRMS (Human Resource Management System).
 * The class is used to uniquely identify a department employee assignment.
 */
package com.hrms.entities;

import java.io.Serializable;

public class DepartmentEmployeeId implements Serializable {

    private Departments department;
    private Employee employee;

    /**
     * Retrieves the department associated with the department employee assignment.
     *
     * @return the department associated with the department employee assignment
     */
    public Departments getDepartment() {
        return department;
    }

    /**
     * Sets the department associated with the department employee assignment.
     *
     * @param department the department associated with the department employee assignment
     */
    public void setDepartment(Departments department) {
        this.department = department;
    }

    /**
     * Retrieves the employee associated with the department employee assignment.
     *
     * @return the employee associated with the department employee assignment
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee associated with the department employee assignment.
     *
     * @param employee the employee associated with the department employee assignment
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Constructs a DepartmentEmployeeId object with the specified parameters.
     *
     * @param department the department associated with the department employee assignment
     * @param employee   the employee associated with the department employee assignment
     */
    public DepartmentEmployeeId(Departments department, Employee employee) {
        this.department = department;
        this.employee = employee;
    }

    /**
     * Constructs an empty DepartmentEmployeeId object.
     */
    public DepartmentEmployeeId() {
    }

}
