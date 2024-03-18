import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/users/user.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLogin implements OnInit{
  // faLock = faLock;
  // isSuccess: boolean = false;
  // isError: boolean = false;

loginForm! : FormGroup;


  constructor(private auth: AuthService, private router: Router, private authUser: UserService){}
  
  navigateToPage(): void {
    this.router.navigate(['/admin-login']);
  }
  ngOnInit(): void {
    this.adminLoggedIn();

    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    });
  }


  adminLoggedIn(){
    if(this.auth.isLoggedIn()){
      this.router.navigate(['admin'])
    }
  }
  
  userLoggedIn(){
  if(this.auth.isLoggedIn()){
    this.router.navigate(['user'])
  }
}
   
  onSubmit() {
   // console.log(this.loginForm.value);
   if(this.loginForm.valid){
    this.auth.adminLogin(this.loginForm.value).subscribe(
      (result)=>{
        this.router.navigate(['admin']);
    },
    (err:Error)=>{
      alert(err.message);
    }
    )
   }
  }

//   Submit() {
//     // console.log(this.loginForm.value);
//     if(this.loginForm.valid){
//      this.authUser.login(this.loginForm.value).subscribe(
//        (result)=>{
//          this.router.navigate(['user']);
//      },
//      (err:Error)=>{
//        alert(err.message);
//      }
//      )
//     }
//    }
}


