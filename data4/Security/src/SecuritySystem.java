public class SecuritySystem {
    private username u;
    private password1 p1;
    private password2 p2;
    private int [] denominations;
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
        && isExactDivision(getP2().getP(), denominations));
    }
    public boolean checkIfValidUsername(String username, int i){ // recursive
        if(username.length() < 1){
            System.out.println("Username length is not enough");
            return false;
        }
        if(i==username.length()){
            System.out.println("Username is suitable");
            return true;
        }
        if(u.usernameValid(username.charAt(i))){ // 
            i++; // counter
            return checkIfValidUsername(username, i);
        }
        else{
            System.out.println("Username consist of different types");
            return false;
        }
    }
    public boolean containsUserNameSpirit(String username, String password1){ // stack in functions
        return (u.sameUsername(username) && p1.samePassword(password1));
    }
    public boolean isBalancedPassword(String password1){ // stack

        return true;
    }
    public boolean isPalindromePossible(String password1){ // recursive
        return p1.isPalindrome(password1); 
    }
    public boolean isExactDivision(int password2, int [] denominations){ // recursive
        return p2.exactDivisior(getDenominations());
    }
    
    public static void main(String[] args) {
        // create system element
        
    }
}
