
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Employee } from 'src/app/modules/classes/employee-list';
import { Salary } from 'src/app/modules/classes/salary';

import { AdminService } from '../../Services/admin.service';
import { Titles } from 'src/app/modules/classes/title';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
  empNo!: number;
  employee!: Employee;
  employeeForm!: FormGroup;
  salary: Salary = new Salary();
  title: Titles = new Titles();
  invalidEmpNo: boolean = false; // Flag to indicate invalid employee number

  constructor(
    private formBuilder: FormBuilder,
    private employeeService: AdminService
  ) { }

  ngOnInit(): void {
    this.employeeForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(4),Validators.pattern('[a-zA-Z ]*')]],
      lastName: ['', [Validators.required, Validators.minLength(5),Validators.pattern('[a-zA-Z ]*')]],
      birthDate: ['', Validators.required],
      hireDate: ['', Validators.required],
      gender: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  getEmployee(): void {
    this.invalidEmpNo = false; // Reset the invalidEmpNo flag
    this.employeeService.getEmployeeById(this.empNo)
      .subscribe(
        (employee: Employee) => {
          this.employee = employee;
          this.employeeForm.patchValue(employee);
        },
        (error: any) => {
          console.error('Error:', error);
          this.invalidEmpNo = true; // Set the invalidEmpNo flag to display the error message
        }
      );
  }

  updateEmployee(): void {
    const updatedEmployee: Employee = {
      empNo: this.empNo,
      ...this.employeeForm.value
    };
    this.employeeService.updateEmployee(this.empNo, updatedEmployee)
      .subscribe(
        (employee: Employee) => {
          console.log('Employee updated successfully');
          // Reset form fields
          this.employeeForm.reset();
        },
        (error: any) => {
          console.error('Error:', error);
        }
      );
  }

  updateSalaryByEmpNo(): void {
    this.employeeService.updateSalaryByEmpNo(this.empNo, this.salary)
      .subscribe(
        (updatedSalary) => {
          console.log('Salary updated successfully');
          // Reset form fields
          this.salary = new Salary();
          this.empNo = 0;
        },
        (error) => {
          console.error('Error updating salary:', error);
        }
      );
  }
  
  updateTitleByEmpNo(): void {
    this.employeeService.updateTitleByEmpNo(this.empNo, this.title)
      .subscribe(
        () => {
          console.log('Title updated successfully');
          // Reset form fields
          this.title = new Titles();
          this.empNo = 0;
        },
        (error) => {
          console.error('Error updating title:', error);
        }
      );
  }
}
