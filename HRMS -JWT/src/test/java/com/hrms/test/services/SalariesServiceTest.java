package com.hrms.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hrms.entities.Salaries;
import com.hrms.repository.SalariesRepository;
import com.hrms.services.SalariesService;

@SpringBootTest
public class SalariesServiceTest {

    @Mock
    private SalariesRepository salaryRepository;

    @InjectMocks
    private SalariesService salariesService;

    /**
     * Test case for retrieving all salaries.
     */
    @Test
    void testGetSalary() {
        List<Salaries> salariesList = new ArrayList<>();
        salariesList.add(new Salaries());
        salariesList.add(new Salaries());

        when(salaryRepository.findAll()).thenReturn(salariesList);

        List<Salaries> response = salariesService.getSalary();

        assertNotNull(response);
        assertEquals(salariesList.size(), response.size());
    }

    /**
     * Test case for retrieving salaries by from date.
     */
    @Test
    void testGetSalaryByFromDate() {
        Date fromDate = Date.valueOf("2022-01-01");
        List<Salaries> salariesList = new ArrayList<>();
        salariesList.add(new Salaries());
        salariesList.add(new Salaries());

        when(salaryRepository.findAllByFromDate(fromDate)).thenReturn(salariesList);

        List<Salaries> response = salariesService.getSalaryByFromDate(fromDate);

        assertNotNull(response);
        assertEquals(salariesList.size(), response.size());
    }

    /**
     * Test case for retrieving salaries by employee.
     */
    @Test
    void testGetSalariesByEmployee() {
        int empNo = 1;
        List<Salaries> salariesList = new ArrayList<>();
        salariesList.add(new Salaries());
        salariesList.add(new Salaries());

        when(salaryRepository.findSalaryByEmployee(empNo)).thenReturn(salariesList);

        List<Salaries> response = salariesService.getSalariesByEmployee(empNo);

        assertNotNull(response);
        assertEquals(salariesList.size(), response.size());
    }

    /**
     * Test case for retrieving salaries within a salary range.
     */
    @Test
    void testGetSalaryByRange() {
        int minSalary = 5000;
        int maxSalary = 10000;
        List<Salaries> salariesList = new ArrayList<>();
        salariesList.add(new Salaries());
        salariesList.add(new Salaries());

        when(salaryRepository.findAllBySalaryBetween(minSalary, maxSalary)).thenReturn(salariesList);

        List<Salaries> response = salariesService.getSalaryByRange(minSalary, maxSalary);

        assertNotNull(response);
        assertEquals(salariesList.size(), response.size());
    }

    /**
     * Test case for retrieving a salary by employee number and from date.
     */
    @Test
    void testGetSalaryByEmpNoandFromDate() {
        int empNo = 1;
        Date fromDate = Date.valueOf("2022-01-01");
        Salaries salary = new Salaries();

        when(salaryRepository.findbyempnoandfromdate(empNo, fromDate)).thenReturn(salary);

        Salaries response = salariesService.getSalaryByEmpNoandFromDate(empNo, fromDate);

        assertNotNull(response);
        assertEquals(salary, response);
    }

    /**
     * Test case for adding a salary.
     */
    @Test
    void testAddSalary() {
        Salaries salary = new Salaries();
        salary.setSalary(5000);

        when(salaryRepository.save(salary)).thenReturn(salary);

        Salaries response = salariesService.addSalary(salary);

        assertNotNull(response);
        assertEquals(salary, response);
    }

    /**
     * Test case for updating a salary.
     */
    @Test
    void testUpdateSalary() {
        Salaries salary = new Salaries();
        salary.setSalary(6000);

        when(salaryRepository.save(salary)).thenReturn(salary);

        Salaries response = salariesService.updateSalary(salary);

        assertNotNull(response);
        assertEquals(salary, response);
    }

    /**
     * Test case for retrieving a salary by from date.
     */
    @Test
    void testGetSalaryByFromDates() {
        Date fromDate = Date.valueOf("2022-01-01");
        Salaries salary = new Salaries();

        when(salaryRepository.findbyfromdate(fromDate)).thenReturn(salary);

        Salaries response = salariesService.getSalaryByFromDates(fromDate);

        assertNotNull(response);
        assertEquals(salary, response);
    }

    /**
     * Test case for retrieving salaries by employees.
     */
    @Test
    void testGetSalariesByEmployees() {
        int empNo = 1;
        Salaries salary = new Salaries();

        when(salaryRepository.findsSalaryByEmployee(empNo)).thenReturn(salary);

        Salaries response = salariesService.getSalariesByEmployees(empNo);

        assertNotNull(response);
        assertEquals(salary, response);
    }

    /**
     * Test case for deleting salaries by employee number and from date.
     */
    @Test
    void testDeleteByEmpNoAndFromDate() {
        int empNo = 1;
        Date fromDate = Date.valueOf("2022-01-01");

        salariesService.deleteByEmpNoAndFromDate(empNo, fromDate);

        verify(salaryRepository, times(1)).deleteByEmployeeEmpNoAndFromDate(empNo, fromDate);
    }

    /**
     * Test case for deleting salaries by from date.
     */
    @Test
    void testDeleteByFromDate() {
        Date fromDate = Date.valueOf("2022-01-01");

        salariesService.deleteByFromDate(fromDate);

        verify(salaryRepository, times(1)).deleteByFromDate(fromDate);
    }

    /**
     * Test case for deleting salaries by employee number.
     */
    @Test
    void testDeleteByEmpNo() {
        int empNo = 1;

        salariesService.deleteByEmpNo(empNo);

        verify(salaryRepository, times(1)).deleteByEmployeeEmpNo(empNo);
    }
}
