package com.hrms.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hrms.controller.EmployeeController;
import com.hrms.entities.CreateEmployeeRequest;
import com.hrms.entities.DepartmentEmployee;
import com.hrms.entities.Departments;
import com.hrms.entities.Employee;
import com.hrms.entities.Gender;
import com.hrms.entities.Salaries;
import com.hrms.entities.Titles;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.exceptions.NotFoundException;
import com.hrms.services.DepartmentEmployeeService;
import com.hrms.services.DepartmentService;
import com.hrms.services.EmployeeService;
import com.hrms.services.SalariesService;
import com.hrms.services.TitleService;

/**
 * Unit tests for the EmployeeController class.
 */
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentEmployeeService departmentEmployeeService;

    @Mock
    private SalariesService salaryService;

    @Mock
    private TitleService titleService;

    @Mock
    private DepartmentService departmentServices;

    @InjectMocks
    private EmployeeController employeeController;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for finding all employees.
     */
    @Test
    void testFindAllEmployees() {
        // Prepare mocked data
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getEmployee()).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.findAllEmployees();

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for finding an employee by ID.
     */
    @Test
    void testFindEmployeeById() {
        // Prepare input data
        int id = 1;
        Employee employee = new Employee();

        // Mock the service method
        when(employeeService.getEmployeeById(id)).thenReturn(employee);

        // Invoke the controller method
        Employee response = employeeController.findEmployeeById(id);

        // Perform assertions
        assertEquals(employee, response);
    }

    /**
     * Test case for finding an employee by ID when the employee is not found.
     */
    @Test
    void testFindEmployeeByIdNotFound() {
        // Prepare input data
        int id = 1;

        // Mock the service method
        when(employeeService.getEmployeeById(id)).thenReturn(null);

        // Perform assertions
        assertThrows(NotFoundException.class, () -> employeeController.findEmployeeById(id));
    }

    /**
     * Test case for finding employees by first name.
     */
    @Test
    void testFindEmployeeByFirstName() {
        // Prepare input data
        String firstName = "John";
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getEmployeeByFirstName(firstName)).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.findEmployeeByFirstName(firstName);

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for finding employees by first name when no employees are found.
     */
    @Test
    void testFindEmployeeByFirstNameNotFound() {
        // Prepare input data
        String firstName = "John";

        // Mock the service method
        when(employeeService.getEmployeeByFirstName(firstName)).thenReturn(null);

        // Perform assertions
        assertThrows(NotFoundException.class, () -> employeeController.findEmployeeByFirstName(firstName));
    }

    /**
     * Test case for finding employees by last name.
     */
    @Test
    void testFindEmployeeByLastName() {
        // Prepare input data
        String lastName = "Doe";
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getEmployeeByLastName(lastName)).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.findEmployeeByLastName(lastName);

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for finding employees by email.
     */
    @Test
    void testFindEmployeeByEmail() {
        // Prepare input data
        String email = "john.doe@example.com";
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getEmployeeByemail(email)).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.findEmployeeByEmail(email);

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for finding employees by gender.
     */
    @Test
    void testFindByGender() {
        // Prepare input data
        String gender = "M";
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getEmployeesByGender(Gender.M)).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.findByGender(gender);

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for getting the count of employees by gender.
     */
    @Test
    void testGetGenderCount() {
        // Prepare input data
        String gender = "M";
        int count = 5;

        // Mock the service method
        when(employeeService.getGenderCount(Gender.M)).thenReturn(count);

        // Invoke the controller method
        int response = employeeController.getGenderCount(gender);

        // Perform assertions
        assertEquals(count, response);
    }

    /**
     * Test case for getting awarded employees with a specified number of years of experience.
     */
    @Test
    void testGetAwardedEmployeesWithExperience() {
        // Prepare input data
        int yearsOfExperience = 10;
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getAwardedEmployeesWithExperience(yearsOfExperience)).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.getAwardedEmployeesWithExperience(yearsOfExperience);

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for finding employees by hire date.
     */
    @Test
    void testFindEmployeesByHireDate() {
        // Prepare input data
        Date hireDate = new Date(System.currentTimeMillis());
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getEmployeesByHireDate(hireDate)).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.findEmployeesByHireDate(hireDate);

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for finding employees by birth date.
     */
    @Test
    void testFindEmployeesByBirthDate() {
        // Prepare input data
        Date birthDate = new Date(System.currentTimeMillis());
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getEmployeesByBirthDate(birthDate)).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.findEmployeesByBirthDate(birthDate);

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for getting employees sorted by hire date in descending order.
     */
    @Test
    void testGetEmp() {
        // Prepare mocked data
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getAllEmployeesSortedByHireDateDesc()).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.getEmp();

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for getting employees who joined the company in the last 10 years.
     */
    @Test
    void testGetEmployeesJoinedLast10Years() {
        // Prepare mocked data
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getEmployeesJoinedLast10Years()).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.getEmployeesJoinedLast10Years();

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for getting the count of employees who joined the company in the last 10 years.
     */
    @Test
    void testGetCountEmployeesJoinedLast10Years() {
        // Prepare input data
        int count = 5;

        // Mock the service method
        when(employeeService.getCountEmployeesJoinedLast10Years()).thenReturn(count);

        // Invoke the controller method
        int response = employeeController.getCountEmployeesJoinedLast10Years();

        // Perform assertions
        assertEquals(count, response);
    }

    /**
     * Test case for getting employees who joined the company after a specific year.
     */
    @Test
    void testGetEmployeesJoinedAfter() {
        // Prepare input data
        int year = 2010;
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());

        // Mock the service method
        when(employeeService.getEmployeesJoinedAfter2005(year)).thenReturn(employeeList);

        // Invoke the controller method
        List<Employee> response = employeeController.getEmployeesJoinedAfter(year);

        // Perform assertions
        assertEquals(employeeList, response);
    }

    /**
     * Test case for getting the count of employees who joined the company after a specific year.
     */
    @Test
    void testGetCountEmployeesJoinedAfter() {
        // Prepare input data
        int year = 2010;
        int count = 5;

        // Mock the service method
        when(employeeService.getCountEmployeesJoinedAfter2005(year)).thenReturn(count);

        // Invoke the controller method
        int response = employeeController.getCountEmployeesJoinedAfter(year);

        // Perform assertions
        assertEquals(count, response);
    }

    /**
     * Test case for adding a new employee.
     */
    @Test
    void testAddEmployeeC() {
        // Prepare input data
        Employee employee = new Employee();

        // Mock the service method
        when(employeeService.addEmployee(employee)).thenReturn(employee);

        // Invoke the controller method
        ResponseEntity<String> response = employeeController.addEmployeeC(employee);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"New employee added successfully\"}", response.getBody());
    }

    /**
     * Test case for adding a new employee with invalid data.
     */
    @Test
    void testAddEmployeeCInvalidData() {
        // Prepare input data
        Employee employee = new Employee();

        // Mock the service method
        when(employeeService.addEmployee(employee)).thenReturn(null);

        // Perform assertions
        assertThrows(InvalidDataException.class, () -> employeeController.addEmployeeC(employee));
    }

    /**
     * Test case for updating an employee by last name.
     */
    @Test
    void testUpdateEmployeeByLastNameC() {
        // Prepare input data
        int empNo = 1;
        Employee employee = new Employee();

        // Mock the service method
        when(employeeService.updateEmployee(employee, empNo)).thenReturn(employee);

        // Invoke the controller method
        ResponseEntity<Employee> response = employeeController.updateEmployeeByLastNameC(empNo, employee);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    /**
     * Test case for updating an employee by last name when the employee is not found.
     */
    @Test
    void testUpdateEmployeeByLastNameCNotFound() {
        // Prepare input data
        int empNo = 1;
        Employee employee = new Employee();

        // Mock the service method
        when(employeeService.updateEmployee(employee, empNo)).thenReturn(null);

        // Perform assertions
        assertThrows(NotFoundException.class, () -> employeeController.updateEmployeeByLastNameC(empNo, employee));
    }

    /**
     * Test case for updating an employee by employee number.
     */
    @Test
    void testUpdateEmployeeByEmpNo() {
        // Prepare input data
        int empNo = 1;
        Employee employee = new Employee();

        // Mock the service method
        when(employeeService.updateEmployee(employee, empNo)).thenReturn(employee);

        // Invoke the controller method
        ResponseEntity<Employee> response = employeeController.updateEmployeeByEmpNo(empNo, employee);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    /**
     * Test case for updating an employee by employee number when the employee is not found.
     */
    @Test
    void testUpdateEmployeeByEmpNoNotFound() {
        // Prepare input data
        int empNo = 1;
        Employee employee = new Employee();

        // Mock the service method
        when(employeeService.updateEmployee(employee, empNo)).thenReturn(null);

        // Perform assertions
        assertThrows(NotFoundException.class, () -> employeeController.updateEmployeeByEmpNo(empNo, employee));
    }

    /**
     * Test case for updating an employee by hire date.
     */
    @Test
    void testUpdateEmployeeByHireDateC() {
        // Prepare input data
        int empNo = 1;
        Employee employee = new Employee();

        // Mock the service method
        when(employeeService.updateEmployeeByHireDate(employee, empNo)).thenReturn(employee);

        // Invoke the controller method
        Employee response = employeeController.updateEmployeeByHireDateC(empNo, employee);

        // Perform assertions
        assertEquals(employee, response);
    }

    /**
     * Test case for updating an employee by birth date.
     */
    @Test
    void testUpdateEmployeeByBirthDateC() {
        // Prepare input data
        int empNo = 1;
        Employee employee = new Employee();

        // Mock the service method
        when(employeeService.updateEmployeeBirthDate(employee, empNo)).thenReturn(employee);

        // Invoke the controller method
        Employee response = employeeController.updateEmployeeByBithDateC(empNo, employee);

        // Perform assertions
        assertEquals(employee, response);
    }

    /**
     * Test case for creating an employee.
     */
    @Test
    void testCreateEmployee() {
        // Prepare input data
        CreateEmployeeRequest request = new CreateEmployeeRequest();
        Employee employee = new Employee();
        Departments department = new Departments();
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        Salaries salary = new Salaries();
        Titles title = new Titles();

        request.setEmployee(employee);
        request.setDepartment(department);
        request.setDepartmentEmployee(departmentEmployee);
        request.setSalary(salary);
        request.setTitle(title);

        // Mock the service methods
        when(employeeService.addEmployee(employee)).thenReturn(employee);
        when(departmentServices.addDepartment(department)).thenReturn(department);
        when(departmentEmployeeService.saveDepartmentEmployee(departmentEmployee)).thenReturn(departmentEmployee);
        when(salaryService.addSalary(salary)).thenReturn(salary);
        when(titleService.addTitle(title)).thenReturn(title);

        // Invoke the controller method
        ResponseEntity<String> response = employeeController.createEmployee(request);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"Employee, DepartmentEmployee, Salary, and Title created successfully\"}", response.getBody());
    }

    /**
     * Test case for creating an employee with exception.
     */
    @Test
    void testCreateEmployeeException() {
        // Prepare input data
        CreateEmployeeRequest request = new CreateEmployeeRequest();
        Employee employee = new Employee();
        Departments department = new Departments();
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        Salaries salary = new Salaries();
        Titles title = new Titles();

        request.setEmployee(employee);
        request.setDepartment(department);
        request.setDepartmentEmployee(departmentEmployee);
        request.setSalary(salary);
        request.setTitle(title);

        // Mock the service method
        when(employeeService.addEmployee(employee)).thenReturn(null);

        // Invoke the controller method
        ResponseEntity<String> response = employeeController.createEmployee(request);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test case for handling the "forgot password" functionality.
     */
    @Test
    void testForgotPasswordC() {
        // Prepare input data
        int empNo = 1;
        String email = "john.doe@example.com";
        String password = "newpassword";
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setPassword(password);

        // Mock the service method
        when(employeeService.forgotPassword(empNo, email, employee)).thenReturn(employee);

        // Invoke the controller method
        Employee response = employeeController.forgotPasswordC(empNo, email, employee);

        // Perform assertions
        assertEquals(employee, response);
    }
}
