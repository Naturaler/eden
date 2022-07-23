import { Injectable } from '@angular/core';
import {UserLoginReq} from "../model/user-login-req";
import {HttpClient} from "@angular/common/http";
import {HttpResponse} from "../model/http-response";

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {

  private loginUrl: string;

  constructor(private http: HttpClient) {
    this.loginUrl = 'http://localhost:18512/eden/login';
  }

  public login(loginReq: UserLoginReq) {
    return this.http.post(this.loginUrl, loginReq);
  }
}
