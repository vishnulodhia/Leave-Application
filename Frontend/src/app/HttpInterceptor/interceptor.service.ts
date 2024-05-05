import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = localStorage.getItem("logintoken");
    console.log("Token",token);
    let jwttoken = req.clone({
      setHeaders:{
        Authorization: `Bearer ${token}`
      }
    })

   return next.handle(jwttoken);
  }
}
