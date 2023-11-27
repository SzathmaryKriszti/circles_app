import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NumberService {

  randomNumberSubject: Subject<number> = new Subject<number>()

  constructor() { }
}
