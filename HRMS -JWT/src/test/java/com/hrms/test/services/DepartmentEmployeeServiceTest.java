/**
 * This package contains test classes for the services in the HRMS (Human Resource Management System) application.
 */
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

import com.hrms.entities.DepartmentEmployee;
import com.hrms.repository.DepartmentEmployeeRepository;
import com.hrms.services.DepartmentEmployeeService;

/**
 * Test class for the DepartmentEmployeeService.
 */
class DepartmentEmployeeServiceTest {

    @Mock
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @InjectMocks
    private DepartmentEmployeeService departmentEmployeeServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the getDepartmentEmployee method.
     * 
     * Retrieves a list of all department employees.
     * 
     * @assertion The returned list should be equal to the provided departmentEmployeeList.
     */
    @Test
    void testGetDepartmentEmployee() {
        List<DepartmentEmployee> departmentEmployeeList = new ArrayList<>();
        departmentEmployeeList.add(new DepartmentEmployee());
        departmentEmployeeList.add(new DepartmentEmployee());

        when(departmentEmployeeRepository.findAll()).thenReturn(departmentEmployeeList);

        List<DepartmentEmployee> response = departmentEmployeeServices.getdepartmentemployee();

        assertEquals(departmentEmployeeList, response);
    }

    /**
     * Test case for the findByDeptNoAndFromDate method.
     * 
     * Retrieves a list of department employees based on the department number and from date.
     * 
     * @param deptNo   the department number
     * @param fromDate the from date
     * 
     * @assertion The returned list should be equal to the provided departmentEmployeeList.
     */
    @Test
    void testFindByDeptNoAndFromDate() {
        String deptNo = "D001";
        Date fromDate = new Date(System.currentTimeMillis());
        List<DepartmentEmployee> departmentEmployeeList = new ArrayList<>();
        departmentEmployeeList.add(new DepartmentEmployee());

        when(departmentEmployeeRepository.findByDepartmentDeptNoAndFromDate(deptNo, fromDate)).thenReturn(departmentEmployeeList);

        List<DepartmentEmployee> response = departmentEmployeeServices.findByDeptNoAndFromDate(deptNo, fromDate);

        assertEquals(departmentEmployeeList, response);
    }

    /**
     * Test case for the findByDeptNo method.
     * 
     * Retrieves a list of department employees based on the department number.
     * 
     * @param deptNo the department number
     * 
     * @assertion The returned list should be equal to the provided departmentEmployeeList.
     */
    @Test
    void testFindByDeptNo() {
        String deptNo = "D001";
        List<DepartmentEmployee> departmentEmployeeList = new ArrayList<>();
        departmentEmployeeList.add(new DepartmentEmployee());

        when(departmentEmployeeRepository.findByDepartment_DeptNo(deptNo)).thenReturn(departmentEmployeeList);

        List<DepartmentEmployee> response = departmentEmployeeServices.findByDeptNo(deptNo);

        assertEquals(departmentEmployeeList, response);
    }

    /**
     * Test case for the findBysDeptNoAndFromDate method.
     * 
     * Retrieves a department employee based on the department number and from date.
     * 
     * @param deptNo   the department number
     * @param fromDate the from date
     * 
     * @assertion The returned department employee should be equal to the provided departmentEmployee.
     */
    @Test
    void testFindBysDeptNoAndFromDate() {
        String deptNo = "D001";
        Date fromDate = new Date(System.currentTimeMillis());
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();

        when(departmentEmployeeRepository.findByDepartment_DeptNoAndFromDate(deptNo, fromDate)).thenReturn(departmentEmployee);

        DepartmentEmployee response = departmentEmployeeServices.findBysDeptNoAndFromDate(deptNo, fromDate);

        assertEquals(departmentEmployee, response);
    }

    /**
     * Test case for the findByempNoAndFromDate method.
     * 
     * Retrieves a department employee based on the employee number and from date.
     * 
     * @param empNo    the employee number
     * @param fromDate the from date
     * 
     * @assertion The returned department employee should be equal to the provided departmentEmployee.
     */
    @Test
    void testFindByempNoAndFromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();

        when(departmentEmployeeRepository.findByEmployee_EmpNoAndFromDate(empNo, fromDate)).thenReturn(departmentEmployee);

        DepartmentEmployee response = departmentEmployeeServices.findByempNoAndFromDate(empNo, fromDate);

        assertEquals(departmentEmployee, response);
    }

    /**
     * Test case for the getDepartEmployeeByEmpNoAndDeptNo method.
     * 
     * Retrieves a department employee based on the employee number and department number.
     * 
     * @param empNo  the employee number
     * @param deptNo the department number
     * 
     * @assertion The returned department employee should be equal to the provided departmentEmployee.
     */
    @Test
    void testGetDepartEmployeeByEmpNoAndDeptNo() {
        int empNo = 1;
        String deptNo = "D001";
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();

        when(departmentEmployeeRepository.findByEmployee_EmpNoAndDepartment_DeptNo(empNo, deptNo)).thenReturn(departmentEmployee);

        DepartmentEmployee response = departmentEmployeeServices.getDepartEmployeeByEmpNoAndDeptNo(empNo, deptNo);

        assertEquals(departmentEmployee, response);
    }

    /**
     * Test case for the getDepartEmployeeByEmpNo method.
     * 
     * Retrieves a list of department employees based on the employee number.
     * 
     * @param empNo the employee number
     * 
     * @assertion The returned list should be equal to the provided departmentEmployeeList.
     */
    @Test
    void testGetDepartEmployeeByEmpNo() {
        int empNo = 1;
        List<DepartmentEmployee> departmentEmployeeList = new ArrayList<>();
        departmentEmployeeList.add(new DepartmentEmployee());

        when(departmentEmployeeRepository.findByEmployee_EmpNo(empNo)).thenReturn(departmentEmployeeList);

        List<DepartmentEmployee> response = departmentEmployeeServices.getDepartEmployeeByEmpNo(empNo);

        assertEquals(departmentEmployeeList, response);
    }

    /**
     * Test case for the getDepartEmployeeEmpNo method.
     * 
     * Retrieves a department employee based on the employee number.
     * 
     * @param empNo the employee number
     * 
     * @assertion The returned department employee should be equal to the provided departmentEmployee.
     */
    @Test
    void testGetDepartEmployeeEmpNo() {
        int empNo = 1;
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();

        when(departmentEmployeeRepository.findByEmployeeEmpNo(empNo)).thenReturn(departmentEmployee);

        DepartmentEmployee response = departmentEmployeeServices.getDepartEmployeeEmpNo(empNo);

        assertEquals(departmentEmployee, response);
    }

    /**
     * Test case for the getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate method.
     * 
     * Retrieves a department employee based on the employee number, department number, and from date.
     * 
     * @param empNo    the employee number
     * @param deptNo   the department number
     * @param fromDate the from date
     * 
     * @assertion The returned department employee should be equal to the provided departmentEmployee.
     */
    @Test
    void testGetDepartmentEmployeeByEmpNoAndDeptNoAndFromDate() {
        int empNo = 1;
        String deptNo = "D001";
        Date fromDate = new Date(System.currentTimeMillis());
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();

        when(departmentEmployeeRepository.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo, fromDate)).thenReturn(departmentEmployee);

        DepartmentEmployee response = departmentEmployeeServices.getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);

        assertEquals(departmentEmployee, response);
    }

    /**
     * Test case for the updateDepartmentEmployee method.
     * 
     * Updates a department employee.
     * 
     * @param departmentEmployee the department employee to be updated
     * 
     * @assertion The returned department employee should be equal to the provided departmentEmployee.
     */
    @Test
    void testUpdateDepartmentEmployee() {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();

        when(departmentEmployeeRepository.save(departmentEmployee)).thenReturn(departmentEmployee);

        DepartmentEmployee response = departmentEmployeeServices.updateDepartmentEmployee(departmentEmployee);

        assertEquals(departmentEmployee, response);
    }

    /**
     * Test case for the saveDepartmentEmployee method.
     * 
     * Saves a department employee.
     * 
     * @param departmentEmployee the department employee to be saved
     * 
     * @assertion The returned department employee should be equal to the provided departmentEmployee.
     */
    @Test
    void testSaveDepartmentEmployee() {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();

        when(departmentEmployeeRepository.save(departmentEmployee)).thenReturn(departmentEmployee);

        DepartmentEmployee response = departmentEmployeeServices.saveDepartmentEmployee(departmentEmployee);

        assertEquals(departmentEmployee, response);
    }

    /**
     * Test case for the getEmployeesByDepartmentAndYear method.
     * 
     * Retrieves a list of department employees based on the department number and year.
     * 
     * @param deptNo the department number
     * @param year   the year
     * 
     * @assertion The returned list should be equal to the provided departmentEmployeeList.
     */
    @Test
    void testGetEmployeesByDepartmentAndYear() {
        String deptNo = "D001";
        int year = 2021;
        List<DepartmentEmployee> departmentEmployeeList = new ArrayList<>();
        departmentEmployeeList.add(new DepartmentEmployee());

        when(departmentEmployeeRepository.findEmployeesByDepartmentAndYear(deptNo, year)).thenReturn(departmentEmployeeList);

        List<DepartmentEmployee> response = departmentEmployeeServices.getEmployeesByDepartmentAndYear(deptNo, year);

        assertEquals(departmentEmployeeList, response);
    }

    /**
     * Test case for the deleteByEmpNoAndDeptNoAndFromDate method.
     * 
     * Deletes a department employee based on the employee number, department number, and from date.
     * 
     * @param empNo    the employee number
     * @param fromDate the from date
     * @param deptNo   the department number
     */
    @Test
    void testDeleteByEmpNoAndDeptNoAndFromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        String deptNo = "D001";

        departmentEmployeeServices.deleteByEmpNoAndDeptNoAndFromDate(empNo, fromDate, deptNo);

        verify(departmentEmployeeRepository, times(1)).deleteByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
    }

    /**
     * Test case for the deleteByEmpNoAndDeptNo method.
     * 
     * Deletes a department employee based on the employee number and department number.
     * 
     * @param empNo  the employee number
     * @param deptNo the department number
     */
    @Test
    void testDeleteByEmpNoAndDeptNo() {
        int empNo = 1;
        String deptNo = "D001";

        departmentEmployeeServices.deleteByEmpNoAndDeptNo(empNo, deptNo);

        verify(departmentEmployeeRepository, times(1)).deleteByEmpNoAndDeptNo(empNo, deptNo);
    }

    /**
     * Test case for the deleteByEmpNoAndFromDate method.
     * 
     * Deletes a department employee based on the employee number and from date.
     * 
     * @param empNo    the employee number
     * @param fromDate the from date
     */
    @Test
    void testDeleteByEmpNoAndFromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());

        departmentEmployeeServices.deleteByEmpNoAndFromDate(empNo, fromDate);

        verify(departmentEmployeeRepository, times(1)).deleteByEmpNoAndFromDate(empNo, fromDate);
    }

    /**
     * Test case for the deleteByDeptNoAndFromDate method.
     * 
     * Deletes department employees based on the department number and from date.
     * 
     * @param deptNo   the department number
     * @param fromDate the from date
     */
    @Test
    void testDeleteByDeptNoAndFromDate() {
        String deptNo = "D001";
        Date fromDate = new Date(System.currentTimeMillis());

        departmentEmployeeServices.deleteByDeptNoAndFromDate(deptNo, fromDate);

        verify(departmentEmployeeRepository, times(1)).deleteByDeptNoAndFromDate(deptNo, fromDate);
    }

    /**
     * Test case for the deleteBysDeptNo method.
     * 
     * Deletes department employees based on the department number.
     * 
     * @param deptNo the department number
     */
    @Test
    void testDeleteBysDeptNo() {
        String deptNo = "D001";

        departmentEmployeeServices.deleteBysDeptNo(deptNo);

        verify(departmentEmployeeRepository, times(1)).deleteByDeptNo(deptNo);
    }

    /**
     * Test case for the deleteByEmpNo method.
     * 
     * Deletes department employees based on the employee number.
     * 
     * @param empNo the employee number
     */
    @Test
    void testDeleteByEmpNo() {
        int empNo = 1;

        departmentEmployeeServices.deleteByEmpNo(empNo);

        verify(departmentEmployeeRepository, times(1)).deletebyEmpNo(empNo);
    }
}
