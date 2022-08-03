import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AntidoteAddComponent} from "./component/antidote-add/antidote-add.component";
import {AntidoteListComponent} from "./component/antidote-list/antidote-list.component";
import {UserLoginComponent} from "./component/user-login/user-login.component";
import {AntidoteEditComponent} from "./component/antidote-edit/antidote-edit.component";

const routes: Routes = [
  {path: 'angular/antidoteAdd', component: AntidoteAddComponent},
  {path: 'angular/antidoteEdit', component: AntidoteEditComponent},
  {path: 'angular/antidoteList', component: AntidoteListComponent},
  {path: 'angular/login', component: UserLoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
