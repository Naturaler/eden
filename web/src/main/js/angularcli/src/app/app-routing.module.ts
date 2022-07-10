import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AntidoteAddComponent} from "./component/antidote-add/antidote-add.component";
import {AntidoteListComponent} from "./component/antidote-list/antidote-list.component";

const routes: Routes = [
  {path: 'antidoteAdd', component: AntidoteAddComponent},
  {path: 'antidoteList', component: AntidoteListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
