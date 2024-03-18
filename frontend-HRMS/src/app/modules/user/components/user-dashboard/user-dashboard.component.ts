import { Component } from '@angular/core';

import { Router } from '@angular/router';

import { Employee } from 'src/app/modules/classes/employee-list';

import { AuthService } from 'src/app/services/auth.service';

import { UserService } from 'src/app/users/user.service';





@Component({

  selector: 'app-user-dashboard',

  templateUrl: './user-dashboard.component.html',

  styleUrls: ['./user-dashboard.component.css']

})

export class UserDashboardComponent {




  employee: Employee;




  constructor(private authUser: UserService, private employeeService: UserService) {

    const loggedInEmployeeStr = localStorage.getItem('loggedInEmployee');

    this.employee = loggedInEmployeeStr ? JSON.parse(loggedInEmployeeStr)[0] : new Employee();

    console.log(this.employee);

  }




  ngOnInit(): void {

    console.log(this.employee);

    const empNo = this.employee?.empNo;

    const fn=this.employee?.firstName;

    if (!empNo) {

      console.error('Employee number is missing.');

      return;

    }




  }




  logout(): void {

    this.authUser.logout();

  }




  // updateProfile(): void {

  //   this.employeeService.updateProfile(this.employee).subscribe(

  //     (response) => {

  //       this.employee = response;

  //     },

  //     (error) => {

  //       console.error('Update failed:', error);

  //     }

  //   );

  // }

  }