import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { DepartmentEmployee } from 'src/app/modules/classes/DepartmentEmployee';

import { Employee } from 'src/app/modules/classes/employee-list';

import { UserService } from 'src/app/users/user.service';




@Component({

  selector: 'app-employee-list',

  templateUrl: './employee-list.component.html',

  styleUrls: ['./employee-list.component.css']

})




export class EmployeeListComponent implements OnInit {

  employeeList !: Employee[];

 employeeObj: Employee = new Employee();





  title = "pagination";

  page :number = 1;

  count : number = 0;

  tableSize : number = 10;

  tableSizes :any = [10,20,30]




  onTableDataChange(event : any){

    this.page = event;

    this.getEmployees();

  }




  onTableSizeChange(event : any) :void{

    this.tableSize = event.target.value;

    this.page = 1;

    this.getEmployees();




  }




  constructor(private services: UserService, private router: Router) { }




  ngOnInit(): void {

    this.getEmployees();


  }




  private getEmployees() {

    this.services.getEmployees().subscribe(data => {

      this.employeeList = data;

    })
  }

  getEmployeeDetailsByEmpNo() {

    this.services.getEmployeeByEmpNo(this.employeeObj.empNo).subscribe(

      (data: Employee) => {

        this.employeeList = [data];

      },

      (error) => {

        // console.error("Error fecting data", error);

        alert("No data found");

      }

    );

  }


  // getDepartmentEmployeesByDeptNo(): void {
  //   this.services.getDepartmentEmployeesByDeptNo(this.deptNo)
  //     .subscribe((data: DepartmentEmployee[]) => {
  //       this.departmentEmployees = data;
  //     });
  // }
}