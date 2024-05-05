import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../Service/Login/login.service';

@Injectable({
  providedIn: 'root'
})
export class ManagerGuard implements CanActivate {
  constructor(private auth: LoginService, private router: Router) {}

  canActivate(): boolean {
    let role = this.auth.getrole();
    if (role != null && this.auth.isLoggedin()) {
      if (role.toLowerCase() == 'manager') {
        return true;
      } else if (role.toLowerCase() == 'admin') {
        this.router.navigateByUrl('/AdminDashboard');
        return false;
      } else {
        this.router.navigateByUrl('/EmployeerDashboard');
        return false;
      }
    } else {
      this.router.navigateByUrl('/login');
      return false;
    }
  }
}
