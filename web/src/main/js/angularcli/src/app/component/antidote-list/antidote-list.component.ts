import {Component, OnInit} from '@angular/core';
import {AntidoteRsp} from "../../model/antidote-rsp";
import {AntidoteService} from "../../service/antidote.service";
import {AntidoteListReq} from "../../model/antidote-list-req";
import {ApiResponse} from "../../model/api-response";

@Component({
  selector: 'app-antidote-list',
  templateUrl: './antidote-list.component.html',
  styleUrls: ['./antidote-list.component.css']
})
export class AntidoteListComponent implements OnInit {
  apiResponse!: ApiResponse<AntidoteRsp[]>;

  constructor(private antidoteService: AntidoteService) {
  }

  ngOnInit(): void {
    this.antidoteService.list(new AntidoteListReq()).subscribe(data => {
      this.apiResponse = data;
    })
  }

}
