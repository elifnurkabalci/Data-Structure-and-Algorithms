import java.util.Scanner;

public class SecuritySystem {
    private username u;
    private password1 p1;
    private password2 p2;
    private int [] denominations;

    public void setU(username u) {
        this.u = u;
    }

    public void setP1(password1 p1) {
        this.p1 = p1;
    }

    public void setP2(password2 p2) {
        this.p2 = p2;
    }

    public username getU() {
        return u;
    }
    public password1 getP1() {
        return p1;
    }
    public password2 getP2() {
        return p2;
    }
    public int[] getDenominations() {
        return denominations;
    }
    public void setDenominations(int[] denominations) {
        this.denominations = denominations;
    }
    public SecuritySystem(String user, String pass1, int pass2){
        this.u = new username(user);
        this.p1 = new password1(pass1);
        this.p2 = new password2(pass2);
    }
    public boolean control(){
        return (p2.passwordValid()  && checkIfValidUsername(getU().getName(), 0)
        && containsUserNameSpirit(getU().getName(), getP1().getPassword()) &&
        isBalancedPassword(getP1().getPassword()) && isPalindromePossible(getP1().getPassword())
        && isExactDivision(getP2().getP(), getDenominations()));
    }
    public boolean checkIfValidUsername(String username, int i){ // recursive
        if(username.length() < 1){ // empty username 
            System.out.println("Username length is not enough: username validation");
            return false;
        }
        if(i==username.length()){ // reach goal
            System.out.println("Username is suitable: username validation");
            return true;
        }
        if(u.usernameValid(username.charAt(i))){ 
            i++; // recursion counter
            return checkIfValidUsername(username, i);
        }
        else{  // find nonletter character
            System.out.println("Username consist of different types: username validation");
            return false;
        }
    }
    public boolean containsUserNameSpirit(String username, String password1){ // stack in functions
        return p1.validUsernamePassword(username);
    }
    public boolean isBalancedPassword(String password1){ // stack
        return p1.balanceBracket(password1);
    }
    public boolean isPalindromePossible(String password1){ // recursive
        return p1.isPalindrome(password1); 
    }
    public boolean isExactDivision(int password2, int [] denominations){ // recursive
        int temp=0;
        for(int i=0; i<denominations.length; i++){
            if(password2 < denominations[i]){
                temp++; // if every element of denominator array greater than password, calculation part cannot call
            } 
        }
        if(temp != denominations.length){
            if(p2.exactDivisior(password2, getDenominations(), 0)){ // call
                System.out.println("Password2 is suitable: exactDivision");
                return true;
            }
            else{
                System.out.println("Password2 is not suitable: exactDivision");
                return false;
            }
        } 
        else{
            System.out.println("Password2 is less than every element of denominations array, so it cannot be calculate.");
            return false;
        }
    }
    public static void driverHelper(String username, String pass1, int pass2){
        System.out.println("Security system object is created..");
        SecuritySystem s = new SecuritySystem(username, pass1, pass2); // creation
        System.out.println("Denominations is defined as [4, 17, 29]");
        int arr[]= new int[]{4, 17, 29}; // it can be changable
        s.setDenominations(arr);
        System.out.println("Test: Inputs:");
        System.out.println("username: " + username); 
        System.out.println("password1: " + pass1);
        System.out.println("password2: " + pass2);

        if(s.control()) // tests
            System.out.println("Door will be open, Please wait..");
        else
            System.out.println("Informations are not match please try again.");

        System.out.println("\n");
    }
    public static void Driver(){
        driverHelper("gizem", "{[(abacaba)]}", 75);
        driverHelper("gizem", "{[(abacaba)]}", 35);
        driverHelper("gizem", "{ab[bac]caba}", 54);
        driverHelper("gokhan", "{ab[bac]caba}", 75);
        driverHelper("gokhan", "{[(abacaba)]}", 35);
        driverHelper("gokhan", "[a]bcd(cb)a", 54);
        driverHelper("sibelgulmez", "{(abba)cac}", 75);
        driverHelper("elifnur", ")abc(cba", 35);
        driverHelper("kabalci", "{[(abacaba)]}", 54);
        driverHelper("sibelgulmez", "{(abba)cac}", 75);
        driverHelper("sibelgulmez", "{[(abacaba)]}", 35);
        driverHelper("sibelgulmez", "{[(ecarcar)]}", 54);

    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean test= false;
        int arr[]= new int[]{4, 17, 29};
        System.out.print("Driver test(1) or write with hand(2)?");
        int a = input.nextInt();
        input.nextLine();

        if(a==1){  // driver part
            System.out.println("\n");
            Driver();
        }
        else{ // user entered part
            while(test==false){
                System.out.print("Enter your username: ");
                String username = input.nextLine();
                System.out.print("Enter your password1: ");
                String password1 = input.nextLine();
                System.out.print("Enter password2: ");
                int password2 = input.nextInt();
                input.nextLine();
                SecuritySystem s = new SecuritySystem(username, password1, password2); // creation
                s.setDenominations(arr);
                if(s.control()){ // tests
                    test=true;
                    System.out.println("Door will be open, Please wait..");
                }
                else{
                    System.out.println("Informations are not match please try again.");
                }

            }
            input.close();
        }
        
       
    }
}
