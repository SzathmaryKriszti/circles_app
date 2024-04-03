import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {RegistrationComponent} from "../component/registration/registration.component";
import {WelcomeComponent} from "../component/welcome/welcome.component";
import {LoginComponent} from "../component/login/login.component";
import {UserProfileComponent} from "../component/user-profile/user-profile.component";
import {GroupFormComponent} from "../component/group-form/group-form.component";
import {MyGroupsComponent} from "../component/my-groups/my-groups.component";
import {FindGroupComponent} from "../component/find-group/find-group.component";


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent, pathMatch: 'full'},
  {path: 'login', component: LoginComponent, pathMatch: 'full'},
  {path: 'me', component: UserProfileComponent, pathMatch: 'full'},
  {path: 'group-form', component: GroupFormComponent, pathMatch: 'full'},
  {path: 'group-form/:id', component: GroupFormComponent, pathMatch: 'full'},
  {path: 'my-groups', component: MyGroupsComponent, pathMatch: 'full'},
  {path: 'find-group', component: FindGroupComponent, pathMatch: 'full'},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
