/**
 * The DepartmentManagerController class handles the HTTP requests for Department Manager operations.
 * It provides endpoints for retrieving, adding, updating, and deleting Department Manager objects.
 */

package com.hrms.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entities.DepartmentEmployee;
import com.hrms.entities.DepartmentManager;
import com.hrms.entities.DepartmentManagerId;
import com.hrms.entities.Employee;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.exceptions.NotFoundException;
import com.hrms.services.DepartmentEmployeeService;
import com.hrms.services.DepartmentManagerService;
import com.hrms.services.DepartmentService;
import com.hrms.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/departmentmanager")
@CrossOrigin
public class DepartmentManagerController {

    @Autowired
    private DepartmentManagerService departmentManagerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentServices;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeServices;

    /**
     * Retrieves all Department Managers.
     *
     * @return The list of all Department Managers.
     */
    @GetMapping("/all")
    @Operation(summary = "Fetch all department manager objects")
    public List<DepartmentManager> findAllDepartmentManager() {
        return departmentManagerService.getDepartmentManager();
    }

    /**
     * Retrieves a Department Manager by employee number and department number.
     *
     * @param empNo  The employee number.
     * @param deptNo The department number.
     * @return The Department Manager with the given employee number and department number.
     */
    @GetMapping("/empno/{empNo}/deptno/{deptNo}")
    @Operation(summary = "Search department manager by employee number and department number")
    public DepartmentManager findByEmpNoDeptNo(@PathVariable("empNo") int empNo,
                                              @PathVariable("deptNo") String deptNo) {
        return departmentManagerService.getDepartmentManagersByEmpNoAndDeptNo(empNo, deptNo);
    }

    /**
     * Retrieves a list of Department Managers by department number.
     *
     * @param deptNo The department number.
     * @return The list of Department Managers with the given department number.
     */
    @GetMapping("/deptno/{deptNo}")
    @Operation(summary = "Search department manager by department number")
    public List<DepartmentManager> findByEmpNoDeptNo(@PathVariable("deptNo") String deptNo) {
        return departmentManagerService.findByDeptNo(deptNo);
    }

    /**
     * Retrieves a list of Department Managers by department number and from date.
     *
     * @param deptNo    The department number.
     * @param fromDate  The from date.
     * @return The list of Department Managers with the given department number and from date.
     */
    @GetMapping("/deptno/{deptNo}/fromdate/{fromDate}")
    @Operation(summary = "Fetch department manager objects by department number and from date")
    public List<DepartmentManager> findByDeptNoFromDate(@PathVariable("deptNo") String deptNo,
                                                        @PathVariable("fromDate") Date fromDate) {
        List<DepartmentManager> departmentManagers = departmentManagerService.findByDeptNoAndFromDate(deptNo, fromDate);
        System.out.println(departmentManagers);
        return departmentManagers;
    }

    /**
     * Retrieves a Department Manager by employee number and from date.
     *
     * @param empNo     The employee number.
     * @param fromDate  The from date.
     * @return The Department Manager with the given employee number and from date.
     */
    @GetMapping("/empno/{empNo}/fromdate/{fromDate}")
    @Operation(summary = "Fetch department manager object by employee number and from date")
    public DepartmentManager findByEmpNoFromDate(@PathVariable("empNo") int empNo,
                                                @PathVariable("fromDate") Date fromDate) {
        DepartmentManager departmentManagers = departmentManagerService.findByEmpNoAndFromDate(empNo, fromDate);
        System.out.println(departmentManagers);
        return departmentManagers;
    }

    /**
     * Retrieves a Department Manager by employee number, department number, and from date.
     *
     * @param empNo     The employee number.
     * @param deptNo    The department number.
     * @param fromDate  The from date.
     * @return The Department Manager with the given employee number, department number, and from date.
     */
    @GetMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
    @Operation(summary = "Fetch department manager object by employee number, department number, and from date")
    public DepartmentManager findByEmpNoDeptNoAndFromDate(@PathVariable("empNo") int empNo,
                                                          @PathVariable("deptNo") String deptNo,
                                                          @PathVariable("fromDate") Date fromDate) {
        return departmentManagerService.getDepartmentManagersByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
    }

    /**
     * Retrieves a list of employees from a specific date.
     *
     * @param fromDate The from date.
     * @return The list of employees from the given date.
     */
    @GetMapping("/from-date/{fromDate}")
    @Operation(summary = "Fetch employees from a specific date")
    public List<Object[]> getEmployeesFromDate(@PathVariable("fromDate") Date fromDate) {
        return departmentManagerService.getEmployeesFromDate(fromDate);
    }

    /**
     * Adds a new Department Manager.
     *
     * @param departmentManager The Department Manager to add.
     * @return The created Department Manager.
     */
    @PostMapping("/add")
    @Operation(summary = "Add new department manager object to the database")
    public ResponseEntity<DepartmentManager> addNewdepartmentemployee(
            @RequestBody DepartmentManager departmentManager) {
        DepartmentManager createdTitle = departmentManagerService.saveDepartmentManager(departmentManager);
        if (createdTitle != null) {
            return ResponseEntity.status(HttpStatus.OK).body(createdTitle);
        } else {
            throw new InvalidDataException("Validation Failed");
        }
    }

    /**
     * Updates a Department Manager by department number and from date.
     *
     * @param deptNo            The department number.
     * @param fromDate          The from date.
     * @param departmentManager The updated Department Manager.
     * @return The updated Department Manager.
     */
    @Transactional
    @PutMapping("/deptNo/{deptNo}/fromdate/{fromDate}")
    @Operation(summary = "Update department manager by department number and from date")
    public ResponseEntity<DepartmentManager> updateDepartmentManager(@PathVariable String deptNo,
                                                                      @PathVariable("fromDate") Date fromDate,
                                                                      @RequestBody DepartmentManager departmentManager) {
        DepartmentManager existingDepartment = departmentManagerService.getDepartmentDeptNoAndFromDate(deptNo,
                fromDate);
        if (existingDepartment != null) {
            existingDepartment.getEmployee().setFirstName(departmentManager.getEmployee().getFirstName());
            existingDepartment.getEmployee().setLastName(departmentManager.getEmployee().getLastName());
            existingDepartment.getEmployee().setBirthDate(departmentManager.getEmployee().getBirthDate());
            DepartmentManager updatedManager = departmentManagerService
                    .updatebyempnoAnddeptnoAndfromdate(existingDepartment);

            return ResponseEntity.ok(updatedManager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates a Department Manager by employee number and department number.
     *
     * @param empNo             The employee number.
     * @param deptNo            The department number.
     * @param departmentManager The updated Department Manager.
     * @return The updated Department Manager.
     */
    @Transactional
    @PutMapping("/empNo/{empNo}/deptNo/{deptNo}")
    @Operation(summary = "Update department manager by employee number and department number")
    public ResponseEntity<DepartmentManager> updateDepartmentManager2(@PathVariable int empNo,
                                                                       @PathVariable String deptNo,
                                                                       @RequestBody DepartmentManager departmentManager) {
        DepartmentManager existingDepartment = departmentManagerService.getDepartmentManagerByEmpNoAndDeptNo(empNo,
                deptNo);
        if (existingDepartment != null) {
            existingDepartment.getEmployee().setFirstName(departmentManager.getEmployee().getFirstName());
            existingDepartment.getEmployee().setLastName(departmentManager.getEmployee().getLastName());
            existingDepartment.getEmployee().setBirthDate(departmentManager.getEmployee().getBirthDate());
            DepartmentManager updatedManager = departmentManagerService
                    .updatebyempnoAnddeptnoAndfromdate(existingDepartment);

            return ResponseEntity.ok(updatedManager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates a Department Manager by employee number, department number, and from date.
     *
     * @param empNo             The employee number.
     * @param fromDate          The from date.
     * @param deptNo            The department number.
     * @param departmentManager The updated Department Manager.
     * @return The updated Department Manager.
     */
    @Transactional
    @PutMapping("/empNo/{empNo}/dept{deptNo}/fromdate/{fromDate}")
    @Operation(summary = "Update department manager by employee number, department number, and from date")
    public ResponseEntity<DepartmentManager> updateDepartmentManager(@PathVariable int empNo,
                                                                      @PathVariable("fromDate") Date fromDate,
                                                                      @PathVariable String deptNo,
                                                                      @RequestBody DepartmentManager departmentManager) {
        DepartmentManagerId de = new DepartmentManagerId();
        DepartmentManager existingDepartment = departmentManagerService
                .getDepartmentManagerByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
        if (existingDepartment != null) {
            existingDepartment.getEmployee().setFirstName(departmentManager.getEmployee().getFirstName());
            existingDepartment.getEmployee().setLastName(departmentManager.getEmployee().getLastName());
            existingDepartment.getEmployee().setBirthDate(departmentManager.getEmployee().getBirthDate());
            existingDepartment.getEmployee().setGender(departmentManager.getEmployee().getGender());
            existingDepartment.setFromDate(departmentManager.getFromDate());
            existingDepartment.setToDate(departmentManager.getToDate());

            DepartmentManager updatedManager = departmentManagerService
                    .updatebyempnoAnddeptnoAndfromdate(existingDepartment);

            return ResponseEntity.ok(updatedManager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates a Department Manager by employee number and from date.
     *
     * @param empNo             The employee number.
     * @param fromDate          The from date.
     * @param departmentManager The updated Department Manager.
     * @return The updated Department Manager.
     */
    @Transactional
    @PutMapping("/empNo/{empNo}/fromdate/{fromDate}")
    @Operation(summary = "Update department manager by employee number and from date")
    public ResponseEntity<DepartmentManager> updateDepartmentManager(@PathVariable int empNo,
                                                                      @PathVariable("fromDate") Date fromDate,
                                                                      @RequestBody DepartmentManager departmentManager) {
        DepartmentManager existingDepartment = departmentManagerService.getDepartmentManagerByEmpNoAndFromDate(empNo,
                fromDate);
        if (existingDepartment != null) {
            existingDepartment.getEmployee().setFirstName(departmentManager.getEmployee().getFirstName());
            existingDepartment.getEmployee().setLastName(departmentManager.getEmployee().getLastName());
            existingDepartment.getEmployee().setBirthDate(departmentManager.getEmployee().getBirthDate());
            existingDepartment.getEmployee().setGender(departmentManager.getEmployee().getGender());
            DepartmentManager updatedManager = departmentManagerService
                    .updatebyempnoAnddeptnoAndfromdate(existingDepartment);

            return ResponseEntity.ok(updatedManager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a Department Manager by employee number, department number, and from date.
     *
     * @param empNo     The employee number.
     * @param deptNo    The department number.
     * @param fromDate  The from date.
     * @return The HTTP response indicating success or failure of the delete operation.
     */
    @DeleteMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
    @Operation(summary = "Delete department manager by employee number, department number, and from date")
    public ResponseEntity<Void> deleteDepartmentManagerByEmpNoAndDeptNoAndFromDate(@PathVariable int empNo,
                                                                                    @PathVariable String deptNo,
                                                                                    @PathVariable Date fromDate) {
        departmentManagerService.deleteByEmpNoAndDeptNoAndFromDate(empNo, fromDate, deptNo);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a Department Manager by employee number and department number.
     *
     * @param empNo     The employee number.
     * @param deptNo    The department number.
     * @return The HTTP response indicating success or failure of the delete operation.
     */
    @DeleteMapping("/empno/{empNo}/deptno/{deptNo}")
    @Operation(summary = "Delete department manager by employee number and department number")
    public ResponseEntity<Void> deleteDepartmentManagerByEmpNoAndDeptNo(@PathVariable int empNo,
                                                                        @PathVariable String deptNo) {
        departmentManagerService.deleteByEmpNoAndDeptNo(empNo, deptNo);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a Department Manager by employee number and from date.
     *
     * @param empNo     The employee number.
     * @param fromDate  The from date.
     * @return The HTTP response indicating success or failure of the delete operation.
     */
    @DeleteMapping("/empno/{empNo}/fromdate/{fromDate}")
    @Operation(summary = "Delete department manager by employee number and from date")
    public ResponseEntity<Void> deleteDepartmentManagerByEmpNoAndFromDate(@PathVariable int empNo,
                                                                          @PathVariable Date fromDate) {
        departmentManagerService.deleteByEmpNoAndFromDate(empNo, fromDate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a Department Manager by department number and from date.
     *
     * @param deptNo    The department number.
     * @param fromDate  The from date.
     * @return The HTTP response indicating success or failure of the delete operation.
     */
    @DeleteMapping("/deptno/{deptNo}/fromdate/{fromDate}")
    @Operation(summary = "Delete department manager by department number and from date")
    public ResponseEntity<Void> deleteDepartmentManagerByDeptNoAndFromDate(@PathVariable String deptNo,
                                                                           @PathVariable Date fromDate) {
        departmentManagerService.deleteByDeptNoAndFromDate(deptNo, fromDate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes all Department Managers with a specific department number.
     *
     * @param deptNo    The department number.
     * @return The HTTP response indicating success or failure of the delete operation.
     */
    @DeleteMapping("/deptno/{deptNo}")
    @Operation(summary = "Delete department manager by department number")
    public ResponseEntity<Void> deleteDepartmentManagerByDeptNo(@PathVariable String deptNo) {
        departmentManagerService.deleteByDeptNo(deptNo);
        return ResponseEntity.noContent().build();
    }

    /**
     * Adds an existing Employee to a Department Manager.
     *
     * @param empNo             The employee number.
     * @param departmentManager The Department Manager to add.
     * @return The created Department Manager.
     */
    @PostMapping("/departmentmanager/{empNo}")
    @Operation(summary = "Add an existing Employee to Department Manager")
    public ResponseEntity<DepartmentManager> addExistingEmployeeToDepartmentManager(@PathVariable int empNo,
                                                                                     @RequestBody DepartmentManager departmentManager) {
        Employee employee = employeeService.getEmployeeById(empNo);
        DepartmentEmployee departmentEmployee = departmentEmployeeServices.getDepartEmployeeEmpNo(empNo);
        if (employee == null || departmentEmployee == null) {
            throw new NotFoundException("Employee or DepartmentEmployee not found");
        }
        departmentManager.setEmployee(employee);
        departmentManager.setDepartment(departmentEmployee.getDepartment());

        DepartmentManager createdDepartmentManager = departmentManagerService.saveDepartmentManager(departmentManager);

        departmentEmployeeServices.deleteByEmpNo(empNo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartmentManager);
    }
}
