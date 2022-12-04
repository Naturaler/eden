import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AntidoteListComponent} from './module/antidote/component/antidote-list/antidote-list.component';
import {FormsModule} from "@angular/forms";
import {AntidoteService} from "./module/antidote/service/antidote.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./module/common/global/auth.interceptor";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NoteListComponent } from './module/note/component/note-list/note-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AntidoteListComponent,
    NoteListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [AntidoteService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
