package hu.progmasters.thefriendzoneapp.domain;

import hu.progmasters.thefriendzoneapp.dto.incoming.UserCreationCommand;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;

    @Column(name = "in_relation_with_members")
    private Integer inRelationWithMembers;

    @ManyToMany
    @JoinTable(name = "users_groups", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private List<Group> groups;

    @OneToMany(mappedBy = "owner")
    private List<Group> ownedGroups;

    @OneToMany(mappedBy = "eventOwner")
    private List<Event> events;

    @OneToMany(mappedBy = "postOwner")
    private List<Post> posts;


    public AppUser(UserCreationCommand command) {
        this.name = command.getName();
        this.imgUrl = "https://images.unsplash.com/photo-1683523946422-64283e8af4d5?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8bm8lMjBwaWN0dXJlfGVufDB8fDB8fHww";
        this.username = command.getUsername();
        this.email = command.getEmail();
        this.password = command.getPassword();
        this.role = UserRole.ROLE_USER;
        this.inRelationWithMembers = 0;
        this.groups = new ArrayList<>();
        this.ownedGroups = new ArrayList<>();
        this.events = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public AppUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Integer getInRelationWithMembers() {
        return inRelationWithMembers;
    }

    public void setInRelationWithMembers(Integer inRelationWithMembers) {
        this.inRelationWithMembers = inRelationWithMembers;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getOwnedGroups() {
        return ownedGroups;
    }

    public void setOwnedGroups(List<Group> ownedGroups) {
        this.ownedGroups = ownedGroups;
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

}
