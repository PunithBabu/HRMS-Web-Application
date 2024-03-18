import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
// import { RegisterComponent } from './components/register/register.component';
import { UserDashboardComponent } from './modules/user/components/user-dashboard/user-dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';

import { NotFoundComponent } from './components/not-found/not-found.component';
import { EmployeeListComponent } from './modules/user/components/employee-list/employee-list.component';

import { adminauthGuard } from './admingaurd/admingaurd.guard';
import { userauthgaurd } from './usersgaurd/usergaurd.guard';
import { AdminLogin } from './components/admin-login/admin-login.component';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  {path:'' , component:LandingPageComponent},
  {path:'register', component:RegisterComponent},
  {path:'admin-login', component:AdminLogin},
  {path:'user-login',component:LoginComponent},
  {path:'login',component:LoginComponent},
  {path:'forgot-password',component:ForgotPasswordComponent},
  {path:'',redirectTo:'/login',pathMatch:'full'},
  {
    path:'admin', 
    canActivate:[adminauthGuard],
    loadChildren:()=> import('./modules/admin/admin.module').then((m)=>m.AdminModule),
  },
  {
    path:'user', 
    canActivate:[userauthgaurd],
    loadChildren:()=> import('./modules/user/user.module').then((m)=>m.UserModule),
  },
  {path:'**',component:NotFoundComponent},
  ];
  
  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }
  