import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppUserProfileDetailsModel} from "../models/app-user-profile-details.model";
import {GroupCreationModel} from "../models/group-creation.model";

const BASE_URL: string = environment.BASE_URL;
const USERS_BASE_URL: string = BASE_URL + '/api/users';
const GROUPS_BASE_URL: string = BASE_URL + '/api/groups';

@Injectable({
  providedIn: 'root'
})
export class CirclesService {

  constructor(private http: HttpClient) { }

  getUserProfileDetails():Observable<AppUserProfileDetailsModel> {
    return this.http.get<AppUserProfileDetailsModel>(USERS_BASE_URL, {headers: {
        'X-Requested-With': 'XMLHttpRequest',
      },withCredentials: true});
  }

  createGroup(groupData: GroupCreationModel):Observable<any> {
    return this.http.post(GROUPS_BASE_URL, groupData);
  }
}
