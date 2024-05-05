import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TriggrednavService } from 'src/app/Service/Triggrednav/triggrednav.service';
import { Adduser } from '../../Adduser';
import { UsercrudService } from '../../usercrud.service';
import { PopupService } from 'src/app/Service/popup/popup.service';

@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.component.html',
  styleUrls: ['./adduser.component.css']
})
export class AdduserComponent implements OnInit {
  addUser!: FormGroup;
  showPassword: boolean = false;
  issubmited:boolean = false;
  Adduser:Adduser = new Adduser("","","","");
  isLoading:boolean = false;

  constructor(
    private formbulider: FormBuilder
  ,private triggrednav:TriggrednavService,private service:UsercrudService,private popup:PopupService) {
    triggrednav.shownav=false;
  }

  ngOnInit(): void {
    this.addUser = this.formbulider.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      role: ['', Validators.required],
    });
  }
  hidePassword = true;

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(){
  this.issubmited=true;
  this.isLoading = true;
    if (this.addUser.invalid) {
      console.log('Invalid form');
      this.isLoading =false;

      return;
    }
    
    this.Adduser.username = this.addUser.get("username")?.value;
    this.Adduser.email = this.addUser.get("email")?.value;
    this.Adduser.password= this.addUser.get("password")?.value;
    this.Adduser.roles = {role_id:this.addUser.get("role")?.value}
    this.addUser.reset();
    this.service.adduser(this.Adduser).subscribe({
      next: (data: any) => {
    this.isLoading=false;
     this.popup.openSuccessDialog("Success","User Register Successfully")
    
      },
      error: (error) => {
    
        this.isLoading=false
        this.popup.openErrorDialog("Fail","Fail TO Register User");
      }
    });
    this.issubmited=false
    
  }
  

}
