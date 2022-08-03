import {Component, OnInit} from '@angular/core';
import {Modal} from "bootstrap";
import {AntidoteAddReq} from "../../model/antidote-add-req";
import {CryptoService} from "../../service/crypto.service";
import {AntidoteService} from "../../service/antidote.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AntidoteRsp} from "../../model/antidote-rsp";

@Component({
  selector: 'app-antidote-edit',
  templateUrl: './antidote-edit.component.html',
  styleUrls: ['./antidote-edit.component.css']
})
export class AntidoteEditComponent implements OnInit {
  antidoteReq: AntidoteAddReq;
  errorMsg!: string;
  singleAntidoteRsp!: AntidoteRsp;
  decryptVal!: string;

  constructor(private route: ActivatedRoute, private router: Router, private antidoteService: AntidoteService,
              private cryptoService: CryptoService) {
    this.antidoteReq = new AntidoteAddReq();
    this.errorMsg = '';
    this.singleAntidoteRsp = new AntidoteRsp();
    this.decryptVal = '';
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(param => {
      this.antidoteReq.id = param['id'];
      console.log('读取url参数: id: ' + this.antidoteReq.id);
      this.antidoteService.get(this.antidoteReq.id).subscribe(data => {
        this.singleAntidoteRsp = data.apiResponse.data;
        this.decryptVal = this.cryptoService.decrypt(this.singleAntidoteRsp.val);
        this.antidoteReq.val = this.decryptVal;
        this.antidoteReq.title = this.singleAntidoteRsp.title;
        this.antidoteReq.key = this.singleAntidoteRsp.key;
        this.antidoteReq.remark = this.singleAntidoteRsp.remark;
      });
    })
  }

  onSubmit() {
    if (this.antidoteReq.val != null) {
      this.antidoteReq.val = this.cryptoService.encrypt(this.antidoteReq.val);
    }
    this.antidoteService.edit(this.antidoteReq).subscribe(result => {
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
