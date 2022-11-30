import {Component} from '@angular/core';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import {LoginReq} from "./module/common/model/login-req";
import {LoginService} from "./module/common/service/login.service";
import {Modal} from "bootstrap";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  // add NgbModalConfig and NgbModal to the component providers
  providers: [NgbModalConfig, NgbModal],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angularclient';
  loginReq: LoginReq;

  constructor(config: NgbModalConfig, private modalService: NgbModal, private loginService: LoginService) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
    this.loginReq = new LoginReq();
  }

  open({content}: { content: any }) {
    this.modalService.open(content);
  }

  onSubmit() {
    this.loginService.login(this.loginReq).subscribe(result => {
      // const target = document.getElementById('staticBackdrop');
      // if (target != null) {
      //   console.log("app component 获取登录弹框modal");
      //   const myModal = new Modal(target);
      //   myModal.dispose();
      //   // myModal.toggle();
      // }
    });
  }
}
