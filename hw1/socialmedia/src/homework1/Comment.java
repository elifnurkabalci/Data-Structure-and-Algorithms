package homework1;

public class Comment extends Interaction{
    private String comment_content;

    public Comment(String content, String interactionid, String accountid, String postid){
        super(interactionid, accountid, postid);
        this.comment_content = ""; 
        comment_content = content;
        // # of word in comment is 50
    }
    @Override public void CreateInteraction(){}
    public String getComments(){ return comment_content; }
}