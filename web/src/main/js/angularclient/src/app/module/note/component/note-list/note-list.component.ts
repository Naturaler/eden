import {Component, OnInit} from '@angular/core';
import {Note} from "../../model/note";
import {Router} from "@angular/router";
import {NoteService} from "../../service/note.service";

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {
  noteList!: Note[];
  note!: Note;
  keyword!: string;
  selectedId!: bigint;
  preSelectedId!: bigint;

  constructor(private router: Router, private noteService: NoteService) {
    this.note = new Note();
    this.keyword = '';
    this.selectedId = BigInt(-1);
    this.preSelectedId = BigInt(-1);
  }

  ngOnInit(): void {
    this.list(this.emptyListReq());
  }

  search() {
    const req = this.buildSearchReq();
    this.list(req);
  }

  private list(req: Note) {
    this.noteService.list(req).subscribe(data => {
      if (data.httpCode === 200) {
        const apiResponseData = data.apiResponse.data;
        this.noteList = apiResponseData.list;
        this.noteList.forEach(note => {
          if (note != null && note.content != null) {
            if (note.content.length > 60) {
              note.content = note.content.substring(0, 60) + "...";
            }
          }
        });
      }
    });
  }

  add() {
    this.note = new Note();
    this.identifySelected(BigInt(-1));
  }

  delete() {
    this.noteService.delete(this.note.id).subscribe(data => this.list(this.emptyListReq()));
    this.note = new Note();
    this.selectedId = BigInt(-1);
  }

  save() {
    const req = this.buildNoteReq();
    if (req.id == null) {
      if (req.content == null || req.content.length === 0) {
        return;
      }
      if (req.title == null) {
        req.title = '未命名标题';
      }
      this.noteService.add(req).subscribe(data => {
        this.note = data.apiResponse.data;
        this.selectedId = this.note.id;
        this.list(this.emptyListReq());
      });
    } else {
      this.noteService.update(req).subscribe(data => {
        this.list(this.emptyListReq());
      });
    }
  }

  get(id?: bigint) {
    if (id == null) {
      return;
    }
    this.noteService.get(id).subscribe(data => {
      if (data.httpCode === 200) {
        let apiResponseData = data.apiResponse.data;
        this.note = apiResponseData;
      }
    });
    this.identifySelected(id);
  }

  private identifySelected(id: bigint) {
    if (id == null) {
      return;
    }
    console.log("三个id: " + id + ", " + this.selectedId + ", " + this.preSelectedId);
    this.preSelectedId = this.selectedId;
    this.selectedId = id;
    const selectedOne = document.getElementById("noteCard" + this.selectedId);
    if (selectedOne != null) {
      console.log('渲染了selected one' + this.selectedId);
      selectedOne.className = 'card border-primary mt-3';
    }
    if (this.preSelectedId == this.selectedId) {
      return;
    }
    const preSelectedOne = document.getElementById("noteCard" + this.preSelectedId);
    if (preSelectedOne != null) {
      console.log('取消渲染了pre selected one' + this.preSelectedId);
      preSelectedOne.className = 'card mt-3';
    }
  }

  private buildSearchReq(): Note {
    const target = new Note();
    target.title = this.note.title;

    return target;
  }

  private buildNoteReq(): Note {
    const req = new Note();
    req.id = this.note.id;
    req.title = this.note.title;
    req.content = this.note.content;

    return req;
  }

  private emptyListReq(): Note {
    return new Note();
  }
}
