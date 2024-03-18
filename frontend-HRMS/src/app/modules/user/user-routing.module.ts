import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';

import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';

import { HomeComponent } from './components/home/home.component';

import { EmployeeListComponent } from './components/employee-list/employee-list.component';

import { SalaryComponent } from './components/salary/salary.component';

import { ProfileComponent } from './components/profile/profile.component';

import { DepartmentManagerComponent } from './components/department-manager/department-manager.component';

import { UpdateComponent } from './components/update/update.component';




const routes: Routes = [

  {

    path: '',

    component: UserDashboardComponent,

    children: [

      { path: 'home', component: HomeComponent },

      { path: 'elist', component: EmployeeListComponent },

      { path: 'salary', component: SalaryComponent },

      { path: 'profile', component: ProfileComponent },

      { path: 'departmentManager', component: DepartmentManagerComponent },

      {path: 'update', component: UpdateComponent},

      { path: '', redirectTo: 'user', pathMatch: 'full' },

    ],

  },

];




@NgModule({

  imports: [RouterModule.forChild(routes)],

  exports: [RouterModule],

})

export class UserRoutingModule {}