import {Component, OnInit} from '@angular/core';
import {AntidoteRsp} from "../../model/antidote-rsp";
import {AntidoteService} from "../../service/antidote.service";
import {AntidoteListReq} from "../../model/antidote-list-req";
import {ApiResponse} from "../../model/api-response";
import {Router} from "@angular/router";
import {HttpResponse} from "../../model/http-response";
import {PageListRsp} from "../../model/page-list-rsp";
import {CryptoService} from "../../service/crypto.service";

@Component({
  selector: 'app-antidote-list',
  templateUrl: './antidote-list.component.html',
  styleUrls: ['./antidote-list.component.css']
})
export class AntidoteListComponent implements OnInit {
  httpResponse!: HttpResponse<PageListRsp<AntidoteRsp>>;
  apiResponse!: ApiResponse<PageListRsp<AntidoteRsp>>;
  antidoteListReq!: AntidoteListReq;
  pageNum!: number;
  shouPrevious!: string;
  shouNext!: string;
  singleAntidoteRsp!: AntidoteRsp;
  decryptVal!: string;

  constructor(private router: Router, private antidoteService: AntidoteService, private cryptoService: CryptoService) {
    this.pageNum = 1;
    this.antidoteListReq = new AntidoteListReq();
    this.antidoteListReq.pageSize = 10;
    this.antidoteListReq.pageNum = this.pageNum;
    this.shouPrevious = 'enabled';
    this.shouNext = 'enabled';
    this.singleAntidoteRsp = new AntidoteRsp();
    this.decryptVal = '';
  }

  ngOnInit(): void {
    this.list();
  }

  onSubmit() {
    this.query();
  }

  query() {
    // 查询时，重置页码
    this.pageNum = 1;
    this.antidoteListReq.pageNum = this.pageNum;
    this.list();
  }

  list() {
    // 执行查询
    this.antidoteService.list(this.antidoteListReq).subscribe(data => {
      console.log("list结果响应: " + data);
      this.httpResponse = data;
      if (this.httpResponse.httpCode === 200) {
        this.apiResponse = this.httpResponse.apiResponse;
        if (this.apiResponse.data.hasPreviousPage) {
          this.shouPrevious = 'enabled';
        } else {
          this.shouPrevious = 'disabled';
        }
        if (this.apiResponse.data.hasNextPage) {
          this.shouNext = 'enabled';
        } else {
          this.shouNext = 'disabled';
        }
      } else if (this.httpResponse.httpCode === 403) {
        this.router.navigate(['/angular/login']);
        AntidoteListComponent.hideNavbar();
      } else {
        this.router.navigate(['/angular/login']);
        AntidoteListComponent.hideNavbar();
      }
    })
  }

  /**
   * 页面跳转时，隐藏导航条
   * @private
   */
  private static hideNavbar() {
    const navbar = document.getElementById('navbarSupportedContent');
    if (navbar != null) {
      navbar.className = ' navbar-collapse collapse';
    }
  }

  add() {
    this.router.navigate(['/angular/antidoteAdd']);
  }

  nextPage() {
    if (this.pageNum + 1 > this.apiResponse.data.pages) {
      return;
    }
    this.pageNum += 1;
    this.antidoteListReq.pageNum = this.pageNum;
    this.list();
  }

  previousPage() {
    if (this.pageNum - 1 < 1) {
      return;
    }
    this.pageNum -= 1;
    this.antidoteListReq.pageNum = this.pageNum;
    this.list();
  }

  jump(pageNum: number) {
    this.pageNum = pageNum;
    this.antidoteListReq.pageNum = this.pageNum;
    this.list();
  }

  showAntidote(id: bigint) {
    this.antidoteService.get(id).subscribe(data => {
      this.singleAntidoteRsp = data.apiResponse.data;
      this.decryptVal = this.cryptoService.decrypt(this.singleAntidoteRsp.val);
    })
  }

  remove(id: bigint) {
    this.antidoteService.remove(id).subscribe(data => this.list());
  }

  edit(id: bigint) {
    this.router.navigate(["/angular/antidoteEdit"], {queryParams: {id}});
  }
}
