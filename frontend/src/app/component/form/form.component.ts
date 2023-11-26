import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  basicForm: FormGroup
  constructor() {
    this.basicForm = new FormGroup({
      'name': new FormControl(''),
      'age': new FormControl(18)
    })
  }

  ngOnInit(): void {
  }

  handleSubmit(){}
}
