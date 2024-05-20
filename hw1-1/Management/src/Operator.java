public class Operator extends Person{
    private int wage;
    private Customer[] customers;

    public int get_wage(){
        return wage;
    }
    public Customer[] get_customers(){
        return customers;
    }

    public void set_wage(int w){
        wage = w;
    }
    public int Comparer(String str1, String str2){
        if(str1.length() != str2.length()){
            return 0;
        }
        for(int i=0; i<str1.length(); i++){
            char c1= str1.charAt(i);
            char c2 = str2.charAt(i);
            if(c1 != c2){
                return 0;
            }
        }
        return 1;
    }
    public Operator(String[][] infos, int index){ // constructor
        Customer[] cst = new Customer[100];
        int indexofCustomers = 0;
        String personinfo[] = infos[index];
        define_customers(cst);
        //person
        set_name(personinfo[1]);
        set_surname(personinfo[2]);
        set_address(personinfo[3]);
        set_phone(personinfo[4]);
        set_ID(Integer.parseInt(personinfo[5]));
        set_wage(Integer.parseInt(personinfo[6]));

        String str1= "retail_customer";
        String id = Integer.toString(get_ID());

        //customer
        for(int i=0; i<index; i++){
            if(infos[i][0] != null && infos[i][0].equals(str1) && Comparer(infos[i][6],id) == 1){  // definitions of the operator's customers
                RetailCustomer a = new RetailCustomer(infos, i);
                get_customers()[indexofCustomers] = a;
                indexofCustomers++;
            }
            if(infos[i][0] != null && infos[i][0].equals("corporate_customer") && infos[i][6].equals(Integer.toString(get_ID()))){
                CorporateCustomer a = new CorporateCustomer(infos, i);
                get_customers()[indexofCustomers] = a;
                indexofCustomers++;
            }
        }    
        

    }
    public void print_operator(){
        System.out.println("*** Operator Screen ***");
        System.out.println("Name & Surname: " + get_name() +" " + get_surname());
        System.out.println("Address: " + get_address());
        System.out.println("Phone: " + get_phone());
        System.out.println("ID : " + get_ID());
        System.out.println("Wage: " + get_wage());
        System.out.println("-----------------------");
        print_customers();
    }
    public void print_customers(){ // print operator's customers information and call indirectly orders also
        String a;
        int i=0;
        if(customers[0] == null){
            System.out.println("This operator doesnt have any customer.");
        }
        while(customers[i] != null){
            if(customers[i].get_type() == 1){ // There is using type of customer
                a = "a retail customer";
            }
            else{
                a = "a corporate customer";
            }
            
            System.out.println("Customer #" + (i+1) + ": " + a); 
            customers[i].print_customer();
            System.out.println("-----------------------------");
            i++;
        }
    }
    public void define_customers(Customer[] c){
        customers = new Customer[100];
    
    }
    
}
