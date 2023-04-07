package arraylist;

import java.util.ArrayList;

public class TestClass4 {
    private ArrayList<Account> accounts;

    public TestClass4(){
        this.accounts = new ArrayList<>();
    }
    public void addAccount(Account account){
        accounts.add(account);
    }

    public Account control_login(String username){ // for logging account, username exist or not
        for (int i = 0; i <accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)) {
                return accounts.get(i);
            }
        }
        return null;
    }  
    public static void main(String[] args){
        final long startTime = System.currentTimeMillis();
        // social media system control center
        TestClass4 system = new TestClass4();

        System.out.println("Creating accounts...");
        //create accounts
        Account gizemsungu = new Account("gizemsungu", "01/01/2000", "Istanbul", "1-gizemsungu");
        System.out.println("An account with username gizemsungu has been created.");
        
        Account sibelgulmez = new Account("sibelgulmez", "02/02/2001", "Ankara", "2-sibelgulmez");
        System.out.println("An account with username sibelgulmez has been created.");
        
        Account gokhankaya = new Account("gokhankaya", "03/03/2002", "Izmir", "3-gokhankaya");
        System.out.println("An account with username gokhankaya has been created.");

        Account account1 = new Account("account1", "01/01/2001", "Istanbul", "4-account1");
        System.out.println("An account with username account1 has been created.");

        Account account2 = new Account("account2", "01/01/2002", "Istanbul", "5-account2");
        System.out.println("An account with username account2 has been created.");

        Account account3 = new Account("account3", "01/01/2003", "Istanbul", "6-account3");
        System.out.println("An account with username account3 has been created.");

        Account account4 = new Account("accound4", "01/01/2004", "Istanbul", "7-account4");
        System.out.println("An account with username account4 has been created.");

        Account account5 = new Account("account5", "01/01/2005", "Istanbul", "8-account5");
        System.out.println("An account with username account5 has been created.");

        Account account6 = new Account("account6", "01/01/2006", "Istanbul", "9-account6");
        System.out.println("An account with username account6 has been created.");

        Account account7 = new Account("account7", "01/01/2007", "Istanbul", "10-account7");
        System.out.println("An account with username account7 has been created.");
        
        // add acounts to system
        system.addAccount(gizemsungu);
        system.addAccount(sibelgulmez);
        system.addAccount(gokhankaya);
        system.addAccount(account1);
        system.addAccount(account2);
        system.addAccount(account3);
        system.addAccount(account4);
        system.addAccount(account5);
        system.addAccount(account6);
        system.addAccount(account7);

        // control for loggin account
        Account logged_account = new Account();

        //login as sibel
        logged_account = system.control_login(sibelgulmez.login());

        if(logged_account !=null){// if account is exist
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            // share posts
            sibelgulmez.addPost("post1","I like Java", logged_account.getUsername());
            sibelgulmez.addPost("post2", "Java the coffee..", logged_account.getUsername());
            System.out.println(" ");

            // follow
            sibelgulmez.follow(gizemsungu, logged_account);
            sibelgulmez.follow(gokhankaya, logged_account);
            System.out.println(" ");

            // logout
            sibelgulmez.logout(logged_account);
            System.out.println(" ");
        }
        else{
            System.out.println("Account is not exist");
        }
        
        //login as gokhan
        logged_account = system.control_login(gokhankaya.login());

        if(logged_account !=null){// if account is exist
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            // view sibel's profile
            gokhankaya.viewProfile(sibelgulmez);
            System.out.println(" ");

            // view sibel's posts
            gokhankaya.viewPosts(sibelgulmez,logged_account);
            System.out.println(" ");

            // like sibel's post
            gokhankaya.addLike("post2", sibelgulmez);
            System.out.println(" ");

            // comment sibel's post
            gokhankaya.addComment("post2", sibelgulmez, "Hi Sibel, I am gokhan..");
            System.out.println(" ");

            //follow other accounts
            gokhankaya.follow(sibelgulmez, logged_account);
            gokhankaya.follow(gizemsungu, logged_account);
            System.out.println(" ");

            // send message to gizemsungu
            gokhankaya.addMessagetoOutbox("message1", logged_account.getAccountid(), gizemsungu, "Hi gizem, I am following to you..");
            System.out.println(" ");

            //log out the gokhankaya
            gokhankaya.logout(logged_account);
        }
        else{
            System.out.println("Account is not exist");
        }
        // login as gizemsungu
        logged_account = system.control_login(gizemsungu.login());

        if(logged_account !=null){// if account is exist
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            // check # of message in the outbox
            System.out.println("Checking outbox..");
            System.out.println("There is/are " + gizemsungu.getoutbox().size() + " message(s) in the outbox.");
            System.out.println(" ");

            // check # of message in the inbox
            System.out.println("Checking inbox..");
            System.out.println("There is/are " + gizemsungu.getinbox().size() + " message(s) in the inbox.");
            System.out.println(" ");

            //view message inbox
            gizemsungu.viewInbox();
            System.out.println(" ");

            //view sibel's profile
            gizemsungu.viewProfile(sibelgulmez);
            System.out.println(" ");

            //view sibel's posts
            gizemsungu.viewPosts(sibelgulmez,logged_account);
            System.out.println(" ");

            //view sibel's posts interactions
            gizemsungu.viewInteractions(sibelgulmez);
            System.out.println(" ");

            //like sibel's posts
            gizemsungu.addLike("post1", sibelgulmez);
            System.out.println(" ");

            //view sibel's posts interactions
            gizemsungu.viewInteractions(sibelgulmez);
            System.out.println(" ");

            // block sibel's profile
            gizemsungu.block_user(sibelgulmez, logged_account);
            System.out.println(" ");

            // logout gizem's profile
            gizemsungu.logout(logged_account);
        }
        else{
            System.out.println("Account is not exist");
        }
        //******************************************************* */
        //login as sibel
        logged_account = system.control_login(sibelgulmez.login());

        if(logged_account !=null){// if account is exist
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            // try to view gizem's profile
            sibelgulmez.viewProfile(gizemsungu);
            System.out.println(" ");

            // try to send message to gizem
            sibelgulmez.addMessagetoOutbox("message4", sibelgulmez.getAccountid(), gizemsungu, "Halo");            
            System.out.println(" ");
            
            // logout sibel's account
            sibelgulmez.logout(logged_account);
        }   
        else{
            System.out.println("Account is not exist");
        }
        
        logged_account = system.control_login(account1.login());

        if(logged_account != null){
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            /* follow - viewprofile - add comment - addpost - showaction */        
            logged_account.follow(account7, logged_account);
            System.out.println(" ");
            logged_account.viewProfile(sibelgulmez);
            System.out.println(" ");
            logged_account.addComment("post2", sibelgulmez, "This comment made from acoount1");
            System.out.println(" ");
            logged_account.addPost("post3", "I am account1, This is my first post!", logged_account.getUsername());   
            System.out.println(" ");
            logged_account.showActions();
            System.out.println(" ");
        }
        else{
            System.out.println("Account is not exist");
        }

        logged_account = system.control_login(account2.login());

        if(logged_account != null){
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            /* follow - follow - addpost - addlike - addmesage- account5 */            
            logged_account.follow(gokhankaya, logged_account);
            System.out.println(" ");
            logged_account.follow(gizemsungu, logged_account);
            System.out.println(" ");
            logged_account.addPost("post4", "three.js is very good.", logged_account.getUsername());
            System.out.println(" ");
            logged_account.addLike("post3", account1);
            System.out.println(" ");
            logged_account.addMessagetoOutbox("message2", logged_account.getAccountid(), account5, "The ring that will rule of them.");
            System.out.println(" ");
        }
        else{
            System.out.println("Account is not exist");
        }
        logged_account = system.control_login(account3.login());

        if(logged_account != null){
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            /* addpost - viewprofilebyself - blockuser -account 4 */
            logged_account.addPost("post5", "This is test message.", logged_account.getUsername());   
            System.out.println(" ");         
            logged_account.viewPostsByself();
            System.out.println(" ");
            logged_account.block_user(account4, logged_account);
            System.out.println(" ");
        }
        else{
            System.out.println("Account is not exist");
        }

        logged_account = system.control_login(account4.login());

        if(logged_account != null){
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            /* addpost - addpost - addlike - viewprofile gizem
            viewprofile account3 - for error - unlike - showactions */
            logged_account.addPost("post6", "Mabel Matix's new album is release.", logged_account.getUsername());   
            System.out.println(" ");         
            logged_account.addPost("post7", "Tony Ann is the best.", logged_account.getUsername());
            System.out.println(" ");
            logged_account.addLike("post3", account1);
            System.out.println(" ");
            logged_account.viewProfile(gizemsungu);
            System.out.println(" ");
            logged_account.viewProfile(account3); // error because of account3 blocked to accont4
            System.out.println(" ");
            logged_account.removeLike("post3", account1);
            System.out.println(" ");
            logged_account.showActions();
            System.out.println(" ");
        }
        else{
            System.out.println("Account is not exist");
        }

        logged_account = system.control_login(account5.login());

        if(logged_account != null){
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            /* addcomment - message to account1 -viewınbox -> account2 mesaj grnmeli
            uncomment - showactions */
            logged_account.addComment("post7", account4, "Nooo Evgeny is better");
            System.out.println(" ");
            logged_account.addMessagetoOutbox("message4", logged_account.getAccountid(), account1, "I want to meet you");
            System.out.println(" ");
            logged_account.viewInbox();
            System.out.println(" ");
            logged_account.addComment("post6", account4, "You are liar");
            System.out.println(" ");
            logged_account.removeComment("post6", account4);
            System.out.println(" ");
            logged_account.showActions();            
            System.out.println(" ");
        }
        else{
            System.out.println("Account is not exist");
        }

        logged_account = system.control_login(account6.login());

        if(logged_account != null){
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            /* follow - addlike - viewposts- account2 - unfollow - showactions */
            logged_account.follow(account5, logged_account);
            System.out.println(" ");
            logged_account.addLike("post7", account4);
            System.out.println(" ");
            logged_account.viewPosts(account2, logged_account); 
            System.out.println(" ");
            logged_account.unfollow(account5, logged_account);
            System.out.println(" ");
            logged_account.showActions();
            System.out.println(" ");
        }
        else{
            System.out.println("Account is not exist");
        }

        logged_account = system.control_login(account7.login());

        if(logged_account != null){
            System.out.println(" ");
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            /* blockuser- account1
            viewprofile account1
            unblockuser-account1
            viewprofile account1
            showactions */
            logged_account.block_user(account1, logged_account);
            System.out.println(" ");
            logged_account.viewProfile(account1);
            System.out.println(" ");
            logged_account.unblock_user(account1, logged_account);
            System.out.println(" ");
            logged_account.viewProfile(account1);
            System.out.println(" ");
            logged_account.showActions();            
            System.out.println(" ");
        }
        else{
            System.out.println("Account is not exist");
        }
        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime) + " millisecond.");
    }
}
