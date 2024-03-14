import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './../service/AuthService/Auth.service';

@Injectable({
  providedIn:'root'
})
export class authGuard implements CanActivate {
  constructor(private authService: AuthService,private router:Router){

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    throw new Error('Method not implemented.');

    if(this.authService.getToken()){
      return true;
    }

    this.router.navigate(['/']);
    return false;
  }

}
