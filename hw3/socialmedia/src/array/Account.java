
package array;
public class Account{
    private String username;
    private String birthdate;
    private String location;
    private Post[] posts;
    private Account[] followers;
    private Account[] following;
    private int numberoffollowing;
    private int numberoffollowers;
    private String accountid;
    private int NumberOfPosts;
    private Message[] inbox;
    private Message[] outbox;
    private int numberofmessage_inbox;
    private int numberofmessage_outbox;
    private Account[] blokedAccounts;
    private int numberofbloked;
    private Interaction[] interactions;
    private int numberofinteraction;

    public int getnumberofinbox(){ return numberofmessage_inbox; }
    public int getnumberoutbox(){ return numberofmessage_outbox; }
    public String getUsername(){ return username; }
    public String getBirthdate(){ return birthdate; }
    public String getLocation(){ return location; }
    public String getAccountid(){ return accountid; }
    public int getnumberofinteraciton(){ return numberofinteraction; }
    public String login(){
        return username;
    }
    public int logout(Account loggedAccount){
        if(getUsername() == loggedAccount.getUsername()){
            loggedAccount = null;
            System.out.println("Logging out from account: " + getUsername());
            return 0;
        }
        else{
            System.out.println("This account is not logged account: " + getUsername());
        }
        return 1;
    }
    public void follow(Account foloowingaAccount, Account logged_account){
        if(!is_bloked(foloowingaAccount)){
            if(getUsername() == logged_account.getUsername()){
                System.out.println("Following " + foloowingaAccount.getUsername());
                following[numberoffollowing] = foloowingaAccount;
                numberoffollowing++;
                foloowingaAccount.add_follower(logged_account);
            }
            else{
                System.out.println("This account is not logged account");
            }
        }
        
    }
    public void add_follower(Account account){
        followers[numberoffollowers] = account;
        numberoffollowers++;
    }
    public Account(){
    }
    public Account(String username, String birthdate, String location, String accountid){
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;
        this.posts = new Post[10];
        this.followers = new Account[10];
        this.following = new Account[10];
        this.accountid = accountid;
        this.NumberOfPosts = 0;
        this.numberoffollowers = 0;
        this.numberoffollowing = 0;
        this.inbox = new Message[10];
        this.outbox = new Message[10];
        this.numberofmessage_inbox = 0;
        this.numberofmessage_outbox = 0;
        this.blokedAccounts = new Account[10];
        this.numberofbloked = 0;
        this.interactions = new Interaction[10];
        this.numberofinteraction = 0;
    }
    public void addPost(String postid, String content, String logged_account_username){
        if(getUsername() == logged_account_username){
            System.out.println("Sharing post..");
            Post newpost = new Post(postid,accountid, content);
            posts[NumberOfPosts] = newpost;
            NumberOfPosts++;
        }
        else{
            System.out.println("This account is not logged account: " + getUsername());
        }
    }   
    public void viewProfileBySelf(){
        System.out.println("User ID: " + getAccountid());
        System.out.println("Username: " + getUsername());
        System.out.println("Location: " + getLocation());
        System.out.println("Birthday: " + getBirthdate());
        System.out.println(getUsername() + " is following " + numberoffollowing + " account(s) and has " + numberoffollowers + " follower(s).");
        if(numberoffollowers>0) System.out.print("The followers of " + getUsername() + " are: ");
        for(int i=0; i<numberoffollowers; i++){
            System.out.print(followers[i].getUsername() + ", ");
        }
        System.out.println(" ");
        if(numberoffollowing >0) System.out.print(getUsername() + " is folllowing: ");
        for(int i=0; i<numberoffollowing; i++){
            System.out.print(following[i].getUsername() + ", ");
        }
        System.out.println(" ");
        System.out.print(getUsername() + " has " + NumberOfPosts + " posts.\n");
    }
    public void viewProfile(Account anotherAccount){
        if(!is_bloked(anotherAccount)){
            System.out.println("Viewing " + anotherAccount.getUsername() + "'s profile...");
            System.out.println("--------------------");
            anotherAccount.viewProfileBySelf();;
        }
        else{
            System.out.println("This user cannot found: " + anotherAccount.getUsername());
        }
    }
    public void viewPosts(Account anotherAccount, Account loggAccount){
        if(anotherAccount.NumberOfPosts == 0){
            System.out.println("This user doesnt have any post: " + anotherAccount.getUsername());
        }
        else{
            if(!is_bloked(anotherAccount) && loggAccount.getAccountid() == getAccountid()){
                System.out.println("Viewing " + anotherAccount.getUsername() + "'s posts...");
                // if there is exist
                anotherAccount.viewPostsByself();
            }
        }
        
    }
    public void viewPostsByself(){
        if(posts[0].getpostid() == "0"){
            System.out.println("This account doesnt have any post.");
        }
        else{
            for(int i=0; i<NumberOfPosts; i++){
                System.out.println("PostID: " + posts[i].getpostid() + "  " + getUsername() + ": " + posts[i].getcontent());
            }
        }
    }
    public void addLike(String postid, Account account){
        if(!is_bloked(account)){
            Like newlike = new Like("like interaction", getAccountid(), postid);

            for(int i=0; i<account.NumberOfPosts; i++){
                if(account.posts[i].getpostid() == postid){
                    account.posts[i].setlikes(newlike); 
                    interactions[numberofinteraction] = newlike;
                    numberofinteraction++;
                    System.out.println("Liking a post of " + account.getUsername() + "..");
                }
            }
        }
    }
    public void addMessagetoOutbox(String messageid, String senderid, Account receiver, String content){
        if(!is_bloked(receiver) && senderid == accountid){ // if user doesnt blocked and logging account and sender are same person
            Message newmessage = new Message(messageid, senderid, receiver, content);
            outbox[numberofmessage_outbox] = newmessage;
            receiver.addMessagetoInbox(newmessage);
            numberofmessage_outbox++;
            System.out.println("Sending a message to " + receiver.getUsername() + "...");
        }
        else{
            System.out.println("This user cannot found: " + receiver.getUsername());
        }
    }
    public void addMessagetoInbox(Message newmessage){
        inbox[numberofmessage_inbox] = newmessage;
        numberofmessage_inbox++;
    }
    public void addComment(String postid, Account account, String content){
        if(!is_bloked(account)){
            Comment newcomment = new Comment(content, "comment interaction", getAccountid(), postid);

            for(int i=0; i<account.NumberOfPosts; i++){
                if(account.posts[i].getpostid() == postid){
                    account.posts[i].setcomment(newcomment);
                    interactions[numberofinteraction] = newcomment;
                    numberofinteraction++;
                    System.out.println("Commenting a post of " + account.getUsername() + "..");
                }
            }
        }
    }
    public void viewInbox(){
        System.out.println("Viewing Inbox: ");
        for(int i=0; i<numberofmessage_inbox; i++){
            System.out.println("Message ID: " + inbox[i].getMessageid());
            System.out.println("From: " + inbox[i].getSenderid());
            System.out.println("To: " + inbox[i].getReceiverid());
            System.out.println("Message: " + inbox[i].getContent());
            System.out.println(" ");
        }
    }
    public void viewOutbox(){
        System.out.println("Viewing Outbox:");
        for(int i=0; i<numberofmessage_outbox; i++){
            System.out.println("Message ID: " + outbox[i].getMessageid());
            System.out.println("From: " + outbox[i].getSenderid());
            System.out.println("To: " + outbox[i].getReceiverid());
            System.out.println("Message: " + outbox[i].getContent());
            System.out.println(" ");
        }
    }
    public void viewInteractions(Account account){
        if(!is_bloked(account) && !account.is_bloked(this)) account.viewInteractionsByself();
    }
    public void viewInteractionsByself(){
        System.out.println("Interactions:");
        for(int i=0; i<NumberOfPosts; i++){
            System.out.println("----------------------");
            System.out.println("PostID: " + posts[i].getpostid() + ": " + posts[i].getcontent());
            if(posts[i].getnumberoflikes() >0){ 
                System.out.println("The post was liked by the following accounts:");
                for(int j=0; j<posts[i].getnumberoflikes(); j++){
                    System.out.println(posts[i].getlikes(j).getAccountid());
                }
            }
            else{
                System.out.println("The post has no likes.");
            }
            if(posts[i].getnumberofcomments() >0){
                System.out.println("The post has " + posts[i].getnumberofcomments() + " comment(s)...");
                for(int j=0; j<posts[i].getnumberofcomments(); j++){
                    System.out.println("Comment " + posts[i].getnumberofcomments() + " : (" + posts[i].getComments(j).getAccountid() + ") said " + posts[i].getComments(j).getComments());
                }
            }
            else{
                System.out.println("The post has no comments.");
            }
            
        }

    }
    public void block_user(Account account, Account logged_account){
        if(logged_account.getAccountid() == getAccountid()){
            blokedAccounts[numberofbloked] = account;
            numberofbloked++;
        }
        
    }
    public void readMessages(Account from_account){
        if(numberofmessage_inbox == 0) System.out.println("There is no message: " + getUsername());
        for(int i=0; i<getnumberofinbox(); i++){
            if(inbox[i].getSenderid() == from_account.getAccountid()){
                System.out.println("Message "+ (i+1) + ": " + inbox[i].getContent());
            }
        }
    }
    public boolean is_bloked(Account account){
        for(int i=0; i<account.numberofbloked; i++){
            if(account.blokedAccounts[i].getUsername() == getUsername()){
                return true;
            }
        }
        return false;
    }

}