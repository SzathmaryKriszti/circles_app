import {PostListItemModel} from "./post-list-item.model";

export interface PostListModel {
  postCount: number;
  items: PostListItemModel[];
}