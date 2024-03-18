package com.hrms.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hrms.entities.Employee;
import com.hrms.entities.Gender;
import com.hrms.exceptions.NotFoundException;
import com.hrms.repository.DepartmentEmployeeRepository;
import com.hrms.repository.DepartmentManagerRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.SalariesRepository;
import com.hrms.repository.TitlesRepository;
import com.hrms.services.EmployeeService;

/**
 * Unit tests for the EmployeeService class.
 */
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Mock
    private SalariesRepository salaryRepository;

    @Mock
    private TitlesRepository titlesRepository;

    @Mock
    private DepartmentManagerRepository departmentManagerRespository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for getting all employees.
     */
    @Test
    void testGetEmployee() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> response = employeeService.getEmployee();

        assertEquals(employees, response);
    }

    /**
     * Test case for getting an employee by ID.
     */
    @Test
    void testGetEmployeeById() {
        int id = 1;
        Employee employee = new Employee();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Employee response = employeeService.getEmployeeById(id);

        assertEquals(employee, response);
    }

    /**
     * Test case for getting employees joined after a specific year.
     */
    @Test
    void testGetEmployeesJoinedAfter2005() {
        int year = 2005;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findEmployeesJoinedAfterYear(year)).thenReturn(employees);

        List<Employee> response = employeeService.getEmployeesJoinedAfter2005(year);

        assertEquals(employees, response);
    }

    /**
     * Test case for getting the count of employees joined after a specific year.
     */
    @Test
    void testGetCountEmployeesJoinedAfter2005() {
        int year = 2005;
        int count = 3;

        when(employeeRepository.countEmployeesJoinedAfterYear(year)).thenReturn(count);

        int response = employeeService.getCountEmployeesJoinedAfter2005(year);

        assertEquals(count, response);
    }

    /**
     * Test case for getting employees by first name.
     */
    @Test
    void testGetEmployeeByFirstName() {
        String firstName = "John";
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findByFirstName(firstName)).thenReturn(employees);

        List<Employee> response = employeeService.getEmployeeByFirstName(firstName);

        assertEquals(employees, response);
    }

    /**
     * Test case for getting employees by last name.
     */
    @Test
    void testGetEmployeeByLastName() {
        String lastName = "Doe";
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findByLastName(lastName)).thenReturn(employees);

        List<Employee> response = employeeService.getEmployeeByLastName(lastName);

        assertEquals(employees, response);
    }

    /**
     * Test case for getting employees by email.
     */
    @Test
    void testGetEmployeeByemail() {
        String email = "john.doe@example.com";
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findByEmailContains(email)).thenReturn(employees);

        List<Employee> response = employeeService.getEmployeeByemail(email);

        assertEquals(employees, response);
    }

    /**
     * Test case for getting employees by gender.
     */
    @Test
    void testGetEmployeesByGender() {
        Gender gender = Gender.M;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findByGenderContains(gender)).thenReturn(employees);

        List<Employee> response = employeeService.getEmployeesByGender(gender);

        assertEquals(employees, response);
    }

    /**
     * Test case for getting the count of employees by gender.
     */
    @Test
    void testGetGenderCount() {
        Gender gender = Gender.F;
        int count = 2;

        when(employeeRepository.findByGenderCount(gender)).thenReturn(count);

        int response = employeeService.getGenderCount(gender);

        assertEquals(count, response);
    }

    /**
     * Test case for getting awarded employees with a specific years of experience.
     */
    @Test
    void testGetAwardedEmployeesWithExperience() {
        int yearsOfExperience = 5;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findEmployeesWithExperience(yearsOfExperience)).thenReturn(employees);

        List<Employee> response = employeeService.getAwardedEmployeesWithExperience(yearsOfExperience);

        assertEquals(employees, response);
    }

    /**
     * Test case for getting employees by hire date.
     */
    @Test
    void testGetEmployeesByHireDate() {
        Date hireDate = Date.valueOf("2020-01-01");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findByHireDate(hireDate)).thenReturn(employees);

        List<Employee> response = employeeService.getEmployeesByHireDate(hireDate);

        assertEquals(employees, response);
    }

    /**
     * Test case for getting employees by birth date.
     */
    @Test
    void testGetEmployeesByBirthDate() {
        Date birthDate = Date.valueOf("1990-01-01");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findByBirthDate(birthDate)).thenReturn(employees);

        List<Employee> response = employeeService.getEmployeesByBirthDate(birthDate);

        assertEquals(employees, response);
    }

    /**
     * Test case for adding an employee.
     */
    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        Employee savedEmployee = new Employee();
        savedEmployee.setEmpNo(1);

        when(employeeRepository.save(employee)).thenReturn(savedEmployee);

        Employee response = employeeService.addEmployee(employee);

        assertNotNull(response);
        assertEquals(savedEmployee.getEmpNo(), response.getEmpNo());
    }

    /**
     * Test case for updating an employee.
     */
    @Test
    void testUpdateEmployee() {
        int empNo = 1;
        Employee employee = new Employee();
        employee.setEmpNo(empNo);

        Employee readEmployee = new Employee();
        readEmployee.setEmpNo(empNo);

        when(employeeRepository.findById(empNo)).thenReturn(Optional.of(readEmployee));
        when(employeeRepository.save(readEmployee)).thenReturn(readEmployee);

        Employee response = employeeService.updateEmployee(employee, empNo);

        assertNotNull(response);
        assertEquals(empNo, response.getEmpNo());
    }

//    @Test
//    void testUpdateEmployeeNotFound() {
//        int empNo = 1;
//        Employee employee = new Employee();
//        employee.setEmpNo(empNo);
//
//        when(employeeRepository.findById(empNo)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> employeeService.updateEmployee(employee, empNo));
//    }

    /**
     * Test case for updating an employee's first name and last name.
     */
    @Test
    void testUpdateEmployeefirstNameLastName() {
        int empNo = 1;
        Employee employee = new Employee();
        employee.setEmpNo(empNo);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        Employee readEmployee = new Employee();
        readEmployee.setEmpNo(empNo);

        when(employeeRepository.findById(empNo)).thenReturn(Optional.of(readEmployee));
        when(employeeRepository.save(readEmployee)).thenReturn(readEmployee);

        Employee response = employeeService.updateEmployeefirstNameLastName(employee);

        assertNotNull(response);
        assertEquals(empNo, response.getEmpNo());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
    }

    /**
     * Test case for updating an employee's first name and last name when the employee is not found.
     */
    @Test
    void testUpdateEmployeefirstNameLastNameNotFound() {
        int empNo = 1;
        Employee employee = new Employee();
        employee.setEmpNo(empNo);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeRepository.findById(empNo)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> employeeService.updateEmployeefirstNameLastName(employee));
    }

    /**
     * Test case for updating an employee's email and password.
     */
    @Test
    void testUpadateEmailAndPassword() {
        Employee employee = new Employee();
        employee.setEmail("john.doe@example.com");
        employee.setPassword("password");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee response = employeeService.upadateEmailAndPassword(employee);

        assertEquals(employee, response);
    }

    /**
     * Test case for finding an employee by email and password.
     */
    @Test
    void testFindByEmailAndPassword() {
        String email = "john.doe@example.com";
        String password = "password";
        Employee employee = new Employee();

        when(employeeRepository.findByEmailAndPassword(email, password)).thenReturn(employee);

        Employee response = employeeService.findByEmailAndPassword(email, password);

        assertEquals(employee, response);
    }

    /**
     * Test case for finding an employee by employee number.
     */
    @Test
    void testFindByempNo() {
        int empNo = 1;
        Employee employee = new Employee();

        when(employeeRepository.findById(empNo)).thenReturn(Optional.of(employee));

        Optional<Employee> response = employeeService.findByempNo(empNo);

        assertEquals(Optional.of(employee), response);
    }

    /**
     * Test case for updating an employee.
     */
    @Test
    void testUpdateemployee() {
        Employee employee = new Employee();

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee response = employeeService.updateemployee(employee);

        assertEquals(employee, response);
    }

    /**
     * Test case for getting the employee ID by email.
     */
    @Test
    void testGetIdByEmail() {
        String email = "john.doe@example.com";
        int empNo = 1;

        Employee employee = new Employee();
        employee.setEmpNo(empNo);

        when(employeeRepository.findByEmail(email)).thenReturn(employee);

        int response = employeeService.getIdByEmail(email);

        assertEquals(empNo, response);
    }

    /**
     * Test case for getting employees by hire date range.
     */
    @Test
    void testGetEmployeesByHireDateRange() {
        int startYear = 2010;
        int endYear = 2020;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findEmployeesByHireDateRange(startYear, endYear)).thenReturn(employees);

        List<Employee> response = employeeService.getEmployeesByHireDateRange(startYear, endYear);

        assertEquals(employees, response);
    }

    /**
     * Test case for deleting an employee by employee number.
     */
    @Test
    void testDeleteByEmpNo() {
        int empNo = 1;

        employeeService.deleteByEmpNo(empNo);

        verify(employeeRepository, times(1)).deleteByempNo(empNo);
    }

    /**
     * Test case for deleting an employee by employee number and cascading delete related entities.
     */
    @Test
    void testDeleteEmployeeByEmpNo() {
        int empNo = 1;
        Employee employee = new Employee();

        when(employeeRepository.findById(empNo)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeByEmpNo(empNo);

        verify(departmentEmployeeRepository, times(1)).deleteByEmpNo(empNo);
        verify(salaryRepository, times(1)).deleteByEmployeeEmpNo(empNo);
        verify(titlesRepository, times(1)).deleteByEmployee_EmpNo(empNo);
        verify(departmentManagerRespository, times(1)).deleteByEmpNo(empNo);
        verify(employeeRepository, times(1)).deleteByempNo(empNo);
    }

    /**
     * Test case for resetting the password of an employee.
     */
    @Test
    void testForgotPassword() {
        int empNo = 1;
        String email = "john.doe@example.com";
        Employee employee = new Employee();
        employee.setEmpNo(empNo);
        employee.setEmail(email);
        employee.setPassword("newpassword");

        Employee readEmployee = new Employee();
        readEmployee.setEmpNo(empNo);
        readEmployee.setEmail(email);

        when(employeeRepository.findByEmpNoAndEmail(empNo, email)).thenReturn(readEmployee);
        when(employeeRepository.save(readEmployee)).thenReturn(readEmployee);

        Employee response = employeeService.forgotPassword(empNo, email, employee);

        assertNotNull(response);
        assertEquals(empNo, response.getEmpNo());
        assertEquals("newpassword", response.getPassword());
    }

    /**
     * Test case for resetting the password of an employee when the employee is not found.
     */
    @Test
    void testForgotPasswordNotFound() {
        int empNo = 1;
        String email = "john.doe@example.com";
        Employee employee = new Employee();
        employee.setEmpNo(empNo);
        employee.setEmail(email);
        employee.setPassword("newpassword");

        when(employeeRepository.findByEmpNoAndEmail(empNo, email)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> employeeService.forgotPassword(empNo, email, employee));
    }
}
