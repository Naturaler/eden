import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AntidoteListComponent} from './module/antidote/component/antidote-list/antidote-list.component';
import {FormsModule} from "@angular/forms";
import {AntidoteService} from "./module/antidote/service/antidote.service";

@NgModule({
  declarations: [
    AppComponent,
    AntidoteListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [AntidoteService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
