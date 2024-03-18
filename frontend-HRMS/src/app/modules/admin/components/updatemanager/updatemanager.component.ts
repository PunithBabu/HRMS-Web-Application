import { Component } from '@angular/core';
import { DepartmentList } from 'src/app/modules/classes/department';
import { DepartmentManager } from 'src/app/modules/classes/department-manager';
import { AdminService } from '../../Services/admin.service';

@Component({
  selector: 'app-updatemanager',
  templateUrl: './updatemanager.component.html',
  styleUrls: ['./updatemanager.component.css']
})
export class UpdatemanagerComponent {

  departmentManager: DepartmentManager = new DepartmentManager();
  empNo!: number;
  department: DepartmentList = new DepartmentList();
  departments: DepartmentList[] = [];
  isEmpNoInvalid: boolean = false; // Flag to track invalid employee number
  constructor(private departmentManagerService: AdminService) { }

  ngOnInit(): void {
    this.getAllDepartments();
  }

  onDeptNoChange(): void {
    const selectedDept = this.departments.find(department => department.dept_no === this.department.dept_no);
    if (selectedDept) {
      this.department.dept_name = selectedDept.dept_name;
    } else {
      this.department.dept_name = ''; // Clear the department name if no department is selected
    }
  }

  getAllDepartments(): void {
    this.departmentManagerService.getAllDepartments().subscribe(
      (departments: DepartmentList[]) => {
        this.departments = departments;
      },
      (error: any) => {
        console.error('Error:', error);
      }
    );
  }

  addExistingEmployeeToDepartmentManager(): void {
    this.departmentManagerService.getEmployeeById(this.empNo)
      .subscribe(
        (employee) => {
          // Valid employee number, proceed with adding to department manager
          this.isEmpNoInvalid = false; // Reset the invalid flag

          this.departmentManagerService.addExistingEmployeeToDepartmentManager(this.empNo, this.departmentManager)
            .subscribe(
              (createdDepartmentManager) => {
                console.log('Department Manager created successfully');
                // Reset form fields
                this.departmentManager = new DepartmentManager();
                this.empNo = 0;
              },
              (error) => {
                console.error('Error creating Department Manager:', error);
              }
            );
        },
        (error) => {
          console.error('Error getting employee:', error);
          this.isEmpNoInvalid = true; // Set the invalid flag
        }
      );
  }

}
