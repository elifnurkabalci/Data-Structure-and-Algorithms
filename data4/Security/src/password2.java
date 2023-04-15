public class password2 {
    private int p;

    public int getP() {
        return p;
    }
    public boolean passwordValid(){
        if(getP()>=10 && getP()<=10000){
            System.out.println("Password2 is suitable");
            return true;
        }
        else{
            System.out.println("Password2 is not suitable");
            return false;
        }
    }
    public boolean exactDivisior(int [] denominations){ // recursive

        return true;
    }
    public password2(int password){
        this.p = password;
    }    
}
