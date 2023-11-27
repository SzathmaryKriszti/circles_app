import {Component, OnInit} from '@angular/core';
import {max} from "rxjs";
import {NumberService} from "../../service/number.service";

@Component({
  selector: 'app-number',
  templateUrl: './number.component.html',
  styleUrls: ['./number.component.css']
})
export class NumberComponent implements OnInit {

  max: number = 100;
  min: number = 1;

  constructor(private numberService: NumberService) {
  }

  ngOnInit(): void {
  }

  generateRandomNumber() {
    const newNumber: number = Math.floor(Math.random() * (this.max - this.min + 1)) + this.min;
    this.numberService.randomNumberSubject.next(newNumber)
  }
}
