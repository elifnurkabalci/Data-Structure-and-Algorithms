package homework1;

public class TestClass1{
    private Account[] accounts;
    private int num_of_accounts;

    public int get_num_of_accounts(){ return num_of_accounts;}
    public TestClass1(){
        this.num_of_accounts = 0;
        this.accounts = new Account[10];
    }
    public void addAccount(Account account){
        accounts[this.num_of_accounts] = account;
        this.num_of_accounts++;
    }

    public Account control_login(String username){ // for logging account, username exist or not
        for (int i = 0; i < this.num_of_accounts; i++) {
            if (accounts[i].getUsername().equals(username)) {
                return accounts[i];
            }
        }
        return null;
    }   
    
    public static void main(String[] args){
        // social media system control center
        TestClass1 system = new TestClass1();

        System.out.println("Creating accounts...");
        //create accounts
        Account gizemsungu = new Account("gizemsungu", "01/01/2000", "Istanbul", "1-gizemsungu");
        System.out.println("An account with username gizemsungu has been created.");
        
        Account sibelgulmez = new Account("sibelgulmez", "02/02/2001", "Ankara", "2-sibelgulmez");
        System.out.println("An account with username sibelgulmez has been created.");
        
        Account gokhankaya = new Account("gokhankaya", "03/03/2002", "Izmir", "3-gokhankaya");
        System.out.println("An account with username gokhankaya has been created.");

        // add acounts to system
        system.addAccount(gizemsungu);
        system.addAccount(sibelgulmez);
        system.addAccount(gokhankaya);

        // control for loggin account
        Account logged_account = new Account();

        //login as sibel
        logged_account = system.control_login(sibelgulmez.login());
        
        if(logged_account !=null){// if account is exist
            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            // share posts
            gizemsungu.addPost("post1","I like Java", logged_account.getUsername());
            sibelgulmez.addPost("post2", "Java the coffee..", logged_account.getUsername());
            
            System.out.println(" ");

            // needs to say This account is not logged account
            gokhankaya.addPost("post3", "It is not working", logged_account.getUsername());
            System.out.println(" ");

            // follow
            gizemsungu.follow(sibelgulmez, logged_account);
            sibelgulmez.follow(gokhankaya, logged_account);
            System.out.println(" ");

            // logout
            sibelgulmez.logout(logged_account);
            System.out.println(" ");

            //error
            gizemsungu.logout(logged_account);
        }
        else{
            System.out.println("Account is not exist");
        }
        
        //login as gokhan
        logged_account = system.control_login(gokhankaya.login());

        if(logged_account !=null){// if account is exist
            System.out.println(" ");

            System.out.println("Logging into an account: " + logged_account.getUsername());
            System.out.println(" ");

            // view sibel's profile
            gokhankaya.viewProfile(sibelgulmez);
            System.out.println(" ");

            // view sibel's posts
            gokhankaya.viewPosts(sibelgulmez, logged_account);
            System.out.println(" ");

            //error, gizem doesnt have any post
            gokhankaya.viewPosts(gizemsungu,logged_account);
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

            // check # of message in the outbox
            System.out.println("Checking outbox..");
            System.out.println("There is/are " + gizemsungu.getnumberoutbox() + " message(s) in the outbox.");

            // check # of message in the inbox
            System.out.println("Checking inbox..");
            System.out.println("There is/are " + gizemsungu.getnumberofinbox() + " message(s) in the inbox.");
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

            // logout the gizem
            gizemsungu.logout(logged_account);
        }
        else{
            System.out.println("Account is not exist");
        }
    }
}