/**
 * Represents a request to create an employee in the HRMS (Human Resource Management System).
 * The request includes information about the employee, department assignment, salary, title, and department.
 */
package com.hrms.entities;

public class CreateEmployeeRequest {
    private Employee employee;
    private DepartmentEmployee departmentEmployee;
    private Salaries salary;
    private Titles title;
    private Departments department;

    /**
     * Constructs a CreateEmployeeRequest object with the specified parameters.
     *
     * @param employee           the employee information
     * @param departmentEmployee the department employee information
     * @param salary             the employee's salary information
     * @param title              the employee's title information
     * @param department         the department information
     */
    public CreateEmployeeRequest(Employee employee, DepartmentEmployee departmentEmployee, Salaries salary,
                                 Titles title, Departments department) {
        this.employee = employee;
        this.departmentEmployee = departmentEmployee;
        this.salary = salary;
        this.title = title;
        this.department = department;
    }

    /**
     * Constructs an empty CreateEmployeeRequest object.
     */
    public CreateEmployeeRequest() {
    }

    /**
     * Retrieves the department associated with the employee.
     *
     * @return the department of the employee
     */
    public Departments getDepartment() {
        return department;
    }

    /**
     * Sets the department of the employee.
     *
     * @param department the department of the employee
     */
    public void setDepartment(Departments department) {
        this.department = department;
    }

    /**
     * Retrieves the employee information.
     *
     * @return the employee information
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee information.
     *
     * @param employee the employee information
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Retrieves the department employee information.
     *
     * @return the department employee information
     */
    public DepartmentEmployee getDepartmentEmployee() {
        return departmentEmployee;
    }

    /**
     * Sets the department employee information.
     *
     * @param departmentEmployee the department employee information
     */
    public void setDepartmentEmployee(DepartmentEmployee departmentEmployee) {
        this.departmentEmployee = departmentEmployee;
    }

    /**
     * Retrieves the employee's salary information.
     *
     * @return the employee's salary information
     */
    public Salaries getSalary() {
        return salary;
    }

    /**
     * Sets the employee's salary information.
     *
     * @param salary the employee's salary information
     */
    public void setSalary(Salaries salary) {
        this.salary = salary;
    }

    /**
     * Retrieves the employee's title information.
     *
     * @return the employee's title information
     */
    public Titles getTitle() {
        return title;
    }

    /**
     * Sets the employee's title information.
     *
     * @param title the employee's title information
     */
    public void setTitle(Titles title) {
        this.title = title;
    }
}
