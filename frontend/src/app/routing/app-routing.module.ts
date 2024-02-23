import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {RegistrationComponent} from "../component/registration/registration.component";
import {WelcomeComponent} from "../component/welcome/welcome.component";
import {LoginComponent} from "../component/login/login.component";

const routes: Routes = [
  {path: '', component: WelcomeComponent, pathMatch: 'full'},
  {path: 'registration', component: RegistrationComponent, pathMatch: 'full'},
  {path: 'login', component: LoginComponent, pathMatch: 'full'}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
