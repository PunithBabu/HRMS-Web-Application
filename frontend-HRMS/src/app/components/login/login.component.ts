import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/users/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  // faLock = faLock;
  // isSuccess: boolean = false;
  // isError: boolean = false;




  alertMessage: string = '';
  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  });

  constructor(private auth: AuthService, private router: Router, private authUser: UserService){}
  
  navigateToPage(): void {
    this.router.navigate(['/user-login']);
  }
  ngOnInit(): void {
    this.adminLoggedIn();
    this.userLoggedIn()
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
   
  // onSubmit() {
  //  // console.log(this.loginForm.value);
  //  if(this.loginForm.valid){
  //   this.auth.adminLogin(this.loginForm.value).subscribe(
  //     (result)=>{
  //       this.router.navigate(['admin']);
  //   },
  //   (err:Error)=>{
  //     alert(err.message);
  //   }
  //   )
  //  }
  // }

  Submit() {
    // console.log(this.loginForm.value);
    if(this.loginForm.valid){
     this.authUser.login(this.loginForm.value).subscribe(
       (result)=>{
         this.router.navigate(['user']);
     },
     (err:Error)=>{
       alert(err.message);
     }
     )
    }
   }
}


