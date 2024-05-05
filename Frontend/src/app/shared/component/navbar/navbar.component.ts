import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../../Service/Login/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router,private loginservices:LoginService) { 

  }

  ngOnInit(): void {
  }
  Logout():void{
    this.loginservices.removeToken();
    this.router.navigateByUrl("login")
  }

}
