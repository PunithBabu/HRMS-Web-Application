/**
 * Unit tests for the SalariesController class.
 */
package com.hrms.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hrms.controller.SalariesController;
import com.hrms.entities.Salaries;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.SalariesService;

class SalariesControllerTest {

    @Mock
    private SalariesService salaryService;

    @InjectMocks
    private SalariesController salariesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for finding all salaries.
     */
    @Test
    void testFindAllSalary() {
        List<Salaries> salaryList = new ArrayList<>();
        salaryList.add(new Salaries());
        salaryList.add(new Salaries());

        when(salaryService.getSalary()).thenReturn(salaryList);

        List<Salaries> response = salariesController.findAllSalary();

        assertEquals(salaryList, response);
    }

    /**
     * Test case for finding salaries by fromDate.
     */
    @Test
    void testFindSalaryByFromDate() {
        Date fromDate = new Date(System.currentTimeMillis());
        List<Salaries> salaryList = new ArrayList<>();
        salaryList.add(new Salaries());

        when(salaryService.getSalaryByFromDate(fromDate)).thenReturn(salaryList);

        List<Salaries> response = salariesController.findSalaryByFromDate(fromDate);

        assertEquals(salaryList, response);
    }

    /**
     * Test case for finding salaries by employee.
     */
    @Test
    void testFindSalaryByEmployee() {
        int empNo = 1;
        List<Salaries> salaryList = new ArrayList<>();
        salaryList.add(new Salaries());

        when(salaryService.getSalariesByEmployee(empNo)).thenReturn(salaryList);

        List<Salaries> response = salariesController.findSalaryByEmployee(empNo);

        assertEquals(salaryList, response);
    }

    /**
     * Test case for finding salaries by range.
     */
    @Test
    void testFindSalaryByRange() {
        int minSalary = 50000;
        int maxSalary = 100000;
        List<Salaries> salaryList = new ArrayList<>();
        salaryList.add(new Salaries());

        when(salaryService.getSalaryByRange(minSalary, maxSalary)).thenReturn(salaryList);

        List<Salaries> response = salariesController.findSalaryByRange(minSalary, maxSalary);

        assertEquals(salaryList, response);
    }

    /**
     * Test case for finding salary by fromDate and empNo.
     */
    @Test
    void testFindSalaryByFromDateAndEmpNo() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        Salaries salary = new Salaries();

        when(salaryService.getSalaryByEmpNoandFromDate(empNo, fromDate)).thenReturn(salary);

        Salaries response = salariesController.findSalaryByFromDate(empNo, fromDate);

        assertEquals(salary, response);
    }

    /**
     * Test case for adding salary.
     */
    @Test
    void testAddSalaryC() {
        Salaries salary = new Salaries();

        when(salaryService.addSalary(salary)).thenReturn(salary);

        ResponseEntity<Salaries> response = salariesController.addSalaryC(salary);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(salary, response.getBody());
    }

    /**
     * Test case for adding salary with invalid data.
     */
    @Test
    void testAddSalaryCInvalidData() {
        Salaries salary = new Salaries();

        when(salaryService.addSalary(salary)).thenReturn(null);

        assertThrows(InvalidDataException.class, () -> salariesController.addSalaryC(salary));
    }

    /**
     * Test case for updating salary by empNo and fromDate.
     */
    @Test
    void testUpdateSalaryByEmpNoAndFromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        Salaries salary = new Salaries();

        when(salaryService.getSalaryByEmpNoandFromDate(empNo, fromDate)).thenReturn(salary);
        when(salaryService.updateSalary(salary)).thenReturn(salary);

        ResponseEntity<Salaries> response = salariesController.updateSalaryByEmpNoAndFromDate(salary, empNo, fromDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(salary, response.getBody());
    }

    /**
     * Test case for updating salary by empNo.
     */
    @Test
    void testUpdateSalaryByEmpNo() {
        int empNo = 1;
        Salaries salary = new Salaries();

        when(salaryService.getSalariesByEmployees(empNo)).thenReturn(salary);
        when(salaryService.updateSalary(salary)).thenReturn(salary);

        ResponseEntity<Salaries> response = salariesController.updateSalaryByEmpNoAndFromDate(salary, empNo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(salary, response.getBody());
    }

    /**
     * Test case for deleting salary by empNo and fromDate.
     */
    @Test
    void testDeleteTitleByEmpNofromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());

        ResponseEntity<String> response = salariesController.deleteTitleByEmpNofromDate(empNo, fromDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title deleted successfully", response.getBody());

        verify(salaryService, times(1)).deleteByEmpNoAndFromDate(empNo, fromDate);
    }

    /**
     * Test case for deleting salary by fromDate.
     */
    @Test
    void testDeleteTitleByEmpNoFromDate() {
        Date fromDate = new Date(System.currentTimeMillis());

        ResponseEntity<String> response = salariesController.deleteTitleByEmpNoFromDate(fromDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title deleted successfully", response.getBody());

        verify(salaryService, times(1)).deleteByFromDate(fromDate);
    }

    /**
     * Test case for deleting salary by empNo.
     */
    @Test
    void testDeleteTitleByEmpNo() {
        int empNo = 1;

        ResponseEntity<String> response = salariesController.deleteTitleByEmpNo(empNo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title deleted successfully", response.getBody());

        verify(salaryService, times(1)).deleteByEmpNo(empNo);
    }
}
