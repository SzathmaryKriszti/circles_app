import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {UsernameValidator} from "../../utils/username-validator";


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registerForm: FormGroup;

  constructor(
    public formBuilder: FormBuilder,
    public authenticationService: AuthenticationService,
    public router: Router,
    public usernameValidator: UsernameValidator) {

    this.registerForm = formBuilder.group({
        name: ['', [Validators.required, Validators.minLength(6)]],
        username: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(15)],
          usernameValidator.checkUsername.bind(usernameValidator)],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', Validators.required]
      },
      {
        validators: this.passwordMatchValidator,
      }
    );
  }

  passwordMatchValidator(control: AbstractControl) {
    return control.get('password')?.value === control.get('confirmPassword')?.value
      ? null
      : {mismatch: true};
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
