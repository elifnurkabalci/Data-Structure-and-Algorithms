public class Customer extends Person{
    private Order orders[];
    private int operator_ID;
    private int type; // 1-Retail , 2-Corprote

    public Order[] get_orders(){
        return orders;
    }
    public int get_operator_id(){
        return operator_ID;
    }
    
    public void set_operator_id(int id){
        operator_ID = id;
    }
    public int get_type(){
        return type;
    }
    public void set_type(int t){
        type = t;
    }

    public  void print_customer(){
        System.out.println("*** Customer Screen ***");
        System.out.println("Name & Surname: " + get_name() +" " + get_surname());
        System.out.println("Address: " + get_address());
        System.out.println("Phone: " + get_phone());
        System.out.println("ID : " + get_ID());
        System.out.println("Operator ID: " + get_operator_id());
        if(get_type() == 1){
            if(get_orders()[0] != null) print_orders(); // call print orders if there is a order
            else{
                System.out.println("This customer does not have any order.");
            }
        }
    }
    public void print_orders(){ // print the exist customer's orders
        for(int i=0; i<orders.length; i++){
            if(orders[i] != null){
                System.out.print("Order #" + (i+1) + " => ");
                orders[i].print_order();
            }         
        }
    }
    public void define_orders(Order[] ordr){
        orders = new Order[100];     
    }
 

}
