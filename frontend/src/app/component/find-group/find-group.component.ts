import { Component, OnInit } from '@angular/core';
import {CirclesService} from "../../services/circles.service";
import {GroupListItemModel} from "../../models/group-list-item.model";

@Component({
  selector: 'app-find-group',
  templateUrl: './find-group.component.html',
  styleUrls: ['./find-group.component.css']
})
export class FindGroupComponent implements OnInit {

  page = 0;
  groups: Array<GroupListItemModel> = [];
  totalPages!: number;
  constructor(private circlesService: CirclesService) { }

  ngOnInit(): void {
    this.loadGroups();
  }

  loadGroups() {
    this.circlesService.fetchMoreNotJoinedGroups(this.page).subscribe({
      next: (data) => {
        this.groups = this.groups.concat(data.items);
        this.totalPages = data.totalPageNumber;
      },
      error: err => console.warn(err)
    });
  }

  clickMore(){
    if (this.page !== this.totalPages){
      this.page++;
      this.loadGroups();
    } else {
      console.log("No more pages")
    }
  }
}
