import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { WelcomeComponent } from './component/welcome/welcome.component';
import { AppRoutingModule } from './routing/app-routing.module';
import { TranslocoRootModule } from './transloco/transloco-root.module';
import {NavbarComponent} from "./component/navbar/navbar.component";
import {RouterOutlet} from "@angular/router";
import { RegistrationComponent } from './component/registration/registration.component';
import { LoginComponent } from './component/login/login.component';
import { UserProfileComponent } from './component/user-profile/user-profile.component';
import {AuthInterceptor} from "./utils/auth.interceptor";
import { GroupFormComponent } from './component/group-form/group-form.component';
import { MyGroupsComponent } from './component/my-groups/my-groups.component';
import { FindGroupComponent } from './component/find-group/find-group.component';
import {UsernameValidator} from "./utils/username-validator";
import { SearchGroupComponent } from './component/search-group/search-group.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    NavbarComponent,
    RegistrationComponent,
    LoginComponent,
    UserProfileComponent,
    GroupFormComponent,
    MyGroupsComponent,
    FindGroupComponent,
    SearchGroupComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    TranslocoRootModule,
    RouterOutlet,
    FormsModule
  ],
  providers: [
    UsernameValidator,
    {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {

}
