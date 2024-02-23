package hu.progmasters.circlesapp.domain;

import hu.progmasters.circlesapp.dto.incoming.GroupCreationCommand;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "friend_groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "groupName")
    private String groupName;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "creation_at")
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Event> events;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Post> posts;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser owner;

    @ManyToMany(mappedBy = "groups")
    private List<AppUser>  members;

    public Group() {
    }

    public Group(GroupCreationCommand command, AppUser user) {
        this.groupName = command.getGroupName();
        this.imgUrl = command.getImgUrl();
        this.createdAt = LocalDateTime.now();
        this.events = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.owner = user;
        this.members = new ArrayList<>();
        this.members.add(user);
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public List<AppUser> getMembers() {
        return members;
    }

    public void setMembers(List<AppUser> members) {
        this.members = members;
    }

    public void addUser(AppUser user) {
        this.members.add(user);
    }

}
