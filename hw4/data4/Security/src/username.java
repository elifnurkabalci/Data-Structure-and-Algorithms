import java.util.Stack;

public class username {
    private String name;

     public String getName() {
         return name;
     }
    public boolean usernameValid(char u){
        return Character.isLetter(u);

    }
    public void fillStack(Stack<Character> s, String u){
        for(int i=0; i<u.length(); i++){
            s.push(u.charAt(i));
        }
    }
    public username(String username){
        this.name = username;

    }
    public boolean checkIfValidUsername(String username, int i){ // recursive
        if(username.length() < 1){
            System.out.println("Username length is not enough: username validation");
            return false;
        }
        if(i==username.length()){
            System.out.println("Username is suitable: username validation");
            return true;
        }
        if(usernameValid(username.charAt(i))){ //
            i++; // counter
            return checkIfValidUsername(username, i);
        }
        else{
            System.out.println("Username consist of different types: username validation");
            return false;
        }
    }
}
