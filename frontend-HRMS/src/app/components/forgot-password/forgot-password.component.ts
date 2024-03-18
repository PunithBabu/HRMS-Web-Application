import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Employee } from 'src/app/modules/classes/employee-list';

import { Gender } from 'src/app/modules/classes/gender';

import { UserService } from 'src/app/users/user.service';




@Component({

  selector: 'app-forgot-password',

  templateUrl: './forgot-password.component.html',

  styleUrls: ['./forgot-password.component.css']

})

export class ForgotPasswordComponent implements OnInit {




  employeeForm!: FormGroup;

  employee!: Employee;




  constructor(private formBuilder: FormBuilder, private forgotPasswordService: UserService) {





    this.employeeForm = this.formBuilder.group({

      empNo: ['', Validators.required],

      email: ['', [Validators.required, Validators.email]],

      password: ['', Validators.required]

    });




  }




  ngOnInit(): void {

   this.updatePassword();

  }




  forgotPassword: Employee = new Employee();




  updatePassword(): void {

    if (this.employeeForm.valid) {

      console.log("Update password is called");

     




      this.forgotPassword.empNo = this.employeeForm.value.empNo;

      this.forgotPassword.email = this.employeeForm.value.email;

      this.forgotPassword.password = this.employeeForm.value.password;




      console.log("empNO: "+ this.employeeForm.value.empNo);

      console.log("email: "+ this.employeeForm.value.email);

      console.log("password: "+ this.employeeForm.value.password);




      console.log("empNO FP: "+ this.forgotPassword.empNo);

      console.log("email FP: "+ this.forgotPassword.email);

      console.log("password FP: "+ this.forgotPassword.password);

     




      this.forgotPassword.firstName = this.employeeForm.value.firstName;

      console.log("firstName"+ this.employeeForm.value.firstName);

      console.log("firstName FP"+ this.forgotPassword.firstName);

      this.forgotPassword.lastName = this.employeeForm.value.lastName;

      this.forgotPassword.birthDate = this.employeeForm.value.birthDate;




      this.forgotPassword.hireDate = this.employeeForm.value.hireDate;

      this.forgotPassword.gender = this.employeeForm.value.gender;




      this.forgotPasswordService.updateForgotPassword(this.forgotPassword.empNo, this.forgotPassword.email, this.forgotPassword).subscribe(

        () => {

          console.log("empNo: pass to UFP:  "+ this.forgotPassword.empNo);

          console.log("password: pass to UFP:  "+ this.forgotPassword.password);

          console.log("full: pass to UFP:  "+ this.forgotPassword);

          console.log('Password updated successfully!');

         

          alert('Password updated successfully!');

        },

        (error) => {

          console.error('Error updating password:', error);

        }

      );

    }

  }




}