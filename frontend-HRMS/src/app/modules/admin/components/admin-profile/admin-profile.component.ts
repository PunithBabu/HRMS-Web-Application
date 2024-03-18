import { Component, OnInit } from '@angular/core';
import { DepartmentEmployee } from 'src/app/modules/classes/DepartmentEmployee';
import { Employee } from 'src/app/modules/classes/employee-list';
import { AuthService } from 'src/app/services/auth.service';
import { AdminService } from '../../Services/admin.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {

  employee: Employee;
  DeptEmp!:DepartmentEmployee[];

  constructor(private adminservices: AdminService) {
    const loggedInEmployeeStr = localStorage.getItem('loggedInEmployee');
    this.employee = loggedInEmployeeStr ? JSON.parse(loggedInEmployeeStr)[0] : new Employee();
    console.log(this.employee);
  }

  ngOnInit(): void {
    this.getDeptEmpbyempNo();
  }
  

  getDeptEmpbyempNo(): void {
    this.adminservices.getDeptEmpByempNo(this.employee.empNo).subscribe((data: DepartmentEmployee[]) => {
      this.DeptEmp = data;
    });
  }
  

}
