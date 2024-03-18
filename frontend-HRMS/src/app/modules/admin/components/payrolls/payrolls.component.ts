import { Component } from '@angular/core';
import { Salary } from 'src/app/modules/classes/salary';
import { AdminService } from '../../Services/admin.service';

@Component({
  selector: 'app-payrolls',
  templateUrl: './payrolls.component.html',
  styleUrls: ['./payrolls.component.css']
})
export class PayrollsComponent {

  salaries: Salary[] = [];
  selectedEmpNo!: number;
  invalidEmpNo: boolean = false;


  constructor(private salaryService: AdminService) { }

  ngOnInit(): void {
    this.getSalaries();
  }
  

  getSalaries(): void {
    this.salaryService. getsalaries()
      .subscribe(
        (salaries: Salary[]) => {
          this.salaries = salaries;
        },
        (error: any) => {
          console.error('Error retrieving salaries:', error);
        }
      );
  }

  fetchSalariesByEmployee() {
    this.invalidEmpNo = false;
    if (this.selectedEmpNo) {
      this.salaryService.getSalariesByEmployee(this.selectedEmpNo).subscribe(
        (salaries: Salary[]) => {
          if (salaries.length === 0) {
            this.invalidEmpNo = true;
            this.salaries = [];
          } else {
            this.salaries = salaries;
          }
        },
        (error: any) => {
          console.error('Error fetching salaries:', error);
          this.invalidEmpNo = true;
          this.salaries = [];
        }
      );
    } else {
      this.invalidEmpNo = true;
      this.salaries = [];
    }
  }
  }


