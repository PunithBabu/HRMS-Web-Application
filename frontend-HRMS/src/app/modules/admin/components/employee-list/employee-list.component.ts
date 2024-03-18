import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/modules/classes/employee-list';
import { UserService } from 'src/app/users/user.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent {
  employeeList !:Employee[];

  constructor(private services: UserService, private router: Router){}

  ngOnInit(): void {
     this.getEmployees();
  }
  
  

  private getEmployees(){
    this.services.getEmployees().subscribe(data=>{
      this.employeeList = data;
    })
  }

}
