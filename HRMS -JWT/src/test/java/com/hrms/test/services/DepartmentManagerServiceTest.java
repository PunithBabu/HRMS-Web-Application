package com.hrms.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hrms.entities.DepartmentManager;
import com.hrms.repository.DepartmentManagerRepository;
import com.hrms.services.DepartmentManagerService;

/**
 * Unit tests for the DepartmentManagerService class.
 */
class DepartmentManagerServiceTest {

    @Mock
    private DepartmentManagerRepository departmentManagerRepository;

    @InjectMocks
    private DepartmentManagerService departmentManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for getting all department managers.
     */
    @Test
    void testGetDepartmentManager() {
        List<DepartmentManager> departmentManagerList = new ArrayList<>();
        departmentManagerList.add(new DepartmentManager());
        departmentManagerList.add(new DepartmentManager());

        when(departmentManagerRepository.findAll()).thenReturn(departmentManagerList);

        List<DepartmentManager> response = departmentManagerService.getDepartmentManager();

        assertEquals(departmentManagerList, response);
    }

    /**
     * Test case for getting department managers by employee number and department number.
     */
    @Test
    void testGetDepartmentManagersByEmpNoAndDeptNo() {
        int empNo = 1;
        String deptNo = "D001";
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.findByEmployee_EmpNoAndDepartment_DeptNo(empNo, deptNo))
                .thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.getDepartmentManagersByEmpNoAndDeptNo(empNo, deptNo);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for finding department managers by department number and from date.
     */
    @Test
    void testFindByDeptNoAndFromDate() {
        String deptNo = "D001";
        Date fromDate = new Date(System.currentTimeMillis());
        List<DepartmentManager> departmentManagerList = new ArrayList<>();
        departmentManagerList.add(new DepartmentManager());

        when(departmentManagerRepository.findByDepartmentDeptNoAndFromDate(deptNo, fromDate))
                .thenReturn(departmentManagerList);

        List<DepartmentManager> response = departmentManagerService.findByDeptNoAndFromDate(deptNo, fromDate);

        assertEquals(departmentManagerList, response);
    }

    /**
     * Test case for finding department managers by department number.
     */
    @Test
    void testFindByDeptNo() {
        String deptNo = "D001";
        List<DepartmentManager> departmentManagerList = new ArrayList<>();
        departmentManagerList.add(new DepartmentManager());

        when(departmentManagerRepository.findByDepartmentDeptNo(deptNo)).thenReturn(departmentManagerList);

        List<DepartmentManager> response = departmentManagerService.findByDeptNo(deptNo);

        assertEquals(departmentManagerList, response);
    }

    /**
     * Test case for finding department managers by employee number.
     */
    @Test
    void testFindByempNo() {
        int empNo = 1;
        List<DepartmentManager> departmentManagerList = new ArrayList<>();
        departmentManagerList.add(new DepartmentManager());

        when(departmentManagerRepository.findByEmployee_EmpNo(empNo)).thenReturn(departmentManagerList);

        List<DepartmentManager> response = departmentManagerService.findByempNo(empNo);

        assertEquals(departmentManagerList, response);
    }

    /**
     * Test case for finding department managers by employee number and from date.
     */
    @Test
    void testFindByEmpNoAndFromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.findByEmployee_EmpNoAndFromDate(empNo, fromDate)).thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.findByEmpNoAndFromDate(empNo, fromDate);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for getting department managers by employee number, department number, and from date.
     */
    @Test
    void testGetDepartmentManagersByEmpNoAndDeptNoAndFromDate() {
        int empNo = 1;
        String deptNo = "D001";
        Date fromDate = new Date(System.currentTimeMillis());
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo, fromDate))
                .thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.getDepartmentManagersByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for saving a department manager.
     */
    @Test
    void testSaveDepartmentManager() {
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.save(departmentManager)).thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.saveDepartmentManager(departmentManager);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for getting employees from a specific date.
     */
    @Test
    void testGetEmployeesFromDate() {
        Date fromDate = new Date(System.currentTimeMillis());
        List<Object[]> employees = new ArrayList<>();
        employees.add(new Object[]{1, "John"});
        employees.add(new Object[]{2, "Jane"});

        when(departmentManagerRepository.findEmployeesFromDate(fromDate)).thenReturn(employees);

        List<Object[]> response = departmentManagerService.getEmployeesFromDate(fromDate);

        assertEquals(employees, response);
    }

    /**
     * Test case for updating a department manager by employee number and department number.
     */
    @Test
    void testUpdateByEmpNoAndDeptNo() {
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.save(departmentManager)).thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.updateByEmpNoAndDeptNo(departmentManager);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for updating a department manager by employee number and from date.
     */
    @Test
    void testUpdateByEmpNoAndFromDate() {
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.save(departmentManager)).thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.updateByEmpNoAndFromDate(departmentManager);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for updating a department manager by department number and from date.
     */
    @Test
    void testUpdateByDeptNoAndFromDate() {
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.save(departmentManager)).thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.updateByDeptNoAndFromDate(departmentManager);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for updating a department manager by employee number, department number, and from date.
     */
    @Test
    void testUpdateByEmpNoAndDeptNoAndFromDate() {
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.save(departmentManager)).thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.updateByEmpNoAndDeptNoAndFromDate(departmentManager);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for updating a department manager by department number and from date.
     */
    @Test
    void testUpdatebydeptnoAndfromdate() {
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.save(departmentManager)).thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.updatebydeptnoAndfromdate(departmentManager);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for updating a department manager by employee number, department number, and from date.
     */
    @Test
    void testUpdatebyempnoAnddeptnoAndfromdate() {
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.save(departmentManager)).thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.updatebyempnoAnddeptnoAndfromdate(departmentManager);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for deleting a department manager by employee number, department number, and from date.
     */
    @Test
    void testDeleteByEmpNoAndDeptNoAndFromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        String deptNo = "D001";

        departmentManagerService.deleteByEmpNoAndDeptNoAndFromDate(empNo, fromDate, deptNo);

        verify(departmentManagerRepository, times(1)).deleteByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
    }

    /**
     * Test case for deleting a department manager by employee number and department number.
     */
    @Test
    void testDeleteByEmpNoAndDeptNo() {
        int empNo = 1;
        String deptNo = "D001";

        departmentManagerService.deleteByEmpNoAndDeptNo(empNo, deptNo);

        verify(departmentManagerRepository, times(1)).deleteByEmpNoAndDeptNo(empNo, deptNo);
    }

    /**
     * Test case for deleting a department manager by employee number and from date.
     */
    @Test
    void testDeleteByEmpNoAndFromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());

        departmentManagerService.deleteByEmpNoAndFromDate(empNo, fromDate);

        verify(departmentManagerRepository, times(1)).deleteByEmpNoAndFromDate(empNo, fromDate);
    }

    /**
     * Test case for deleting a department manager by department number and from date.
     */
    @Test
    void testDeleteByDeptNoAndFromDate() {
        String deptNo = "D001";
        Date fromDate = new Date(System.currentTimeMillis());

        departmentManagerService.deleteByDeptNoAndFromDate(deptNo, fromDate);

        verify(departmentManagerRepository, times(1)).deleteByDeptNoAndFromDate(deptNo, fromDate);
    }

    /**
     * Test case for deleting a department manager by department number.
     */
    @Test
    void testDeleteByDeptNo() {
        String deptNo = "D001";

        departmentManagerService.deleteByDeptNo(deptNo);

        verify(departmentManagerRepository, times(1)).deleteByDeptNo(deptNo);
    }

    /**
     * Test case for getting a department manager by employee number, department number, and from date.
     */
    @Test
    void testGetDepartmentManagerByEmpNoAndDeptNoAndFromDate() {
        int empNo = 1;
        String deptNo = "D001";
        Date fromDate = new Date(System.currentTimeMillis());
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo, fromDate))
                .thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.getDepartmentManagerByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for getting a department manager by department number and from date.
     */
    @Test
    void testGetDepartmentDeptNoAndFromDate() {
        String deptNo = "D001";
        Date fromDate = new Date(System.currentTimeMillis());
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.findByDepartment_DeptNoAndFromDate(deptNo, fromDate))
                .thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.getDepartmentDeptNoAndFromDate(deptNo, fromDate);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for getting a department manager by employee number and department number.
     */
    @Test
    void testGetDepartmentManagerByEmpNoAndDeptNo() {
        int empNo = 1;
        String deptNo = "D001";
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.findByEmployee_EmpNoAndDepartment_DeptNo(empNo, deptNo))
                .thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.getDepartmentManagerByEmpNoAndDeptNo(empNo, deptNo);

        assertEquals(departmentManager, response);
    }

    /**
     * Test case for getting a department manager by employee number and from date.
     */
    @Test
    void testGetDepartmentManagerByEmpNoAndFromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        DepartmentManager departmentManager = new DepartmentManager();

        when(departmentManagerRepository.findByEmployee_EmpNoAndFromDate(empNo, fromDate)).thenReturn(departmentManager);

        DepartmentManager response = departmentManagerService.getDepartmentManagerByEmpNoAndFromDate(empNo, fromDate);

        assertEquals(departmentManager, response);
    }
}
