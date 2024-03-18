/**
 * Unit tests for the TitlesController class.
 */
package com.hrms.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.hrms.controller.TitlesController;
import com.hrms.entities.Titles;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.TitleService;

class TitlesControllerTest {

    @Mock
    private TitleService titleService;

    @InjectMocks
    private TitlesController titlesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for finding all titles.
     */
    @Test
    void testFindAllTitles() {
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());
        titlesList.add(new Titles());

        when(titleService.getTitles()).thenReturn(titlesList);

        List<Titles> response = titlesController.findAllSalary();

        assertEquals(titlesList, response);
    }

    /**
     * Test case for adding new titles.
     */
    @Test
    void testAddNewTitles() {
        Titles titles = new Titles();
        Titles createdTitle = new Titles();

        when(titleService.addTitle(titles)).thenReturn(createdTitle);

        ResponseEntity<Titles> response = titlesController.addNewTitles(titles);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdTitle, response.getBody());
    }

    /**
     * Test case for adding new titles with invalid data.
     */
    @Test
    void testAddNewTitlesInvalidData() {
        Titles titles = new Titles();

        when(titleService.addTitle(titles)).thenReturn(null);

        assertThrows(InvalidDataException.class, () -> titlesController.addNewTitles(titles));
    }

    /**
     * Test case for finding titles by empNo, fromDate, and title.
     */
    @Test
    void testFindByEmpNoAndFromDateAndTitle() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        String title = "Manager";
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());

        when(titleService.getTitleByEmpNoAndDeptNo(empNo, fromDate, title)).thenReturn(titlesList);

        List<Titles> response = titlesController.findByEmpNoAndFromDateAndTitle(empNo, fromDate, title);

        assertEquals(titlesList, response);
    }

    /**
     * Test case for finding all titles by title.
     */
    @Test
    void testFindAllByTitle() {
        String title = "Manager";
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());

        when(titleService.getAllByTitle(title)).thenReturn(titlesList);

        List<Titles> response = titlesController.findAllByTitle(title);

        assertEquals(titlesList, response);
    }

    /**
     * Test case for finding titles by fromDate.
     */
    @Test
    void testFindByFromDate() {
        Date fromDate = new Date(System.currentTimeMillis());
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());

        when(titleService.findAllByFromDate(fromDate)).thenReturn(titlesList);

        List<Titles> response = titlesController.findByFromDate(fromDate);

        assertEquals(titlesList, response);
    }

    /**
     * Test case for finding titles by fromDate and title.
     */
    @Test
    void testFindByFromDateAndTitle() {
        Date fromDate = new Date(System.currentTimeMillis());
        String title = "Manager";
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());

        when(titleService.getTitleByTitleAndFromDate(fromDate, title)).thenReturn(titlesList);

        List<Titles> response = titlesController.findByEmpNoAndFromDateAndTitle(fromDate, title);

        assertEquals(titlesList, response);
    }

    /**
     * Test case for finding titles by empNo and title.
     */
    @Test
    void testFindByEmpNoAndAndTitle() {
        int empNo = 1;
        String title = "Manager";
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());

        when(titleService.getTitleByTitleAndFromDate(empNo, title)).thenReturn(titlesList);

        List<Titles> response = titlesController.findByEmpNoAndAndTitle(empNo, title);

        assertEquals(titlesList, response);
    }

    /**
     * Test case for finding titles by empNo.
     */
    @Test
    void testFindByEmpNo() {
        int empNo = 1;
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());

        when(titleService.getTitleByEmpNo(empNo)).thenReturn(titlesList);

        List<Titles> response = titlesController.findByEmpNo(empNo);

        assertEquals(titlesList, response);
    }

    /**
     * Test case for finding titles by empNo and fromDate.
     */
    @Test
    void testFindByEmpNoAndFromDate() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles());

        when(titleService.getTitleByEmpNoAndFromDate(empNo, fromDate)).thenReturn(titlesList);

        List<Titles> response = titlesController.findByEmpNoAndFromDate(empNo, fromDate);

        assertEquals(titlesList, response);
    }

    /**
     * Test case for updating title by empNo, fromDate, and title when title not found.
     */
    @Test
    void testUpdateTitleByEmpNoFromDateAndTitleNotFound() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        String title = "Manager";

        when(titleService.findByEmpNoAndFromDateAndTitle(empNo, fromDate, title)).thenReturn(null);

        ResponseEntity<Void> response = titlesController.updateTitle(new Titles(), empNo, fromDate, title);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(titleService, times(0)).updateByEmpNo(any());
    }

    /**
     * Test case for updating title by empNo when empNo not found.
     */
    @Test
    void testUpdateTitleByEmpNoNotFound() {
        int empNo = 1;

        when(titleService.getByEmpNo(empNo)).thenReturn(null);

        ResponseEntity<Void> response = titlesController.updateTitle(new Titles(), empNo);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(titleService, times(0)).updateByEmpNo(any());
    }

    /**
     * Test case for updating title by fromDate when fromDate not found.
     */
    @Test
    void testUpdateTitleByFromDateNotFound() {
        Date fromDate = new Date(System.currentTimeMillis());

        when(titleService.findByFromDates(fromDate)).thenReturn(null);

        ResponseEntity<Void> response = titlesController.updateTitle(new Titles(), fromDate);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(titleService, times(0)).updateByEmpNo(any());
    }

    /**
     * Test case for updating title by title when title not found.
     */
    @Test
    void testUpdateTitleByTitleNotFound() {
        String title = "Manager";

        when(titleService.getbytitle(title)).thenReturn(null);

        ResponseEntity<Void> response = titlesController.updateTitle(new Titles(), title);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(titleService, times(0)).updateByEmpNo(any());
    }

    /**
     * Test case for deleting title by empNo, fromDate, and title.
     */
    @Test
    void testDeleteTitleByEmpNoFromDateAndTitle() {
        int empNo = 1;
        Date fromDate = new Date(System.currentTimeMillis());
        String title = "Manager";

        ResponseEntity<String> response = titlesController.deleteTitleByEmpNoFromDateAndTitle(empNo, fromDate, title);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title deleted successfully", response.getBody());

        verify(titleService, times(1)).deleteByEmpNoFromDateAndTitle(empNo, fromDate, title);
    }

    /**
     * Test case for deleting title by empNo.
     */
    @Test
    void testDeleteTitleByEmpNo() {
        int empNo = 1;

        ResponseEntity<String> response = titlesController.deleteTitleByEmpNo(empNo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title deleted successfully", response.getBody());

        verify(titleService, times(1)).deleteByEmpNo(empNo);
    }

    /**
     * Test case for deleting title by fromDate.
     */
    @Test
    void testDeleteTitleByFromDate() {
        Date fromDate = new Date(System.currentTimeMillis());

        ResponseEntity<String> response = titlesController.deleteTitleByEmpNoFromDateAndTitle(fromDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title deleted successfully", response.getBody());

        verify(titleService, times(1)).deleteByFromDate(fromDate);
    }

    /**
     * Test case for deleting title by title.
     */
    @Test
    void testDeleteByTitle() {
        String title = "Manager";

        ResponseEntity<String> response = titlesController.deleteByTitle(title);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Title deleted successfully", response.getBody());

        verify(titleService, times(1)).deleteByTitle(title);
    }
}
