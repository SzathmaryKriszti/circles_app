import { Component, OnInit } from '@angular/core';
import {CirclesService} from "../../services/circles.service";
import {Router} from "@angular/router";
import {AppUserProfileDetailsModel} from "../../models/app-user-profile-details.model";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  appUserProfileDetails!: AppUserProfileDetailsModel;

  constructor(private circlesService: CirclesService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadUserProfileDetails();
  }

  loadUserProfileDetails(){
    this.circlesService.getUserProfileDetails().subscribe({
      next: value => {
        this.appUserProfileDetails = value;
      },
      error: err => console.warn(err)
    });
  }
}
