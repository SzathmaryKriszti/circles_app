import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CirclesService} from "../../services/circles.service";
import {GroupDetailsItemModel} from "../../models/group-details-item.model";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  groupId!: number;
  groupDetails!: GroupDetailsItemModel;

  constructor(private route: ActivatedRoute,
              private circlesService: CirclesService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      map => {
        let idParam = +map.get('id')!;
        if (idParam && !isNaN(idParam)) {
          this.groupId = idParam;
          this.loadGroupDetails();
        }
      },
    );
  }

  private loadGroupDetails() {
    this.circlesService.fetchGroupDetails(this.groupId).subscribe({
      next: (data: GroupDetailsItemModel) => {
        this.groupDetails = data;
      },
      error: err => console.error(err)
    });
  }
}
