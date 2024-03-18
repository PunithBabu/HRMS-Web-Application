/**

The EmployeeController class is responsible for handling HTTP requests related to employee operations.
*/
package com.hrms.controller;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
public class EmployeeController {
	@Autowired
	public EmployeeService employeeService;

	@Autowired
	private DepartmentEmployeeService departmentEmployeeService;

	@Autowired
	private SalariesService salaryService;

	@Autowired
	private TitleService titleService;

	@Autowired
	private DepartmentService departmentServices;

	/**
	 * Retrieves a list of all employees.
	 *
	 * @return The list of all employees.
	 */
	@GetMapping("/all")
	@Operation(summary = "Fetch all employees.")
	public List<Employee> findAllEmployees() {
	    ResponseEntity.ok().body("{\"message\": \"All employees\"}");
	    return employeeService.getEmployee();
	}

	/**
	 * Retrieves an employee by their employee number.
	 *
	 * @param id The employee number.
	 * @return The employee with the specified employee number.
	 * @throws NotFoundException if the employee is not found.
	 */
	@GetMapping("/id/{id}")
	@Operation(summary = "Fetch an employee by employee number")
	public Employee findEmployeeById(@PathVariable int id) {
	    if (employeeService.getEmployeeById(id) != null)
	        return employeeService.getEmployeeById(id);
	    else
	        throw new NotFoundException("Employee ID is not correct! Please check again.");
	}

	/**
	 * Retrieves a list of employees by their first name.
	 *
	 * @param firstName The first name.
	 * @return The list of employees with the specified first name.
	 * @throws NotFoundException if no employees are found with the given first name.
	 */
	@GetMapping("/firstname/{firstName}")
	@Operation(summary = "Search Employee by First Name")
	public List<Employee> findEmployeeByFirstName(@PathVariable("firstName") String firstName) {
	    if (employeeService.getEmployeeByFirstName(firstName) != null)
	        return employeeService.getEmployeeByFirstName(firstName);
	    else
	        throw new NotFoundException("First Name is not correct! Please check again.");
	}

	/**
	 * Retrieves a list of employees by their last name.
	 *
	 * @param lastName The last name.
	 * @return The list of employees with the specified last name.
	 * @throws NotFoundException if no employees are found with the given last name.
	 */
	@GetMapping("/lastname/{lastName}")
	@Operation(summary = "Search Employee by Last Name")
	public List<Employee> findEmployeeByLastName(@PathVariable("lastName") String lastName) {
	    if (employeeService.getEmployeeByLastName(lastName) != null)
	        return employeeService.getEmployeeByLastName(lastName);
	    else
	        throw new NotFoundException("Last Name is not correct! Please check again.");
	}

	/**
	 * Retrieves a list of employees by their email.
	 *
	 * @param email The email.
	 * @return The list of employees with the specified email.
	 * @throws NotFoundException if no employees are found with the given email.
	 */
	@GetMapping("/email/{email}")
	@Operation(summary = "Search Employee by Email")
	public List<Employee> findEmployeeByEmail(@PathVariable("email") String email) {
	    if (employeeService.getEmployeeByemail(email) != null)
	        return employeeService.getEmployeeByemail(email);
	    else
	        throw new NotFoundException("Email is not correct! Please check again.");
	}

	/**
	 * Retrieves a list of employees by their gender.
	 *
	 * @param gender The gender.
	 * @return The list of employees with the specified gender.
	 * @throws NotFoundException if no employees are found with the given gender.
	 */
	@GetMapping("/gender/{gender}")
	@Operation(summary = "Search employees by gender")
	public List<Employee> findByGender(@PathVariable("gender") String gender) {
	    Gender g = Gender.valueOf(gender);
	    if (employeeService.getEmployeesByGender(g) != null)
	        return employeeService.getEmployeesByGender(g);
	    else
	        throw new NotFoundException("Gender is not valid! Please check again.");
	}

	/**
	 * Retrieves the count of employees by their gender.
	 *
	 * @param gender The gender.
	 * @return The count of employees with the specified gender.
	 */
	@GetMapping("/gendercount/{gender}")
	public int getGenderCount(@PathVariable("gender") String gender) {
	    Gender g = Gender.valueOf(gender);
	    return employeeService.getGenderCount(g);
	}

	/**
	 * Retrieves a list of employees who have been awarded with a certain number of years of experience.
	 *
	 * @param yearsOfExperience The number of years of experience.
	 * @return The list of employees with the specified years of experience.
	 * @throws NotFoundException if no employees are found with the given years of experience.
	 */
	@GetMapping("/awarded-employees/{yearsOfExperience}")
	public List<Employee> getAwardedEmployeesWithExperience(@PathVariable("yearsOfExperience") int yearsOfExperience) {
	    if (employeeService.getAwardedEmployeesWithExperience(yearsOfExperience) != null)
	        return employeeService.getAwardedEmployeesWithExperience(yearsOfExperience);
	    else
	        throw new NotFoundException("Years of Experience not found!");
	}

	/**
	 * Retrieves a list of employees hired on a specific hire date.
	 *
	 * @param hireDate The hire date.
	 * @return The list of employees hired on the specified hire date.
	 * @throws NotFoundException if no employees are found with the given hire date.
	 */
	@GetMapping("/hiredate/{hiredate}")
	@Operation(summary = "Search employees by hire date")
	public List<Employee> findEmployeesByHireDate(@PathVariable("hiredate") Date hireDate) {
	    if (employeeService.getEmployeesByHireDate(hireDate) != null)
	        return employeeService.getEmployeesByHireDate(hireDate);
	    else
	        throw new NotFoundException("HireDate is not correct! Please check again.");
	}

	/**
	 * Retrieves a list of employees born on a specific birth date.
	 *
	 * @param birthDate The birth date.
	 * @return The list of employees born on the specified birth date.
	 * @throws NotFoundException if no employees are found with the given birth date.
	 */
	@GetMapping("/birthdate/{birthdate}")
	@Operation(summary = "Search employees by birth date")
	public List<Employee> findEmployeesByBirthDate(@PathVariable("birthdate") Date birthDate) {
	    if (employeeService.getEmployeesByBirthDate(birthDate) != null)
	        return employeeService.getEmployeesByBirthDate(birthDate);
	    else
	        throw new NotFoundException("BirthDate is not correct! Please check again.");
	}

	/**
	 * Retrieves a list of employees sorted by hire date in descending order.
	 *
	 * @return The list of employees sorted by hire date in descending order.
	 */
	@GetMapping("/value")
	public List<Employee> getEmp() {
	    ResponseEntity.ok().body("{\"message\": \"All employees\"}");
	    return employeeService.getAllEmployeesSortedByHireDateDesc();
	}

	/**
	 * Retrieves a list of employees who joined the company in the last 10 years.
	 *
	 * @return The list of employees who joined the company in the last 10 years.
	 */
	@GetMapping("/joined-last-10-years")
	public List<Employee> getEmployeesJoinedLast10Years() {
	    return employeeService.getEmployeesJoinedLast10Years();
	}

	/**
	 * Retrieves the count of employees who joined the company in the last 10 years.
	 *
	 * @return The count of employees who joined the company in the last 10 years.
	 */
	@GetMapping("/joined-last-10-years/count")
	public int getCountEmployeesJoinedLast10Years() {
	    return employeeService.getCountEmployeesJoinedLast10Years();
	}

	/**
	 * Retrieves a list of employees who joined the company after a specific year.
	 *
	 * @param year The year.
	 * @return The list of employees who joined the company after the specified year.
	 */
	@GetMapping("/joined-after-year/{year}")
	public List<Employee> getEmployeesJoinedAfter(@PathVariable("year") int year) {
	    return employeeService.getEmployeesJoinedAfter2005(year);
	}

	/**
	 * Retrieves the count of employees who joined the company after a specific year.
	 *
	 * @param year The year.
	 * @return The count of employees who joined the company after the specified year.
	 */
	@GetMapping("/joined-after-year/count{year}")
	public int getCountEmployeesJoinedAfter(@PathVariable("year") int year) {
	    return employeeService.getCountEmployeesJoinedAfter2005(year);
	}

	/**
	 * Adds a new employee to the database.
	 *
	 * @param employee The employee to be added.
	 * @return The response entity with the success message if the employee is added successfully.
	 * @throws InvalidDataException if the employee data is invalid.
	 */
	@PostMapping("/add")
	@Operation(summary = "Add new employee object in DB")
	public ResponseEntity<String> addEmployeeC(@RequestBody Employee employee) {
	    Employee response = employeeService.addEmployee(employee);
	    if (response != null)
	        return ResponseEntity.ok().body("{\"message\": \"New employee added successfully\"}");
	    else
	        throw new InvalidDataException("Validation Failed: Invalid entry. Please check again.");
	}

	/**
	 * Updates the last name of an employee.
	 *
	 * @param empNo    The employee number.
	 * @param employee The updated employee information.
	 * @return The response entity with the updated employee object if the update is successful.
	 * @throws NotFoundException if the employee with the specified employee number is not found.
	 */
	@PutMapping("/lastname/{empno}")
	@Operation(summary = "Update Last Name of an employee given empno")
	public ResponseEntity<Employee> updateEmployeeByLastNameC(@PathVariable("empno") int empNo,
	                                                          @RequestBody Employee employee) {
	    Employee emp = employeeService.updateEmployee(employee, empNo);
	    if (emp != null)
	        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	    else
	        throw new NotFoundException("Invalid ID: Unable to update the last name");
	}

	/**
	 * Updates the first name of an employee.
	 *
	 * @param empNo    The employee number.
	 * @param employee The updated employee information.
	 * @return The response entity with the updated employee object if the update is successful.
	 * @throws NotFoundException if the employee with the specified employee number is not found.
	 */
	@PutMapping("/updateemployee/{empno}")
	@Operation(summary = "Update first Name of an employee given empno")
	public ResponseEntity<Employee> updateEmployeeByEmpNo(@PathVariable("empno") int empNo,
	                                                      @RequestBody Employee employee) {
	    Employee emp = employeeService.updateEmployee(employee, empNo);
	    if (emp != null)
	        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	    else
	        throw new NotFoundException("Invalid ID: Unable to update the first name");
	}

	/**
	 * Updates the email and password of an employee for signup.
	 *
	 * @param employee The updated employee information.
	 * @param empNo    The employee number.
	 * @return The response entity with the updated employee object if the update is successful.
	 */
	@PutMapping("/signup/empno/{empNo}")
	public ResponseEntity<Employee> signupemailandpassword(@RequestBody Employee employee,
	                                                       @PathVariable("empNo") int empNo) {
	    Employee existingDetails = employeeService.getEmployeeById(empNo);

	    if (existingDetails != null) {
	        existingDetails.setEmail(employee.getEmail());
	        existingDetails.setPassword(employee.getPassword());
	        Employee updateEmailPassword = employeeService.upadateEmailAndPassword(existingDetails);
	        return ResponseEntity.ok(updateEmailPassword);
	    }
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * Updates the hire date of an employee.
	 *
	 * @param empNo    The employee number.
	 * @param employee The updated employee information.
	 * @return The updated employee object if the update is successful.
	 * @throws NotFoundException if the employee with the specified employee number is not found.
	 */
	@PutMapping("/hiredate/{empno}")
	@Operation(summary = "Update hiredate for given empno")
	public Employee updateEmployeeByHireDateC(@PathVariable("empno") int empNo, @RequestBody Employee employee) {
	    if (employeeService.updateEmployeeByHireDate(employee, empNo) != null)
	        return employeeService.updateEmployeeByHireDate(employee, empNo);
	    else
	        throw new NotFoundException("Please check your employee number");
	}

	/**
	 * Updates the birth date of an employee.
	 *
	 * @param empNo    The employee number.
	 * @param employee The updated employee information.
	 * @return The updated employee object if the update is successful.
	 */
	@PutMapping("/birthdate/{empno}")
	@Operation(summary = "Update birthdate for given empno")
	public Employee updateEmployeeByBithDateC(@PathVariable("empno") int empNo, @RequestBody Employee employee) {
	    return employeeService.updateEmployeeBirthDate(employee, empNo);
	}

	/**
	 * Updates employee details as an employee from the frontend.
	 *
	 * @param empNo    The employee number.
	 * @param employee The updated employee information.
	 * @return The updated employee object if the update is successful.
	 */
	@PutMapping("/frontend/{empno}")
	@Operation(summary = "Update employee details as an employee")
	public Employee updateFromFrontEndE(@PathVariable("empno") int empNo, @RequestBody Employee employee) {
	    return employeeService.updateEmployeeDetials(employee, empNo);
	}

	/**
	 * Logs in an employee.
	 *
	 * @param request The login request containing email and password.
	 * @return The response entity with the logged in employee object if the login is successful.
	 */
	@PostMapping("/login")
	public ResponseEntity<Employee> loginEmployee(@RequestBody Employee request) {
	    Employee employee = employeeService.findByEmailAndPassword(request.getEmail(), request.getPassword());
	    if (employee != null) {
	        return ResponseEntity.ok(employee);
	    } else {
	        return ResponseEntity.badRequest().build();
	    }
	}

	/**
	 * Updates the employee profile.
	 *
	 * @param empNo    The employee number.
	 * @param request  The updated employee information.
	 * @return The response entity with the updated employee object if the update is successful.
	 */
	@PutMapping("/empno/{empNo}")
	public ResponseEntity<Employee> updateEmployeeProfile(@PathVariable int empNo, @RequestBody Employee request) {
	    Employee existingEmployee = employeeService.findByempNo(empNo).orElse(null);
	    if (existingEmployee != null) {
	        if (!existingEmployee.getEmail().equals(request.getEmail())) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Return forbidden status if not authorized
	        }
	        existingEmployee.setFirstName(request.getFirstName());
	        existingEmployee.setLastName(request.getLastName());
	        Employee updatedEmployee = employeeService.updateemployee(existingEmployee);
	        return ResponseEntity.ok(updatedEmployee);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	/**
	 * Retrieves a list of employees hired between a specific range of years.
	 *
	 * @param startYear The start year of the range.
	 * @param endYear   The end year of the range.
	 * @return The list of employees hired between the specified range of years.
	 */
	@GetMapping("/hiredate/{startYear}/{endYear}")
	public ResponseEntity<List<Employee>> getEmployeesByHireDateRange(@PathVariable("startYear") int startYear,
	                                                                  @PathVariable("endYear") int endYear) {
	    List<Employee> employees = employeeService.getEmployeesByHireDateRange(startYear, endYear);
	    return ResponseEntity.ok(employees);
	}

	/**
	 * Retrieves the employee ID by email.
	 *
	 * @param email The email.
	 * @return The employee ID associated with the email.
	 */
	@PostMapping("/email/{email}")
	public int findByEmail(@PathVariable String email) {
	    return employeeService.getIdByEmail(email);
	}

	/**
	 * Deletes an employee by their employee number.
	 *
	 * @param empNo The employee number.
	 * @return The response entity with no content if the deletion is successful.
	 */
	@DeleteMapping("/deleteemployeebyempno/{empNo}")
	public ResponseEntity<Void> deleteEmployeeByEmpNo(@PathVariable int empNo) {
	    employeeService.deleteEmployeeByEmpNo(empNo);
	    return ResponseEntity.noContent().build();
	}

	/**
	 * Creates an employee with associated department, department employee, salary, and title.
	 *
	 * @param request The create employee request containing employee, department, department employee, salary, and title objects.
	 * @return The response entity with the success message if the creation is successful.
	 */
	@PostMapping("/createemployee")
	public ResponseEntity<String> createEmployee(@RequestBody CreateEmployeeRequest request) {
	    try {
	        Employee employee = request.getEmployee();
	        Departments department = request.getDepartment();
	        DepartmentEmployee departmentEmployee = request.getDepartmentEmployee();
	        Salaries salary = request.getSalary();
	        Titles title = request.getTitle();

	        Employee savedEmployee = employeeService.addEmployee(employee);
	        Departments savedDepartment = departmentServices.addDepartment(department);

	        departmentEmployee.setEmployee(savedEmployee);
	        departmentEmployee.setDepartment(savedDepartment);
	        DepartmentEmployee savedDepartmentEmployee = departmentEmployeeService.saveDepartmentEmployee(departmentEmployee);

	        salary.setEmployee(savedEmployee);
	        Salaries savedSalary = salaryService.addSalary(salary);
	        title.setEmployee(savedEmployee);
	        Titles savedTitle = titleService.addTitle(title);
	        String message = "Employee, DepartmentEmployee, Salary, and Title created successfully";
	        return ResponseEntity.ok().body("{\"message\": \"" + message + "\"}");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("{\"error\": \"Error creating employee: " + e.getMessage() + "\"}");
	    }
	}

	/**
	 * Resets the password of an employee.
	 *
	 * @param empNo    The employee number.
	 * @param email    The email.
	 * @param employee The employee information containing the new password.
	 * @return The updated employee object if the password reset is successful.
	 */
	@PutMapping("/password/empno/{empno}/email/{email}")
	@Operation(summary = "Forgot Password")
	public Employee forgotPasswordC(@PathVariable("empno") int empNo, @PathVariable("email") String email,
	                                @RequestBody Employee employee) {
	    System.out.println("email in Controller" + email);
	    System.out.println("password in Controller " + employee.getPassword());
	    return employeeService.forgotPassword(empNo, email, employee);
	}

}