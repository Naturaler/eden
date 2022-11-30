import {Injectable} from '@angular/core';
import {LoginReq} from "../model/login-req";
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {CryptoService} from "./crypto.service";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  httpHost = environment.httpHost;
  loginEncryptionKey = environment.loginEncryptionKey;
  private readonly loginUrl: string;

  constructor(private http: HttpClient, private cryptoService: CryptoService) {
    this.loginUrl = this.httpHost + '/eden/login';
  }

  public login(loginReq: LoginReq) {
    const time = new Date().getTime();
    const plaintext = 'Antidote' + '-' + loginReq.password + '-' + time;
    const req = new LoginReq();
    req.account = loginReq.account;
    req.password = this.cryptoService.encryptWithKey(plaintext, this.loginEncryptionKey);
    return this.http.post(this.loginUrl, req);
  }
}
