import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../Service/Login/login.service';

@Injectable({
  providedIn: 'root'
})
export class EmployeeGuard implements CanActivate {
  constructor(private auth: LoginService, private router: Router) {}

  canActivate(): boolean {
    let role = this.auth.getrole();
    if (role != null && this.auth.isLoggedin()) {
      if (role.toLowerCase() == 'employee') {
        return true;
      } else if (role.toLowerCase() == 'manger') {
        this.router.navigateByUrl('/ManagerDashboard');
        return false;
      } else {
        this.router.navigateByUrl('/AdminDashboard');
        return false;
      }
    } else {
      this.router.navigateByUrl('/login');
      return false;
    }
  }
  
}
