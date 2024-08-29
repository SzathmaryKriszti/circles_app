package hu.progmasters.circlesapp.dto.outgoing;

import java.util.List;

public class GroupSearchList {

    private List<GroupSearchListItem> items;

    public GroupSearchList(List<GroupSearchListItem> items) {
        this.items = items;
    }

    public List<GroupSearchListItem> getItems() {
        return items;
    }

    public void setItems(List<GroupSearchListItem> items) {
        this.items = items;
    }
}
