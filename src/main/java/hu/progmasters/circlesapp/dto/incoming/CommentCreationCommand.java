package hu.progmasters.circlesapp.dto.incoming;

public class CommentCreationCommand {
    private String commentBody;

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
}
