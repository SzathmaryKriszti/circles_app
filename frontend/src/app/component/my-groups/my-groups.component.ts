import { Component, OnInit } from '@angular/core';
import {CirclesService} from "../../services/circles.service";
import {Router} from "@angular/router";
import {GroupListItemModel} from "../../models/group-list-item.model";


@Component({
  selector: 'app-my-groups',
  templateUrl: './my-groups.component.html',
  styleUrls: ['./my-groups.component.css']
})
export class MyGroupsComponent implements OnInit {

  groups: Array<GroupListItemModel> = [];
  page = 0;
  totalPages!: number;
  constructor(private circlesService: CirclesService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadJoinedGroups();
  }


  private loadJoinedGroups() {
    this.circlesService.fetchMoreJoinedGroups(this.page).subscribe({
      next: (data) => {
        this.groups = this.groups.concat(data.items);
        this.totalPages = data.totalPageNumber;
      },
      error: err => console.warn(err)
    });
  }

  createGroup() {
    this.router.navigate(["/group-form"]);
  }
}
