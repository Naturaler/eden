import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AntidoteAddReq} from "../model/antidote-add-req";
import {AntidoteRsp} from "../model/antidote-rsp";
import {Observable} from "rxjs";
import {AntidoteListReq} from "../model/antidote-list-req";
import {ApiResponse} from "../model/api-response";

@Injectable({
  providedIn: 'root'
})
export class AntidoteService {
  private addUrl: string;
  private listUrl: string;

  constructor(private http: HttpClient) {
    this.addUrl = 'http://localhost:18512/eden/antidote/add';
    this.listUrl = 'http://localhost:18512/eden/antidote/list';
  }

  add(antidoteReq: AntidoteAddReq) {
    return this.http.post<AntidoteAddReq>(this.addUrl, antidoteReq);
  }

  list(antidoteListReq: AntidoteListReq): Observable<ApiResponse<AntidoteRsp[]>> {
    return this.http.post<ApiResponse<AntidoteRsp[]>>(this.listUrl, antidoteListReq);
  }
}
