import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../User';
import { LoginService } from '../../../Service/Login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  issubmited = false;
  loginForm!: FormGroup;
  showPassword: boolean = false;
  user: User = new User('', '');
  message: any;
  isvaliduser: boolean = true;

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  constructor(
    private formBulider: FormBuilder,
    private router: Router,
    private services: LoginService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBulider.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  // onSubmit(): void {
  //   this.issubmited=true

  //   if(this.loginForm.invalid){
  //     console.log("invalid form")
  //     return;
  //   }

  //   else{

  //     this.user.username = this.loginForm.get('username')?.value;
  //     this.user.password=this.loginForm.get('password')?.value;
  //     console.log(this.user);
  //     let resp = this.services.doReg(this.user);
  //    console.log("from Registration",resp);
  //    resp.subscribe((data:any)=>{
  //     console.log("data",data);

  //     if(data.status==401){
  //       this.isvaliduser =false;
  //       console.log(data.status);

  //     }

  //     if(data.result){
  //     alert("logon succesuful")
  //     localStorage.setItem("logintoken",data.jwtToken);
  //     this.router.navigateByUrl('AdminDashboard')
  //     this.isvaliduser=true;
  //     }
  //    else{
  //     this.router.navigateByUrl("AdminDashboard")

  //    }

  //   })

  //     this.router.navigateByUrl('Dashbored')
  //   }

  // }

  onSubmit(): void {
    this.issubmited = true;

    if (this.loginForm.invalid) {
      console.log('Invalid form');
      return;
    }

    this.user.username = this.loginForm.get('username')?.value;
    this.user.password = this.loginForm.get('password')?.value;
    console.log(this.user);

    this.services.doReg(this.user).subscribe({
      next: (data: any) => {
        console.log('Data:', data);
        if (data.result) {
          alert('Login successful');
          
          this.services.storeToken(data.jwtToken,data.role);
          let role = data.role;

          if(role.toLowerCase()=="admin")
          this.router.navigateByUrl("AdminDashboard")
          else if(role.toLowerCase()=="manager")
          this.router.navigateByUrl("ManagerDashboard")
          else
          this.router.navigateByUrl("EmployeerDashboard")
        
        } 
      },
      error: (error) => {
        console.error('Error:', error);
        if (error.status === 401) {
          // Handle unauthorized access
          this.isvaliduser = false;
          console.log('Unauthorized access', error.status);
          // Optional: Redirect to login page or show an error message
          this.router.navigateByUrl('/login');
        } else {
          // Handle other errors if necessary
        }
      },
    });

    // Remove the unconditional navigation here
  }
}
