import {Component, OnInit} from '@angular/core';
import {Antidote} from "../../model/antidote";
import {AntidoteService} from "../../service/antidote.service";
import {AntidoteListReq} from "../../model/antidote-list-req";
import {Router} from "@angular/router";
import {CryptoService} from "../../../common/service/crypto.service";
import {AntidoteSearchParam} from "../../model/antidote-search-param";
import {SubmitType} from "../../../common/model/submit-type";

@Component({
  selector: 'app-antidote-list',
  templateUrl: './antidote-list.component.html',
  styleUrls: ['./antidote-list.component.css']
})
export class AntidoteListComponent implements OnInit {
  searchParam!: AntidoteSearchParam;
  shouPrevious!: string;
  shouNext!: string;
  modalPrefix!: string;
  antidote!: Antidote;
  antidoteList!: Antidote[];
  prePageNum!: number;
  pageNum!: number;
  nextPageNum!: number;
  pageSize!: number;
  pages!: number;
  total!: number;
  submitType!: SubmitType;
  disabled!: boolean;

  constructor(private router: Router, private antidoteService: AntidoteService, private cryptoService: CryptoService) {
    this.searchParam = new AntidoteSearchParam();
    this.antidote = new Antidote();
    this.pageSize = 10;
    this.prePageNum = 0;
    this.pageNum = 1;
    this.nextPageNum = 0;
    this.total = 0;
    this.pages = 0;
    this.shouPrevious = 'disabled';
    this.shouNext = 'disabled';
    this.modalPrefix = '';
    this.submitType = SubmitType.LIST;
    this.disabled = false;
  }

  ngOnInit(): void {
    this.list(this.emptyListReq());
  }

  query() {
    const req = this.buildSearchReq();
    // 查询时，重置页码
    req.pageNum = 1;
    this.list(req);
  }

  list(req: AntidoteListReq) {
    // 执行查询
    this.antidoteService.list(req).subscribe(data => {
      if (data.httpCode === 200) {
        const apiResponseData = data.apiResponse.data;
        this.antidoteList = apiResponseData.list;
        this.prePageNum = apiResponseData.prePage;
        this.pageNum = apiResponseData.pageNum;
        this.nextPageNum = apiResponseData.nextPage;
        this.pageSize = apiResponseData.pageSize;
        this.pages = apiResponseData.pages;
        this.total = apiResponseData.total;
        this.shouPrevious = apiResponseData.hasPreviousPage ? 'enabled' : 'disabled';
        this.shouNext = apiResponseData.hasNextPage ? 'enabled' : 'disabled';
      }
    })
  }

  onSubmit() {
    switch (this.submitType) {
      case SubmitType.ADD:
        this.add()
        break;
      case SubmitType.UPDATE:
        this.update();
        break;
      case SubmitType.REMOVE:
        this.remove();
        break;
      case SubmitType.GET:
      case SubmitType.LIST:
      default:
        return;
    }
  }

  add() {
    const req = this.buildAntidoteReq();
    this.antidoteService.add(req).subscribe(data => this.list(this.emptyListReq()));
  }

  emptyListReq(): AntidoteListReq {
    const req = new AntidoteListReq();
    req.pageNum = this.pageNum;
    req.pageSize = this.pageSize;

    return req;
  }

  nextPage() {
    if (this.pageNum + 1 > this.pages) {
      return;
    }
    this.pageNum += 1;
    const req = this.buildSearchReq();
    this.list(req);
  }

  previousPage() {
    if (this.pageNum - 1 < 1) {
      return;
    }
    this.pageNum -= 1;
    const req = this.buildSearchReq();
    this.list(req);
  }

  jump(pageNum: number) {
    this.pageNum = pageNum;
    const req = this.buildSearchReq();
    this.list(req);
  }

  showAntidoteDetail(operation: string, id?: bigint) {
    const antidoteDetailButton = document.getElementById("antidoteDetailButton");
    if (antidoteDetailButton != null) {
      switch (operation) {
        case 'add':
          this.disabled = false;
          this.modalPrefix = '新增';
          this.antidote = new Antidote();
          this.submitType = SubmitType.ADD;
          antidoteDetailButton.textContent = '保存';
          antidoteDetailButton.className = "btn btn-primary";
          break;
        case 'get':
          this.get(id);
          this.disabled = false;
          this.modalPrefix = '';
          this.submitType = SubmitType.UPDATE;
          antidoteDetailButton.textContent = '保存';
          antidoteDetailButton.className = "btn btn-primary";
          break;
        case 'update':
          this.get(id);
          this.disabled = false;
          this.modalPrefix = '更新';
          this.submitType = SubmitType.UPDATE;
          antidoteDetailButton.textContent = '更新';
          antidoteDetailButton.className = "btn btn-primary";
          break;
        case 'remove':
          this.get(id);
          this.disabled = true;
          this.modalPrefix = '删除';
          this.submitType = SubmitType.REMOVE;
          antidoteDetailButton.textContent = '确认删除';
          antidoteDetailButton.className = "btn btn-danger";
          break;
        default:
          return;
      }
    }
  }

  remove() {
    this.antidoteService.remove(this.antidote.id).subscribe(data => this.list(this.emptyListReq()));
  }

  update() {
    const req = this.buildAntidoteReq();
    this.antidoteService.update(req).subscribe(data => this.list(this.emptyListReq()));
  }

  buildAntidoteReq(): Antidote {
    const req = new Antidote();
    req.id = this.antidote.id;
    req.title = this.antidote.title;
    req.key = this.antidote.key;
    req.val = this.cryptoService.encrypt(this.antidote.val);
    req.remark = this.antidote.remark;

    return req;
  }

  buildSearchReq(): AntidoteListReq {
    const target = new AntidoteListReq();
    target.title = this.searchParam.title;
    target.key = this.searchParam.key;
    target.remark = this.searchParam.remark;
    target.pageNum = this.pageNum;
    target.pageSize = this.pageSize;

    return target;
  }

  private get(id?: bigint) {
    if (id == null) {
      return;
    }
    this.antidoteService.get(id).subscribe(data => {
      if (data.httpCode === 200) {
        let apiResponseData = data.apiResponse.data;
        this.antidote = apiResponseData;
        this.antidote.val = this.cryptoService.decrypt(apiResponseData.val);
      }
    })
  }
}
