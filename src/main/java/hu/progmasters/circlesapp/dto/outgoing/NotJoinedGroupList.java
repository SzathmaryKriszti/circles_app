package hu.progmasters.circlesapp.dto.outgoing;

import java.util.List;

public class NotJoinedGroupList {

    private Integer totalPageNumber;
    private List<GroupListItem> items;

    public NotJoinedGroupList(Integer totalPageNumber, List<GroupListItem> items) {
        this.totalPageNumber = totalPageNumber;
        this.items = items;
    }

    public Integer getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(Integer totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public List<GroupListItem> getItems() {
        return items;
    }

    public void setItems(List<GroupListItem> items) {
        this.items = items;
    }
}
