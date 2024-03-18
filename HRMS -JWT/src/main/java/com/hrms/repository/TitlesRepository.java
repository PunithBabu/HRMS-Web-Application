package com.hrms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entities.Employee;
import com.hrms.entities.Titles;
import com.hrms.entities.TitlesId;

/**
 * Repository interface for managing Titles entities.
 */
@Repository
public interface TitlesRepository extends JpaRepository<Titles, TitlesId> {

    /**
     * Finds a list of Titles entities by employee number, from date, and title.
     *
     * @param empNo     The employee number.
     * @param fromDate  The from date.
     * @param title     The title.
     * @return The list of Titles entities.
     */
    @Query("SELECT t FROM Titles t WHERE t.employee.empNo = :empNo AND t.fromDate = :fromDate AND t.title = :title")
    List<Titles> findByTypeId(@Param("empNo") int empNo, @Param("fromDate") Date fromDate, @Param("title") String title);

    /**
     * Finds a list of Titles entities by employee.
     *
     * @param employee The Employee entity.
     * @return The list of Titles entities.
     */
    List<Titles> findByEmployee(Employee employee);

    /**
     * Finds a list of Titles entities by title.
     *
     * @param title The title.
     * @return The list of Titles entities.
     */
    @Query("SELECT t FROM Titles t WHERE t.title = :title")
    List<Titles> findByTitles(@Param("title") String title);

    /**
     * Finds a list of Titles entities by from date.
     *
     * @param fromDate The from date.
     * @return The list of Titles entities.
     */
    @Query("SELECT t FROM Titles t WHERE t.fromDate = :fromDate")
    List<Titles> findByFromDate(@Param("fromDate") Date fromDate);

    /**
     * Finds a list of Titles entities by from date and title.
     *
     * @param fromDate The from date.
     * @param title    The title.
     * @return The list of Titles entities.
     */
    @Query("SELECT t FROM Titles t WHERE t.fromDate = :fromDate AND t.title = :title")
    List<Titles> findByFromDateAndTitle(@Param("fromDate") Date fromDate, @Param("title") String title);

    /**
     * Finds a list of Titles entities by employee number and title.
     *
     * @param empNo The employee number.
     * @param title The title.
     * @return The list of Titles entities.
     */
    @Query("SELECT t FROM Titles t WHERE t.employee.empNo = :empNo AND t.title = :title")
    List<Titles> findByEmpnoAndTitle(@Param("empNo") int empNo, @Param("title") String title);

    /**
     * Finds a list of Titles entities by employee number and from date.
     *
     * @param empNo     The employee number.
     * @param fromDate  The from date.
     * @return The list of Titles entities.
     */
    @Query("SELECT t FROM Titles t WHERE t.employee.empNo = :empNo AND t.fromDate = :fromDate")
    List<Titles> findByFromDateAnDempNo(@Param("empNo") int empNo, @Param("fromDate") Date fromDate);

    /**
     * Finds a Titles entity by employee number, from date, and title.
     *
     * @param empNo     The employee number.
     * @param fromDate  The from date.
     * @param title     The title.
     * @return The Titles entity.
     */
    Titles findByEmployee_EmpNoAndFromDateAndTitle(int empNo, Date fromDate, String title);

    /**
     * Finds a Titles entity by employee number.
     *
     * @param empNo The employee number.
     * @return The Titles entity.
     */
    Titles findByEmployee_EmpNo(int empNo);

    /**
     * Finds a Titles entity by from date.
     *
     * @param fromDate The from date.
     * @return The Titles entity.
     */
    @Query("SELECT t FROM Titles t WHERE t.fromDate = :fromDate")
    Titles findByFromDate1(Date fromDate);

    /**
     * Finds a Titles entity by title.
     *
     * @param title The title.
     * @return The Titles entity.
     */
    Titles findByTitle(String title);

    /**
     * Deletes a Titles entity by employee number, from date, and title.
     *
     * @param empNo     The employee number.
     * @param fromDate  The from date.
     * @param title     The title.
     */
    @Modifying
    @Query("DELETE FROM Titles t WHERE t.employee.empNo = :empNo AND t.fromDate = :fromDate AND t.title = :title")
    void deleteByEmployee_EmpNoAndFromDateAndTitle(@Param("empNo") int empNo, @Param("fromDate") Date fromDate, @Param("title") String title);

    /**
     * Deletes Titles entities by employee number.
     *
     * @param empNo The employee number.
     */
    @Modifying
    @Query("DELETE FROM Titles t WHERE t.employee.empNo = :empNo")
    void deleteByEmployee_EmpNo(@Param("empNo") int empNo);

    /**
     * Deletes Titles entities by from date.
     *
     * @param fromDate The from date.
     */
    @Modifying
    @Query("DELETE FROM Titles t WHERE t.fromDate = :fromDate")
    void deleteByFromDate(@Param("fromDate") Date fromDate);

    /**
     * Deletes Titles entities by title.
     *
     * @param title The title.
     */
    @Modifying
    @Query("DELETE FROM Titles t WHERE t.title = :title")
    void deletebyTitle(@Param("title") String title);

    /**
     * Finds a list of Titles entities by employee number.
     *
     * @param empNo The employee number.
     * @return The list of Titles entities.
     */
    @Query("SELECT t FROM Titles t WHERE t.employee.empNo = :empNo")
    List<Titles> findByempNo(int empNo);

}

