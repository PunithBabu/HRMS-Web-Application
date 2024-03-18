package com.hrms.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.entities.DepartmentEmployee;
import com.hrms.repository.DepartmentEmployeeRepository;

@Service
public class DepartmentEmployeeService {

	@Autowired
	private DepartmentEmployeeRepository departmentEmployeeRepository;

	/**
	 * Retrieves all department employees.
	 *
	 * @return The list of all department employees.
	 */
	public List<DepartmentEmployee> getdepartmentemployee() {
		return departmentEmployeeRepository.findAll();
	}

	/**
	 * Finds department employees by department number and from date.
	 *
	 * @param deptNo   The department number.
	 * @param fromDate The from date.
	 * @return The list of department employees with the specified department number and from date.
	 */
	public List<DepartmentEmployee> findByDeptNoAndFromDate(String deptNo, Date fromDate) {
		return departmentEmployeeRepository.findByDepartmentDeptNoAndFromDate(deptNo, fromDate);
	}

	/**
	 * Finds department employees by department number.
	 *
	 * @param deptNo The department number.
	 * @return The list of department employees with the specified department number.
	 */
	public List<DepartmentEmployee> findByDeptNo(String deptNo) {
		return departmentEmployeeRepository.findByDepartment_DeptNo(deptNo);
	}

	/**
	 * Finds department employee by department number and from date.
	 *
	 * @param deptNo   The department number.
	 * @param fromDate The from date.
	 * @return The department employee with the specified department number and from date.
	 */
	public DepartmentEmployee findBysDeptNoAndFromDate(String deptNo, Date fromDate) {
		return departmentEmployeeRepository.findByDepartment_DeptNoAndFromDate(deptNo, fromDate);
	}

	/**
	 * Finds department employee by employee number and from date.
	 *
	 * @param empNo    The employee number.
	 * @param fromDate The from date.
	 * @return The department employee with the specified employee number and from date.
	 */
	public DepartmentEmployee findByempNoAndFromDate(int empNo, Date fromDate) {
		return departmentEmployeeRepository.findByEmployee_EmpNoAndFromDate(empNo, fromDate);
	}

	/**
	 * Finds department employee by employee number and department number.
	 *
	 * @param empNo   The employee number.
	 * @param deptNo  The department number.
	 * @return The department employee with the specified employee number and department number.
	 */
	public DepartmentEmployee getDepartEmployeeByEmpNoAndDeptNo(int empNo, String deptNo) {
		return departmentEmployeeRepository.findByEmployee_EmpNoAndDepartment_DeptNo(empNo, deptNo);
	}

	/**
	 * Finds department employees by employee number.
	 *
	 * @param empNo The employee number.
	 * @return The list of department employees with the specified employee number.
	 */
	public List<DepartmentEmployee> getDepartEmployeeByEmpNo(int empNo) {
		return departmentEmployeeRepository.findByEmployee_EmpNo(empNo);
	}

	/**
	 * Finds department employee by employee number.
	 *
	 * @param empNo The employee number.
	 * @return The department employee with the specified employee number.
	 */
	public DepartmentEmployee getDepartEmployeeEmpNo(int empNo) {
		return departmentEmployeeRepository.findByEmployeeEmpNo(empNo);
	}

	/**
	 * Finds department employee by employee number, department number, and from date.
	 *
	 * @param empNo    The employee number.
	 * @param deptNo   The department number.
	 * @param fromDate The from date.
	 * @return The department employee with the specified employee number, department number, and from date.
	 */
	public DepartmentEmployee getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(int empNo, String deptNo,
			Date fromDate) {
		return departmentEmployeeRepository.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo,
				fromDate);
	}

	/**
	 * Updates the information of a department employee.
	 *
	 * @param departmentEmployee The department employee with updated information.
	 * @return The updated department employee.
	 */
	public DepartmentEmployee updateDepartmentEmployee(DepartmentEmployee departmentEmployee) {
		return departmentEmployeeRepository.save(departmentEmployee);
	}

	/**
	 * Saves a department employee.
	 *
	 * @param departmentEmployee The department employee to save.
	 * @return The saved department employee.
	 */
	public DepartmentEmployee saveDepartmentEmployee(DepartmentEmployee departmentEmployee) {
		return departmentEmployeeRepository.save(departmentEmployee);
	}

	/**
	 * Retrieves employees in a department for a specific year.
	 *
	 * @param deptNo The department number.
	 * @param year   The year.
	 * @return The list of employees in the specified department for the given year.
	 */
	public List<DepartmentEmployee> getEmployeesByDepartmentAndYear(String deptNo, int year) {
		return departmentEmployeeRepository.findEmployeesByDepartmentAndYear(deptNo, year);
	}

	/**
	 * Finds a department employee by email and password.
	 *
	 * @param request The department employee request containing the email and password.
	 * @return The department employee with the specified email and password, or null if not found.
	 */
	public DepartmentEmployee findByEmailAndPassword(DepartmentEmployee request) {
		return departmentEmployeeRepository
				.findTheHr(request.getEmployee().getEmail(), request.getEmployee().getPassword(), "d003").orElse(null);
	}

	/**
	 * Deletes a department employee by employee number, department number, and from date.
	 *
	 * @param empNo    The employee number.
	 * @param fromDate The from date.
	 * @param deptNo   The department number.
	 */
	@Transactional
	public void deleteByEmpNoAndDeptNoAndFromDate(int empNo, Date fromDate, String deptNo) {
		departmentEmployeeRepository.deleteByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
	}

	/**
	 * Deletes a department employee by employee number and department number.
	 *
	 * @param empNo  The employee number.
	 * @param deptNo The department number.
	 */
	@Transactional
	public void deleteByEmpNoAndDeptNo(int empNo, String deptNo) {
		departmentEmployeeRepository.deleteByEmpNoAndDeptNo(empNo, deptNo);
	}

	/**
	 * Deletes a department employee by employee number and from date.
	 *
	 * @param empNo    The employee number.
	 * @param fromDate The from date.
	 */
	@Transactional
	public void deleteByEmpNoAndFromDate(int empNo, Date fromDate) {
		departmentEmployeeRepository.deleteByEmpNoAndFromDate(empNo, fromDate);
	}

	/**
	 * Deletes department employees by department number and from date.
	 *
	 * @param deptNo   The department number.
	 * @param fromDate The from date.
	 */
	@Transactional
	public void deleteByDeptNoAndFromDate(String deptNo, Date fromDate) {
		departmentEmployeeRepository.deleteByDeptNoAndFromDate(deptNo, fromDate);
	}

	/**
	 * Deletes department employees by department number.
	 *
	 * @param deptNo The department number.
	 */
	@Transactional
	public void deleteBysDeptNo(String deptNo) {
		departmentEmployeeRepository.deleteByDeptNo(deptNo);
	}

	/**
	 * Deletes department employees by employee number.
	 *
	 * @param empNo The employee number.
	 */
	@Transactional
	public void deleteByEmpNo(int empNo) {
		departmentEmployeeRepository.deletebyEmpNo(empNo);
	}
}
