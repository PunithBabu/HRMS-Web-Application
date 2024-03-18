import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { DepartmentList } from '../../classes/department';
import { DepartmentManager } from '../../classes/department-manager';
import { DepartmentEmployee } from '../../classes/DepartmentEmployee';
import { Employee } from '../../classes/employee-list';
import { Salary } from '../../classes/salary';
import { Titles } from '../../classes/title';

@Injectable({
  providedIn: 'root'
})
export class AdminService   {

  constructor(
    private router: Router,
    private http: HttpClient,
    private admin: DepartmentEmployee
  ) {}
  private baseUrl="http://localhost:9191/api/v1/employees";

  private departmentEmpUrl="http://localhost:9191/api/v1/deptemp";

  private departmentUrl="http://localhost:9191/api/v1/Departments";

  private SalaryUrl="http://localhost:9191/api/v1/salaries";
    
  private titleUrl="http://localhost:9191/api/v1/titles";

  private departmentManagerURL= "http://localhost:9191/api/v1/departmentmanager"

 
  addSalary(salary: Salary): Observable<Salary> {
    return this.http.post<Salary>(`${this.SalaryUrl}/add`, salary);
  }

  updateEmployee(empNo: number, employee: Employee): Observable<Employee> {
    const url = `${this.baseUrl}/Updateemployee/${empNo}`;
    return this.http.put<Employee>(url, employee);
  }

  createEmployee(employee: Employee, department: DepartmentList, departmentEmployee: DepartmentEmployee, salary: Salary, title: Titles): Observable<any> {
    const request = {
      employee,
      department,
      departmentEmployee,
      salary,
      title
    };
    return this.http.post(`${this.baseUrl}/create-employee`, request);
  }

  addExistingEmployeeToDepartmentManager(empNo: number, departmentManager: DepartmentManager): Observable<DepartmentManager> {
    const url = `${this.departmentManagerURL}/DepartmentManager/${empNo}`;
    return this.http.post<DepartmentManager>(url, departmentManager);
  }

  getsalaries(): Observable<Salary[]> {
    return this.http.get<Salary[]>(`${this.SalaryUrl}/all`);
  }

  getSalariesByEmployee(empNo: number): Observable<Salary[]> {
    return this.http.get<Salary[]>(`${this.SalaryUrl}/empno/${empNo}`);
  }

  deleteDepartmentByDeptNo(deptNo: string): Observable<any> {
    return this.http.delete(`${this.departmentUrl}/deptNo/${deptNo}`);
  }

  deleteEmployeeByEmpNo(empNo: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deleteemployeebyempNo/${empNo}`);
  }

  adddepartment(department: DepartmentList): Observable<DepartmentList>{
    return this.http.post<DepartmentList>(`${this.departmentUrl}/add`,department);
  }

  getTitlesByEmpNo(empNo: number): Observable<Titles[]> {
    return this.http.get<Titles[]>(`${this.titleUrl}/empno/${empNo}`);
  }

  getTitlesByTitle(title: string): Observable<Titles[]> {
    return this.http.get<Titles[]>(`${this.titleUrl}/title/${title}`);
  }

  addtitle(title: Titles): Observable<Titles>{
    return this.http.post<Titles>(`${this.titleUrl}/add`,title);
  }

  addDepartmentEmployee(departmentEmployee: DepartmentEmployee): Observable<DepartmentEmployee> {
    const url = `${this.departmentEmpUrl}/add`;
    return this.http.post<DepartmentEmployee>(url, departmentEmployee);
  }

 

  getAllDepartments(): Observable<DepartmentList[]> {
    return this.http.get<DepartmentList[]>(`${this.departmentUrl}/all`);
  }
  
  getDeptEmpByempNo(empNo: number): Observable<DepartmentEmployee[]> {

    return this.http.get<DepartmentEmployee[]>(`${this.departmentEmpUrl}/empno/${empNo}`)

  }



  getEmployeesByHireDateRange(startYear: number, endYear: number): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/hiredate/${startYear}/${endYear}`);
  }


  getDepartmentManager(): Observable<DepartmentManager[]> {

    return this.http.get<DepartmentManager[]>(`${this.departmentManagerURL}/all`)

  }

   updateSalaryByEmpNo(empNo: number, salary: Salary): Observable<Salary> {
    const url = `${this.SalaryUrl}/empno/${empNo}`;
    return this.http.put<Salary>(url, salary);
  }
  
  updateTitleByEmpNo(empNo: number, title: Titles): Observable<void> {
    const url = `${this.titleUrl}/empno/${empNo}`;
    return this.http.put<void>(url, title);
  }

  getGenderCount(gender: string): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/gendercount/${gender}`);
  }

  addEmployee(employee: Employee): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/add`, employee);
  }

  getEmployeesByGender(gender: string): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/gender/${gender}`);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.baseUrl}/id/${id}`);
  }

  getEmployeesByFirstName(firstName: string): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/firstname/${firstName}`);
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/all`);
  }


  getDepartmentEmployeesByDeptNo(deptNo: string): Observable<DepartmentEmployee[]> {
    return this.http.get<DepartmentEmployee[]>(`${this.departmentEmpUrl}/deptno/${deptNo}`);
  }

}
