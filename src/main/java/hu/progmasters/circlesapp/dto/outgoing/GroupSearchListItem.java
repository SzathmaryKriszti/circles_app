package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.elastic.GroupSearch;

public class GroupSearchListItem {

    private Long id;
    private String name;

    public GroupSearchListItem(GroupSearch group) {
        this.id = group.getId();
        this.name = group.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
