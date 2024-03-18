/**
 * Represents an employee in the HRMS (Human Resource Management System).
 * The class maps to the "employees" table in the database.
 */
package com.hrms.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "emp_no", nullable = false)
    private int empNo;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "first_name", nullable = false, length = 14)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 16)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = true)
    private Gender gender;

    @Column(name = "hire_date", nullable = false)
    private Date hireDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DepartmentManager> departmentManager;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DepartmentEmployee> departmentEmployee;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Salaries> salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Titles> titles;

    /**
     * Retrieves the employee's role.
     *
     * @return the employee's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the employee's role.
     *
     * @param role the employee's role
     */
    public void setRole(String role) {
        this.role = "USER";
    }

    /**
     * Retrieves the employee number.
     *
     * @return the employee number
     */
    public int getEmpNo() {
        return empNo;
    }

    /**
     * Sets the employee number.
     *
     * @param empNo the employee number
     */
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    /**
     * Retrieves the employee's birth date.
     *
     * @return the employee's birth date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the employee's birth date.
     *
     * @param birthDate the employee's birth date
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retrieves the employee's first name.
     *
     * @return the employee's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the employee's first name.
     *
     * @param firstName the employee's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the employee's last name.
     *
     * @return the employee's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the employee's last name.
     *
     * @param lastName the employee's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the employee's gender.
     *
     * @return the employee's gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the employee's gender.
     *
     * @param gender the employee's gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Retrieves the employee's hire date.
     *
     * @return the employee's hire date
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * Sets the employee's hire date.
     *
     * @param hireDate the employee's hire date
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Retrieves the employee's email.
     *
     * @return the employee's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the employee's email.
     *
     * @param email the employee's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the employee's password.
     *
     * @return the employee's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the employee's password.
     *
     * @param password the employee's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Constructs an Employee object with the specified parameters.
     *
     * @param firstName  the employee's first name
     * @param lastName   the employee's last name
     * @param email      the employee's email
     * @param password   the employee's password
     * @param gender     the employee's gender
     * @param hireDate   the employee's hire date
     * @param salary     the list of salaries associated with the employee
     * @param titles     the list of titles associated with the employee
     */
    public Employee(String firstName, String lastName, String email, String password, Gender gender, Date hireDate,
                    List<Salaries> salary, List<Titles> titles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.hireDate = hireDate;
        this.salary = salary;
        this.titles = titles;
    }

    /**
     * Constructs an empty Employee object.
     */
    public Employee() {
        super();
    }
}
