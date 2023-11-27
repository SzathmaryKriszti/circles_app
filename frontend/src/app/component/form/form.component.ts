import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {FormDataModel} from "../../model/formData.model";
import {FormService} from "../../service/form.service";
import {NumberService} from "../../service/number.service";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  basicForm: FormGroup
  randomNumber: number = 0;
  constructor(
    private numberService:NumberService,
    private formService: FormService) {
    this.basicForm = new FormGroup({
      'name': new FormControl(''),
      'age': new FormControl(18)
    })
  }

  ngOnInit(): void {
    this.numberService.randomNumberSubject.subscribe({
      next: (value) => this.randomNumber = value,
      error: (err) => console.log(err)
    })
  }

  handleSubmit(){
    const data: FormDataModel = this.basicForm.value;
     this.formService.sendFormData(data).subscribe({
        next: (value) => console.log("Sikeres mentes"),
       error: (err) => console.log(err),
       complete: () => ("Lefutott a complete ag.")
      })
  }
}
