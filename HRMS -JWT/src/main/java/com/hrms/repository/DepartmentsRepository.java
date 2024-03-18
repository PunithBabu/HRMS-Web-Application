package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entities.Departments;

/**
 * Repository interface for managing Departments entities.
 */
@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {

    /**
     * Finds a list of Departments entities by the department number.
     *
     * @param deptNo The department number.
     * @return The list of Departments entities.
     */
    @Query("SELECT d FROM Departments d WHERE d.deptNo = :deptNo")
    List<Departments> findByDeptNo(@Param("deptNo") String deptNo);

    /**
     * Finds a list of Departments entities by the department name.
     *
     * @param deptName The department name.
     * @return The list of Departments entities.
     */
    @Query("SELECT d FROM Departments d WHERE d.deptName = :deptName")
    List<Departments> findByDeptName(@Param("deptName") String deptName);

    /**
     * Deletes a Departments entity by the department number.
     *
     * @param deptNo The department number.
     */
    @Modifying
    @Query("DELETE FROM Departments d WHERE d.deptNo = :deptNo")
    void deleteByDeptNo(@Param("deptNo") String deptNo);

    /**
     * Deletes a Departments entity by the department name.
     *
     * @param deptName The department name.
     */
    @Modifying
    @Query("DELETE FROM Departments d WHERE d.deptName = :deptName")
    void deleteByDeptName(@Param("deptName") String deptName);

    /**
     * Finds a Departments entity by the department number.
     *
     * @param deptNo The department number.
     * @return The Departments entity.
     */
    @Query("SELECT d FROM Departments d WHERE d.deptNo = :deptNo")
    Departments findByDepartmentNumber(@Param("deptNo") String deptNo);

}
