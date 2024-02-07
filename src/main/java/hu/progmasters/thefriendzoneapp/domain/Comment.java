package hu.progmasters.thefriendzoneapp.domain;

import hu.progmasters.thefriendzoneapp.dto.incoming.CommentCreationCommand;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "comment_body", columnDefinition = "TEXT")
    private String commentBody;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    private Post post;

    public Comment() {
    }

    public Comment(CommentCreationCommand command, String author, Post post) {
        this.commentBody = command.getCommentBody();
        this.author = author;
        this.post = post;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
