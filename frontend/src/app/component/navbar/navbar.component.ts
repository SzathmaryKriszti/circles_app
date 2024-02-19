import { Component, OnInit } from '@angular/core';
import {TranslocoService} from "@ngneat/transloco";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public translocoService: TranslocoService) { }

  ngOnInit(): void {
  }

  changeLanguage(language: string) {
    this.translocoService.setActiveLang(language);
    localStorage.setItem('language', language);
  }
}
