import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registerForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private  authenticationService: AuthenticationService,
    private router: Router) {

    this.registerForm = formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(6)]],
      username: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(15)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['',[Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    },
      {
        validators: this.passwordMatchValidator,
      }
    );
  }

  passwordMatchValidator(control: AbstractControl){
    return control.get('password')?.value === control.get('confirmPassword')?.value
    ? null
      : { mismatch: true};
  }

  ngOnInit(): void {
  }

  registerUser() {
this.authenticationService.register(this.registerForm.value).subscribe({
  next: response => this.router.navigate(['login']),
  error: error => console.error(error)
});
  }


}
