import {Component, OnInit} from '@angular/core';
import {AntidoteRsp} from "../../model/antidote-rsp";
import {AntidoteService} from "../../service/antidote.service";
import {AntidoteListReq} from "../../model/antidote-list-req";
import {ApiResponse} from "../../model/api-response";
import {Router} from "@angular/router";
import {HttpResponse} from "../../model/http-response";

@Component({
  selector: 'app-antidote-list',
  templateUrl: './antidote-list.component.html',
  styleUrls: ['./antidote-list.component.css']
})
export class AntidoteListComponent implements OnInit {
  httpResponse!: HttpResponse<AntidoteRsp[]>;
  apiResponse!: ApiResponse<AntidoteRsp[]>;

  constructor(private router: Router, private antidoteService: AntidoteService) {
  }

  ngOnInit(): void {
    this.antidoteService.list(new AntidoteListReq()).subscribe(data => {
      this.httpResponse = data;
      if (this.httpResponse.httpCode === 200) {
        this.apiResponse = this.httpResponse.apiResponse;
      } else if (this.httpResponse.httpCode === 403) {
        this.router.navigate(['/angular/login']);
      }
    })
  }

}
