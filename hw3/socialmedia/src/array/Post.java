package array;

public class Post{
    private String postid;
    private String accountid;
    private Like[] likes;
    private Comment[] comments;
    private String content; // ingredient of post
    private int numberoflikes;
    private int numberofcomment;

    public Post(String postid ,String accountid, String content){
        this.accountid = accountid;
        this.content = content;
        this.postid = postid;
        this.likes = new Like[10];
        this.comments = new Comment[10];
        this.numberoflikes = 0;
        this.numberofcomment = 0;
    }
    public String getpostid(){ return postid;}
    public String getcontent(){ return content; } 
    public String getAccountid(){ return accountid; }
    public int getnumberoflikes(){ return numberoflikes;}
    public int getnumberofcomments(){ return numberofcomment; }
    public Like getlikes(int index){ return likes[index]; }
    public Comment getComments(int index){ return comments[index]; }
    public void setlikes(Like account){ 
        likes[numberoflikes] = account;
        numberoflikes++;
    }
    public void setcomment(Comment account){
        comments[numberofcomment] = account;
        numberofcomment++;
    }
    
} 