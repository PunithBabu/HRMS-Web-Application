import { Component } from '@angular/core';
import { Employee } from 'src/app/modules/classes/employee-list';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {

  employee!: Employee;
  constructor(private auth: AuthService){
    const loggedInEmployeeStr = localStorage.getItem('loggedInEmployee');
    this.employee = loggedInEmployeeStr ? JSON.parse(loggedInEmployeeStr)[0] : new Employee();
    console.log(localStorage);
  }
  ngOnInit():void{

  }

  logout():void{
    localStorage.removeItem('loggedInEmployee'); 
    this.auth.logout();
  }
}
