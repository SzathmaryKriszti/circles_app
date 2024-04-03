import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserCreationCommandModel} from "../models/user-creation-command.model";
import {LoginFormModel} from "../models/login-form.model";
import {AppUserDetailsModel} from "../models/app-user-details.model";
import {environment} from "../../environments/environment";

const BASE_URL: string =  environment.BASE_URL + '/api/users';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public isLoggedIn: boolean = false;

  constructor(private http: HttpClient) {

    // this.isLoggedIn = !!this.isAuthenticated();
    this.isLoggedIn = Boolean(this.isAuthenticated());
    //igy mindig a sessionStorage-bol lesz kinezve a statje az isLoggedIn-nek, refresh-kor nem allitodik mindig false-ra.

  }

  register(userData: UserCreationCommandModel): Observable<any> {
    return this.http.post(BASE_URL + '/registration', userData);
  }

  login(credentials: LoginFormModel):Observable<AppUserDetailsModel> {
    const headers = new HttpHeaders(credentials ? {
        'X-Requested-With': 'XMLHttpRequest',
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)}
    : {});
    return this.http.get<AppUserDetailsModel>(BASE_URL + "/login", {headers: headers, withCredentials: true});
  }

  isAuthenticated() {
    return sessionStorage.getItem('user');
  }

  logout(): Observable<any> {
    return this.http.get(BASE_URL + '/logout');
  }

  validateUsername(username: string):Observable<boolean> {
    return this.http.get<boolean>(BASE_URL + 'auth/validate-username/' + username);
  }
}
