
public interface Device {
    /* core features */
    void add_device(String category, String name, double price, int quantity);

    void remove_device(String name);

    void update_device(String name, double price, int quantity);

    void display(); /* for all devices lists */

    void id_min_price_device(); /* what is the device have minimum price */

    /* advance features */
    void sort_devices(); /* btw all device and all types, based on price */

    double total_value(); /* calculate total value of inventory */

    void restock_devices(String name, String choose, int num); /* update quantity */

    void inventory_file(); /* exporting the inventory list to a file */

}
