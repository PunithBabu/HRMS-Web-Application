import { Injectable } from '@angular/core';

import { Router } from '@angular/router';

import { map, Observable, of, switchMap, tap, throwError } from 'rxjs';

import { HttpClient } from '@angular/common/http';

import { userlogin } from '../modules/classes/userlogin';

import { Employee } from '../modules/classes/employee-list';

import { Salary } from '../modules/classes/salary';

import { DepartmentManager } from '../modules/classes/department-manager';

import { DepartmentEmployee } from '../modules/classes/DepartmentEmployee';

import { Titles } from '../modules/classes/title';




@Injectable({

  providedIn: 'root',

})




export class UserService {




  constructor(private router: Router, private httpClient: HttpClient,private userlogin: userlogin) {}




  private employeeURL= "http://localhost:9191/api/v1/employees";




  checkEmailAvailability(email: string): Observable<any> {

    return this.httpClient.post<any>(`${this.employeeURL}/email/${email}`, {});

  }




  updateForgotPassword(empNo: number, email: string, employee: Employee): Observable<void> {

    return this.httpClient.put<void>(`${this.employeeURL}/password/empno/${empNo}/email/${email}`, employee);

  }




  getEmployees(): Observable<Employee[]> {

    return this.httpClient.get<Employee[]>(`${this.employeeURL}/all`);

  }




  getEmployeeByEmail(email: string): Observable<Employee> {

    return this.httpClient.get<Employee>(`${this.employeeURL}/email/${email}`);

  }




  getEmployeeByEmpNo(id: number): Observable<Employee> {

    return this.httpClient.get<Employee>(`${this.employeeURL}/id/${id}`)

  }

 

  updateEmployeeDetailsFrontEnd(empNo: number, employee: Employee): Observable<void> {

      return this.httpClient.put<void>(`${this.employeeURL}/frontend/${empNo}`, employee);

  }




 




  private employeeOwnSalary="http://localhost:9191/api/v1/salaries";

  SalaryByEmpNo(empNo: number): Observable<Salary[]> {

    return this.httpClient.get<Salary[]>(`${this.employeeOwnSalary}/empno/${empNo}`)

  }




  getSalaryByEmpNo(empno:number): Observable<Salary[]> {

    return this.httpClient.get<Salary[]>(`${this.employeeOwnSalary}/empno/${empno}`)




  }





  private empDepartmentURL="http://localhost:9191/api/v1/deptemp";

  deptByEmpNo(empNo: number): Observable<DepartmentEmployee[]> {

    return this.httpClient.get<DepartmentEmployee[]>(`${this.empDepartmentURL}/empno/${empNo}`)

  }





  private titleEmpURL="http://localhost:9191/api/v1/titles";

  gettitleByEmpNo(empno:number): Observable<Titles[]> {

    return this.httpClient.get<Titles[]>(`${this.titleEmpURL}/empno/${empno}`)

  }





  private departmentEmployeeURL= "http://localhost:9191/api/v1/deptemp"

  getDepartment(empNo: number): Observable<DepartmentEmployee[]> {

    return this.httpClient.get<DepartmentEmployee[]>(`${this.departmentEmployeeURL}/empno/${empNo}`)

  }





  private departmentManagerURL= "http://localhost:9191/api/v1/departmentmanager"

  getDepartmentManager(): Observable<DepartmentManager[]> {

    return this.httpClient.get<DepartmentManager[]>(`${this.departmentManagerURL}/all`)

  }







  setToken(token: string): void {

    localStorage.setItem('token', token);

  }




  getToken(): string | null {

    return localStorage.getItem('token');

  }




  isLoggedIn() {

    return this.getToken() !== null;

  }




 logout() {

    localStorage.removeItem('token');

    this.router.navigate(['']);

  }









  private validateUserUrl = 'http://localhost:9191/api/v1/employees/login';

  validateUser(login: userlogin): Observable<boolean> {

    return this.httpClient.post<boolean>(this.validateUserUrl, login);

  }

  login({ email, password }: any): Observable<any> {

    this.userlogin.email = email;

    this.userlogin.password = password;

    return this.validateUser(this.userlogin).pipe(

      switchMap((result: boolean) => {

        if (result) {

          return this.getEmployeeByEmail(email).pipe(

            tap((employee: Employee) => {

              localStorage.setItem('loggedInEmployee', JSON.stringify(employee));

              this.setToken('abcdefghijklmnopqrstuvwxyz');

            }),

            map(() => ({ name: 'dil', email: email }))

          );

        } else {

          return throwError(new Error('Failed to login'));

        }

      })

    );

  }

 




 




}