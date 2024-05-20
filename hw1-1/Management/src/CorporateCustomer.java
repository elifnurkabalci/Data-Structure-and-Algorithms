public class CorporateCustomer extends Customer{
    
    private String company_name;
    
    public String get_company_name(){

        return company_name;
    }
    public void set_company_name(String name){
        company_name = name;
    }
    public void print_customer(){
        System.out.println("*** Customer Screen ***");
        System.out.println("Name & Surname: " + get_name() +" " + get_surname());
        System.out.println("Address: " + get_address());
        System.out.println("Phone: " + get_phone());
        System.out.println("ID : " + get_ID());
        System.out.println("Operator ID: " + get_operator_id());
        System.out.println("Company name: " + get_company_name()); // this line difference from retail customer
        if(get_orders()[0] != null) print_orders();
        else{
            System.out.println("This customer does not have any order.");
        }
    }
    

    public CorporateCustomer(String[][] infos, int index){ // constructor
        String personinfo[] = infos[index];
        int ordersIndex = 0;
        Order[] ord = new Order[100]; 
        define_orders(ord);

        //person features
        set_name(personinfo[1]);
        set_surname(personinfo[2]);
        set_address(personinfo[3]);
        set_phone(personinfo[4]);
        set_ID(Integer.parseInt(personinfo[5]));
        set_operator_id(Integer.parseInt(personinfo[6]));
        set_company_name(personinfo[7]);
        set_type(2);
        
        //orders
        for(int i=0; i<index; i++){ // searching orders of the exist customer
            if(infos[i][0].equals("order") && infos[i][5].equals(Integer.toString(get_ID()))){
                Order a = new Order(infos[i]);
                get_orders()[ordersIndex] = a;
                ordersIndex++;        
            }
        }
    }


}
