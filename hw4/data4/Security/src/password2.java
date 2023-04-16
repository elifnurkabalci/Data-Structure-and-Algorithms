public class password2 {
    private int p;
    public password2(int password){
        this.p = password;
    }
    public int getP() {
        return p;
    }
    public boolean passwordValid(){
        if(getP()>=10 && getP()<=10000){
            System.out.println("Password2 is suitable: validation");
            return true;
        }
        else{
            System.out.println("Password2 is not suitable: validation");
            return false;
        }
    }
    public boolean exactDivisior(int pw, int [] denominations, int index){ // recursive
        if (pw == 0) { 
            // number reach the goal
            return true;
        }
        if (index == denominations.length || pw < 0) { 
            // if number of toure is equal the denominations array's size
            // but already cannot find the summation
            return false;
        }
        int maxm = pw / denominations[index]; // maximum multipler
        for (int i = 0; i <= maxm; i++) {
            int pwnew = pw - i * denominations[index];
            if (exactDivisior(pwnew, denominations, index + 1)) {
                return true;
            }
        }
        return false;       
    }

}
