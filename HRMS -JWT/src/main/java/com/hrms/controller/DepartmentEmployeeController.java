/**
 * The DepartmentEmployeeController class handles the HTTP requests related to Department Employee management.
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
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.DepartmentEmployeeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/deptemp")
@CrossOrigin
public class DepartmentEmployeeController {

    @Autowired
    private DepartmentEmployeeService departmentEmployeeServices;

    /**
     * Retrieves all department employees.
     *
     * @return The list of department employees.
     */
    @GetMapping("/all")
    public List<DepartmentEmployee> findAlldepartmentsemployee() {
        return departmentEmployeeServices.getdepartmentemployee();
    }

    /**
     * Retrieves a department employee by employee number and department number.
     *
     * @param empNo   The employee number.
     * @param deptNo  The department number.
     * @return The department employee.
     */
    @GetMapping("/empno/{empNo}/deptno/{deptNo}")
    public DepartmentEmployee findByEmpNoAndDeptNo(@PathVariable("empNo") int empNo,
                                                   @PathVariable("deptNo") String deptNo) {
        return departmentEmployeeServices.getDepartEmployeeByEmpNoAndDeptNo(empNo, deptNo);
    }

    /**
     * Retrieves department employees by employee number.
     *
     * @param empNo  The employee number.
     * @return The list of department employees.
     */
    @GetMapping("/empno/{empNo}")
    public List<DepartmentEmployee> findByEmpNo(@PathVariable("empNo") int empNo) {
        return departmentEmployeeServices.getDepartEmployeeByEmpNo(empNo);
    }

    /**
     * Retrieves department employees by department number and year.
     *
     * @param deptNo  The department number.
     * @param year    The year.
     * @return The list of department employees.
     */
    @GetMapping("/department/{deptNo}/year/{year}")
    public List<DepartmentEmployee> getEmployeesByDepartmentAndYear(@PathVariable("deptNo") String deptNo,
                                                                     @PathVariable("year") int year) {
        return departmentEmployeeServices.getEmployeesByDepartmentAndYear(deptNo, year);
    }

    /**
     * Retrieves department employees by department number and from date.
     *
     * @param deptNo    The department number.
     * @param fromDate  The from date.
     * @return The list of department employees.
     */
    @GetMapping("/deptno/{deptNo}/fromdate/{fromDate}")
    public List<DepartmentEmployee> findByDeptNoFromDate(@PathVariable("deptNo") String deptNo,
                                                         @PathVariable("fromDate") Date fromDate) {
        List<DepartmentEmployee> departmentManagers = departmentEmployeeServices.findByDeptNoAndFromDate(deptNo, fromDate);
        System.out.println(departmentManagers);
        return departmentManagers;
    }

    /**
     * Retrieves department employees by department number.
     *
     * @param deptNo  The department number.
     * @return The list of department employees.
     */
    @GetMapping("/deptno/{deptNo}")
    public List<DepartmentEmployee> findByDeptNo(@PathVariable("deptNo") String deptNo) {
        List<DepartmentEmployee> departmentEmployee = departmentEmployeeServices.findByDeptNo(deptNo);
        System.out.println(departmentEmployee);
        return departmentEmployee;
    }

    /**
     * Retrieves a department employee by employee number and from date.
     *
     * @param empNo     The employee number.
     * @param fromDate  The from date.
     * @return The department employee.
     */
    @GetMapping("/empno/{empNo}/fromdate/{fromDate}")
    public DepartmentEmployee findByempNoFromDate(@PathVariable("empNo") int empNo,
                                                  @PathVariable("fromDate") Date fromDate) {
        DepartmentEmployee departmentManagers = departmentEmployeeServices.findByempNoAndFromDate(empNo, fromDate);
        System.out.println(departmentManagers);
        return departmentManagers;
    }

    /**
     * Retrieves a department employee by employee number, department number, and from date.
     *
     * @param empNo     The employee number.
     * @param deptNo    The department number.
     * @param fromDate  The from date.
     * @return The department employee.
     */
    @GetMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
    public DepartmentEmployee findbyempnodeptnoAndfromdate(@PathVariable("empNo") int empNo,
                                                           @PathVariable("deptNo") String deptNo,
                                                           @PathVariable("fromDate") Date fromDate) {
        return departmentEmployeeServices.getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
    }

    /**
     * Adds a new department employee.
     *
     * @param departmentEmployee  The department employee object to be added.
     * @return The created department employee.
     */
    @PostMapping("/add")
    @Operation(summary = "Add new saveDepartmentEmployee object in DB")
    public ResponseEntity<DepartmentEmployee> addNewdepartmentemployee(
            @RequestBody DepartmentEmployee departmentEmployee) {
        DepartmentEmployee createdTitle = departmentEmployeeServices.saveDepartmentEmployee(departmentEmployee);
        if (createdTitle != null) {
            return ResponseEntity.status(HttpStatus.OK).body(createdTitle);
        } else {
            throw new InvalidDataException("Validation Failed");
        }
    }

    /**
     * Updates a department employee by employee number, department number, and from date.
     *
     * @param empNo              The employee number.
     * @param deptNo             The department number.
     * @param fromDate           The from date.
     * @param departmentEmployee The updated department employee object.
     * @return The updated department employee.
     */
    @Transactional
    @PutMapping("/empNo/{empNo}/deptNo/{deptNo}/fromDate/{fromDate}")
    public ResponseEntity<DepartmentEmployee> updateDepartmentEmployee(@PathVariable int empNo,
                                                                        @PathVariable String deptNo,
                                                                        @PathVariable("fromDate") Date fromDate,
                                                                        @RequestBody DepartmentEmployee departmentEmployee) {

        DepartmentEmployee existingEmployee = departmentEmployeeServices
                .getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
        if (existingEmployee != null) {
            existingEmployee.getEmployee().setFirstName(departmentEmployee.getEmployee().getFirstName());
            existingEmployee.getEmployee().setLastName(departmentEmployee.getEmployee().getLastName());
            existingEmployee.getEmployee().setBirthDate(departmentEmployee.getEmployee().getBirthDate());
            existingEmployee.getEmployee().setGender(departmentEmployee.getEmployee().getGender());
            existingEmployee.setFromDate(departmentEmployee.getFromDate());
            existingEmployee.setToDate(departmentEmployee.getToDate());

            DepartmentEmployee updatedEmployee = departmentEmployeeServices.updateDepartmentEmployee(existingEmployee);

            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates a department employee by employee number and from date.
     *
     * @param empNo              The employee number.
     * @param fromDate           The from date.
     * @param departmentEmployee The updated department employee object.
     * @return The updated department employee.
     */
    @Transactional
    @PutMapping("/empNo/{empNo}/fromDate/{fromDate}")
    public ResponseEntity<DepartmentEmployee> updateDepartmentEmployee(@PathVariable int empNo,
                                                                        @PathVariable("fromDate") Date fromDate,
                                                                        @RequestBody DepartmentEmployee departmentEmployee) {

        DepartmentEmployee existingEmployee = departmentEmployeeServices.findByempNoAndFromDate(empNo, fromDate);
        if (existingEmployee != null) {
            existingEmployee.getEmployee().setFirstName(departmentEmployee.getEmployee().getFirstName());
            existingEmployee.getEmployee().setLastName(departmentEmployee.getEmployee().getLastName());
            existingEmployee.getEmployee().setBirthDate(departmentEmployee.getEmployee().getBirthDate());

            DepartmentEmployee updatedEmployee = departmentEmployeeServices.updateDepartmentEmployee(existingEmployee);

            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates a department employee by employee number and department number.
     *
     * @param empNo              The employee number.
     * @param deptNo             The department number.
     * @param departmentEmployee The updated department employee object.
     * @return The updated department employee.
     */
    @Transactional
    @PutMapping("/empNo/{empNo}/deptNo/{deptNo}")
    public ResponseEntity<DepartmentEmployee> updateDepartmentEmployee(@PathVariable int empNo,
                                                                        @PathVariable String deptNo,
                                                                        @RequestBody DepartmentEmployee departmentEmployee) {

        DepartmentEmployee existingEmployee = departmentEmployeeServices.getDepartEmployeeByEmpNoAndDeptNo(empNo,
                deptNo);
        if (existingEmployee != null) {
            existingEmployee.getEmployee().setFirstName(departmentEmployee.getEmployee().getFirstName());
            existingEmployee.getEmployee().setLastName(departmentEmployee.getEmployee().getLastName());
            existingEmployee.getEmployee().setBirthDate(departmentEmployee.getEmployee().getBirthDate());

            DepartmentEmployee updatedEmployee = departmentEmployeeServices.updateDepartmentEmployee(existingEmployee);

            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates a department employee by department number and from date.
     *
     * @param deptNo             The department number.
     * @param fromDate           The from date.
     * @param departmentEmployee The updated department employee object.
     * @return The updated department employee.
     */
    @Transactional
    @PutMapping("/deptNo/{deptNo}/fromDate/{fromDate}")
    public ResponseEntity<DepartmentEmployee> updateDepartmentEmployee(@PathVariable String deptNo,
                                                                        @PathVariable("fromDate") Date fromDate,
                                                                        @RequestBody DepartmentEmployee departmentEmployee) {

        DepartmentEmployee existingEmployee = departmentEmployeeServices.findBysDeptNoAndFromDate(deptNo, fromDate);
        if (existingEmployee != null) {
            existingEmployee.getEmployee().setFirstName(departmentEmployee.getEmployee().getFirstName());
            existingEmployee.getEmployee().setLastName(departmentEmployee.getEmployee().getLastName());
            existingEmployee.getEmployee().setBirthDate(departmentEmployee.getEmployee().getBirthDate());

            DepartmentEmployee updatedEmployee = departmentEmployeeServices.updateDepartmentEmployee(existingEmployee);

            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a department employee by employee number, department number, and from date.
     *
     * @param empNo     The employee number.
     * @param deptNo    The department number.
     * @param fromDate  The from date.
     * @return The response entity indicating the success of the operation.
     */
    @DeleteMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
    public ResponseEntity<Void> deleteDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(@PathVariable int empNo,
                                                                                     @PathVariable String deptNo,
                                                                                     @PathVariable Date fromDate) {
        departmentEmployeeServices.deleteByEmpNoAndDeptNoAndFromDate(empNo, fromDate, deptNo);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a department employee by employee number and department number.
     *
     * @param empNo    The employee number.
     * @param deptNo   The department number.
     * @return The response entity indicating the success of the operation.
     */
    @DeleteMapping("/empno/{empNo}/deptno/{deptNo}")
    public ResponseEntity<Void> deleteDepartmentEmployeeByEmpNoAndDeptNo(@PathVariable int empNo,
                                                                         @PathVariable String deptNo) {
        departmentEmployeeServices.deleteByEmpNoAndDeptNo(empNo, deptNo);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a department employee by employee number and from date.
     *
     * @param empNo      The employee number.
     * @param fromDate   The from date.
     * @return The response entity indicating the success of the operation.
     */
    @DeleteMapping("/empno/{empNo}/fromdate/{fromDate}")
    public ResponseEntity<Void> deleteDepartmentEmployeeByEmpNoAndFromDate(@PathVariable int empNo,
                                                                           @PathVariable Date fromDate) {
        departmentEmployeeServices.deleteByEmpNoAndFromDate(empNo, fromDate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a department employee by department number and from date.
     *
     * @param deptNo     The department number.
     * @param fromDate   The from date.
     * @return The response entity indicating the success of the operation.
     */
    @DeleteMapping("/deptno/{deptNo}/fromdate/{fromDate}")
    public ResponseEntity<Void> deleteDepartmentEmployeeByDeptNoAndFromDate(@PathVariable String deptNo,
                                                                            @PathVariable Date fromDate) {
        departmentEmployeeServices.deleteByDeptNoAndFromDate(deptNo, fromDate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes department employees by department number.
     *
     * @param deptNo   The department number.
     * @return The response entity indicating the success of the operation.
     */
    @DeleteMapping("/deptno/{deptNo}")
    public ResponseEntity<Void> deleteDepartmentEmployeeByDeptNo(@PathVariable String deptNo) {
        departmentEmployeeServices.deleteBysDeptNo(deptNo);
        return ResponseEntity.noContent().build();
    }

    /**
     * Authenticates an employee by email and password.
     *
     * @param request   The DepartmentEmployee object containing email and password.
     * @return The response entity containing the authenticated employee or a bad request if authentication fails.
     */
    @PostMapping("/adminlogin")
    public ResponseEntity<DepartmentEmployee> loginEmployee(@RequestBody DepartmentEmployee request) {
        DepartmentEmployee employee = departmentEmployeeServices.findByEmailAndPassword(request);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
