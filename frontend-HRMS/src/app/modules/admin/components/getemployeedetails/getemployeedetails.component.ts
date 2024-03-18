import { Component, OnInit } from '@angular/core';
import { DepartmentList } from 'src/app/modules/classes/department';
import { DepartmentManager } from 'src/app/modules/classes/department-manager';
import { DepartmentEmployee } from 'src/app/modules/classes/DepartmentEmployee';
import { Employee } from 'src/app/modules/classes/employee-list';
import { Gender } from 'src/app/modules/classes/gender';
import { Titles } from 'src/app/modules/classes/title';
import { AdminService } from '../../Services/admin.service';

@Component({
  selector: 'app-getemployeedetails',
  templateUrl: './getemployeedetails.component.html',
  styleUrls: ['./getemployeedetails.component.css']
})
export class GetemployeedetailsComponent implements OnInit {

  employeeId: number = 0;
  showResults: string = 'dateRange';
  startYear!: number;
  endYear!: number;
  employees: Employee[] = [];
  firstName!: string;
  selectedGender: string = '';
  genderCount: number = 0;
  empNo!: number;
  title!: string;
  titles: Titles[] = [];
  employee: Employee | null = null;
  invalidEmpNo: boolean = false; // Add invalidEmpNo property
  departmentManager: DepartmentManager[] = [];
  departments: DepartmentList[] = [];

  deptNo!: string;
  departmentEmployees!: DepartmentEmployee[];

  constructor(private employeeService: AdminService) {}

  ngOnInit(): void {
    this.getAllDepartmentManagers();
    this.getAllDepartments();
  }

  isYearValid(): boolean {
    return (
      !!this.startYear &&
      !!this.endYear &&
      this.startYear >= 1900 &&
      this.startYear <= 2023 &&
      this.endYear >= 1900 &&
      this.endYear <= 2050
    );
  }

  isEmpNoInvalid(): boolean {
    return !this.employeeId || isNaN(this.employeeId);
  }

  toggleResults(resultType: string): void {
    this.showResults = resultType;
  }

  getEmployeesByHireDateRange(): void {
    this.employeeService.getEmployeesByHireDateRange(this.startYear, this.endYear)
      .subscribe((data: Employee[]) => {
        this.employees = data;
      });
  }

  getEmployeesByGender(): void {
    if (this.selectedGender) {
      this.employeeService.getEmployeesByGender(this.selectedGender)
        .subscribe((data: Employee[]) => {
          this.employees = data;
        });
    }
  }

  getEmployeeById(): void {
    if (this.isEmpNoInvalid()) {
      this.employee = null;
      return;
    }
    this.employeeService.getEmployeeById(this.employeeId).subscribe(
      (data: Employee) => {
        this.employee = data;
        if (!this.employee) {
          this.employee = null;
          this.invalidEmpNo = true; // Set invalidEmpNo flag to display the error message
        } else {
          this.invalidEmpNo = false; // Reset invalidEmpNo flag
        }
      },
      (error) => {
        this.employee = null;
        this.invalidEmpNo = true; // Set invalidEmpNo flag to display the error message
      }
    );
  }

  invalidFirstName: boolean = false; // Add invalidFirstName property

  getEmployeesByFirstName(): void {
    if (!this.firstName) {
      this.invalidFirstName = true; // Set invalidFirstName flag to display the error message
      this.employees = []; // Clear the employee list
      return;
    }

    this.employeeService.getEmployeesByFirstName(this.firstName)
      .subscribe(
        (data: Employee[]) => {
          this.employees = data;
          this.invalidFirstName = this.employees.length === 0; // Set invalidFirstName flag based on the employee list
        },
        (error) => {
          this.invalidFirstName = true; // Set invalidFirstName flag to display the error message
          this.employees = []; // Clear the employee list
        }
      );
  }

  getAllEmployees(): void {
    this.employeeService.getAllEmployees()
      .subscribe((data: Employee[]) => {
        this.employees = data;
      });
  }

  getGenderCount(): void {
    if (this.selectedGender) {
      this.employeeService.getGenderCount(this.selectedGender).subscribe(count => {
        this.genderCount = count;
      });
    } else {
      this.genderCount = 0;
    }
  }

  getDepartmentEmployeesByDeptNo(): void {
    this.employeeService.getDepartmentEmployeesByDeptNo(this.deptNo)
      .subscribe((data: DepartmentEmployee[]) => {
        this.departmentEmployees = data;
      });
  }

  getAllDepartmentManagers() {
    this.employeeService.getDepartmentManager().subscribe(
      (data: DepartmentManager[]) => {
        this.departmentManager = data;
      },
      (error) => {
        console.error("Error", error);
      }
    );
  }

  getTitlesByEmpNo(): void {
    if (!this.empNo) {
      this.invalidEmpNo = true;
      this.titles = [];
      return;
    }

    this.employeeService.getTitlesByEmpNo(this.empNo)
      .subscribe(
        (titles: Titles[]) => {
          this.titles = titles;
          this.invalidEmpNo = this.titles.length === 0;
        },
        (error: any) => {
          console.error('Error:', error);
          this.invalidEmpNo = true;
          this.titles = [];
        }
      );
  }

  getTitlesByTitle(): void {
    if (!this.title) {
      this.titles = [];
      return;
    }

    this.employeeService.getTitlesByTitle(this.title)
      .subscribe(
        titles => {
          this.titles = titles;
        },
        error => {
          console.error('Error fetching titles by title:', error);
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

  closeList(): void {
    this.showResults = 'dateRange';
  }

}