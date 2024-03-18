package com.hrms.test.controller;
import com.hrms.controller.DepartmentManagerController;
import com.hrms.entities.DepartmentManager;
import com.hrms.entities.Employee;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.exceptions.NotFoundException;
import com.hrms.services.DepartmentEmployeeService;
import com.hrms.services.DepartmentManagerService;
import com.hrms.services.EmployeeService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the DepartmentManagerController class.
 */
public class DepartmentManagerControllerTest {

    @Mock
    private DepartmentManagerService departmentManagerService;

    @InjectMocks
    private DepartmentManagerController departmentManagerController;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test case for findByDeptNo method

    /**
     * Test case for finding department managers by department number.
     */
    @Test
    public void testFindByDeptNo() {
        // Prepare input data
        String deptNo = "D001";

        // Prepare mocked data
        List<DepartmentManager> mockedDepartmentManagers = new ArrayList<>();
        mockedDepartmentManagers.add(new DepartmentManager());
        mockedDepartmentManagers.add(new DepartmentManager());

        // Mock the service method
        when(departmentManagerService.findByDeptNo(deptNo)).thenReturn(mockedDepartmentManagers);

        // Invoke the controller method
        List<DepartmentManager> departmentManagers = departmentManagerController.findByEmpNoDeptNo(deptNo);

        // Perform assertions
        assertEquals(2, departmentManagers.size());
    }

    // Test case for findByDeptNoFromDate method

    /**
     * Test case for finding department managers by department number and from date.
     */
    @Test
    public void testFindByDeptNoFromDate() {
        // Prepare input data
        String deptNo = "D001";
        Date fromDate = Date.valueOf("2023-01-01");

        // Prepare mocked data
        List<DepartmentManager> mockedDepartmentManagers = new ArrayList<>();
        mockedDepartmentManagers.add(new DepartmentManager());
        mockedDepartmentManagers.add(new DepartmentManager());

        // Mock the service method
        when(departmentManagerService.findByDeptNoAndFromDate(deptNo, fromDate))
                .thenReturn(mockedDepartmentManagers);

        // Invoke the controller method
        List<DepartmentManager> departmentManagers = departmentManagerController.findByDeptNoFromDate(deptNo, fromDate);

        // Perform assertions
        assertEquals(2, departmentManagers.size());
    }

    // Test case for findByEmpNoFromDate method

    /**
     * Test case for finding department managers by employee number and from date.
     */
    @Test
    public void testFindByEmpNoFromDate() {
        // Prepare input data
        int empNo = 1001;
        Date fromDate = Date.valueOf("2023-01-01");

        // Prepare mocked data
        DepartmentManager mockedDepartmentManager = new DepartmentManager();

        // Mock the service method
        when(departmentManagerService.findByEmpNoAndFromDate(empNo, fromDate))
                .thenReturn(mockedDepartmentManager);

        // Invoke the controller method
        DepartmentManager departmentManager = departmentManagerController.findByEmpNoFromDate(empNo, fromDate);

        // Perform assertions
        assertNotNull(departmentManager);
    }

    // Test case for findByEmpNoDeptNoAndFromDate method

    /**
     * Test case for finding department managers by employee number, department number, and from date.
     */
    @Test
    public void testFindByEmpNoDeptNoAndFromDate() {
        // Prepare input data
        int empNo = 1001;
        String deptNo = "D001";
        Date fromDate = Date.valueOf("2023-01-01");

        // Prepare mocked data
        DepartmentManager mockedDepartmentManager = new DepartmentManager();

        // Mock the service method
        when(departmentManagerService.getDepartmentManagersByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate))
                .thenReturn(mockedDepartmentManager);

        // Invoke the controller method
        DepartmentManager departmentManager = departmentManagerController.findByEmpNoDeptNoAndFromDate(empNo, deptNo, fromDate);

        // Perform assertions
        assertNotNull(departmentManager);
    }

    // Test case for getEmployeesFromDate method

    /**
     * Test case for retrieving employees from a specific date.
     */
    @Test
    public void testGetEmployeesFromDate() {
        // Prepare input data
        Date fromDate = Date.valueOf("2023-01-01");

        // Prepare mocked data
        List<Object[]> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Object[]{1, "John Doe"});
        mockedEmployees.add(new Object[]{2, "Jane Smith"});

        // Mock the service method
        when(departmentManagerService.getEmployeesFromDate(fromDate)).thenReturn(mockedEmployees);

        // Invoke the controller method
        List<Object[]> employees = departmentManagerController.getEmployeesFromDate(fromDate);

        // Perform assertions
        assertEquals(2, employees.size());
        assertEquals(1, employees.get(0)[0]);
        assertEquals("John Doe", employees.get(0)[1]);
        assertEquals(2, employees.get(1)[0]);
        assertEquals("Jane Smith", employees.get(1)[1]);
    }

    // Test case for addNewDepartmentManager method

    /**
     * Test case for adding a new department manager.
     */
    @Test
    public void testAddNewDepartmentManager() {
        // Prepare input data
        DepartmentManager departmentManager = new DepartmentManager();
        departmentManager.setFromDate(Date.valueOf("2023-01-01"));
        departmentManager.setToDate(Date.valueOf("2024-01-01"));

        // Mock the service method
        when(departmentManagerService.saveDepartmentManager(departmentManager)).thenReturn(departmentManager);

        // Invoke the controller method
        ResponseEntity<DepartmentManager> response = departmentManagerController.addNewdepartmentemployee(departmentManager);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentManager, response.getBody());
    }

    // Test case for deleteDepartmentManagerByEmpNoAndDeptNo method

    /**
     * Test case for deleting a department manager by employee number and department number.
     */
    @Test
    public void testDeleteDepartmentManagerByEmpNoAndDeptNo() {
        // Prepare input data
        int empNo = 1001;
        String deptNo = "D001";

        // Invoke the controller method
        ResponseEntity<Void> response = departmentManagerController.deleteDepartmentManagerByEmpNoAndDeptNo(empNo, deptNo);

        // Perform assertions
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentManagerService, times(1)).deleteByEmpNoAndDeptNo(empNo, deptNo);
    }

    // Test case for deleteDepartmentManagerByEmpNoAndFromDate method

    /**
     * Test case for deleting a department manager by employee number and from date.
     */
    @Test
    public void testDeleteDepartmentManagerByEmpNoAndFromDate() {
        // Prepare input data
        int empNo = 1001;
        Date fromDate = Date.valueOf("2023-01-01");

        // Invoke the controller method
        ResponseEntity<Void> response = departmentManagerController.deleteDepartmentManagerByEmpNoAndFromDate(empNo, fromDate);

        // Perform assertions
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentManagerService, times(1)).deleteByEmpNoAndFromDate(empNo, fromDate);
    }

    // Test case for deleteDepartmentManagerByDeptNoAndFromDate method

    /**
     * Test case for deleting department managers by department number and from date.
     */
    @Test
    public void testDeleteDepartmentManagerByDeptNoAndFromDate() {
        // Prepare input data
        String deptNo = "D001";
        Date fromDate = Date.valueOf("2023-01-01");

        // Invoke the controller method
        ResponseEntity<Void> response = departmentManagerController.deleteDepartmentManagerByDeptNoAndFromDate(deptNo, fromDate);

        // Perform assertions
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentManagerService, times(1)).deleteByDeptNoAndFromDate(deptNo, fromDate);
    }

    // Test case for deleteDepartmentManagerByDeptNo method

    /**
     * Test case for deleting department managers by department number.
     */
    @Test
    public void testDeleteDepartmentManagerByDeptNo() {
        // Prepare input data
        String deptNo = "D001";

        // Invoke the controller method
        ResponseEntity<Void> response = departmentManagerController.deleteDepartmentManagerByDeptNo(deptNo);

        // Perform assertions
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentManagerService, times(1)).deleteByDeptNo(deptNo);
    }
    
    


}
