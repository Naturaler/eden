import {Component} from '@angular/core';
import {AntidoteAddReq} from "../../model/antidote-add-req";
import {ActivatedRoute, Router} from "@angular/router";
import {AntidoteService} from "../../service/antidote.service";
import {CryptoService} from "../../service/crypto.service";
import {Modal} from "bootstrap";

@Component({
  selector: 'app-antidote-add',
  templateUrl: './antidote-add.component.html',
  styleUrls: ['./antidote-add.component.css']
})
export class AntidoteAddComponent {
  antidoteReq: AntidoteAddReq;
  errorMsg!: string;

  constructor(private route: ActivatedRoute, private router: Router, private antidoteService: AntidoteService,
              private cryptoService: CryptoService) {
    this.antidoteReq = new AntidoteAddReq();
    this.errorMsg = '';
  }

  onSubmit() {
    if (this.antidoteReq.val != null) {
      this.antidoteReq.val = this.cryptoService.encrypt(this.antidoteReq.val);
    }
    this.antidoteService.add(this.antidoteReq).subscribe(result => {
      if (result.httpCode === 200) {
        this.gotoList();
        return;
      }
      if (result.httpCode === 400) {
        this.errorMsg = result.httpMsg;
        const myModal = new Modal("#validationErrorModal");
        myModal.show();
      }
    });
  }

  private gotoList() {
    this.router.navigate(['/angular/antidoteList']);
  }
}
