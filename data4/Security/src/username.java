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
    public boolean sameUsername(String username){
        Stack<Character> usernameStack = new Stack<>();
        Stack<Character> nameStack = new Stack<>();
        
        fillStack(usernameStack, username);
        fillStack(nameStack, getName());

        if(usernameStack.size() == nameStack.size()){
            for(int i=0; i<nameStack.size(); i++){
                if(usernameStack.pop() != nameStack.pop()){
                    System.out.println("Username is not same : reason different character");
                    return false;
                }
            }
            System.out.println("Username is suitable");
            return true;
        }
        else{
            System.out.println("Username is not same: reason size");
            return false;
        }
    }
    public username(String username){
        this.name = username;

    }
}
