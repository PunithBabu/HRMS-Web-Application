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

import com.hrms.entities.Titles;
import com.hrms.repository.TitlesRepository;
import com.hrms.services.TitleService;

@SpringBootTest
public class TitleServiceTest {

    @Mock
    private TitlesRepository titlesRepository;

    @InjectMocks
    private TitleService titleService;

    /**
     * Test case for retrieving all titles.
     */
    @Test
    void testGetTitles() {
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());
        titlesList.add(new Titles());

        when(titlesRepository.findAll()).thenReturn(titlesList);

        List<Titles> response = titleService.getTitles();

        assertNotNull(response);
        assertEquals(titlesList.size(), response.size());
    }

    /**
     * Test case for adding a title.
     */
    @Test
    void testAddTitle() {
        Titles title = new Titles();
        title.setTitle("Manager");

        when(titlesRepository.save(title)).thenReturn(title);

        Titles response = titleService.addTitle(title);

        assertNotNull(response);
        assertEquals(title, response);
    }

    /**
     * Test case for retrieving titles by employee number and department number.
     */
    @Test
    void testGetTitleByEmpNoAndDeptNo() {
        int empNo = 1;
        Date fromDate = Date.valueOf("2022-01-01");
        String title = "Manager";
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());
        titlesList.add(new Titles());

        when(titlesRepository.findByTypeId(empNo, fromDate, title)).thenReturn(titlesList);

        List<Titles> response = titleService.getTitleByEmpNoAndDeptNo(empNo, fromDate, title);

        assertNotNull(response);
        assertEquals(titlesList.size(), response.size());
    }

    /**
     * Test case for retrieving titles by employee number.
     */
    @Test
    void testGetTitleByEmpNo() {
        int empNo = 1;
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());
        titlesList.add(new Titles());

        when(titlesRepository.findByempNo(empNo)).thenReturn(titlesList);

        List<Titles> response = titleService.getTitleByEmpNo(empNo);

        assertNotNull(response);
        assertEquals(titlesList.size(), response.size());
    }

    /**
     * Test case for retrieving all titles by title name.
     */
    @Test
    void testGetAllByTitle() {
        String title = "Manager";
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());
        titlesList.add(new Titles());

        when(titlesRepository.findByTitles(title)).thenReturn(titlesList);

        List<Titles> response = titleService.getAllByTitle(title);

        assertNotNull(response);
        assertEquals(titlesList.size(), response.size());
    }

    /**
     * Test case for retrieving titles by from date.
     */
    @Test
    void testFindAllByFromDate() {
        Date fromDate = Date.valueOf("2022-01-01");
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());
        titlesList.add(new Titles());

        when(titlesRepository.findByFromDate(fromDate)).thenReturn(titlesList);

        List<Titles> response = titleService.findAllByFromDate(fromDate);

        assertNotNull(response);
        assertEquals(titlesList.size(), response.size());
    }

    /**
     * Test case for retrieving titles by from date and title.
     */
    @Test
    void testGetTitleByfromAndFromDate() {
        Date fromDate = Date.valueOf("2022-01-01");
        String title = "Manager";
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());
        titlesList.add(new Titles());

        when(titlesRepository.findByFromDateAndTitle(fromDate, title)).thenReturn(titlesList);

        List<Titles> response = titleService.getTitleByTitleAndFromDate(fromDate, title);

        assertNotNull(response);
        assertEquals(titlesList.size(), response.size());
    }

    /**
     * Test case for retrieving titles by employee number and title.
     */
    @Test
    void testGetTitleByTitleAndFromDate() {
        int empNo = 1;
        String title = "Manager";
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());
        titlesList.add(new Titles());

        when(titlesRepository.findByEmpnoAndTitle(empNo, title)).thenReturn(titlesList);

        List<Titles> response = titleService.getTitleByTitleAndFromDate(empNo, title);

        assertNotNull(response);
        assertEquals(titlesList.size(), response.size());
    }

    /**
     * Test case for retrieving titles by employee number and from date.
     */
    @Test
    void testGetTitleByEmpNoAndFromDate() {
        int empNo = 1;
        Date fromDate = Date.valueOf("2022-01-01");
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());
        titlesList.add(new Titles());

        when(titlesRepository.findByFromDateAnDempNo(empNo, fromDate)).thenReturn(titlesList);

        List<Titles> response = titleService.getTitleByEmpNoAndFromDate(empNo, fromDate);

        assertNotNull(response);
        assertEquals(titlesList.size(), response.size());
    }

    /**
     * Test case for retrieving a title by employee number, from date, and title.
     */
    @Test
    void testFindByEmpNoAndFromDateAndTitle() {
        int empNo = 1;
        Date fromDate = Date.valueOf("2022-01-01");
        String title = "Manager";
        Titles titleObj = new Titles();

        when(titlesRepository.findByEmployee_EmpNoAndFromDateAndTitle(empNo, fromDate, title)).thenReturn(titleObj);

        Titles response = titleService.findByEmpNoAndFromDateAndTitle(empNo, fromDate, title);

        assertNotNull(response);
        assertEquals(titleObj, response);
    }

    /**
     * Test case for retrieving a title by employee number.
     */
    @Test
    void testGetByEmpNo() {
        int empNo = 1;
        Titles title = new Titles();

        when(titlesRepository.findByEmployee_EmpNo(empNo)).thenReturn(title);

        Titles response = titleService.getByEmpNo(empNo);

        assertNotNull(response);
        assertEquals(title, response);
    }

    /**
     * Test case for retrieving a title by from date.
     */
    @Test
    void testFindByFromDates() {
        Date fromDate = Date.valueOf("2022-01-01");
        Titles title = new Titles();

        when(titlesRepository.findByFromDate1(fromDate)).thenReturn(title);

        Titles response = titleService.findByFromDates(fromDate);

        assertNotNull(response);
        assertEquals(title, response);
    }

    /**
     * Test case for retrieving a title by title name.
     */
    @Test
    void testGetByTitle() {
        String title = "Manager";
        Titles titleObj = new Titles();

        when(titlesRepository.findByTitle(title)).thenReturn(titleObj);

        Titles response = titleService.getbytitle(title);

        assertNotNull(response);
        assertEquals(titleObj, response);
    }

    /**
     * Test case for updating a title by employee number.
     */
    @Test
    void testUpdateByEmpNo() {
        Titles title = new Titles();
        title.setTitle("Manager");

        when(titlesRepository.save(title)).thenReturn(title);

        Titles response = titleService.updateByEmpNo(title);

        assertNotNull(response);
        assertEquals(title, response);
    }

    /**
     * Test case for deleting a title by employee number, from date, and title.
     */
    @Test
    void testDeleteByEmpNoFromDateAndTitle() {
        int empNo = 1;
        Date fromDate = Date.valueOf("2022-01-01");
        String title = "Manager";

        titleService.deleteByEmpNoFromDateAndTitle(empNo, fromDate, title);

        verify(titlesRepository, times(1)).deleteByEmployee_EmpNoAndFromDateAndTitle(empNo, fromDate, title);
    }

    /**
     * Test case for deleting a title by employee number.
     */
    @Test
    void testDeleteByEmpNo() {
        int empNo = 1;

        titleService.deleteByEmpNo(empNo);

        verify(titlesRepository, times(1)).deleteByEmployee_EmpNo(empNo);
    }

    /**
     * Test case for deleting titles by from date.
     */
    @Test
    void testDeleteByFromDate() {
        Date fromDate = Date.valueOf("2022-01-01");

        titleService.deleteByFromDate(fromDate);

        verify(titlesRepository, times(1)).deleteByFromDate(fromDate);
    }

    /**
     * Test case for deleting titles by title name.
     */
    @Test
    void testDeleteByTitle() {
        String title = "Manager";

        titleService.deleteByTitle(title);

        verify(titlesRepository, times(1)).deletebyTitle(title);
    }
}
