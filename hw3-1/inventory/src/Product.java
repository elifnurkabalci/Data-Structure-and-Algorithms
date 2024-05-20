public class Product {
    /*
     * this class base class for products(tv, laptop..)
     * 
     * I designed for clean code
     */
    private String category;
    private String name;
    private double price;
    private int quantity;

    /* getter */
    String get_category() {
        return category;
    }

    String get_name() {
        return name;
    }

    double get_price() {
        return price;
    }

    int get_quantity() {
        return quantity;
    }

    /* setter */
    void set_category(String n) {
        category = n;
    }

    void set_name(String n) {
        name = n;
    }

    void set_price(double p) {
        price = p;
    }

    void set_quantity(int q) {
        quantity = q;
    }
}
