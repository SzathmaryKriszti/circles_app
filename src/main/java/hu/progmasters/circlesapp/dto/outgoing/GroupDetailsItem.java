package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.Group;


public class GroupDetailsItem {

    private Long id;
    private String groupName;
    private String imgUrl;
    private String createdAt;
    private String owner;
    private EventList events;
    private PostList posts;
    private MemberList members;

    public GroupDetailsItem(Group group) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.imgUrl = group.getImgUrl();
        this.createdAt = group.getCreatedAt().toString();
        this.owner = group.getOwner().getName();
        this.events = new EventList(group);
        this.posts = new PostList(group);
        this.members = new MemberList(group);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public EventList getEvents() {
        return events;
    }

    public void setEvents(EventList events) {
        this.events = events;
    }

    public PostList getPosts() {
        return posts;
    }

    public void setPosts(PostList posts) {
        this.posts = posts;
    }

    public MemberList getMembers() {
        return members;
    }

    public void setMembers(MemberList members) {
        this.members = members;
    }
}
