package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.AppUser;
import hu.progmasters.circlesapp.domain.Group;

public class GroupListItem {

    private Long id;
    private String name;
    private String owner;
    private String imgUrl;
    private Integer events;
    private Integer posts;

    private boolean isCurrentUserTheOwner;

    public GroupListItem() {
    }

    public GroupListItem(Group group) {
        this.id = group.getId();
        this.name = group.getGroupName();
        this.owner = group.getOwner().getName();
        this.events = group.getEvents().stream().filter(e -> !e.isDeleted()).toList().size();
        this.posts = group.getPosts().stream().filter(p -> !p.isDeleted()).toList().size();
        this.imgUrl = group.getImgUrl();
    }

    public GroupListItem(AppUser user, Group group) {
        this.id = group.getId();
        this.name = group.getGroupName();
        this.owner = group.getOwner().getName();
        this.events = group.getEvents().stream().filter(e -> !e.isDeleted()).toList().size();
        this.posts = group.getPosts().stream().filter(p -> !p.isDeleted()).toList().size();
        this.imgUrl = group.getImgUrl();
        this.isCurrentUserTheOwner = group.getOwner().equals(user);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Integer getEvents() {
        return events;
    }

    public Integer getPosts() {
        return posts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCurrentUserTheOwner() {
        return isCurrentUserTheOwner;
    }

    public void setCurrentUserTheOwner(boolean currentUserTheOwner) {
        isCurrentUserTheOwner = currentUserTheOwner;
    }
}
