import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';

import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';

import { HomeComponent } from './components/home/home.component';

// import { EmployeeListComponent } from './components/employee-list/employee-list.component';

import { HeaderComponent } from './components/header/header.component';

import { FooterComponent } from './components/footer/footer.component';

import { SalaryComponent } from './components/salary/salary.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ProfileComponent } from './components/profile/profile.component';

import { DepartmentManagerComponent } from './components/department-manager/department-manager.component';

import { UpdateComponent } from './components/update/update.component';


import { NgxPaginationModule } from 'ngx-pagination';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
// import jsPDF from 'jspdf';
// import { CountUpDirective } from 'src/app/count-up.directive';




import { jsPDF } from "jspdf";
import html2canvas from 'html2canvas';




@NgModule({

  declarations: [

    UserDashboardComponent,

    HomeComponent,

    // EmployeeListComponent,

    HeaderComponent,

    FooterComponent,

    SalaryComponent,

    ProfileComponent,

    DepartmentManagerComponent,

    UpdateComponent,
    EmployeeListComponent,


  ],




  imports: [

    CommonModule,

    UserRoutingModule,

    FormsModule,

    ReactiveFormsModule,

    NgxPaginationModule,


   

  ]




})




export class UserModule { }