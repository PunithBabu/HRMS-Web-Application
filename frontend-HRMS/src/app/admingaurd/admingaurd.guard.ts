import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/auth.service';
// import { UserService } from '../users/user.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn:'root'
}) 

export class adminauthGuard implements CanActivate{
  constructor(private auth:AuthService, private router:Router){}
  canActivate(route: ActivatedRouteSnapshot,
              state:RouterStateSnapshot): boolean | Promise<boolean> | Observable<boolean>{
                //return true;
                if(!this.auth.isLoggedIn){
                this.router.navigate(['admin-login']);
                return false;
                }
                return this.auth.isLoggedIn();
              }

              

}