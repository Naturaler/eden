import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Antidote} from "../model/antidote";
import {Observable} from "rxjs";
import {AntidoteListReq} from "../model/antidote-list-req";
import {HttpResponse} from "../../common/model/http-response";
import {PageListRsp} from "../../common/model/page-list-rsp";
import {environment} from "../../../../environments/environment";

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

  add(antidote: Antidote): Observable<HttpResponse<Antidote>> {
    return this.http.post<HttpResponse<Antidote>>(this.addUrl, antidote);
  }

  list(antidoteListReq: AntidoteListReq): Observable<HttpResponse<PageListRsp<Antidote>>> {
    return this.http.post<HttpResponse<PageListRsp<Antidote>>>(this.listUrl, antidoteListReq);
  }

  get(id: bigint) {
    return this.http.get<HttpResponse<Antidote>>(this.getUrl + id);
  }

  remove(id: bigint) {
    return this.http.get(this.deleteUrl + id);
  }

  update(antidote: Antidote): Observable<HttpResponse<Antidote>> {
    return this.http.post<HttpResponse<Antidote>>(this.editUrl, antidote);
  }
}
