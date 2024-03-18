import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
// import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HttpClientModule } from '@angular/common/http';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
// import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminLogin } from './components/admin-login/admin-login.component';
import { RegisterComponent } from './components/register/register.component';
import { DepartmentEmployee } from './modules/classes/DepartmentEmployee';




@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    ForgotPasswordComponent,
    NotFoundComponent,
    AdminLogin,
    LoginComponent,
    RegisterComponent


   

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgbModule,
    // FontAwesomeModule
    HttpClientModule,
    FormsModule,
    
    
    

  ],
  providers: [DepartmentEmployee],
  bootstrap: [AppComponent]
})
export class AppModule { }
