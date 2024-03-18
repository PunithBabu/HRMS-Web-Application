package com.hrms.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.entities.Employee;
import com.hrms.entities.Gender;
import com.hrms.exceptions.NotFoundException;
import com.hrms.repository.DepartmentEmployeeRepository;
import com.hrms.repository.DepartmentManagerRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.SalariesRepository;
import com.hrms.repository.TitlesRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    private SalariesRepository salaryRepository;

    @Autowired
    private TitlesRepository titlesRepository;

    @Autowired
    private DepartmentManagerRepository departmentManagerRespository;

    /**
     * Retrieves all employees.
     *
     * @return The list of all employees.
     */
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    /**
     * Retrieves an employee by employee ID.
     *
     * @param id The employee ID.
     * @return The employee with the specified ID, or null if not found.
     */
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves a list of employees who joined the company in the last 10 years.
     *
     * @return The list of employees who joined in the last 10 years.
     */
    public List<Employee> getEmployeesJoinedLast10Years() {
        LocalDate tenYearsAgo = LocalDate.now().minusYears(10);
        Date tenYearsAgoDate = Date.valueOf(tenYearsAgo);
        return employeeRepository.findEmployeesJoinedLast10Years(tenYearsAgoDate);
    }

    /**
     * Retrieves the count of employees who joined the company in the last 10 years.
     *
     * @return The count of employees who joined in the last 10 years.
     */
    public int getCountEmployeesJoinedLast10Years() {
        LocalDate tenYearsAgo = LocalDate.now().minusYears(10);
        Date tenYearsAgoDate = Date.valueOf(tenYearsAgo);
        return employeeRepository.countEmployeesJoinedLast10Years(tenYearsAgoDate);
    }

    /**
     * Retrieves a list of employees who joined the company after a specific year.
     *
     * @param year The year.
     * @return The list of employees who joined after the specified year.
     */
    public List<Employee> getEmployeesJoinedAfter2005(int year) {
        return employeeRepository.findEmployeesJoinedAfterYear(year);
    }

    /**
     * Retrieves the count of employees who joined the company after a specific year.
     *
     * @param year The year.
     * @return The count of employees who joined after the specified year.
     */
    public int getCountEmployeesJoinedAfter2005(int year) {
        return employeeRepository.countEmployeesJoinedAfterYear(year);
    }

    /**
     * Retrieves a list of employees by first name.
     *
     * @param firstName The first name.
     * @return The list of employees with the specified first name.
     */
    public List<Employee> getEmployeeByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    /**
     * Retrieves a list of employees by last name.
     *
     * @param lastName The last name.
     * @return The list of employees with the specified last name.
     */
    public List<Employee> getEmployeeByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    /**
     * Retrieves a list of employees by email.
     *
     * @param email The email.
     * @return The list of employees with the specified email.
     */
    public List<Employee> getEmployeeByemail(String email) {
        return employeeRepository.findByEmailContains(email);
    }

    /**
     * Retrieves a list of employees by gender.
     *
     * @param gender The gender.
     * @return The list of employees with the specified gender.
     */
    public List<Employee> getEmployeesByGender(Gender gender) {
        try {
            List<Employee> records = employeeRepository.findByGenderContains(gender);
            System.out.println(records);
            return records;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the count of employees by gender.
     *
     * @param gender The gender.
     * @return The count of employees with the specified gender.
     */
    public int getGenderCount(Gender gender) {
        return employeeRepository.findByGenderCount(gender);
    }

    /**
     * Retrieves a list of employees with a certain years of experience.
     *
     * @param yearsOfExperience The years of experience.
     * @return The list of employees with the specified years of experience.
     */
    public List<Employee> getAwardedEmployeesWithExperience(int yearsOfExperience) {
        return employeeRepository.findEmployeesWithExperience(yearsOfExperience);
    }

    /**
     * Retrieves a list of employees by hire date.
     *
     * @param hireDate The hire date.
     * @return The list of employees with the specified hire date.
     */
    public List<Employee> getEmployeesByHireDate(Date hireDate) {
        return employeeRepository.findByHireDate(hireDate);
    }

    /**
     * Retrieves a list of employees by birth date.
     *
     * @param birthDate The birth date.
     * @return The list of employees with the specified birth date.
     */
    public List<Employee> getEmployeesByBirthDate(Date birthDate) {
        return employeeRepository.findByBirthDate(birthDate);
    }

    /**
     * Retrieves all employees sorted by hire date in descending order.
     *
     * @return The list of all employees sorted by hire date in descending order.
     */
    public List<Employee> getAllEmployeesSortedByHireDateDesc() {
        return employeeRepository.findAllByOrderByHireDateDesc();
    }

    /**
     * Adds a new employee.
     *
     * @param employee The employee to add.
     * @return The newly added employee.
     */
    public Employee addEmployee(Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        return newEmployee;
    }

    /**
     * Updates the first name and last name of an employee.
     *
     * @param employee The employee with updated first name and last name.
     * @return The updated employee.
     * @throws NotFoundException If the employee is not found.
     */
    public Employee updateEmployeefirstNameLastName(Employee employee) {
        Employee readEmployee = employeeRepository.findById(employee.getEmpNo()).orElse(null);
        if (readEmployee != null) {
            readEmployee.setFirstName(employee.getFirstName());
            readEmployee.setLastName(employee.getLastName());
            return employeeRepository.save(readEmployee);
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    /**
     * Updates the information of an employee.
     *
     * @param employee The employee with updated information.
     * @param empNo    The employee number.
     * @return The updated employee.
     */
    public Employee updateEmployee(Employee employee, int empNo) {
        Employee readEmployee = employeeRepository.findById(empNo).orElse(null);
        if (readEmployee != null) {
            readEmployee.setFirstName(employee.getFirstName());
            readEmployee.setLastName(employee.getLastName());
            readEmployee.setBirthDate(employee.getBirthDate());
            readEmployee.setHireDate(employee.getHireDate());
            readEmployee.setGender(employee.getGender());
            readEmployee.setEmail(employee.getEmail());
            readEmployee.setPassword(employee.getPassword());
            return employeeRepository.save(readEmployee);
        }
        return null;
    }

    /**
     * Updates the first name of an employee.
     *
     * @param employee The employee with updated first name.
     * @param empNo    The employee number.
     * @return The updated employee.
     */
    public Employee updateEmployeeFirstName(Employee employee, int empNo) {
        Employee readEmployee = employeeRepository.findById(empNo).orElse(null);
        if (readEmployee != null) {
            readEmployee.setFirstName(employee.getFirstName());
            return employeeRepository.save(readEmployee);
        }
        return null;
    }

    /**
     * Updates the hire date of an employee.
     *
     * @param employee The employee with updated hire date.
     * @param empNo    The employee number.
     * @return The updated employee.
     */
    public Employee updateEmployeeByHireDate(Employee employee, int empNo) {
        Employee readEmployee = employeeRepository.findById(empNo).orElse(null);
        readEmployee.setHireDate(employee.getHireDate());
        return employeeRepository.save(readEmployee);
    }

    /**
     * Updates the birth date of an employee.
     *
     * @param employee The employee with updated birth date.
     * @param empNo    The employee number.
     * @return The updated employee.
     */
    public Employee updateEmployeeBirthDate(Employee employee, int empNo) {
        Employee readEmployee = employeeRepository.findById(empNo).orElse(null);
        readEmployee.setBirthDate(employee.getBirthDate());
        return employeeRepository.save(readEmployee);
    }

    /**
     * Updates the email and password of an employee.
     *
     * @param employee The employee with updated email and password.
     * @return The updated employee.
     */
    public Employee upadateEmailAndPassword(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Finds an employee by email and password.
     *
     * @param email    The email.
     * @param password The password.
     * @return The employee with the specified email and password, or null if not found.
     */
    public Employee findByEmailAndPassword(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }

    /**
     * Finds an employee by employee number.
     *
     * @param empNo The employee number.
     * @return The optional containing the employee if found, or an empty optional if not found.
     */
    public Optional<Employee> findByempNo(int empNo) {
        return employeeRepository.findById(empNo);
    }

    /**
     * Updates an employee.
     *
     * @param employee The updated employee.
     * @return The updated employee.
     */
    public Employee updateemployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Retrieves the ID of an employee by email.
     *
     * @param email The email.
     * @return The ID of the employee with the specified email.
     */
    public int getIdByEmail(String email) {
        Employee emp = employeeRepository.findByEmail(email);
        int id = emp.getEmpNo();
        return id;
    }

    /**
     * Retrieves a list of employees by hire date range.
     *
     * @param startYear The start year of the range.
     * @param endYear   The end year of the range.
     * @return The list of employees within the specified hire date range.
     */
    public List<Employee> getEmployeesByHireDateRange(int startYear, int endYear) {
        return employeeRepository.findEmployeesByHireDateRange(startYear, endYear);
    }

    /**
     * Deletes an employee by employee number.
     *
     * @param empNo The employee number.
     */
    @Transactional
    public void deleteByEmpNo(int empNo) {
        employeeRepository.deleteByempNo(empNo);
    }

    /**
     * Deletes an employee and related data by employee number.
     *
     * @param empNo The employee number.
     */
    @Transactional
    public void deleteEmployeeByEmpNo(int empNo) {
        Employee employee = employeeRepository.findById(empNo).orElse(null);
        if (employee != null) {
            departmentEmployeeRepository.deleteByEmpNo(empNo);
            salaryRepository.deleteByEmployeeEmpNo(empNo);
            titlesRepository.deleteByEmployee_EmpNo(empNo);
            departmentManagerRespository.deleteByEmpNo(empNo);
            employeeRepository.deleteByempNo(empNo);
        }
    }

    /**
     * Resets the password of an employee.
     *
     * @param empNo    The employee number.
     * @param email    The email.
     * @param employee The employee information containing the new password.
     * @return The updated employee if the password reset is successful.
     * @throws NotFoundException If the employee is not found.
     */
    public Employee forgotPassword(int empNo, String email, Employee employee) {
        Employee readEmployee = employeeRepository.findByEmpNoAndEmail(empNo, email);
        if (readEmployee != null) {
            readEmployee.setPassword(employee.getPassword());
            System.out.println("password in Service " + employee.getPassword());
            System.out.println("email in Service " + readEmployee);
            System.out.println("employee object" + employee);
            return employeeRepository.save(readEmployee);
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    /**
     * Updates the details of an employee.
     *
     * @param employee The updated employee details.
     * @param empNo    The employee number.
     * @return The updated employee.
     */
    public Employee updateEmployeeDetials(Employee employee, int empNo) {
        Employee readEmployee = employeeRepository.findById(empNo).orElse(null);
        if (readEmployee != null) {
            readEmployee.setFirstName(employee.getFirstName());
            readEmployee.setLastName(employee.getLastName());
            readEmployee.setBirthDate(employee.getBirthDate());
            readEmployee.setPassword(employee.getPassword());
            return employeeRepository.save(readEmployee);
        }
        return null;
    }
}
