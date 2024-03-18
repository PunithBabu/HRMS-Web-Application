/**
 * The DepartmentsController class handles the HTTP requests related to Departments management.
 */
package com.hrms.controller;

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

import com.hrms.entities.Departments;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin
public class DepartmentsController {

    @Autowired
    private DepartmentService departmentServices;
    
    /**
     * Fetches all departments.
     *
     * @return A list of all departments.
     */
    @GetMapping("/all")
    @Operation(summary = "Fetch all departments")
    public List<Departments> findAllDepartments() {
        return departmentServices.getDepartments();
    }

    /**
     * Searches departments by department number.
     *
     * @param deptNo The department number.
     * @return A list of departments matching the department number.
     */
    @GetMapping("/deptno/{deptNo}")
    @Operation(summary = "Search department by department number")
    public List<Departments> findAllDepartmentsByDeptNo(@PathVariable("deptNo") String deptNo) {
        return departmentServices.getDepartmentsByDepNo(deptNo);
    }

    /**
     * Searches departments by department name.
     *
     * @param deptName The department name.
     * @return A list of departments matching the department name.
     */
    @GetMapping("/name/{deptName}")
    @Operation(summary = "Search department by department name")
    public List<Departments> findAllDepartmentsByDeptName(@PathVariable("deptName") String deptName) {
        return departmentServices.getDepartmentsByDeptName(deptName);
    }

    /**
     * Adds a new department.
     *
     * @param department The department to be added.
     * @return A response entity indicating the status of the operation.
     */
    @PostMapping("/add")
    @Operation(summary = "Add new department")
    public ResponseEntity<String> addDepartment(@RequestBody Departments department) {
        Departments addedDepartment = departmentServices.addDepartment(department);

        if (addedDepartment != null) {
            return new ResponseEntity<>("New department added successfully", HttpStatus.OK);
        } else {
            throw new InvalidDataException("Validation Failed");
        }
    }

    /**
     * Updates a department by department number.
     *
     * @param deptNo      The department number.
     * @param departments The updated department.
     * @return A response entity indicating the status of the operation.
     */
    @PutMapping("/deptno/{deptNo}")
    @Operation(summary = "Update department by department number")
    public ResponseEntity<String> updateDepartmentByDeptNo(@PathVariable String deptNo,
                                                           @RequestBody Departments departments) {
        Departments updatedDepartment = departmentServices.updateByDeptNo(departments);
        if (updatedDepartment != null) {
            return new ResponseEntity<>("Department updated successfully", HttpStatus.OK);
        } else {
            throw new InvalidDataException("Updation Failed");
        }
    }

    /**
     * Updates a department by department name.
     *
     * @param deptName    The department name.
     * @param departments The updated department.
     * @return A response entity indicating the status of the operation.
     */
    @PutMapping("/deptname/{deptName}")
    @Operation(summary = "Update department by department name")
    public ResponseEntity<String> updateDepartmentByDeptName(@PathVariable String deptName,
                                                             @RequestBody Departments departments) {
        Departments updatedDepartment = departmentServices.updateByDeptName(departments);
        if (updatedDepartment != null) {
            return new ResponseEntity<>("Department updated successfully", HttpStatus.OK);
        } else {
            throw new InvalidDataException("Updation Failed");
        }
    }

    /**
     * Deletes a department by department number.
     *
     * @param deptNo The department number.
     * @return A response entity indicating the status of the operation.
     */
    @DeleteMapping("/deptNo/{deptNo}")
    @Operation(summary = "Delete department by department number")
    public ResponseEntity<Void> deleteDepartmentByDeptNo(@PathVariable String deptNo) {
        departmentServices.deleteByDeptNo(deptNo);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a department by department name.
     *
     * @param deptName The department name.
     * @return A response entity indicating the status of the operation.
     */
    @DeleteMapping("/deptmentName/{deptName}")
    @Operation(summary = "Delete department by department name")
    public ResponseEntity<String> deleteDepartmentByDeptName(@RequestParam("deptName") String deptName) {
        departmentServices.deleteByDeptName(deptName);
        return ResponseEntity.ok("Department deleted successfully");
    }

}
