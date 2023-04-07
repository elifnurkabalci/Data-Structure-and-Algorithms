package array;

public class Interaction{
    private String interactionid; 
    private String accountid;
    private String postid;

    public Interaction(String interactionid, String accountid, String postid){
        this.interactionid = interactionid;
        this.accountid = accountid;
        this.postid = postid;
    }
    public void CreateInteraction(){}
    public String getInteractionid(){ return interactionid; }
    public String getAccountid(){ return accountid; }
    public String getPostid(){ return postid; }

    
}