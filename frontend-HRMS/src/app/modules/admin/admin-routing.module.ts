import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HomeComponent } from './components/home/home.component';
// import { AboutComponent } from './components/about/about.component';
// import { ContactComponent } from './components/contact/contact.component';
import { AdminLogin } from 'src/app/components/admin-login/admin-login.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { GetemployeedetailsComponent } from './components/getemployeedetails/getemployeedetails.component';
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { DeleteemployeeComponent } from './components/deleteemployee/deleteemployee.component';
import { PayrollsComponent } from './components/payrolls/payrolls.component';
import { UpdatemanagerComponent } from './components/updatemanager/updatemanager.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { ProfileComponent } from '../user/components/profile/profile.component';

const routes: Routes = [{
  path:'', component:AdminDashboardComponent,children:[
    {path:'profile',component: ProfileComponent},
    // {path:'about',component:AboutComponent},
    // {path:'contact',component:ContactComponent},
    {path: 'admin-login', component: AdminLogin},
    {path: 'adminElist', component: EmployeeListComponent},
    {path: 'adminprofile',component: AdminProfileComponent},
    {path:'getdetails', component: GetemployeedetailsComponent},
    {path:'createEmployee',component:CreateEmployeeComponent},
    {path:'deleteEmployee', component:DeleteemployeeComponent},
    {path:'Payrolls',component:PayrollsComponent},
    {path:'UpdateManager',component:UpdatemanagerComponent},
    {path:'UpdateEmployee',component:UpdateEmployeeComponent},
    {path:'', redirectTo: '/admin/profile', pathMatch:'full'},
    
    
  ], 
},];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
