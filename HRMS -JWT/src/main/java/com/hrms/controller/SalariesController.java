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

import com.hrms.entities.Salaries;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.SalariesService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/QAapi/v1/salaries")
@CrossOrigin
public class SalariesController {

    @Autowired
    public SalariesService salaryService;

    /**
     * Fetches all salary objects.
     *
     * @return List of all salary objects.
     */
    @GetMapping("/all")
    @Operation(summary = "Fetch all salary objects")
    public List<Salaries> findAllSalary() {
        return salaryService.getSalary();
    }

    /**
     * Fetches salary objects by from date.
     *
     * @param fromDate The from date to search for.
     * @return List of salary objects matching the from date.
     */
    @GetMapping("/fromdate/{fromdate}")
    @Operation(summary = "Fetch all salary objects by from date")
    public List<Salaries> findSalaryByFromDate(@PathVariable("fromdate") Date fromDate) {
        return salaryService.getSalaryByFromDate(fromDate);
    }

    /**
     * Fetches salary objects by employee number.
     *
     * @param empNo The employee number to search for.
     * @return List of salary objects matching the employee number.
     */
    @GetMapping("/empno/{empno}")
    @Operation(summary = "Fetch all salary objects by empno")
    public List<Salaries> findSalaryByEmployee(@PathVariable("empno") int empNo) {
        return salaryService.getSalariesByEmployee(empNo);
    }

    /**
     * Fetches salary objects by salary range.
     *
     * @param minSalary The minimum salary of the range.
     * @param maxSalary The maximum salary of the range.
     * @return List of salary objects within the specified salary range.
     */
    @GetMapping("/salary/{minsalary}/{maxsalary}")
    @Operation(summary = "Fetch all salary objects by salary")
    public List<Salaries> findSalaryByRange(@PathVariable("minsalary") int minSalary,
                                            @PathVariable("maxsalary") int maxSalary) {
        return salaryService.getSalaryByRange(minSalary, maxSalary);
    }

    /**
     * Fetches a salary object by employee number and from date.
     *
     * @param empNo    The employee number to search for.
     * @param fromDate The from date to search for.
     * @return The salary object matching the employee number and from date.
     */
    @GetMapping("/empNo/{empNo}/fromDate/{fromDate}")
    public Salaries findSalaryByFromDate(@PathVariable("empNo") int empNo,
                                         @PathVariable("fromDate") Date fromDate) {
        return salaryService.getSalaryByEmpNoandFromDate(empNo, fromDate);
    }

    /**
     * Adds a new salary object to the database.
     *
     * @param salary The salary object to add.
     * @return ResponseEntity with the added salary object and HTTP status OK.
     * @throws InvalidDataException if the validation fails for the salary object.
     */
    @PostMapping("/add")
    @Operation(summary = "Add new salary object in DB")
    public ResponseEntity<Salaries> addSalaryC(@RequestBody Salaries salary) {
        Salaries response = salaryService.addSalary(salary);
        if (response != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);
        else
            throw new InvalidDataException("Validation Failed: ");
    }

    /**
     * Updates a salary object by employee number and from date.
     *
     * @param salary   The updated salary object.
     * @param fromDate The from date of the salary object to update.
     * @return ResponseEntity with the updated salary object and HTTP status OK.
     */
    @PutMapping("/fromdate/{fromDate}")
    public ResponseEntity<Salaries> updateSalaryByEmpNoAndFromDate(@RequestBody Salaries salary,
                                                                   @PathVariable("fromDate") Date fromDate) {
        Salaries existingSalary = salaryService.getSalaryByFromDates(fromDate);

        if (existingSalary != null) {
            existingSalary.setSalary(salary.getSalary());
            existingSalary.setToDate(salary.getToDate());
            Salaries updatedSalary = salaryService.updateSalary(existingSalary);
            return ResponseEntity.ok(updatedSalary);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Updates a salary object by employee number and from date.
     *
     * @param salary   The updated salary object.
     * @param empNo    The employee number of the salary object to update.
     * @param fromDate The from date of the salary object to update.
     * @return ResponseEntity with the updated salary object and HTTP status OK.
     */
    @PutMapping("/empno/{empNo}/fromdate/{fromDate}")
    public ResponseEntity<Salaries> updateSalaryByEmpNoAndFromDate(@RequestBody Salaries salary,
                                                                   @PathVariable int empNo,
                                                                   @PathVariable("fromDate") Date fromDate) {
        Salaries existingSalary = salaryService.getSalaryByEmpNoandFromDate(empNo, fromDate);

        if (existingSalary != null) {
            existingSalary.setSalary(salary.getSalary());
            Salaries updatedSalary = salaryService.updateSalary(existingSalary);
            return ResponseEntity.ok(updatedSalary);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Updates a salary object by employee number.
     *
     * @param salary The updated salary object.
     * @param empNo  The employee number of the salary object to update.
     * @return ResponseEntity with the updated salary object and HTTP status OK.
     */
    @PutMapping("/empno/{empNo}")
    public ResponseEntity<Salaries> updateSalaryByEmpNoAndFromDate(@RequestBody Salaries salary,
                                                                   @PathVariable int empNo) {
        Salaries existingSalary = salaryService.getSalariesByEmployees(empNo);

        if (existingSalary != null) {
            existingSalary.setSalary(salary.getSalary());
            existingSalary.setToDate(salary.getToDate());
            Salaries updatedSalary = salaryService.updateSalary(existingSalary);
            return ResponseEntity.ok(updatedSalary);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Deletes a salary object by employee number and from date.
     *
     * @param empNo    The employee number of the salary object to delete.
     * @param fromDate The from date of the salary object to delete.
     * @return ResponseEntity with the success message and HTTP status OK.
     */
    @DeleteMapping("/empno/{empNo}/fromdate/{fromDate}")
    public ResponseEntity<String> deleteTitleByEmpNofromDate(@RequestParam int empNo,
                                                             @RequestParam Date fromDate) {
        salaryService.deleteByEmpNoAndFromDate(empNo, fromDate);
        return ResponseEntity.ok("Title deleted successfully");
    }

    /**
     * Deletes a salary object by from date.
     *
     * @param fromDate The from date of the salary object to delete.
     * @return ResponseEntity with the success message and HTTP status OK.
     */
    @DeleteMapping("/fromdate/{fromDate}")
    public ResponseEntity<String> deleteTitleByEmpNoFromDate(@RequestParam Date fromDate) {
        salaryService.deleteByFromDate(fromDate);
        return ResponseEntity.ok("Title deleted successfully");
    }

    /**
     * Deletes a salary object by employee number.
     *
     * @param empNo The employee number of the salary object to delete.
     * @return ResponseEntity with the success message and HTTP status OK.
     */
    @DeleteMapping("/empno/{empNo}")
    public ResponseEntity<String> deleteTitleByEmpNo(@RequestParam int empNo) {
        salaryService.deleteByEmpNo(empNo);
        return ResponseEntity.ok("Title deleted successfully");
    }

}
