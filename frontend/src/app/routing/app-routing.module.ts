import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {RegistrationComponent} from "../component/registration/registration.component";
import {WelcomeComponent} from "../component/welcome/welcome.component";
import {LoginComponent} from "../component/login/login.component";
import {UserProfileComponent} from "../component/user-profile/user-profile.component";


const routes: Routes = [
  {path: '', component: WelcomeComponent, pathMatch: 'full'},
  {path: 'registration', component: RegistrationComponent, pathMatch: 'full'},
  {path: 'login', component: LoginComponent, pathMatch: 'full'},
  {path: 'me', component: UserProfileComponent, pathMatch: 'full'},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
