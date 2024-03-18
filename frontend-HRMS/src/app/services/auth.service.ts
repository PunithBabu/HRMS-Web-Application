import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable, of, switchMap, tap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Admin } from '../modules/classes/adminlogin';
import { Employee } from '../modules/classes/employee-list';
import { DepartmentEmployee } from '../modules/classes/DepartmentEmployee';


@Injectable({
  providedIn: 'root',
})
export class AuthService {
  
  constructor(
    private router: Router,
    private httpClient: HttpClient,
    private admin: DepartmentEmployee
  ) {}

  

  private baseUrl= "http://localhost:9191/api/v1/employees/all"

  

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

    this.router.navigate(['admin-login']);
  }

    private validateUserUrl = 'http://localhost:9191/api/v1/deptemp/adminlogin';
    private employeeURL= "http://localhost:9191/api/v1/employees";
    getEmployeeByEmpNo(id: number): Observable<Employee> {
      return this.httpClient.get<Employee>(`${this.employeeURL}/id/${id}`)
    }
    getEmployeeByEmail(email: string): Observable<Employee> {
      return this.httpClient.get<Employee>(`${this.employeeURL}/email/${email}`);
    }
  validateUser(admin: DepartmentEmployee): Observable<boolean> {
    return this.httpClient.post<boolean>(this.validateUserUrl, admin);
  }

  adminLogin({ email, password }: any): Observable<any> {
    this.admin.employee = new Employee(); // Initialize employee object
    this.admin.employee.email = email; // Set the email property
    this.admin.employee.password = password; // Set the password property
  
    return this.validateUser(this.admin).pipe(
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

  private departmentEmpUrl="http://localhost:9191/api/v1/deptemp";
  getDeptEmpByempNo(empNo: number): Observable<DepartmentEmployee[]> {

    return this.httpClient.get<DepartmentEmployee[]>(`${this.departmentEmpUrl}/empno/${empNo}`)

  }
}
