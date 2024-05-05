import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/Service/Login/login.service';
import { TriggrednavService } from 'src/app/Service/Triggrednav/triggrednav.service';

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})
export class AdmindashboardComponent implements OnInit {

    
  constructor(protected triggrednav:TriggrednavService,private loginservices:LoginService,private router:Router) { 
  triggrednav.shownav = true;
  }

  ngOnInit(): void {
  }

  Logout():void{ 
    this.loginservices.removeToken();
    this.router.navigateByUrl("login")
  }

}
