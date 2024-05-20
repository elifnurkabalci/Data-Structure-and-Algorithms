public class Order {
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;

    public Order(String[] infos){ // constructor
        set_product_name(infos[1]);
        set_count(Integer.parseInt(infos[2])); 
        set_total_price(Integer.parseInt(infos[3]));
        set_status(Integer.parseInt(infos[4]));
        set_customer_ID(Integer.parseInt(infos[5]));
    }
    public String get_product_name(){
        return product_name;
    }
    public int get_count(){
        return count;
    }
    public int get_total_price(){
        return total_price;
    }
    public int get_status(){
        return status;
    }
    public int get_customer_ID(){
        return customer_ID;
    }

    public void set_product_name(String name){
        product_name = name;
    }
    public void set_count(int cnt){
        count = cnt;
    }
    public void set_total_price(int price){
        total_price = price;
    }
    public void set_status(int st){
        status = st;
    }
    public void set_customer_ID(int id){
        customer_ID = id;
    }

    public String status_turn(int st){ // assign with pdf informations
        if(st==0){
            return "Initialized";
        }
        else if(st==1){
            return "Processing";
        }
        else if(st==2){
            return "Completed";
        }
        else if(st==3){
            return "Cancelled";
        }
        else{
            return "Status wrong";
        }
    }
    public void print_order(){
        System.out.println("Product name: " + get_product_name() + 
                        "- Count: " + get_count() +
                        "- Total price: " + get_total_price() +
                        "- Status: " + status_turn(get_status()));
    }

}
