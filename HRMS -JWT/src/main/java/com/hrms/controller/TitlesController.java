package com.hrms.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entities.Titles;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.TitleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/titles")
@CrossOrigin
public class TitlesController {

    @Autowired
    private TitleService titleService;

    /**
     * Fetches all title objects.
     *
     * @return List of all title objects.
     */
    @GetMapping("/all")
    @Operation(summary = "Fetch all titles objects")
    public List<Titles> findAllSalary() {
        return titleService.getTitles();
    }

    /**
     * Adds a new title object to the database.
     *
     * @param titles The title object to add.
     * @return ResponseEntity with the added title object and HTTP status OK.
     * @throws InvalidDataException if the validation fails for the title object.
     */
    @PostMapping("/add")
    @Operation(summary = "Add new titles object in DB")
    public ResponseEntity<Titles> addNewTitles(@RequestBody Titles titles) {
        Titles createdTitle = titleService.addTitle(titles);
        if (createdTitle != null) {
            return ResponseEntity.status(HttpStatus.OK).body(createdTitle);
        } else {
            throw new InvalidDataException("Validation Failed");
        }
    }

    /**
     * Searches titles by employee number, from date, and title.
     *
     * @param empNo     The employee number to search for.
     * @param fromDate  The from date to search for.
     * @param title     The title to search for.
     * @return List of titles objects matching the employee number, from date, and title.
     */
    @GetMapping("/empno/{empno}/fromdate/{fromdate}/title/{title}")
    @Operation(summary = "Search titles by from date, empno, title")
    public List<Titles> findByEmpNoAndFromDateAndTitle(@PathVariable("empno") int empNo,
                                                       @PathVariable("fromdate") Date fromDate,
                                                       @PathVariable("title") String title) {
        return titleService.getTitleByEmpNoAndDeptNo(empNo, fromDate, title);
    }

    /**
     * Fetches all title objects by title.
     *
     * @param title The title to search for.
     * @return List of title objects matching the title.
     */
    @GetMapping("/title/{title}")
    @Operation(summary = "Fetch all titles objects by title")
    public List<Titles> findAllByTitle(@PathVariable("title") String title) {
        return titleService.getAllByTitle(title);
    }

    /**
     * Fetches all title objects by from date.
     *
     * @param fromDate The from date to search for.
     * @return List of title objects matching the from date.
     */
    @GetMapping("/fromdate/{fromDate}")
    @Operation(summary = "Fetch all titles objects by from date")
    public List<Titles> findByFromDate(@PathVariable("fromDate") Date fromDateParam) {
        return titleService.findAllByFromDate(fromDateParam);
    }

    /**
     * Fetches all title objects by title and from date.
     *
     * @param fromDate The from date to search for.
     * @param title    The title to search for.
     * @return List of title objects matching the title and from date.
     */
    @GetMapping("title/{title}/fromdate/{fromdate}")
    @Operation(summary = "Fetch all titles objects by title and fromdate")
    public List<Titles> findByEmpNoAndFromDateAndTitle(@PathVariable("fromdate") Date fromDate,
                                                       @PathVariable("title") String title) {
        return titleService.getTitleByTitleAndFromDate(fromDate, title);
    }

    /**
     * Fetches all title objects by employee number and title.
     *
     * @param empNo The employee number to search for.
     * @param title The title to search for.
     * @return List of title objects matching the employee number and title.
     */
    @GetMapping("/empno/{empno}/title/{title}")
    @Operation(summary = "Fetch all titles objects by empno and fromdate")
    public List<Titles> findByEmpNoAndAndTitle(@PathVariable("empno") int empNo,
                                               @PathVariable("title") String title) {
        return titleService.getTitleByTitleAndFromDate(empNo, title);
    }

    /**
     * Fetches all title objects by employee number.
     *
     * @param empNo The employee number to search for.
     * @return List of title objects matching the employee number.
     */
    @GetMapping("/empno/{empno}")
    @Operation(summary = "Fetch all titles objects by empno and fromdate")
    public List<Titles> findByEmpNo(@PathVariable("empno") int empNo) {
        return titleService.getTitleByEmpNo(empNo);
    }

    /**
     * Fetches all title objects by employee number and from date.
     *
     * @param empNo    The employee number to search for.
     * @param fromDate The from date to search for.
     * @return List of title objects matching the employee number and from date.
     */
    @GetMapping("/empno/{empno}/fromdate/{fromdate}")
    @Operation(summary = "Fetch all titles objects by empno and title")
    public List<Titles> findByEmpNoAndFromDate(@PathVariable("empno") int empNo,
                                               @PathVariable("fromdate") Date fromDate) {
        return titleService.getTitleByEmpNoAndFromDate(empNo, fromDate);
    }

    /**
     * Updates a title object by employee number, from date, and title.
     *
     * @param titl     The updated title object.
     * @param empNo    The employee number of the title object to update.
     * @param fromDate The from date of the title object to update.
     * @param title    The title of the title object to update.
     * @return ResponseEntity with HTTP status OK if the update is successful.
     */
    @PutMapping("/empno/{empNo}/fromdate/{fromDate}/title/{title}")
    public ResponseEntity<Void> updateTitle(@RequestBody Titles titl,
                                            @PathVariable int empNo,
                                            @PathVariable Date fromDate,
                                            @PathVariable String title) {
        Titles existingTitle = titleService.findByEmpNoAndFromDateAndTitle(empNo, fromDate, title);
        if (existingTitle != null) {
            existingTitle.setTitle(titl.getTitle());
            existingTitle.setTitle(titl.getTitle());
            existingTitle.setToDate(titl.getToDate());
            titleService.updateByEmpNo(existingTitle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Updates a title object by employee number.
     *
     * @param titl  The updated title object.
     * @param empNo The employee number of the title object to update.
     * @return ResponseEntity with HTTP status OK if the update is successful.
     */
    @PutMapping("/empno/{empNo}")
    public ResponseEntity<Void> updateTitle(@RequestBody Titles titl, @PathVariable int empNo) {
        Titles existingTitle = titleService.getByEmpNo(empNo);
        System.out.println("IN");
        if (existingTitle != null) {
            existingTitle.setTitle(titl.getTitle());
            existingTitle.setToDate(titl.getToDate());
            titleService.updateByEmpNo(existingTitle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Updates a title object by from date.
     *
     * @param titl     The updated title object.
     * @param fromDate The from date of the title object to update.
     * @return ResponseEntity with HTTP status OK if the update is successful.
     */
    @PutMapping("/fromdate/{fromDate}")
    public ResponseEntity<Void> updateTitle(@RequestBody Titles titl, @PathVariable Date fromDate) {
        Titles existingTitle = titleService.findByFromDates(fromDate);
        if (existingTitle != null) {
            existingTitle.setTitle(titl.getTitle());
            existingTitle.setToDate(titl.getToDate());
            titleService.updateByEmpNo(existingTitle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Updates a title object by title.
     *
     * @param titl  The updated title object.
     * @param title The title of the title object to update.
     * @return ResponseEntity with HTTP status OK if the update is successful.
     */
    @PutMapping("/title/{title}")
    public ResponseEntity<Void> updateTitle(@RequestBody Titles titl, @PathVariable String title) {
        Titles existingTitle = titleService.getbytitle(title);
        if (existingTitle != null) {
            existingTitle.setTitle(titl.getTitle());
            existingTitle.setToDate(titl.getToDate());
            titleService.updateByEmpNo(existingTitle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Deletes a title object by employee number, from date, and title.
     *
     * @param empNo    The employee number of the title object to delete.
     * @param fromDate The from date of the title object to delete.
     * @param title    The title of the title object to delete.
     * @return ResponseEntity with the success message and HTTP status OK.
     */
    @DeleteMapping("/empno/{empNo}/fromdate/{fromDate}/title/{title}")
    public ResponseEntity<String> deleteTitleByEmpNoFromDateAndTitle(@RequestParam int empNo,
                                                                      @RequestParam Date fromDate,
                                                                      @RequestParam("title") String title) {
        titleService.deleteByEmpNoFromDateAndTitle(empNo, fromDate, title);
        return ResponseEntity.ok("Title deleted successfully");
    }

    /**
     * Deletes a title object by employee number.
     *
     * @param empNo The employee number of the title object to delete.
     * @return ResponseEntity with the success message and HTTP status OK.
     */
    @DeleteMapping("/empno/{empNo}")
    public ResponseEntity<String> deleteTitleByEmpNo(@PathVariable int empNo) {
        titleService.deleteByEmpNo(empNo);
        return ResponseEntity.ok("Title deleted successfully");
    }

    /**
     * Deletes a title object by from date.
     *
     * @param fromDate The from date of the title object to delete.
     * @return ResponseEntity with the success message and HTTP status OK.
     */
    @DeleteMapping("/fromdate/{fromDate}")
    public ResponseEntity<String> deleteTitleByEmpNoFromDateAndTitle(@RequestParam Date fromDate) {
        titleService.deleteByFromDate(fromDate);
        return ResponseEntity.ok("Title deleted successfully");
    }

    /**
     * Deletes a title object by title.
     *
     * @param title The title of the title object to delete.
     * @return ResponseEntity with the success message and HTTP status OK.
     */
    @DeleteMapping("/title/{title}")
    public ResponseEntity<String> deleteByTitle(@RequestParam("title") String title) {
        titleService.deleteByTitle(title);
        return ResponseEntity.ok("Title deleted successfully");
    }
}
