import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CirclesService} from "../../services/circles.service";
import {GroupDetailsItemModel} from "../../models/group-details-item.model";
import {PostListItemModel} from "../../models/post-list-item.model";
import {EventListItemModel} from "../../models/event-list-item.model";
import {MemberListItemModel} from "../../models/member-list-item.model";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  groupId!: number;
  groupDetails!: GroupDetailsItemModel;
  posts!: PostListItemModel[];
  events!: EventListItemModel[];
  members!: MemberListItemModel[];

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
        this.posts = data.posts.posts;
        this.events = data.events.events;
        this.members = data.members.members;
      },
      error: err => console.error(err)
    });
  }
}
