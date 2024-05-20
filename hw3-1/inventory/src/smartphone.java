public class smartphone extends Product {
    public smartphone(String n, Double p, int q) {
        /*
         * it used product's setter
         * This setter's are not static so all products use same thing
         */
        set_name(n);
        set_price(p);
        set_quantity(q);
        set_category("smartphone");
    }
}
