package com.hrms.test.controller;

import com.hrms.controller.DepartmentEmployeeController;
import com.hrms.entities.DepartmentEmployee;
import com.hrms.services.DepartmentEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
*Unit tests for the DepartmentEmployeeController class.
*/

public class DepartmentEmployeeControllerTest {

	@Mock
	private DepartmentEmployeeService departmentEmployeeServices;

	@InjectMocks
	private DepartmentEmployeeController departmentEmployeeController;

	@BeforeEach
	public void setUp() {
	    MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test case for finding all department employees.
	 */
	@Test
	public void testFindAllDepartmentEmployees() {
	    // Prepare mocked data
	    List<DepartmentEmployee> mockedDepartmentEmployees = new ArrayList<>();
	    mockedDepartmentEmployees.add(new DepartmentEmployee());
	    mockedDepartmentEmployees.add(new DepartmentEmployee());

	    // Mock the service method
	    when(departmentEmployeeServices.getdepartmentemployee()).thenReturn(mockedDepartmentEmployees);

	    // Perform the controller method
	    List<DepartmentEmployee> departmentEmployees = departmentEmployeeController.findAlldepartmentsemployee();

	    // Verify the results
	    assertEquals(2, departmentEmployees.size());
	}

	/**
	 * Test case for finding a department employee by employee number and department number.
	 */
	@Test
	public void testFindByEmpNoAndDeptNo() {
	    // Prepare test data
	    int empNo = 1001;
	    String deptNo = "D001";
	    DepartmentEmployee mockedDepartmentEmployee = new DepartmentEmployee();

	    // Mock the service method
	    when(departmentEmployeeServices.getDepartEmployeeByEmpNoAndDeptNo(empNo, deptNo))
	            .thenReturn(mockedDepartmentEmployee);

	    // Perform the controller method
	    DepartmentEmployee departmentEmployee = departmentEmployeeController.findByEmpNoAndDeptNo(empNo, deptNo);

	    // Verify the result
	    assertNotNull(departmentEmployee);
	}

	/**
	 * Test case for finding department employees by employee number.
	 */
	@Test
	public void testFindByEmpNo() {
	    // Prepare test data
	    int empNo = 1001;
	    List<DepartmentEmployee> mockedDepartmentEmployees = new ArrayList<>();
	    mockedDepartmentEmployees.add(new DepartmentEmployee());
	    mockedDepartmentEmployees.add(new DepartmentEmployee());

	    // Mock the service method
	    when(departmentEmployeeServices.getDepartEmployeeByEmpNo(empNo))
	            .thenReturn(mockedDepartmentEmployees);

	    // Perform the controller method
	    List<DepartmentEmployee> departmentEmployees = departmentEmployeeController.findByEmpNo(empNo);

	    // Verify the results
	    assertEquals(2, departmentEmployees.size());
	}

	/**
	 * Test case for getting employees by department and year.
	 */
	@Test
	public void testGetEmployeesByDepartmentAndYear() {
	    // Prepare test data
	    String deptNo = "D001";
	    int year = 2023;
	    List<DepartmentEmployee> mockedDepartmentEmployees = new ArrayList<>();
	    mockedDepartmentEmployees.add(new DepartmentEmployee());
	    mockedDepartmentEmployees.add(new DepartmentEmployee());

	    // Mock the service method
	    when(departmentEmployeeServices.getEmployeesByDepartmentAndYear(deptNo, year))
	            .thenReturn(mockedDepartmentEmployees);

	    // Perform the controller method
	    List<DepartmentEmployee> departmentEmployees = departmentEmployeeController.getEmployeesByDepartmentAndYear(deptNo, year);

	    // Verify the results
	    assertEquals(2, departmentEmployees.size());
	}

	/**
	 * Test case for finding department employees by department number and from date.
	 */
	@Test
	public void testFindByDeptNoFromDate() {
	    // Prepare test data
	    String deptNo = "D001";
	    Date fromDate = Date.valueOf("2023-01-01");
	    List<DepartmentEmployee> mockedDepartmentEmployees = new ArrayList<>();
	    mockedDepartmentEmployees.add(new DepartmentEmployee());
	    mockedDepartmentEmployees.add(new DepartmentEmployee());

	    // Mock the service method
	    when(departmentEmployeeServices.findByDeptNoAndFromDate(deptNo, fromDate))
	            .thenReturn(mockedDepartmentEmployees);

	    // Perform the controller method
	    List<DepartmentEmployee> departmentEmployees = departmentEmployeeController.findByDeptNoFromDate(deptNo, fromDate);

	    // Verify the results
	    assertEquals(2, departmentEmployees.size());
	}

	/**
	 * Test case for finding department employees by department number.
	 */
	@Test
	public void testFindByDeptNo() {
	    // Prepare test data
	    String deptNo = "D001";
	    List<DepartmentEmployee> mockedDepartmentEmployees = new ArrayList<>();
	    mockedDepartmentEmployees.add(new DepartmentEmployee());
	    mockedDepartmentEmployees.add(new DepartmentEmployee());

	    // Mock the service method
	    when(departmentEmployeeServices.findByDeptNo(deptNo))
	            .thenReturn(mockedDepartmentEmployees);

	    // Perform the controller method
	    List<DepartmentEmployee> departmentEmployees = departmentEmployeeController.findByDeptNo(deptNo);

	    // Verify the results
	    assertEquals(2, departmentEmployees.size());
	}

	/**
	 * Test case for finding department employee by employee number and from date.
	 */
	@Test
	public void testFindByEmpNoFromDate() {
	    // Prepare test data
	    int empNo = 1001;
	    Date fromDate = Date.valueOf("2023-01-01");
	    DepartmentEmployee mockedDepartmentEmployee = new DepartmentEmployee();

	    // Mock the service method
	    when(departmentEmployeeServices.findByempNoAndFromDate(empNo, fromDate))
	            .thenReturn(mockedDepartmentEmployee);

	    // Perform the controller method
	    DepartmentEmployee departmentEmployee = departmentEmployeeController.findByempNoFromDate(empNo, fromDate);

	    // Verify the result
	    assertNotNull(departmentEmployee);
	}

	/**
	 * Test case for finding department employee by employee number, department number, and from date.
	 */
	@Test
	public void testFindByEmpNoDeptNoFromDate() {
	    // Prepare test data
	    int empNo = 1001;
	    String deptNo = "D001";
	    Date fromDate = Date.valueOf("2023-01-01");
	    DepartmentEmployee mockedDepartmentEmployee = new DepartmentEmployee();

	    // Mock the service method
	    when(departmentEmployeeServices.getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate))
	            .thenReturn(mockedDepartmentEmployee);

	    // Perform the controller method
	    DepartmentEmployee departmentEmployee = departmentEmployeeController.findbyempnodeptnoAndfromdate(empNo, deptNo, fromDate);

	    // Verify the result
	    assertNotNull(departmentEmployee);
	}

	/**
	 * Test case for adding a new department employee.
	 */
	@Test
	public void testAddNewDepartmentEmployee() {
	    // Prepare test data
	    DepartmentEmployee departmentEmployee = new DepartmentEmployee();
	    departmentEmployee.setFromDate(Date.valueOf("2023-01-01"));
	    departmentEmployee.setToDate(Date.valueOf("2024-01-01"));

	    // Mock the service method
	    when(departmentEmployeeServices.saveDepartmentEmployee(departmentEmployee)).thenReturn(departmentEmployee);

	    // Perform the controller method
	    ResponseEntity<DepartmentEmployee> response = departmentEmployeeController.addNewdepartmentemployee(departmentEmployee);

	    // Verify the results
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(departmentEmployee, response.getBody());
	}

	/**
	 * Test case for deleting a department employee by employee number and department number.
	 */
	@Test
	public void testDeleteDepartmentEmployeeByEmpNoAndDeptNo() {
	    // Prepare test data
	    int empNo = 1001;
	    String deptNo = "D001";

	    // Perform the controller method
	    ResponseEntity<Void> response = departmentEmployeeController.deleteDepartmentEmployeeByEmpNoAndDeptNo(empNo, deptNo);

	    // Verify the result
	    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	    verify(departmentEmployeeServices, times(1)).deleteByEmpNoAndDeptNo(empNo, deptNo);
	}

	/**
	 * Test case for deleting a department employee by employee number and from date.
	 */
	@Test
	public void testDeleteDepartmentEmployeeByEmpNoAndFromDate() {
	    // Prepare test data
	    int empNo = 1001;
	    Date fromDate = Date.valueOf("2023-01-01");

	    // Perform the controller method
	    ResponseEntity<Void> response = departmentEmployeeController.deleteDepartmentEmployeeByEmpNoAndFromDate(empNo, fromDate);

	    // Verify the result
	    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	    verify(departmentEmployeeServices, times(1)).deleteByEmpNoAndFromDate(empNo, fromDate);
	}

	/**
	 * Test case for deleting a department employee by department number and from date.
	 */
	@Test
	public void testDeleteDepartmentEmployeeByDeptNoAndFromDate() {
	    // Prepare test data
	    String deptNo = "D001";
	    Date fromDate = Date.valueOf("2023-01-01");

	    // Perform the controller method
	    ResponseEntity<Void> response = departmentEmployeeController.deleteDepartmentEmployeeByDeptNoAndFromDate(deptNo, fromDate);

	    // Verify the result
	    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	    verify(departmentEmployeeServices, times(1)).deleteByDeptNoAndFromDate(deptNo, fromDate);
	}

	/**
	 * Test case for deleting all department employees by department number.
	 */
	@Test
	public void testDeleteDepartmentEmployeeByDeptNo() {
	    // Prepare test data
	    String deptNo = "D001";

	    // Perform the controller method
	    ResponseEntity<Void> response = departmentEmployeeController.deleteDepartmentEmployeeByDeptNo(deptNo);

	    // Verify the result
	    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	    verify(departmentEmployeeServices, times(1)).deleteBysDeptNo(deptNo);
	}

}
