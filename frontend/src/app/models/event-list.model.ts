import {EventListItemModel} from "./event-list-item.model";

export interface EventListModel {
  eventCount: number;
  items: EventListItemModel[];
}
