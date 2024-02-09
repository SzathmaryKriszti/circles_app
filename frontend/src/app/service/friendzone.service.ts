import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";

const BASE_URL: string = environment.BASE_URL;

@Injectable({
  providedIn: 'root'
})
export class FriendzoneService {

  constructor() { }
}
