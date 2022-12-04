import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Note} from "../model/note";
import {HttpResponse} from "../../common/model/http-response";
import {PageListRsp} from "../../common/model/page-list-rsp";
import {Observable} from "rxjs";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  httpHost = environment.httpHost;
  private readonly addUrl!: string;
  private readonly updateUrl!: string;
  private readonly listUrl!: string;
  private readonly getUrl!: string;
  private readonly deleteUrl!: string;

  constructor(private http: HttpClient) {
    this.addUrl = this.httpHost + '/eden/note/add';
    this.updateUrl = this.httpHost + '/eden/note/update';
    this.listUrl = this.httpHost + '/eden/note/list';
    this.getUrl = this.httpHost + '/eden/note/get?id=';
    this.deleteUrl = this.httpHost + '/eden/note/delete?id=';
  }

  list(req: Note): Observable<HttpResponse<PageListRsp<Note>>> {
    return this.http.post<HttpResponse<PageListRsp<Note>>>(this.listUrl, req);
  }

  add(antidote: Note): Observable<HttpResponse<Note>> {
    return this.http.post<HttpResponse<Note>>(this.addUrl, antidote);
  }

  get(id: bigint) {
    return this.http.get<HttpResponse<Note>>(this.getUrl + id);
  }

  delete(id: bigint) {
    return this.http.get(this.deleteUrl + id);
  }

  update(req: Note): Observable<HttpResponse<Note>> {
    return this.http.post<HttpResponse<Note>>(this.updateUrl, req);
  }
}
