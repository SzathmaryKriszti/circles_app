package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.Post;

public class PostListItem {

    private Long id;
    private String title;
    private String postBodyShortened;
    private String imgUrl;
    private String createdAt;
    private boolean isDeleted;
    private String author;
    private Integer numberOfComments;

    public PostListItem(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();

        if(post.getPostBody().length() < 200){
            this.postBodyShortened = post.getPostBody();
        } else {
            this.postBodyShortened = post.getPostBody().substring(0,200);
        }

        this.imgUrl = post.getImgUrl();
        this.createdAt = post.getCreatedAt().toString();
        this.isDeleted = post.isDeleted();
        this.author = post.getPostOwner().getName();
        this.numberOfComments = post.getComments().size();
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

    public String getPostBodyShortened() {
        return postBodyShortened;
    }

    public void setPostBodyShortened(String postBodyShortened) {
        this.postBodyShortened = postBodyShortened;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Integer numberOfComments) {
        this.numberOfComments = numberOfComments;
    }
}
