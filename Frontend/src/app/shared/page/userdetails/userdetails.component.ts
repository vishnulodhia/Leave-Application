import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { LoginService } from 'src/app/Service/Login/login.service';
import { TriggrednavService } from 'src/app/Service/Triggrednav/triggrednav.service';

@Component({
  selector: 'app-userdetails',
  templateUrl: './userdetails.component.html',
  styleUrls: ['./userdetails.component.css']
})
export class UserdetailsComponent implements OnInit {

  constructor(private shownav:TriggrednavService,private loginservices:LoginService,private router:Router) {
    shownav.shownav= true;
   }

  ngOnInit(): void {
  }

  Logout():void{
    this.loginservices.removeToken();
    this.router.navigateByUrl("login")
  }

}
