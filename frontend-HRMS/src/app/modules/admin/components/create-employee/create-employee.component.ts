import { Component, OnInit } from '@angular/core';
import { DepartmentList } from 'src/app/modules/classes/department';
import { DepartmentEmployee } from 'src/app/modules/classes/DepartmentEmployee';
import { Employee } from 'src/app/modules/classes/employee-list';
import { Salary } from 'src/app/modules/classes/salary';
import { Titles } from 'src/app/modules/classes/title';
import { AdminService } from '../../Services/admin.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit{


   departments: DepartmentList[] = [];


  employee: Employee = new Employee();
  department: DepartmentList = new DepartmentList();
  departmentEmployee: DepartmentEmployee = new DepartmentEmployee();
  salary: Salary = new Salary();
  title: Titles = new Titles();


  constructor(private employeeService: AdminService) { }


  onDeptNoChange(): void {
    const selectedDept = this.departments.find(department => department.dept_no === this.department.dept_no);
    if (selectedDept) {
      this.department.dept_name = selectedDept.dept_name;
    } else {
      this.department.dept_name = ''; // Clear the department name if no department is selected
    }
  }

  ngOnInit(): void {
    this.getAllDepartments();
  }



  getAllDepartments(): void {
    this.employeeService.getAllDepartments().subscribe(
      (departments: DepartmentList[]) => {
        this.departments = departments;
      },
      (error: any) => {
        console.error('Error:', error);
      }
    );
  }

  createEmployee(): void {
    this.employeeService.createEmployee(this.employee, this.department, this.departmentEmployee, this.salary, this.title)
      .subscribe(
        () => {
          console.log('Employee created successfully');
          // Reset form fields
          this.employee = new Employee();
          this.department = new DepartmentList();
          this.departmentEmployee = new DepartmentEmployee();
          this.salary = new Salary();
          this.title = new Titles();
        },
        (error) => {
          console.error('Error creating employee:', error);
        }
      );
  }

}


// }

// import { Component, OnInit } from '@angular/core';
// import { DepartmentList } from 'src/app/modules/classes/department';
// import { DepartmentEmployee } from 'src/app/modules/classes/DepartmentEmployee';
// import { Employee } from 'src/app/modules/classes/employee-list';
// import { Salary } from 'src/app/modules/classes/salary';
// import { Titles } from 'src/app/modules/classes/title';
// import { AdminService } from '../../Services/admin.service';

// @Component({
//   selector: 'app-create-employee',
//   templateUrl: './create-employee.component.html',
//   styleUrls: ['./create-employee.component.css']
// })
// export class CreateEmployeeComponent implements OnInit {
//   departments: DepartmentList[] = [];
//   employee: Employee = new Employee();
//   department: DepartmentList = new DepartmentList();
//   departmentEmployee: DepartmentEmployee = new DepartmentEmployee();
//   salary: Salary = new Salary();
//   title: Titles = new Titles();

//   constructor(private employeeService: AdminService) {}

//   ngOnInit(): void {
//     this.getAllDepartments();
//   }

//   onDeptNoChange(): void {
//     const selectedDept = this.departments.find(department => department.dept_no === this.department.dept_no);
//     if (selectedDept) {
//       this.department.dept_name = selectedDept.dept_name;
//     } else {
//       this.department.dept_name = ''; // Clear the department name if no department is selected
//     }
//   }

//   getAllDepartments(): void {
//     this.employeeService.getAllDepartments().subscribe(
//       (departments: DepartmentList[]) => {
//         this.departments = departments;
//       },
//       (error: any) => {
//         console.error('Error:', error);
//       }
//     );
//   }

//   createEmployee(): void {
//     this.employeeService.createEmployee(this.employee, this.department, this.departmentEmployee, this.salary, this.title)
//       .subscribe(
//         () => {
//           console.log('Employee created successfully');
//           // Reset form fields
//           this.employee = new Employee();
//           this.department = new DepartmentList();
//           this.departmentEmployee = new DepartmentEmployee();
//           this.salary = new Salary();
//           this.title = new Titles();
//         },
//         (error) => {
//           console.error('Error creating employee:', error);
//         }
//       );
//   }
// }

