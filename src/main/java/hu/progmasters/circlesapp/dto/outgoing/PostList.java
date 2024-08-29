package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.Group;

import java.util.List;

public class PostList {

    private Integer postCount;
    private List<PostListItem> posts;

    public PostList(Group group) {
        this.postCount = group.getPosts().size();
        this.posts = group.getPosts().stream().map(PostListItem::new).toList();
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public List<PostListItem> getPosts() {
        return posts;
    }

    public void setPosts(List<PostListItem> posts) {
        this.posts = posts;
    }
}
