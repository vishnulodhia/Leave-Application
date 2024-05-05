import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../Service/Login/login.service';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(private auth: LoginService, private router: Router) {}

  canActivate(): boolean {
    let role = this.auth.getrole();
    if (role != null && this.auth.isLoggedin()) {
      if (role.toLowerCase() == 'admin') {
        return true;
      } else if (role.toLowerCase() == 'manger') {
        this.router.navigateByUrl('/ManagerDashboard');
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
