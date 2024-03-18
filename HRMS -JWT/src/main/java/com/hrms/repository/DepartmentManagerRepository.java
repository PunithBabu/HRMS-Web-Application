package com.hrms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hrms.entities.DepartmentManager;
import com.hrms.entities.DepartmentManagerId;

/**
 * Repository interface for managing DepartmentManager entities.
 */
@Repository
public interface DepartmentManagerRepository extends JpaRepository<DepartmentManager, DepartmentManagerId> {

    /**
     * Finds a DepartmentManager entity by the employee's employee number, department number, and from date.
     *
     * @param empNo    The employee number.
     * @param deptNo   The department number.
     * @param fromDate The from date.
     * @return The DepartmentManager entity.
     */
    DepartmentManager findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(int empNo, String deptNo, Date fromDate);

    /**
     * Finds a DepartmentManager entity by the department number and from date.
     *
     * @param deptNo   The department number.
     * @param fromDate The from date.
     * @return The DepartmentManager entity.
     */
    DepartmentManager findByDepartment_DeptNoAndFromDate(String deptNo, Date fromDate);

    /**
     * Finds a DepartmentManager entity by the employee's employee number and from date.
     *
     * @param empNo    The employee number.
     * @param fromDate The from date.
     * @return The DepartmentManager entity.
     */
    DepartmentManager findByEmployee_EmpNoAndFromDate(int empNo, Date fromDate);

    /**
     * Finds a DepartmentManager entity by the employee's employee number and department number.
     *
     * @param empNo  The employee number.
     * @param deptNo The department number.
     * @return The DepartmentManager entity.
     */
    DepartmentManager findByEmployee_EmpNoAndDepartment_DeptNo(int empNo, String deptNo);

    /**
     * Finds a list of DepartmentManager entities by the department number.
     *
     * @param deptNo The department number.
     * @return The list of DepartmentManager entities.
     */
    List<DepartmentManager> findByDepartmentDeptNo(String deptNo);

    /**
     * Finds a list of DepartmentManager entities by the employee's employee number.
     *
     * @param empNo The employee number.
     * @return The list of DepartmentManager entities.
     */
    List<DepartmentManager> findByEmployee_EmpNo(int empNo);

    /**
     * Finds a list of DepartmentManager entities by the department number and from date.
     *
     * @param deptNo   The department number.
     * @param fromDate The from date.
     * @return The list of DepartmentManager entities.
     */
    List<DepartmentManager> findByDepartmentDeptNoAndFromDate(String deptNo, Date fromDate);

    /**
     * Finds a list of employees' employee number, first name, and hire date who became managers on or after the specified from date.
     *
     * @param fromDate The from date.
     * @return The list of object arrays containing employee number, first name, and hire date.
     */
    @Query("SELECT dm.employee.empNo, dm.employee.firstName, dm.employee.hireDate  FROM DepartmentManager dm WHERE dm.fromDate >= :fromDate")
    List<Object[]> findEmployeesFromDate(@Param("fromDate") Date fromDate);

    /**
     * Deletes a DepartmentManager entity by the employee's employee number, department number, and from date.
     *
     * @param empNo    The employee number.
     * @param deptNo   The department number.
     * @param fromDate The from date.
     */
    @Modifying
    @Query("DELETE FROM DepartmentManager dm WHERE dm.employee.empNo = :empNo AND dm.department.deptNo = :deptNo AND dm.fromDate = :fromDate")
    void deleteByEmpNoAndDeptNoAndFromDate(@Param("empNo") int empNo, @Param("deptNo") String deptNo,
                                           @Param("fromDate") Date fromDate);

    /**
     * Deletes a DepartmentManager entity by the employee's employee number and department number.
     *
     * @param empNo  The employee number.
     * @param deptNo The department number.
     */
    @Modifying
    @Query("DELETE FROM DepartmentManager dm WHERE dm.employee.empNo = :empNo AND dm.department.deptNo = :deptNo")
    void deleteByEmpNoAndDeptNo(@Param("empNo") int empNo, @Param("deptNo") String deptNo);

    /**
     * Deletes a DepartmentManager entity by the employee's employee number and from date.
     *
     * @param empNo     The employee number.
     * @param fromDate The from date.
     */
    @Modifying
    @Query("DELETE FROM DepartmentManager dm WHERE dm.employee.empNo = :empNo AND dm.fromDate = :fromDate")
    void deleteByEmpNoAndFromDate(@Param("empNo") int empNo, @Param("fromDate") Date fromDate);

    /**
     * Deletes a DepartmentManager entity by the department number and from date.
     *
     * @param deptNo   The department number.
     * @param fromDate The from date.
     */
    @Modifying
    @Query("DELETE FROM DepartmentManager dm WHERE dm.department.deptNo = :deptNo AND dm.fromDate = :fromDate")
    void deleteByDeptNoAndFromDate(@Param("deptNo") String deptNo, @Param("fromDate") Date fromDate);

    /**
     * Deletes all DepartmentManager entities by the department number.
     *
     * @param deptNo The department number.
     */
    @Modifying
    @Query("DELETE FROM DepartmentManager dm WHERE dm.department.deptNo = :deptNo")
    void deleteByDeptNo(@Param("deptNo") String deptNo);

    /**
     * Deletes all DepartmentManager entities by the employee's employee number.
     *
     * @param empNo The employee number.
     */
    @Modifying
    @Query("DELETE FROM DepartmentManager dm WHERE dm.employee.empNo = :empNo")
    void deleteByEmpNo(@Param("empNo") int empNo);

}
