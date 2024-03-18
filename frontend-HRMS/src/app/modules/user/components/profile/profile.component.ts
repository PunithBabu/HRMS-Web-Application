import { Component, OnInit } from '@angular/core';

import { DepartmentEmployee } from 'src/app/modules/classes/DepartmentEmployee';

import { Employee } from 'src/app/modules/classes/employee-list';

import { Titles } from 'src/app/modules/classes/title';

import { UserService } from 'src/app/users/user.service';




@Component({

  selector: 'app-profile',

  templateUrl: './profile.component.html',

  styleUrls: ['./profile.component.css']

})




export class ProfileComponent implements OnInit {

  employee: Employee;

  employeeArr: Employee[]=[];

  employeeObj: Employee = new Employee();

  departmentEmployee: DepartmentEmployee[] = [];

  titles!:Titles[];




  constructor(private authUser: UserService, private employeeService: UserService) {

    const loggedInEmployeeStr = localStorage.getItem('loggedInEmployee');

    this.employee = loggedInEmployeeStr ? JSON.parse(loggedInEmployeeStr)[0] : new Employee();

    console.log(this.employee);

  }




  ngOnInit(): void {

    console.log(this.employee);

    this.getdeptbyempNo();

    this.gettitlebyempNo();

    const empNo = this.employee?.empNo;

    const fn = this.employee?.firstName;

    if (!empNo) {

      console.error('Employee number is missing.');

      return;

    }

  }




  getdeptbyempNo(): void {

    this.employeeService.deptByEmpNo(this.employee.empNo).subscribe((data: DepartmentEmployee[]) => {

      this.departmentEmployee = data;

      console.log(this.departmentEmployee)

    });

  }




  gettitlebyempNo(): void {

    this.employeeService.gettitleByEmpNo(this.employee.empNo).subscribe((data: Titles[]) => {

      this.titles = data;

      console.log(this.titles)

    });

  }




  getEmployeeDetailsByEmpNo() {

    this.employeeService.getEmployeeByEmpNo(this.employeeObj.empNo).subscribe(

      (data: Employee) => {

        this.employeeArr = [data];

      },

      (error) => {

        // console.error("Error fecting data", error);

        alert("No data found");

      }

    );

  }

}