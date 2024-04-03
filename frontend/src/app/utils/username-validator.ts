import {Injectable} from '@angular/core';
import {FormControl} from '@angular/forms';
import {AuthenticationService} from "../services/authentication.service";


@Injectable()
export class UsernameValidator {

  debouncer: any;

  constructor(public authenticationService: AuthenticationService) {

  }

  checkUsername(control: FormControl): any {
    clearTimeout(this.debouncer);
    return new Promise(resolve => {
      this.debouncer = setTimeout(() => {
        this.authenticationService.validateUsername(control.value).subscribe({
          next: usernameInUse => resolve(usernameInUse ? {usernameInUse: true} : null),
          error: err => {
            console.error(err);
            resolve(null)
          }
        })
      }, 1000);
    });
  }

}
