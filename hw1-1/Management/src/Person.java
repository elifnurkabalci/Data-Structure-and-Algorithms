public class Person {
    private String name;
    private String surname;
    private String address;
    private String phone;
    private int ID;

    // This informations using customers and operators
    // There is no need to be object creation for this class

    public String get_name(){
        return name;
    }
    public String get_surname(){
        return surname;
    }
    public String get_address(){
        return address;
    }
    public String get_phone(){
        return phone;
    }
    public int get_ID(){
        return ID;
    }

    public void set_name(String n){
        name = n;
    }
    public void set_surname(String sn){
        surname = sn;
    }
    public void set_address(String add){
        address = add;
    }
    public void set_phone(String p){
        phone = p;
    }
    public void set_ID(int id){
        ID = id;
    }
}
