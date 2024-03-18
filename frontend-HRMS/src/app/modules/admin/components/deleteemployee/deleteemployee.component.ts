import { Component, OnInit } from '@angular/core';
import { DepartmentList } from 'src/app/modules/classes/department';
import { Employee } from 'src/app/modules/classes/employee-list';
import { AdminService } from '../../Services/admin.service';

@Component({
  selector: 'app-deleteemployee',
  templateUrl: './deleteemployee.component.html',
  styleUrls: ['./deleteemployee.component.css']
})
export class DeleteemployeeComponent implements OnInit {

  empNo!: number;
  deptNo!: string;
  deletionStatus = '';
  DeletionStatus = '';

  departments: DepartmentList[] = [];
  invalidEmpNo: boolean = false;



  constructor(private employeeService: AdminService) {}

  ngOnInit(): void {
    this.getAllDepartments();
  }


  deleteEmployeeByEmpNo(): void {
    if (!this.empNo) {
      this.invalidEmpNo = true;
      return;
    }

    this.employeeService.getEmployeeById(this.empNo).subscribe(
      (data: Employee) => {
        if (!data) {
          this.invalidEmpNo = true;
          this.DeletionStatus = 'Invalid Employee Number';
        } else {
          this.employeeService.deleteEmployeeByEmpNo(this.empNo).subscribe(
            () => {
              this.DeletionStatus = 'Employee deleted successfully!';
              this.invalidEmpNo = false; // Reset the invalidEmpNo flag
            },
            (error) => {
              console.error('Error deleting employee:', error);
              this.DeletionStatus = 'Error deleting employee.';
            }
          );
        }
      },
      (error) => {
        console.error('Error getting employee:', error);
        this.invalidEmpNo = true;
        this.DeletionStatus = 'Error retrieving employee details.';
      }
    );
  }



  
  
  
  deleteDepartmentByDeptNo(): void {
    this.employeeService.deleteDepartmentByDeptNo(this.deptNo)
      .subscribe(
        () => {
          this.deletionStatus = 'Department deleted successfully.';
          // Handle success as needed
        },
        error => {
          console.error('Error deleting department:', error);
          this.deletionStatus = 'Error deleting department.';
          // Handle error as needed
        }
      );
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

}
