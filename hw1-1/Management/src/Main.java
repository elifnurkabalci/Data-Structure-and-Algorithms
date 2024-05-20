import java.io.File;
import java.util.Scanner;

public class Main {
    private static String[][] allInformations;
    private static Operator[] operators;
    private static RetailCustomer[] rCustomers;
    private static CorporateCustomer[] cCustomers;
    private static int[] IDs;

    public static String[][] get_allInformations(){
        return allInformations;
    }
    public void set_allInformations(String[][] str){
        allInformations = str;
    }

    public static Operator[] get_operators(){
        return operators;
    }
    public void set_operators(Operator[] op){
        operators = op;
    }

    public static RetailCustomer[] get_rCustomers(){
        return rCustomers;
    }
    public void set_rCustomers(RetailCustomer[] r){
        rCustomers = r;
    }

    public static CorporateCustomer[] get_cCustomers(){
        return cCustomers;
    }
    public void set_cCustomers(CorporateCustomer[] c){
        cCustomers = c;
    }

    public static void readFile(){
        allInformations = new String[100][100];
        operators = new Operator[100];

        String fileName = "content.txt";
        File file = new File(fileName);

        if(!file.exists()){
            System.out.println("File cannot found.");
        }
        try{
            Scanner scanner = new Scanner(file);
            int j=0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();

                if(!line.contains(";")){
                    //there is no ; in line
                    //if there is no; dont look at this line, skip it
                }
                else{
                    String[] str = line.split(";", -1); 
                    // str is every part of line
                    for(int i=0; i<str.length; i++){ 
                        allInformations[j][i] = str[i];
                    }
                    j++;
                }
            }
            scanner.close();
        }catch(Exception e ){
            e.printStackTrace();
        }
        
    }
    public static int StringValid(String str){
        int flag =1;

        if(str == null) flag =0; // missing data 
        if(str.isEmpty()) flag = 0;
        
        for(char c : str.toCharArray()){ 
            if(!Character.isLetter(c)){
                flag = 0; 
            }
        }

        return flag;
    }
    public static int IntValid(String value){
        if(value == null){ // for missing coulmn
            return 0;
        }
        if(!value.matches("\\d+")){ // value is a int and it's convertable
            return 0;
        }
        if(Integer.parseInt(value) <= 0 || Integer.parseInt(value) > Integer.MAX_VALUE){ 
            return 0;
        }
        return 1;
    }
    public static int existID(int id){
        for(int i=0; i<IDs.length; i++){
            if(IDs[i] == id){
                return 0;
            }
        }
        return 1;
    }
    public static void checkTheInfos(){
        String[][] str = new String[100][100];
        int counterofStr =0; 
        int flag = 1;
        int idcounter = 0;

        for(int i=0; i<allInformations.length; i++){
            if(allInformations[i][0] != null && allInformations[i][0].equals("order")){
                flag *= StringValid(allInformations[i][1]); // product name
                flag *= IntValid(allInformations[i][2]); // count
                flag *= IntValid(allInformations[i][3]); // total price
                if(IntValid(allInformations[i][4]) == 1){
                    if(Integer.parseInt(allInformations[i][4]) <0 || 
                Integer.parseInt(allInformations[i][4]) >3) flag *=0; // status
                }
                
                flag *= IntValid(allInformations[i][5]); //customer id
                if(flag==0){
                    for(int j=0; j<allInformations.length; j++){
                        if(j != i){
                            str[counterofStr++] = allInformations[j];
                        }
                    }
                    allInformations = str;
                    counterofStr = 0;
                }
                flag=1;
            }
            else if(allInformations[i][0] != null && allInformations[i][0].equals("retail_customer")){            
                flag *= StringValid(allInformations[i][1]); // name
                flag *= StringValid(allInformations[i][2]); //surname
                flag *= StringValid(allInformations[i][3]); // address
                for(char c: allInformations[i][4].toCharArray()){ // phone 
                    if(!(c == '+' || (c >= '0' && c <= '9'))){
                        flag*=0;
                    }
                } 
                flag *= IntValid(allInformations[i][5]); // id
                if(existID(Integer.parseInt(allInformations[i][5])) == 1) IDs[idcounter++] = Integer.parseInt(allInformations[i][5]);
                flag *= IntValid(allInformations[i][6]); // operator id
                if(allInformations[i][5] == allInformations[i][6]) flag *=0; // id and operator id cannot be same

                flag=1;
            }
            else if(allInformations[i][0] != null && allInformations[i][0].equals("corporate_customer")){ 
                flag *= StringValid(allInformations[i][1]); // name
                flag *= StringValid(allInformations[i][2]); //surname
                flag *= StringValid(allInformations[i][3]); // address
                for(char c: allInformations[i][4].toCharArray()){ // phone 
                    if(!(c == '+' || (c >= '0' && c <= '9'))){
                        flag*=0;
                    }
                } 
                flag *= IntValid(allInformations[i][5]); // id
                if(existID(Integer.parseInt(allInformations[i][5])) == 1) IDs[idcounter++] = Integer.parseInt(allInformations[i][5]);
                
                flag *= IntValid(allInformations[i][6]); // operator id
                flag *= StringValid(allInformations[i][7]); // company name
                if(allInformations[i][5] == allInformations[i][6]) flag *=0; // id and operator id cannot be same
                
                flag=1;
            }
            else if(allInformations[i][0] != null && allInformations[i][0].equals("operator")){
                flag *= StringValid(allInformations[i][1]); // name
                flag *= StringValid(allInformations[i][2]); //surname
                flag *= StringValid(allInformations[i][3]); // address
                for(char c: allInformations[i][4].toCharArray()){ // phone 
                    if(!(c == '+' || (c >= '0' && c <= '9'))){
                        flag*=0;
                    }
                } 
                flag *= IntValid(allInformations[i][5]); // id
                if(existID(Integer.parseInt(allInformations[i][5])) == 1) IDs[idcounter++] = Integer.parseInt(allInformations[i][5]);
                
                flag *= IntValid(allInformations[i][6]); //wage
                
                flag=1;
            }
            
            // else, it become invisible
        }
        
    }
    public static void createObject(){
        int indexofOperator = 0;
        int indexofrCustomers = 0;
        int indexofcCustomers = 0;
        rCustomers = new RetailCustomer[100];
        cCustomers = new CorporateCustomer[100];

        for(int i=0; i<allInformations.length; i++){
            // This are search every operator and customers, and define the object
            if(allInformations[i][0] != null && allInformations[i][0].equals("retail_customer")){
                RetailCustomer a = new RetailCustomer(get_allInformations(), i);
                rCustomers[indexofrCustomers] = a;
                indexofrCustomers++;
            }
            else if(allInformations[i][0] != null && allInformations[i][0].equals("corporate_customer")){
                
                CorporateCustomer a = new CorporateCustomer(get_allInformations(), i);
                cCustomers[indexofcCustomers] = a;
                indexofcCustomers++;
            }
            else if(allInformations[i][0] != null && allInformations[i][0].equals("operator")){
                
                Operator a = new Operator(get_allInformations(), i);
                operators[indexofOperator] = a;
                indexofOperator++;
            }

        }
    }
    public static void inputFunc(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your ID...");
        String getid = sc.nextLine();
        if(IntValid(getid) == 0){
            System.out.println("Input is not valid.");
        }
        else{
            int flag=0;
            for(int i=0; i<operators.length; i++){
                if(operators[i] != null && operators[i].get_ID() == Integer.parseInt(getid) && flag==0){
                    operators[i].print_operator();
                    flag=1;
                }
            }
            if(flag==0){ // if ID connet find in operators, search in retail customers
                for(int i=0; i<rCustomers.length; i++){
                    if(rCustomers[i] != null && rCustomers[i].get_ID() == Integer.parseInt(getid) && flag==0){
                        rCustomers[i].print_customer();
                        flag=1;
                    }
                }
            }
            if(flag==0){ // we need to search in corporate customers
                for(int i=0; i<cCustomers.length; i++){
                    if(cCustomers[i] != null && cCustomers[i].get_ID() == Integer.parseInt(getid) && flag==0){
                        cCustomers[i].print_customer();
                        flag=1;
                    }
                }
            }
            if(flag==0){ // we cannot find any person 
                System.out.println("There is no person that id is equal to input.");
            }
        }
        
        sc.close();
    }
    
    
    public static void main(String[] args) throws Exception {
        IDs = new int[100];
        readFile();
        try{
            checkTheInfos();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        
        createObject();
        inputFunc();
        
    }   
}
