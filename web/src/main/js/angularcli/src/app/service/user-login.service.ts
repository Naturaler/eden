import {Injectable} from '@angular/core';
import {UserLoginReq} from "../model/user-login-req";
import {HttpClient} from "@angular/common/http";
import {CryptoService} from "./crypto.service";

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {

  private loginUrl: string;

  constructor(private http: HttpClient, private cryptoService: CryptoService) {
    this.loginUrl = 'http://localhost:18512/eden/login';
  }

  public login(loginReq: UserLoginReq) {
    const time = new Date().getTime();
    const plaintext = 'Antidote' + '-' + loginReq.password + '-' + time;
    loginReq.password = this.cryptoService.encryptWithKey(plaintext, 'LoginAntidote188');
    return this.http.post(this.loginUrl, loginReq);
  }
}
