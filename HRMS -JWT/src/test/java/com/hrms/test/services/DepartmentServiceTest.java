package com.hrms.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hrms.entities.Departments;
import com.hrms.repository.DepartmentEmployeeRepository;
import com.hrms.repository.DepartmentManagerRepository;
import com.hrms.repository.DepartmentsRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.services.DepartmentService;

/**
 * Unit tests for the DepartmentService class.
 */
class DepartmentServiceTest {

    @Mock
    private DepartmentsRepository departmentRepository;

    @Mock
    private DepartmentManagerRepository departmentManagerRespository;

    @Mock
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DepartmentService departmentSerives;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for getting all departments.
     */
    @Test
    void testGetDepartments() {
        List<Departments> departmentsList = new ArrayList<>();
        departmentsList.add(new Departments());
        departmentsList.add(new Departments());

        when(departmentRepository.findAll()).thenReturn(departmentsList);

        List<Departments> response = departmentSerives.getDepartments();

        assertEquals(departmentsList, response);
    }

    /**
     * Test case for getting departments by department number.
     */
    @Test
    void testGetDepartmentsByDepNo() {
        String deptNo = "D001";
        List<Departments> departmentsList = new ArrayList<>();
        departmentsList.add(new Departments());

        when(departmentRepository.findByDeptNo(deptNo)).thenReturn(departmentsList);

        List<Departments> response = departmentSerives.getDepartmentsByDepNo(deptNo);

        assertEquals(departmentsList, response);
    }

    /**
     * Test case for getting departments by department name.
     */
    @Test
    void testGetDepartmentsByDeptName() {
        String deptName = "Finance";
        List<Departments> departmentsList = new ArrayList<>();
        departmentsList.add(new Departments());

        when(departmentRepository.findByDeptName(deptName)).thenReturn(departmentsList);

        List<Departments> response = departmentSerives.getDepartmentsByDeptName(deptName);

        assertEquals(departmentsList, response);
    }

    /**
     * Test case for adding a department.
     */
    @Test
    void testAddDepartment() {
        Departments department = new Departments();

        when(departmentRepository.save(department)).thenReturn(department);

        Departments response = departmentSerives.addDepartment(department);

        assertEquals(department, response);
    }

    /**
     * Test case for updating a department by department number.
     */
    @Test
    void testUpdateByDeptNo() {
        Departments department = new Departments();

        when(departmentRepository.save(department)).thenReturn(department);

        Departments response = departmentSerives.updateByDeptNo(department);

        assertEquals(department, response);
    }

    /**
     * Test case for updating a department by department name.
     */
    @Test
    void testUpdateByDeptName() {
        Departments department = new Departments();

        when(departmentRepository.save(department)).thenReturn(department);

        Departments response = departmentSerives.updateByDeptName(department);

        assertEquals(department, response);
    }

    /**
     * Test case for deleting a department by department number.
     */
    @Test
    void testDeleteByDeptNo() {
        String deptNo = "D001";
        Departments department = new Departments();

        when(departmentRepository.findByDepartmentNumber(deptNo)).thenReturn(department);

        departmentSerives.deleteByDeptNo(deptNo);

        verify(departmentManagerRespository, times(1)).deleteByDeptNo(deptNo);
        verify(departmentEmployeeRepository, times(1)).deleteByDeptNo(deptNo);
        verify(departmentRepository, times(1)).deleteByDeptNo(deptNo);
    }

    /**
     * Test case for deleting a department by department name.
     */
    @Test
    void testDeleteByDeptName() {
        String deptName = "Finance";

        departmentSerives.deleteByDeptName(deptName);

        verify(departmentRepository, times(1)).deleteByDeptName(deptName);
    }
}
