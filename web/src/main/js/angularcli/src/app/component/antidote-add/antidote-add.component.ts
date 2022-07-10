import {Component} from '@angular/core';
import {AntidoteAddReq} from "../../model/antidote-add-req";
import {ActivatedRoute, Router} from "@angular/router";
import {AntidoteService} from "../../service/antidote.service";

@Component({
  selector: 'app-antidote-add',
  templateUrl: './antidote-add.component.html',
  styleUrls: ['./antidote-add.component.css']
})
export class AntidoteAddComponent {
  antidoteReq: AntidoteAddReq;

  constructor(private route: ActivatedRoute, private router: Router, private antidoteService: AntidoteService) {
    this.antidoteReq = new AntidoteAddReq();
  }

  onSubmit() {
    this.antidoteService.add(this.antidoteReq).subscribe(result => this.gotoList());
  }

  private gotoList() {
    this.router.navigate(['/antidoteList']);
  }
}
