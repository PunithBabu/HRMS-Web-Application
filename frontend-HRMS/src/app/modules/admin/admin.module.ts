import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
// import { AboutComponent } from './components/about/about.component';
import { FooterComponent } from './components/footer/footer.component';
// import { ContactComponent } from './components/contact/contact.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { GetemployeedetailsComponent } from './components/getemployeedetails/getemployeedetails.component';
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { DeleteemployeeComponent } from './components/deleteemployee/deleteemployee.component';
import { PayrollsComponent } from './components/payrolls/payrolls.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { UpdatemanagerComponent } from './components/updatemanager/updatemanager.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    AdminDashboardComponent,
    HeaderComponent,
    HomeComponent,
    // AboutComponent,
    FooterComponent,
    // ContactComponent,
    EmployeeListComponent,
    AdminProfileComponent,
    GetemployeedetailsComponent,
    CreateEmployeeComponent,
    DeleteemployeeComponent,
    PayrollsComponent,
    UpdateEmployeeComponent,
    UpdatemanagerComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    NgbModule,
    FormsModule,
    NgxPaginationModule,

  ]
})
export class AdminModule { }
