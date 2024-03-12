import { Component, OnInit } from '@angular/core';
import {TranslocoService} from "@ngneat/transloco";
import {NavigationEnd, Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn: boolean = false;

  constructor(public translocoService: TranslocoService,
              public auth: AuthenticationService,
              private router: Router) {

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isLoggedIn = this.auth.isLoggedIn;
      }
    })

  }

  ngOnInit(): void {
  }

  changeLanguage(language: string) {
    this.translocoService.setActiveLang(language);
    localStorage.setItem('language', language);
  }

  logout(): void {
    this.auth.logout().subscribe(
      () => {
      sessionStorage.clear();
      this.auth.isLoggedIn = false;
      this.router.navigate(['login']).then();
    })

  }

}
