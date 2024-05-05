import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../../shared/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {
   }
   public doReg(user:User){
    return  this.http.post("http://localhost:8082/user/authenticate",user)}

    storeToken(tokenvalue:string,role:string){
      localStorage.setItem('logintoken', tokenvalue);
      localStorage.setItem('role',role)
    }

    removeToken(){
      localStorage.removeItem('logintoken')
      localStorage.removeItem('role')
    }

    getToken(){
      return localStorage.getItem('logintoken')
    }
    
    getrole(){
      return localStorage.getItem("role")
    }
    

    isLoggedin(){
      return !!localStorage.getItem("logintoken");
    }
  
 
}
