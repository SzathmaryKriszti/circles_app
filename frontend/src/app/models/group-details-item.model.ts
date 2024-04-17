import {EventListItemModel} from "./event-list-item.model";
import {PostListItemModel} from "./post-list-item.model";
import {MemberListItemModel} from "./member-list-item.model";

export interface GroupDetailsItemModel {
  id: number;
  groupName: string;
  imgUrl: string;
  createdAt: string;
  owner: string;
  memberCount: number;
  eventCount: number;
  postCount: number;
  events: EventListItemModel[];
  posts: PostListItemModel[];
  members: MemberListItemModel[];

}
