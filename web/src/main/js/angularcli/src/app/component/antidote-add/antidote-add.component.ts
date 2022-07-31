import {Component} from '@angular/core';
import {AntidoteAddReq} from "../../model/antidote-add-req";
import {ActivatedRoute, Router} from "@angular/router";
import {AntidoteService} from "../../service/antidote.service";
import {CryptoService} from "../../service/crypto.service";

@Component({
  selector: 'app-antidote-add',
  templateUrl: './antidote-add.component.html',
  styleUrls: ['./antidote-add.component.css']
})
export class AntidoteAddComponent {
  antidoteReq: AntidoteAddReq;

  constructor(private route: ActivatedRoute, private router: Router, private antidoteService: AntidoteService,
              private cryptoService: CryptoService) {
    this.antidoteReq = new AntidoteAddReq();
  }

  onSubmit() {
    if (this.antidoteReq.val != null) {
      this.antidoteReq.val = this.cryptoService.encrypt(this.antidoteReq.val);
    }
    this.antidoteService.add(this.antidoteReq).subscribe(result => this.gotoList());
  }

  private gotoList() {
    this.router.navigate(['/angular/antidoteList']);
  }
}
