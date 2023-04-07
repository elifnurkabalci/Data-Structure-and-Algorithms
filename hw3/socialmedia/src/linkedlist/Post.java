package linkedlist;

import java.util.LinkedList;

public class Post{
    private String postid;
    private String accountid;
    private LinkedList<Like> likes;
    private LinkedList<Comment> comments;
    private String content; // ingredient of post


    public Post(String postid ,String accountid, String content){
        this.accountid = accountid;
        this.content = content;
        this.postid = postid;
        this.likes = new LinkedList<>();
        this.comments = new LinkedList<>();

    }
    public String getpostid(){ return postid;}
    public String getcontent(){ return content; } 
    public String getAccountid(){ return accountid; }

    public LinkedList<Like> getlikes(){ return likes; }
    public LinkedList<Comment> getComments(){ return comments; }
    public void setlikes(Like account){ 
        likes.add(account);
    }
    public void setcomment(Comment account){
        comments.add(account);
    }
    
} 