package com.hrms.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entities.DepartmentEmployee;
import com.hrms.entities.DepartmentEmployeeId;
import com.hrms.entities.Employee;

/**
 * Repository interface for managing DepartmentEmployee entities.
 */
@Repository
public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, DepartmentEmployeeId> {

    /**
     * Finds a DepartmentEmployee entity by the employee's employee number, department number, and from date.
     *
     * @param empNo    The employee number.
     * @param deptNo   The department number.
     * @param fromDate The from date.
     * @return The DepartmentEmployee entity.
     */
    DepartmentEmployee findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(int empNo, String deptNo, Date fromDate);

    /**
     * Finds a DepartmentEmployee entity by the department number and from date.
     *
     * @param deptNo   The department number.
     * @param fromDate The from date.
     * @return The DepartmentEmployee entity.
     */
    DepartmentEmployee findByDepartment_DeptNoAndFromDate(String deptNo, Date fromDate);

    /**
     * Finds a DepartmentEmployee entity by the employee's employee number and from date.
     *
     * @param empNo    The employee number.
     * @param fromDate The from date.
     * @return The DepartmentEmployee entity.
     */
    DepartmentEmployee findByEmployee_EmpNoAndFromDate(int empNo, Date fromDate);

    /**
     * Finds a DepartmentEmployee entity by the employee's employee number and department number.
     *
     * @param empNo  The employee number.
     * @param deptNo The department number.
     * @return The DepartmentEmployee entity.
     */
    DepartmentEmployee findByEmployee_EmpNoAndDepartment_DeptNo(int empNo, String deptNo);

    /**
     * Finds a list of DepartmentEmployee entities by the employee's employee number.
     *
     * @param empNo The employee number.
     * @return The list of DepartmentEmployee entities.
     */
    List<DepartmentEmployee> findByEmployee_EmpNo(int empNo);

    /**
     * Finds a DepartmentEmployee entity by the employee's employee number.
     *
     * @param empNo The employee number.
     * @return The DepartmentEmployee entity.
     */
    @Query("SELECT dm FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo")
    DepartmentEmployee findByEmployeeEmpNo(int empNo);

    /**
     * Finds a list of DepartmentEmployee entities by the department number.
     *
     * @param deptNo The department number.
     * @return The list of DepartmentEmployee entities.
     */
    List<DepartmentEmployee> findByDepartment_DeptNo(String deptNo);

    /**
     * Finds a list of DepartmentEmployee entities by the department number and from date.
     *
     * @param deptNo   The department number.
     * @param fromDate The from date.
     * @return The list of DepartmentEmployee entities.
     */
    List<DepartmentEmployee> findByDepartmentDeptNoAndFromDate(String deptNo, Date fromDate);

    /**
     * Finds a list of DepartmentEmployee entities by the department number and year.
     *
     * @param deptNo The department number.
     * @param year   The year.
     * @return The list of DepartmentEmployee entities.
     */
    @Query("SELECT e FROM DepartmentEmployee e WHERE e.department.deptNo = :deptNo AND YEAR(e.fromDate) = :year")
    List<DepartmentEmployee> findEmployeesByDepartmentAndYear(@Param("deptNo") String deptNo, @Param("year") int year);

    /**
     * Deletes a DepartmentEmployee entity by the employee's employee number, department number, and from date.
     *
     * @param empNo    The employee number.
     * @param deptNo   The department number.
     * @param fromDate The from date.
     */
    @Modifying
    @Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo AND dm.department.deptNo = :deptNo AND dm.fromDate = :fromDate")
    void deleteByEmpNoAndDeptNoAndFromDate(@Param("empNo") int empNo, @Param("deptNo") String deptNo,
                                           @Param("fromDate") Date fromDate);

    /**
     * Finds the HR department employee by the email, password, and department number.
     *
     * @param email    The email.
     * @param password The password.
     * @param deptNo   The department number.
     * @return The optional DepartmentEmployee entity representing the HR department employee.
     */
    @Query("SELECT dm FROM DepartmentEmployee dm WHERE dm.employee.email = :email AND dm.employee.password = :password AND dm.department.deptNo = :deptNo")
    Optional<DepartmentEmployee> findTheHr(@Param("email") String email, @Param("password") String password,
                                           @Param("deptNo") String deptNo);

    /**
     * Deletes a DepartmentEmployee entity by the employee's employee number and department number.
     *
     * @param empNo  The employee number.
     * @param deptNo The department number.
     */
    @Modifying
    @Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo AND dm.department.deptNo = :deptNo")
    void deleteByEmpNoAndDeptNo(@Param("empNo") int empNo, @Param("deptNo") String deptNo);

    /**
     * Deletes a DepartmentEmployee entity by the employee's employee number.
     *
     * @param empNo The employee number.
     */
    @Modifying
    @Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo")
    void deleteByEmpNo(@Param("empNo") int empNo);

    /**
     * Deletes a DepartmentEmployee entity by the employee's employee number and from date.
     *
     * @param empNo     The employee number.
     * @param fromDate The from date.
     */
    @Modifying
    @Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo AND dm.fromDate = :fromDate")
    void deleteByEmpNoAndFromDate(@Param("empNo") int empNo, @Param("fromDate") Date fromDate);

    /**
     * Deletes a DepartmentEmployee entity by the department number and from date.
     *
     * @param deptNo   The department number.
     * @param fromDate The from date.
     */
    @Modifying
    @Query("DELETE FROM DepartmentEmployee dm WHERE dm.department.deptNo = :deptNo AND dm.fromDate = :fromDate")
    void deleteByDeptNoAndFromDate(@Param("deptNo") String deptNo, @Param("fromDate") Date fromDate);

    /**
     * Deletes a DepartmentEmployee entity by the department number.
     *
     * @param deptNo The department number.
     */
    @Modifying
    @Query("DELETE FROM DepartmentEmployee dm WHERE dm.department.deptNo = :deptNo")
    void deleteByDeptNo(@Param("deptNo") String deptNo);

    /**
     * Finds a list of DepartmentEmployee entities by the employee.
     *
     * @param employee The employee.
     * @return The list of DepartmentEmployee entities.
     */
    List<DepartmentEmployee> findByEmployee(Employee employee);

    /**
     * Deletes a DepartmentEmployee entity by the employee's employee number.
     *
     * @param empNo The employee number.
     */
    @Modifying
    @Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo")
    void deletebyEmpNo(int empNo);

}
