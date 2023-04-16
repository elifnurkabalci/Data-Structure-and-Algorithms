import java.util.Stack;

public class password1 {
    private String password;
    public password1(String password){
        this.password = password;
    }
    public enum Brackets{ // brakets declarations
        LEFT_BRACE('{'),
        RIGHT_BRACE('}'),
        LEFT_BRACKET('['),
        RIGHT_BRACKET(']'),
        LEFT_PARENTHESIS('('),
        RIGHT_PARENTHESIS(')');

        private final char bracketChar;

        Brackets(char bracketChar) {
            this.bracketChar = bracketChar;
        }
        public char getBracketChar() {
            return bracketChar;
        }
    }
    public String getPassword() {
        return password;
    }
    public static void fillStack(Stack<Character> s, String p) { // fill stack
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            s.push(Character.toLowerCase(c));
        }
    }
    public void inversefillStack(Stack<Character> s, String p){ // inverse fill stack
        for (int i = p.length() - 1; i >= 0; i--) {
            s.push(p.charAt(i));
        }
    }
    public Stack<Character> inverseofStack(Stack<Character> stack){ // inverse of stack
        Stack<Character> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            char element = stack.pop();
            tempStack.push(element);
        }
        return tempStack;
    }
    public boolean recursionPalindrome(Stack<Character> p1, Stack<Character> tStack, int i){ // recursion part of pallindrome
        if(p1.isEmpty() && tStack.isEmpty()){
            System.out.println("Password1 is suitable: pallindrome");
            return true;
        }
        else{
            if(p1.isEmpty() || tStack.isEmpty()){
                System.out.println("Password1 is not suitable: palindrome");
                return false;
            }
            else if(p1.pop() != tStack.pop()){
                System.out.println("Password1 is not suitable: palindrome");
                return false;
            }
            else{
                return recursionPalindrome(p1, tStack, i--);
            }
        }
    }
    public boolean isPalindrome(String password){ // control part for pallindrome
        Stack<Character> p1 = new Stack<>();
        for(int i=0; i<password.length(); i++){
            if(Character.isLetter(password.charAt(i))) p1.push(password.charAt(i));
            // for delete the bracets , take only letters
        }
        Stack<Character> tStack = new Stack<>();
        tStack.addAll(p1);
        tStack = inverseofStack(tStack);
        return recursionPalindrome(p1, tStack, p1.size()); 
    }

    public boolean validUsernamePassword(String name){
        Stack<Character> userS = new Stack<>();
        fillStack(userS, name);

        for (int i=0; i<getPassword().length(); i++) {
            char c = getPassword().charAt(i);

            if (userS.contains(Character.toLowerCase(c))) {
                System.out.println("Password is suitable: Username-password match");
                return true;
            }
        }
        System.out.println("Password is not suitable: Username-password match");
        return false;
    }
    public boolean balanceBracket(String password){
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<password.length(); i++) {
            char ch = password.charAt(i);
            if (ch == Brackets.LEFT_PARENTHESIS.getBracketChar() ||
                ch == Brackets.LEFT_BRACE.getBracketChar() ||
                ch == Brackets.LEFT_BRACKET.getBracketChar()) {
                stack.push(ch);
            } else if (ch == Brackets.RIGHT_PARENTHESIS.getBracketChar() ||
                       ch == Brackets.RIGHT_BRACE.getBracketChar() ||
                       ch == Brackets.RIGHT_BRACKET.getBracketChar()) {
                if (stack.isEmpty()) {
                    System.out.println("Password is not suitable: balance bracket");
                    return false;
                }
                char openBracket = stack.pop();
                if ((ch == Brackets.RIGHT_PARENTHESIS.getBracketChar() &&
                    openBracket != Brackets.LEFT_PARENTHESIS.getBracketChar()) ||
                    (ch == Brackets.RIGHT_BRACE.getBracketChar() && 
                    openBracket != Brackets.LEFT_BRACE.getBracketChar()) ||
                    (ch == Brackets.RIGHT_BRACKET.getBracketChar() &&
                    openBracket != Brackets.LEFT_BRACKET.getBracketChar())) {
                    System.out.println("Password is not suitable: balance bracket");   
                    return false;
                }
            }
        }
        if(stack.isEmpty()){
            System.out.println("Password is suitable: balance bracket");  
            return true;
        }
        else{
            System.out.println("Password is not suitable: balance bracket");  
            return false;
        }

    }

}
