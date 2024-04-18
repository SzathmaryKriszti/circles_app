import {EventListModel} from "./event-list.model";
import {PostListModel} from "./post-list.model";
import {MemberListModel} from "./member-list.model";

export interface GroupDetailsItemModel {
  id: number;
  groupName: string;
  imgUrl: string;
  createdAt: string;
  owner: string;
  events: EventListModel;
  posts: PostListModel;
  members: MemberListModel;

}
