import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(
    // private authenticationService: AuthenticationService,
              private formBuilder: FormBuilder,
              private router: Router) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onLogin() {
    const data = {...this.loginForm.value};
    // this.login(data);
  }

  // private login(data: LoginFormModel) {
  //   this.authenticationService.login(data).subscribe({
  //     next: (response) => {
  //       sessionStorage.setItem('user', JSON.stringify(response));
  //       this.authenticationService.isLoggedIn = true;
  //       this.router.navigate(['/myGroups']);
  //     },
  //     error: (err) => {
  //       err.error = {
  //         fieldErrors: [
  //           {
  //             field: 'username',
  //             message: 'Incorrect username or password',
  //           },
  //           {
  //             field: 'password',
  //             message: 'Incorrect username or password',
  //           },
  //         ],
  //       };
  //     }
  //   })
  // }

  ngOnInit(): void {
  }

}
