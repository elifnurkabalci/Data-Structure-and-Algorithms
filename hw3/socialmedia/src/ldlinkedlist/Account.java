package ldlinkedlist;

import java.util.LinkedList;

public class Account{
    private String username;
    private String birthdate;
    private String location;
    private LinkedList<Post> posts;
    private LinkedList<Account> followers;
    private LinkedList<Account> following;
    private String accountid;
    private LinkedList<Message> inbox;
    private LinkedList<Message> outbox;
    private LinkedList<Account> blokedAccounts;
    private LinkedList<Interaction> interactions;
    private LinkedList<String> actions;

    public String getUsername(){ return username; }
    public String getBirthdate(){ return birthdate; }
    public String getLocation(){ return location; }
    public String getAccountid(){ return accountid; }
    public LinkedList<Message> getinbox(){ return inbox; }
    public LinkedList<Message> getoutbox(){ return outbox; }

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
                String a = "You followed " + foloowingaAccount.getUsername();
                actions.add(a);
                following.add(foloowingaAccount);
                foloowingaAccount.add_follower(logged_account);
            }
            else{
                System.out.println("This account is not logged account");
            }
        }
        
    }
    public void add_follower(Account account){
        followers.add(account);
    }
    public Account(){}
    public Account(String username, String birthdate, String location, String accountid){
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;
        this.posts = new LinkedList<>();
        this.followers = new LinkedList<>();
        this.following = new LinkedList<>();
        this.accountid = accountid;
        this.inbox = new LinkedList<>();
        this.outbox = new LinkedList<>();
        this.blokedAccounts = new LinkedList<>();
        this.interactions = new LinkedList<>();
        this.actions = new LinkedList<>();
    }
    public void addPost(String postid, String content, String logged_account_username){
        if(getUsername() == logged_account_username){
            System.out.println("Sharing post..");
            String a = "You shared post, id: " + postid;
            actions.add(a);
            Post newpost = new Post(postid,accountid, content);
            posts.add(newpost);
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
        System.out.println(getUsername() + " is following " + following.size() + " account(s) and has " + followers.size() + " follower(s).");
        if(!followers.isEmpty()) System.out.print("The followers of " + getUsername() + " are: ");
        for(int i=0; i<followers.size(); i++){
            System.out.print(followers.get(i).getUsername() + ", ");
        }
        System.out.println(" ");
        if(!following.isEmpty()) System.out.print(getUsername() + " is folllowing: ");
        for(int i=0; i<following.size(); i++){
            System.out.print(following.get(i).getUsername() + ", ");
        }
        System.out.println(" ");
        System.out.print(getUsername() + " has " + posts.size() + " posts.\n");
    }
    public void viewProfile(Account anotherAccount){
        if(!is_bloked(anotherAccount)){
            System.out.println("Viewing " + anotherAccount.getUsername() + "'s profile...");
            System.out.println("--------------------");
            anotherAccount.viewProfileBySelf();
        }
        else{
            System.out.println("This user cannot found: " + anotherAccount.getUsername());
        }
    }
    public void viewPosts(Account anotherAccount, Account loggAccount){
        if(posts.isEmpty()){
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
        if(posts.isEmpty()){
            System.out.println("This account doesnt have any post.");
        }
        else{
            for(int i=0; i<posts.size(); i++){
                System.out.println("PostID: " + posts.get(i).getpostid() + "  " + getUsername() + ": " + posts.get(i).getcontent());
            }
        }
    }
    public void addLike(String postid, Account account){
        if(!is_bloked(account)){
            Like newlike = new Like("like interaction", getAccountid(), postid);
            for(int i=0; i<account.posts.size(); i++){
                if(account.posts.get(i).getpostid() == postid){
                    account.posts.get(i).setlikes(newlike); 
                    interactions.add(newlike);
                    System.out.println("Liking a post of " + account.getUsername() + "..");
                    String a = "You liked " + account.getUsername() + "'s post. Post id: " + postid;
                    actions.add(a);
                }
            }
        }
    }
    public void addMessagetoOutbox(String messageid, String senderid, Account receiver, String content){
        if(!is_bloked(receiver) && senderid == accountid){ // if user doesnt blocked and logging account and sender are same person
            Message newmessage = new Message(messageid, senderid, receiver, content);
            outbox.add(newmessage);
            receiver.addMessagetoInbox(newmessage);
            System.out.println("Sending a message to " + receiver.getUsername() + "...");
        }
        else{
            System.out.println("This user cannot found: " + receiver.getUsername());
        }
    }
    public void addMessagetoInbox(Message newmessage){
        inbox.add(newmessage);
    }
    public void addComment(String postid, Account account, String content){
        if(!is_bloked(account)){
            Comment newcomment = new Comment(content, "comment interaction", getAccountid(), postid);
            for(int i=0; i<account.posts.size(); i++){
                if(account.posts.get(i).getpostid() == postid){
                    account.posts.get(i).setcomment(newcomment);
                    interactions.add(newcomment);
                    System.out.println("Commenting a post of " + account.getUsername() + "..");
                    String a = "You commented " + account.getUsername() + "'s post. Post id: " + postid;
                    actions.add(a);
                }
            }
        }
    }
    public void viewInbox(){
        System.out.println("Viewing Inbox: ");
        for(int i=0; i<inbox.size(); i++){
            System.out.println("Message ID: " + inbox.get(i).getMessageid());
            System.out.println("From: " + inbox.get(i).getSenderid());
            System.out.println("To: " + inbox.get(i).getReceiverid());
            System.out.println("Message: " + inbox.get(i).getContent());
            System.out.println(" ");
        }
    }
    public void viewOutbox(){
        System.out.println("Viewing Outbox:");
        for(int i=0; i<outbox.size(); i++){
            System.out.println("Message ID: " + outbox.get(i).getMessageid());
            System.out.println("From: " + outbox.get(i).getSenderid());
            System.out.println("To: " + outbox.get(i).getReceiverid());
            System.out.println("Message: " + outbox.get(i).getContent());
            System.out.println(" ");
        }
    }
    public void viewInteractions(Account account){
        if(!is_bloked(account) && !account.is_bloked(this)) account.viewInteractionsByself();
    }
    public void viewInteractionsByself(){
        System.out.println("Interactions:");
        for(int i=0; i<posts.size(); i++){
            System.out.println("----------------------");
            System.out.println("PostID: " + posts.get(i).getpostid() + ": " + posts.get(i).getcontent());
            if(!posts.get(i).getlikes().isEmpty()){ 
                System.out.println("The post was liked by the following accounts:");
                for(int j=0; j<posts.get(i).getlikes().size(); j++){
                    System.out.println(posts.get(i).getlikes().get(j).getAccountid());
                }
            }
            else{
                System.out.println("The post has no likes.");
            }
            if(!posts.get(i).getComments().isEmpty()){
                System.out.println("The post has " + posts.get(i).getComments().size() + " comment(s)...");
                for(int j=0; j<posts.get(i).getComments().size(); j++){
                    System.out.println("Comment " + posts.get(i).getComments().size() + " : (" + posts.get(i).getComments().get(j).getAccountid() + ") said " + posts.get(i).getComments().get(j).getComments());
                }
            }
            else{
                System.out.println("The post has no comments.");
            }
            
        }

    }
    public void block_user(Account account, Account logged_account){
        if(logged_account.getAccountid() == getAccountid()){
            blokedAccounts.add(account);
            String a = "You blocked " + account.getUsername();
            actions.add(a);
            if(following.contains(account)) unfollow(account, logged_account);
            for(int i=0; i<account.posts.size(); i++){
                for(int j=0; j<account.posts.get(i).getlikes().size(); j++){
                    if(account.posts.get(i).getlikes().get(j) != null){
                        if(account.posts.get(i).getlikes().get(j).getAccountid() == logged_account.getAccountid()){
                            String temo = account.posts.get(i).getlikes().get(j).getPostid();
                            removeLike(temo, account);
                        }
                    }
                }
                
            }
            for(int i=0; i<account.posts.size(); i++){
                for(int j=0; j<account.posts.get(i).getComments().size(); j++){
                    if(account.posts.get(i).getComments().get(j) != null){
                        if(account.posts.get(i).getComments().get(j).getAccountid() == logged_account.getAccountid()){
                            String temo = account.posts.get(i).getComments().get(j).getPostid();
                            removeComment(temo, account);
                        }
                    }
                    
                }
            }
        }
    }
    public void readMessages(Account from_account){
        if(inbox.isEmpty()) System.out.println("There is no message: " + getUsername());
        for(int i=0; i<inbox.size(); i++){
            if(inbox.get(i).getSenderid() == from_account.getAccountid()){
                System.out.println("Message "+ (i+1) + ": " + inbox.get(i).getContent());
            }
        }
    }
    public boolean is_bloked(Account account){
        for(int i=0; i<account.blokedAccounts.size(); i++){
            if(account.blokedAccounts.get(i).getUsername() == getUsername()){
                return true;
            }
        }
        return false;
    }
    public void removeLike(String postid, Account account){
        for(int i=0; i<account.posts.size(); i++){
            int temp = 1;
            for(int j=0; j<account.posts.get(i).getlikes().size(); j++){
                if(account.posts.get(i).getlikes().get(j).getAccountid() == getAccountid()){
                    account.posts.get(i).getlikes().set(j,null);
                    temp =0;
                }
            }
            if(temp ==1){
                System.out.println(account.getUsername() + "'s " + postid + "'s post doesnt have like from this account: " + getUsername());
            }  
        }
        delete_interaction(interactions);
        int t = 0;
        if(!account.posts.isEmpty()){
            for(Post post : account.posts){
                for(Like like : post.getlikes()){
                    if(like == null) t++;
                }
            }            
            if( t>1){ // lazy deletion for 2
                for(int i=0; i<account.posts.get(i).getlikes().size(); i++){
                    if(account.posts.get(i).getlikes().get(i) == null){
                        account.posts.get(i).getlikes().remove(i);
                    }
                }
            }
        }
    }
    public void delete_interaction(LinkedList<Interaction> list){
        int t = 0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i) == null) t++;
        }
        if( t>1){ // lazy deletion for 2
            for(int i=0; i<list.size(); i++){
                if(list.get(i) == null){
                    list.remove(i);
                }
            }
        }
    }
    public void removeComment(String postid, Account account){
        int temp=1;
        for(int i=0; i<account.posts.size(); i++){
            for(int j=0; j<account.posts.get(i).getComments().size(); j++){
                if(account.posts.get(i).getComments().get(j).getAccountid() == getAccountid()){
                    account.posts.get(i).getComments().set(j, null);
                    temp =0;
                }
            }
            if(temp ==1){
                System.out.println(account.getUsername() + "'s " + postid + "'s post doesnt have comment from this account: " + getUsername());
            }
        }
        delete_interaction(interactions);
        int t = 0;
        for(Post post : account.posts){
            for(Comment comment : post.getComments()){
                if((comment) == null) t++;
            }
        } 
        if( t>1){ // lazy deletion for 2
            for(int i=0; i<account.posts.get(i).getComments().size(); i++){
                if(account.posts.get(i).getComments().get(i) == null){
                    account.posts.get(i).getComments().remove(i);
                }
            }
        }
    }
    public void delete(LinkedList<Account> list){
        int t = 0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i) == null) t++;
        }
        if( t>1){ // lazy deletion for 2
            for(int i=0; i<list.size(); i++){
                if(list.get(i) == null){
                    list.remove(i);
                }
            }
        }
    }
    public void unblock_user(Account account, Account logged_Account){
        if(logged_Account.getAccountid() == getAccountid()){
            //blokedAccounts.remove(account);
            String a = "You unblocked " + account.getUsername();
            actions.add(a);

            //set lazy deletion
            blokedAccounts.set(blokedAccounts.indexOf(account), null);

            // control and delete
            delete(blokedAccounts);
        }
    }
    public void unfollow(Account account, Account logged_Account){
        if(logged_Account.getAccountid() == getAccountid()){
            String a = "You unfollowed " + account.getUsername();
            actions.add(a);

            // lazy deletion set part
            following.set(following.indexOf(account), null);
            account.followers.set(account.followers.indexOf(logged_Account), null);
            
            // control and deletion
            delete(following);
            account.delete(followers);


        }
    }
    public void showActions(){
        for(int i=0; i<actions.size(); i++){
            System.out.println(actions.get(i));
        }
    }
}