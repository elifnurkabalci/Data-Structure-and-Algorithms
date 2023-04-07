package arraylist;

import java.util.ArrayList;
public class Post{
    private String postid;
    private String accountid;
    private ArrayList<Like> likes;
    private ArrayList<Comment> comments;
    private String content; // ingredient of post

    public Post(String postid ,String accountid, String content){
        this.accountid = accountid;
        this.content = content;
        this.postid = postid;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
    public ArrayList<Like> getLikes(){ return likes; }
    public ArrayList<Comment> getcomment(){ return comments; }
    public String getpostid(){ return postid;}
    public String getcontent(){ return content; } 
    public String getAccountid(){ return accountid; }
    public void setlikes(Like account){ 
        likes.add(account);
    }
    public void setcomment(Comment account){
        comments.add(account);
    }

} 