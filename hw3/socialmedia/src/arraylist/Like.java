package arraylist;

public class Like extends Interaction{
    private String accountid;
    public Like(String interactionid, String accountid, String postid){    
        super(interactionid, accountid, postid);
        this.accountid = accountid;
    }
    public String getaccountid(){ return accountid; }
    
    
}