import {Component, OnInit} from '@angular/core';
import {AntidoteRsp} from "../../model/antidote-rsp";
import {AntidoteService} from "../../service/antidote.service";
import {AntidoteListReq} from "../../model/antidote-list-req";
import {ApiResponse} from "../../model/api-response";
import {Router} from "@angular/router";
import {HttpResponse} from "../../model/http-response";
import {AntidoteAddReq} from "../../model/antidote-add-req";
import {PageListRsp} from "../../model/page-list-rsp";

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

  constructor(private router: Router, private antidoteService: AntidoteService) {
    this.pageNum = 1;
    this.antidoteListReq = new AntidoteListReq();
    this.antidoteListReq.pageSize = 10;
    this.antidoteListReq.pageNum = this.pageNum;
    this.shouPrevious = 'enabled';
    this.shouNext = 'enabled';
  }

  ngOnInit(): void {
    this.list();
  }

  onSubmit() {
    this.list();
  }

  list() {
    this.antidoteService.list(this.antidoteListReq).subscribe(data => {
      console.log("antidote list response: " + data);
      this.httpResponse = data;
      if (this.httpResponse.httpCode === 200) {
        this.apiResponse = this.httpResponse.apiResponse;
        if (this.apiResponse.data.hasPreviousPage) {
          this.shouPrevious = 'enabled';
        }else {
          this.shouPrevious = 'disabled';
        }
        if (this.apiResponse.data.hasNextPage) {
          this.shouNext = 'enabled';
        }else {
          this.shouNext = 'disabled';
        }
      } else if (this.httpResponse.httpCode === 403) {
        console.log("http response code: " + this.httpResponse.httpCode);
        this.router.navigate(['/angular/login']);
      } else {
        console.log("http response code: " + this.httpResponse.httpCode);
        this.router.navigate(['/angular/login']);
      }
    })
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
}
