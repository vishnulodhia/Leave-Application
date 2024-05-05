import { Component, OnInit } from '@angular/core';
import { TriggrednavService } from 'src/app/Service/Triggrednav/triggrednav.service';
import { UsercrudService } from '../../usercrud.service';
import { Adduser } from '../../Adduser';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PopupService } from 'src/app/Service/popup/popup.service';

interface User {
  user_id: number;
  username: String;
  email: String;
  password: String;
  roles: {
    role_id: number;
    role_name: String;
  };
}

@Component({
  selector: 'app-showuser',
  templateUrl: './showuser.component.html',
  styleUrls: ['./showuser.component.css'],
})
export class ShowuserComponent implements OnInit {
  showUsers: User[] = [];
  addUser!: FormGroup;
  showPassword: boolean = false;
  issubmited: boolean = false;
  Adduser: Adduser = new Adduser('', '', '', '');
  isLoading: boolean = false;
  hidePassword = true;
  id: number = 0;
  index:number =0;
  roleid:number=0;
  subdiable:boolean = true;

  constructor(
    private formbulider: FormBuilder,
    private triggrednav: TriggrednavService,
    private popup: PopupService,
    private service: UsercrudService
  ) {
    triggrednav.shownav = false;
  }

 

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
    
  }

  showuserhandler() {
    this.service.getuser().subscribe({
      next: (data: any) => {
        this.showUsers = data;
      },
      error: (error) => {},
    });
  }
 deletearr(deleteindex:number){
   this.showUsers = this.showUsers.splice(deleteindex,1);
  //  this.users = this.users.filter(user => user.id !== userId);
  this.showUsers = this.showUsers.filter((user,index)=>index != deleteindex );
  console.log(this.showUsers);
  
 }
  updateuserarr(){
  this.showUsers[this.index].username = this.Adduser.username;
  this.showUsers[this.index].password = this.Adduser.password;
  this.showUsers[this.index].email = this.Adduser.email;
  this.showUsers[this.index].roles.role_id = this.roleid;
  if(this.roleid == 4)
  this.showUsers[this.index].roles.role_name = "admin";

  else if(this.roleid==3)
  this.showUsers[this.index].roles.role_name = "manager";

  else
  this.showUsers[this.index].roles.role_name = "employee";

  }

  ngOnInit(): void {
    this.addUser = this.formbulider.group({
      username: ['', Validators.required],
      email: ['', [Validators.required,Validators.email]],
      password: ['', Validators.required],
      role: ['', Validators.required],
    });
    this.showuserhandler();
  }

  fillForm(user: User ) {
    this.addUser.get('username')?.setValue(user.username);
    this.addUser.get('email')?.setValue(user.email);
    this.addUser.get('password')?.setValue(user.password);
    this.addUser.get('role')?.setValue(user.roles.role_id);
    this.id = user.user_id;
    this.index = this.showUsers.indexOf(user);
    this.subdiable = false;
  }

  onSubmit() {
    this.issubmited = true;
    this.isLoading = true;
    if (this.addUser.invalid) {
      console.log('Invalid form');
      this.isLoading = false;

      return;
    }

    this.Adduser.username = this.addUser.get('username')?.value;
    this.Adduser.email = this.addUser.get('email')?.value;
    this.Adduser.password = this.addUser.get('password')?.value;
    this.Adduser.roles = { role_id: this.addUser.get('role')?.value };
    this.roleid = this.addUser.get('role')?.value;
    this.addUser.reset();

    this.service.updateuser(this.id, this.Adduser).subscribe({
      next: (data: any) => {
        this.isLoading = false;
        console.log(this.Adduser);
        console.log('Data:', data);
        this.updateuserarr();
        this.popup.openSuccessDialog('Success', 'User update Successfully');
      },
      error: (error) => {
        this.isLoading = false;
        this.popup.openErrorDialog('Fail', 'Fail TO Register User');
      },
    });
    this.issubmited = false;
    this.subdiable = true;

    
  }

  deleteuser(Id: number,user:User) {
    this.service.deleteuser(Id).subscribe({
      next: (data: any) => {
        this.showUsers = data;
        this.showuserhandler();
      },
      error: (error) => {},
    });
    

    
  }
}
