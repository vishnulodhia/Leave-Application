import { Injectable } from '@angular/core';
import { Adduser } from './Adduser';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsercrudService {

  constructor(private http:HttpClient) { }
 adduser(user:Adduser){
return this.http.post('http://localhost:8082/user/addUser',user);
 }

 getuser(){
  return this.http.get('http://localhost:8082/user/getAllUser');
 }

 updateuser(id:number,user:Adduser){

  
  return this.http.post(`http://localhost:8082/user/updateUsers/${id}`,user)
 
 }

 deleteuser(id:number){
  return this.http.delete(`http://localhost:8082/user/deleteUserById/${id}`)
 }

}
