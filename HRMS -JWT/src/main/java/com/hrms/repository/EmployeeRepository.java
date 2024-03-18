package com.hrms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entities.Employee;
import com.hrms.entities.Gender;

/**
 * Repository interface for managing Employee entities.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Finds a list of Employee entities by the first name.
     *
     * @param firstName The first name.
     * @return The list of Employee entities.
     */
    List<Employee> findByFirstName(String firstName);

    /**
     * Finds an Employee entity by the email and password.
     *
     * @param email    The email.
     * @param password The password.
     * @return The Employee entity.
     */
    Employee findByEmailAndPassword(String email, String password);

    /**
     * Finds a list of Employee entities by the last name.
     *
     * @param lastName The last name.
     * @return The list of Employee entities.
     */
    List<Employee> findByLastName(String lastName);

    /**
     * Finds an Employee entity by the employee number.
     *
     * @param empNo The employee number.
     * @return The Employee entity.
     */
    Employee findByEmpNo(int empNo);

    /**
     * Finds an Employee entity by the employee number and email.
     *
     * @param empNo  The employee number.
     * @param email  The email.
     * @return The Employee entity.
     */
    Employee findByEmpNoAndEmail(int empNo, String email);

    /**
     * Finds a list of Employee entities by the gender.
     *
     * @param gender The gender.
     * @return The list of Employee entities.
     */
    @Query("SELECT E FROM Employee E WHERE E.gender = ?1")
    List<Employee> findByGenderContains(Gender gender);

    /**
     * Counts the number of Employee entities by the gender.
     *
     * @param gender The gender.
     * @return The count of Employee entities.
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.gender = ?1")
    int findByGenderCount(Gender gender);

    /**
     * Finds a list of Employee entities with a certain number of years of experience.
     *
     * @param yearsOfExperience The number of years of experience.
     * @return The list of Employee entities.
     */
    @Query("SELECT e FROM Employee e WHERE YEAR(CURRENT_DATE) - YEAR(e.hireDate) >= :yearsOfExperience")
    List<Employee> findEmployeesWithExperience(@Param("yearsOfExperience") int yearsOfExperience);

    /**
     * Finds a list of Employee entities by the hire date.
     *
     * @param hireDate The hire date.
     * @return The list of Employee entities.
     */
    List<Employee> findByHireDate(Date hireDate);

    /**
     * Finds a list of Employee entities by the birth date.
     *
     * @param birthDate The birth date.
     * @return The list of Employee entities.
     */
    List<Employee> findByBirthDate(Date birthDate);

    /**
     * Finds a list of Employee entities ordered by hire date in descending order.
     *
     * @return The list of Employee entities.
     */
    @Query("SELECT E FROM Employee E ORDER BY E.hireDate DESC")
    List<Employee> findAllByOrderByHireDateDesc();

    /**
     * Finds a list of Employee entities who joined the company in the last 10 years.
     *
     * @param date The starting date to consider for the last 10 years.
     * @return The list of Employee entities.
     */
    @Query("SELECT e FROM Employee e WHERE e.hireDate >= :date")
    List<Employee> findEmployeesJoinedLast10Years(@Param("date") Date date);

    /**
     * Counts the number of Employee entities who joined the company in the last 10 years.
     *
     * @param date The starting date to consider for the last 10 years.
     * @return The count of Employee entities.
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.hireDate >= :date")
    int countEmployeesJoinedLast10Years(@Param("date") Date date);

    /**
     * Finds a list of Employee entities who joined the company after a specific year.
     *
     * @param year The year to consider.
     * @return The list of Employee entities.
     */
    @Query("SELECT e FROM Employee e WHERE YEAR(e.hireDate) > :year")
    List<Employee> findEmployeesJoinedAfterYear(@Param("year") int year);

    /**
     * Counts the number of Employee entities who joined the company after a specific year.
     *
     * @param year The year to consider.
     * @return The count of Employee entities.
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE YEAR(e.hireDate) > :year")
    int countEmployeesJoinedAfterYear(@Param("year") int year);

    /**
     * Deletes an Employee entity by the employee number.
     *
     * @param empNo The employee number.
     */
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.empNo = :empNo")
    void deleteByempNo(@Param("empNo") int empNo);

    /**
     * Finds a list of Employee entities by email containing the given email.
     *
     * @param email The email to search for.
     * @return The list of Employee entities.
     */
    List<Employee> findByEmailContains(String email);

    /**
     * Finds an Employee entity by email.
     *
     * @param email The email.
     * @return The Employee entity.
     */
    Employee findByEmail(String email);

    /**
     * Finds a list of Employee entities by hire date within a specific year range.
     *
     * @param startYear The starting year of the range.
     * @param endYear   The ending year of the range.
     * @return The list of Employee entities.
     */
    @Query("SELECT e FROM Employee e WHERE YEAR(e.hireDate) BETWEEN :startYear AND :endYear")
    List<Employee> findEmployeesByHireDateRange(@Param("startYear") int startYear, @Param("endYear") int endYear);

}
