import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AntidoteAddReq} from "../model/antidote-add-req";
import {AntidoteRsp} from "../model/antidote-rsp";
import {Observable} from "rxjs";
import {AntidoteListReq} from "../model/antidote-list-req";
import {HttpResponse} from "../model/http-response";
import {PageListRsp} from "../model/page-list-rsp";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AntidoteService {
  httpHost = environment.httpHost;
  private addUrl!: string;
  private editUrl!: string;
  private listUrl!: string;
  private getUrl!: string;
  private deleteUrl!: string;

  constructor(private http: HttpClient) {
    this.addUrl = this.httpHost + '/eden/antidote/add';
    this.editUrl = this.httpHost + '/eden/antidote/edit';
    this.listUrl = this.httpHost + '/eden/antidote/list';
    this.getUrl = this.httpHost + '/eden/antidote/get?id=';
    this.deleteUrl = this.httpHost + '/eden/antidote/delete?id=';
  }

  add(antidoteReq: AntidoteAddReq): Observable<HttpResponse<AntidoteRsp>> {
    return this.http.post<HttpResponse<AntidoteRsp>>(this.addUrl, antidoteReq);
  }

  list(antidoteListReq: AntidoteListReq): Observable<HttpResponse<PageListRsp<AntidoteRsp>>> {
    return this.http.post<HttpResponse<PageListRsp<AntidoteRsp>>>(this.listUrl, antidoteListReq);
  }

  get(id: bigint) {
    return this.http.get<HttpResponse<AntidoteRsp>>(this.getUrl + id);
  }

  remove(id: bigint) {
    return this.http.get(this.deleteUrl + id);
  }

  edit(antidoteReq: AntidoteAddReq): Observable<HttpResponse<AntidoteRsp>> {
    return this.http.post<HttpResponse<AntidoteRsp>>(this.editUrl, antidoteReq);
  }
}
