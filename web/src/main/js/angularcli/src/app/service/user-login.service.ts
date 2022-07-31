import {Injectable} from '@angular/core';
import {UserLoginReq} from "../model/user-login-req";
import {HttpClient} from "@angular/common/http";
import {CryptoService} from "./crypto.service";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {
  httpHost = environment.httpHost;
  loginEncryptionKey = environment.loginEncryptionKey;
  private loginUrl: string;

  constructor(private http: HttpClient, private cryptoService: CryptoService) {
    this.loginUrl = this.httpHost + '/eden/login';
  }

  public login(loginReq: UserLoginReq) {
    const time = new Date().getTime();
    const plaintext = 'Antidote' + '-' + loginReq.password + '-' + time;
    loginReq.password = this.cryptoService.encryptWithKey(plaintext, this.loginEncryptionKey);
    return this.http.post(this.loginUrl, loginReq);
  }
}
