public class RetailCustomer extends Customer{
    public RetailCustomer(String[][] infos, int index){ // constructor

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
        set_type(1); // this means this customer's type is retail

        //orders
        for(int i=0; i<index; i++){ // this area search the orders of exist customer
            if(infos[i][0] != null && infos[i][0].equals("order") && infos[i][5].equals(Integer.toString(get_ID()))){
                Order a = new Order(infos[i]);
                get_orders()[ordersIndex] = a;
                ordersIndex++;        

            }
        }
        
    }
}
