import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const BASE_URL: string = 'http://localhost:8080/api/users';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  register(userData: { username: string, password: string }): Observable<any> {
    return this.http.post(BASE_URL + '/register', userData);
  }
}
