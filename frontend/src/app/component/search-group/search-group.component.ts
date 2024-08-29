import { Component} from '@angular/core';
import {GroupSearchListItemModel} from "../../models/group-search-list-item.model";
import {CirclesService} from "../../services/circles.service";

@Component({
  selector: 'app-search-group',
  templateUrl: './search-group.component.html',
  styleUrls: ['./search-group.component.css']
})
export class SearchGroupComponent {

  groups: Array<GroupSearchListItemModel> = [];
  keyword: string = '';
  debouncer: NodeJS.Timeout | undefined;

  constructor(private circlesService: CirclesService) {
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

}
