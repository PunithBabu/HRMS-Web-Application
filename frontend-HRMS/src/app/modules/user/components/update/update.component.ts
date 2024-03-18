


import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Employee } from 'src/app/modules/classes/employee-list';

import { Gender } from 'src/app/modules/classes/gender';

import { UserService } from 'src/app/users/user.service';




@Component({

  selector: 'app-update',

  templateUrl: './update.component.html',

  styleUrls: ['./update.component.css']

})

export class UpdateComponent implements OnInit {

  employee: Employee;

  employeeDetails: Employee = new Employee();

  employeeForm!: FormGroup;

  id!: number;




  constructor(private updateEmployee: UserService, private formBuilder: FormBuilder) {

    const loggedInEmployeeStr = localStorage.getItem('loggedInEmployee');

    this.employee = loggedInEmployeeStr ? JSON.parse(loggedInEmployeeStr)[0] : new Employee();

    this.id = this.employee.empNo;

    console.log('Update component' + this.employee);

    console.log(this.id);

  }




  updateEmployeeFrontEnd(): void {

    this.employeeDetails.empNo = this.employee.empNo;

    this.employeeDetails.firstName = this.employeeForm.value.firstName.trim();

    this.employeeDetails.lastName = this.employeeForm.value.lastName.trim();

    this.employeeDetails.email = this.employee.email;

    this.employeeDetails.password = this.employeeForm.value.password;

    this.employeeDetails.birthDate = this.employeeForm.value.birthDate;

    this.employeeDetails.hireDate = this.employee.hireDate;

    this.employeeDetails.gender = this.employee.gender;

    this.updateEmployee.updateEmployeeDetailsFrontEnd(this.employeeDetails.empNo, this.employeeDetails)

      .subscribe(() => {

        alert('Salary updated successfully');

      }, error => {

        console.error('Error updating Salary:', error);

      });

  }




  ngOnInit(): void {

    this.employeeForm = this.formBuilder.group({

      empNo: [this.id],

      firstName: [this.employee.firstName, [Validators.required, Validators.minLength(4), Validators.pattern('[a-zA-Z ]*')]],

      lastName: [this.employee.lastName, [Validators.required, Validators.minLength(4), Validators.pattern('[a-zA-Z ]*')]],

      email: [this.employee.email],

      password: [this.employee.password],

      birthDate: [this.employee.birthDate],

      hireDate: [this.employee.birthDate],

      gender: [this.employee.gender]

    });

  }

}