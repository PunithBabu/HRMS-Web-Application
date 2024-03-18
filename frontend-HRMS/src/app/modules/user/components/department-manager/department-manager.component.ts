import { Component, OnInit } from '@angular/core';

import { DepartmentEmployee } from 'src/app/modules/classes/DepartmentEmployee';

import { DepartmentManager } from 'src/app/modules/classes/department-manager';

import { Employee } from 'src/app/modules/classes/employee-list';

import { Gender } from 'src/app/modules/classes/gender';

import { UserService } from 'src/app/users/user.service';




@Component({

  selector: 'app-department-manager',

  templateUrl: './department-manager.component.html',

  styleUrls: ['./department-manager.component.css'],

})

export class DepartmentManagerComponent implements OnInit {





  employee!: Employee;

 

  constructor(private departmentManagerService: UserService, private employeeService: UserService) {

    const loggedInEmployeeStr = localStorage.getItem('loggedInEmployee');

    this.employee = loggedInEmployeeStr ? JSON.parse(loggedInEmployeeStr)[0] : new Employee();

    console.log(this.employee);

  }





  departmentEmployee: DepartmentEmployee[] = [];




  getDepartmentByEmpNo(): void{

    this.departmentManagerService.getDepartment(this.employee.empNo).subscribe(

      (data: DepartmentEmployee[]) => {

        this.departmentEmployee = data;

        console.log("DepartmentEmployee"+this.departmentEmployee);

       

      }

    )

   

  }




 





  departmentManager: DepartmentManager[] = [];




  departmentManagerWithDepartment: DepartmentManager = {

    fromDate: new Date(),

    toDate: new Date(),

    department: {

      dept_name: 'HRMS',

      dept_no: '',

    },

    employee: {

      empNo: 1,

      birthDate: new Date(),

      firstName: 'firstname',

      lastName: 'lastname',

      email: 'example@gmail.com',

      password: 'Aabc#123',

      gender: Gender.M,

      hireDate: new Date(),

    },

  };




  isTableVisible: boolean = false;




  showTable() {

    this.isTableVisible = !this.isTableVisible;

  }




  getAllDepartmentManager() {

    this.departmentManagerService.getDepartmentManager().subscribe(

      (data: DepartmentManager[]) => {

        this.departmentManager = data;

      },

      (error) => {

        console.error('Error', error);

      }

    );

  }




  ngOnInit(): void {

    this.getAllDepartmentManager();

    this.getDepartmentByEmpNo();

    const empNo = this.employee?.empNo;

    console.log(this.employee);

    if (!empNo) {

      console.error('Employee number is missing.');

      return;

    }




  }

}