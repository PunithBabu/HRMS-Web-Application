import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { UserService } from '../users/user.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn:'root'
}) 

export class userauthgaurd implements CanActivate{
  constructor(private router:Router, private authUser: UserService){}
  canActivate(route: ActivatedRouteSnapshot,
              state:RouterStateSnapshot): boolean | Promise<boolean> | Observable<boolean>{
                //return true;
                if(!this.authUser.isLoggedIn){
                  this.router.navigate(['user-login']);
                }
                return this.authUser.isLoggedIn();
              }

              

}