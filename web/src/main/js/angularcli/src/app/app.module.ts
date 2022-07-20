import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AntidoteAddComponent} from './component/antidote-add/antidote-add.component';
import {AntidoteService} from "./service/antidote.service";
import {HttpClientModule} from "@angular/common/http";
import { AntidoteListComponent } from './component/antidote-list/antidote-list.component';
import { UserLoginComponent } from './component/user-login/user-login.component';
import {UserLoginService} from "./service/user-login.service";

@NgModule({
  declarations: [
    AppComponent,
    AntidoteAddComponent,
    AntidoteListComponent,
    UserLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AntidoteService, UserLoginService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
