package com.hrms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entities.Employee;
import com.hrms.entities.Salaries;
import com.hrms.entities.SalaryId;

/**
 * Repository interface for managing Salaries entities.
 */
@Repository
public interface SalariesRepository extends JpaRepository<Salaries, SalaryId> {

    /**
     * Finds a list of Salaries entities by the from date.
     *
     * @param fromDate The from date.
     * @return The list of Salaries entities.
     */
    List<Salaries> findAllByFromDate(Date fromDate);

    /**
     * Finds a list of Salaries entities by the employee number.
     *
     * @param empNo The employee number.
     * @return The list of Salaries entities.
     */
    @Query("SELECT s FROM Salaries s WHERE s.employee.empNo = :emp_no")
    List<Salaries> findSalaryByEmployee(@Param("emp_no") int empNo);

    /**
     * Finds a list of Salaries entities within a salary range.
     *
     * @param minSalary The minimum salary.
     * @param maxSalary The maximum salary.
     * @return The list of Salaries entities.
     */
    @Query("SELECT s FROM Salaries s WHERE s.salary >= :minsalary AND s.salary <= :maxsalary")
    List<Salaries> findAllBySalaryBetween(@Param("minsalary") int minSalary, @Param("maxsalary") int maxSalary);

    /**
     * Finds a Salary entity by employee number and from date.
     *
     * @param empNo     The employee number.
     * @param fromDate  The from date.
     * @return The Salary entity.
     */
    @Query("SELECT s FROM Salaries s WHERE s.employee.empNo = :empNo AND s.fromDate = :fromDate")
    Salaries findbyempnoandfromdate(@Param("empNo") int empNo, @Param("fromDate") Date fromDate);

    /**
     * Finds the latest Salary entity by employee number.
     *
     * @param empNo The employee number.
     * @return The latest Salary entity.
     */
    @Query("SELECT s FROM Salaries s WHERE s.employee.empNo = :emp_no ORDER BY s.fromDate DESC")
    Salaries findsSalaryByEmployee(@Param("emp_no") int empNo);

    /**
     * Finds a Salary entity by from date.
     *
     * @param fromDate The from date.
     * @return The Salary entity.
     */
    @Query("SELECT s FROM Salaries s WHERE s.fromDate = :fromDate")
    Salaries findbyfromdate(@Param("fromDate") Date fromDate);

    /**
     * Finds a list of Salaries entities by the employee.
     *
     * @param employee The Employee entity.
     * @return The list of Salaries entities.
     */
    List<Salaries> findByEmployee(Employee employee);

    /**
     * Deletes a Salary entity by employee number and from date.
     *
     * @param empNo     The employee number.
     * @param fromDate  The from date.
     */
    @Modifying
    @Query("DELETE FROM Salaries s WHERE s.employee.empNo = :empNo AND s.fromDate = :fromDate")
    void deleteByEmployeeEmpNoAndFromDate(@Param("empNo") int empNo, @Param("fromDate") Date fromDate);

    /**
     * Deletes Salaries entities by the from date.
     *
     * @param fromDate The from date.
     */
    @Modifying
    @Query("DELETE FROM Salaries s WHERE s.fromDate = :fromDate")
    void deleteByFromDate(@Param("fromDate") Date fromDate);

    /**
     * Deletes Salaries entities by employee number.
     *
     * @param empNo The employee number.
     */
    @Modifying
    @Query("DELETE FROM Salaries s WHERE s.employee.empNo = :empNo")
    void deleteByEmployeeEmpNo(@Param("empNo") int empNo);

}
