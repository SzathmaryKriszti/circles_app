package hu.progmasters.thefriendzoneapp.domain;

import hu.progmasters.thefriendzoneapp.dto.incoming.PostCreationCommand;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "post_body", columnDefinition = "TEXT")
    private String postBody;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "creation_at")
    private LocalDateTime createdAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @OrderBy(value = "createdAt desc")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BlogUser postOwner;

    public Post() {
    }

    public Post(PostCreationCommand command, Group group, BlogUser user) {
        this.title = command.getTitle();
        this.postBody = command.getPostBody();
        this.imgUrl = command.getImgUrl();
        this.createdAt = LocalDateTime.now();
        this.isDeleted = false;
        this.group = group;
        this.postOwner = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public BlogUser getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(BlogUser postOwner) {
        this.postOwner = postOwner;
    }

}
