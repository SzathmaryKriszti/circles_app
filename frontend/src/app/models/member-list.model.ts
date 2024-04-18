import {MemberListItemModel} from "./member-list-item.model";

export interface MemberListModel {
  memberCount: number;
  members: MemberListItemModel[];
}
