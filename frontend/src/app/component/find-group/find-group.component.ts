import {Component, OnInit} from '@angular/core';
import {CirclesService} from "../../services/circles.service";
import {GroupSearchListItemModel} from "../../models/group-search-list-item.model";

@Component({
  selector: 'app-find-group',
  templateUrl: './find-group.component.html',
  styleUrls: ['./find-group.component.css']
})
export class FindGroupComponent implements OnInit {

  page = 0;
  groups: Array<GroupSearchListItemModel> = [];
  totalPages!: number;
  keyword: string = '';
  debouncer: NodeJS.Timeout | undefined;

  constructor(private circlesService: CirclesService) {
  }

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

  onSearch(keyword: string) {
    clearTimeout(this.debouncer);
    this.debouncer = setTimeout(() => {
      this.circlesService.searchGroups(keyword).subscribe({
        next: (data) => {
          this.groups = this.groups = data.items;
        },
        error: err => console.warn(err)
      });
    }, 200);
  }

  clickMore() {
    if (this.page !== this.totalPages) {
      this.page++;
      this.loadGroups();
    } else {
      console.log("No more pages")
    }
  }
}
