import {Component} from '@angular/core';
import {UserLoginReq} from "../../model/user-login-req";
import {ActivatedRoute, Router} from "@angular/router";
import {UserLoginService} from "../../service/user-login.service";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {
  loginReq: UserLoginReq;

  constructor(private route: ActivatedRoute, private router: Router, private loginService: UserLoginService) {
    this.loginReq = new UserLoginReq();
  }

  onSubmit() {
    this.loginService.login(this.loginReq).subscribe(result => {
      this.gotoIndex();
    });
  }

  private gotoIndex() {
    this.router.navigate(['/angular/antidoteList']);
  }

}
