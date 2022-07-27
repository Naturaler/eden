import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AntidoteAddReq} from "../model/antidote-add-req";
import {AntidoteRsp} from "../model/antidote-rsp";
import {Observable} from "rxjs";
import {AntidoteListReq} from "../model/antidote-list-req";
import {HttpResponse} from "../model/http-response";
import {PageListRsp} from "../model/page-list-rsp";
import {ApiResponse} from "../model/api-response";

@Injectable({
  providedIn: 'root'
})
export class AntidoteService {
  private addUrl!: string;
  private listUrl!: string;
  private getUrl!: string;

  constructor(private http: HttpClient) {
    this.addUrl = 'http://localhost:18512/eden/antidote/add';
    this.listUrl = 'http://localhost:18512/eden/antidote/list';
    this.getUrl = 'http://localhost:18512/eden/antidote/get?id=';
  }

  add(antidoteReq: AntidoteAddReq) {
    return this.http.post<AntidoteAddReq>(this.addUrl, antidoteReq);
  }

  list(antidoteListReq: AntidoteListReq): Observable<HttpResponse<PageListRsp<AntidoteRsp>>> {
    return this.http.post<HttpResponse<PageListRsp<AntidoteRsp>>>(this.listUrl, antidoteListReq);
  }

  get(id: bigint) {
    return this.http.get<HttpResponse<AntidoteRsp>>(this.getUrl + id);
  }
}
