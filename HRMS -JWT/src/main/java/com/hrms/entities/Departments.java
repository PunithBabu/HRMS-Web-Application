/**
 * Represents a department in the HRMS (Human Resource Management System).
 * The class maps to the "departments" table in the database.
 */
package com.hrms.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Departments {
    @Id
    @Column(name = "dept_no", length = 4, nullable = false)
    private String deptNo;

    @Column(name = "dept_name", length = 40, nullable = false)
    private String deptName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<DepartmentManager> departmentManager;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<DepartmentEmployee> departmentEmployee;

    /**
     * Retrieves the department number.
     *
     * @return the department number
     */
    public String getDeptNo() {
        return deptNo;
    }

    /**
     * Sets the department number.
     *
     * @param deptNo the department number
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * Retrieves the department name.
     *
     * @return the department name
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * Sets the department name.
     *
     * @param deptName the department name
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * Retrieves the list of department managers associated with this department.
     *
     * @return the list of department managers
     */
    public List<DepartmentManager> getDepartmentManager() {
        return departmentManager;
    }

    /**
     * Sets the list of department managers associated with this department.
     *
     * @param departmentManager the list of department managers
     */
    public void setDepartmentManager(List<DepartmentManager> departmentManager) {
        this.departmentManager = departmentManager;
    }

    /**
     * Retrieves the list of department employees associated with this department.
     *
     * @return the list of department employees
     */
    public List<DepartmentEmployee> getDepartmentEmployee() {
        return departmentEmployee;
    }

    /**
     * Sets the list of department employees associated with this department.
     *
     * @param departmentEmployee the list of department employees
     */
    public void setDepartmentEmployee(List<DepartmentEmployee> departmentEmployee) {
        this.departmentEmployee = departmentEmployee;
    }

    /**
     * Constructs an empty Departments object.
     */
    public Departments() {
        super();
    }

    /**
     * Constructs a Departments object with the specified parameters.
     *
     * @param deptNo             the department number
     * @param deptName           the department name
     * @param departmentManager  the list of department managers
     * @param departmentEmployee the list of department employees
     */
    public Departments(String deptNo, String deptName, List<DepartmentManager> departmentManager,
                       List<DepartmentEmployee> departmentEmployee) {
        super();
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.departmentManager = departmentManager;
        this.departmentEmployee = departmentEmployee;
    }

}
