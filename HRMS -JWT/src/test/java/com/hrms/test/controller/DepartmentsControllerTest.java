package com.hrms.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hrms.controller.DepartmentsController;
import com.hrms.entities.Departments;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.DepartmentService;

/**
 * Unit tests for the DepartmentsController class.
 */
public class DepartmentsControllerTest {

    @Mock
    private DepartmentService departmentServices;

    @InjectMocks
    private DepartmentsController departmentsController;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for finding all departments.
     */
    @Test
    public void testFindAllDepartments() {
        // Prepare mocked data
        List<Departments> departmentsList = new ArrayList<>();
        departmentsList.add(new Departments("D001", "Department 1", null, null));
        departmentsList.add(new Departments("D002", "Department 2", null, null));

        // Mock the service method
        when(departmentServices.getDepartments()).thenReturn(departmentsList);

        // Invoke the controller method
        List<Departments> response = departmentsController.findAllDepartments();

        // Perform assertions
        assertEquals(departmentsList, response);
    }

    /**
     * Test case for finding departments by department number.
     */
    @Test
    public void testFindAllDepartmentsByDeptNo() {
        // Prepare input data
        String deptNo = "D001";

        // Prepare mocked data
        List<Departments> departmentsList = new ArrayList<>();
        departmentsList.add(new Departments(deptNo, "Department 1", null, null));

        // Mock the service method
        when(departmentServices.getDepartmentsByDepNo(deptNo)).thenReturn(departmentsList);

        // Invoke the controller method
        List<Departments> response = departmentsController.findAllDepartmentsByDeptName(deptNo);

        // Perform assertions
        assertEquals(departmentsList, response);
    }

    /**
     * Test case for finding departments by department name.
     */
    @Test
    public void testFindAllDepartmentsByDeptName() {
        // Prepare input data
        String deptName = "Department 1";

        // Prepare mocked data
        List<Departments> departmentsList = new ArrayList<>();
        departmentsList.add(new Departments("D001", deptName, null, null));

        // Mock the service method
        when(departmentServices.getDepartmentsByDeptName(deptName)).thenReturn(departmentsList);

        // Invoke the controller method
        List<Departments> response = departmentsController.findAllDepartmentsByDeptName(deptName);

        // Perform assertions
        assertEquals(departmentsList, response);
    }

    /**
     * Test case for adding a new department.
     */
    @Test
    public void testAddDepartment() {
        // Prepare input data
        Departments department = new Departments("D001", "Department 1", null, null);

        // Mock the service method
        when(departmentServices.addDepartment(department)).thenReturn(department);

        // Invoke the controller method
        ResponseEntity<String> response = departmentsController.addDepartment(department);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New department added successfully", response.getBody());
    }

    /**
     * Test case for adding a new department with invalid data.
     */
    @Test
    public void testAddDepartmentInvalidData() {
        // Prepare input data
        Departments department = new Departments();

        // Mock the service method
        when(departmentServices.addDepartment(department)).thenReturn(null);

        // Perform assertions
        assertThrows(InvalidDataException.class, () -> departmentsController.addDepartment(department));
    }

    /**
     * Test case for updating a department by department number.
     */
    @Test
    public void testUpdateDepartmentByDeptNo() {
        // Prepare input data
        String deptNo = "D001";
        Departments department = new Departments(deptNo, "Department 1", null, null);

        // Mock the service method
        when(departmentServices.updateByDeptNo(department)).thenReturn(department);

        // Invoke the controller method
        ResponseEntity<String> response = departmentsController.updateDepartmentByDeptNo(deptNo, department);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Department updated successfully", response.getBody());
    }

    /**
     * Test case for updating a department with invalid data by department number.
     */
    @Test
    public void testUpdateDepartmentByDeptNoInvalidData() {
        // Prepare input data
        String deptNo = "D001";
        Departments department = new Departments(deptNo, "Department 1", null, null);

        // Mock the service method
        when(departmentServices.updateByDeptNo(department)).thenReturn(null);

        // Perform assertions
        assertThrows(InvalidDataException.class, () -> departmentsController.updateDepartmentByDeptNo(deptNo, department));
    }

    /**
     * Test case for updating a department by department name.
     */
    @Test
    public void testUpdateDepartmentByDeptName() {
        // Prepare input data
        String deptName = "Department 1";
        Departments department = new Departments("D001", deptName, null, null);

        // Mock the service method
        when(departmentServices.updateByDeptName(department)).thenReturn(department);

        // Invoke the controller method
        ResponseEntity<String> response = departmentsController.updateDepartmentByDeptName(deptName, department);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Department updated successfully", response.getBody());
    }

    /**
     * Test case for updating a department with invalid data by department name.
     */
    @Test
    public void testUpdateDepartmentByDeptNameInvalidData() {
        // Prepare input data
        String deptName = "Department 1";
        Departments department = new Departments("D001", deptName, null, null);

        // Mock the service method
        when(departmentServices.updateByDeptName(department)).thenReturn(null);

        // Perform assertions
        assertThrows(InvalidDataException.class, () -> departmentsController.updateDepartmentByDeptName(deptName, department));
    }

    /**
     * Test case for deleting a department by department number.
     */
    @Test
    public void testDeleteDepartmentByDeptNo() {
        // Prepare input data
        String deptNo = "D001";

        // Invoke the controller method
        ResponseEntity<Void> response = departmentsController.deleteDepartmentByDeptNo(deptNo);

        // Perform assertions
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentServices, times(1)).deleteByDeptNo(deptNo);
    }

    /**
     * Test case for deleting a department by department name.
     */
    @Test
    public void testDeleteDepartmentByDeptName() {
        // Prepare input data
        String deptName = "Department 1";

        // Invoke the controller method
        ResponseEntity<String> response = departmentsController.deleteDepartmentByDeptName(deptName);

        // Perform assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Department deleted successfully", response.getBody());
        verify(departmentServices, times(1)).deleteByDeptName(deptName);
    }
}
