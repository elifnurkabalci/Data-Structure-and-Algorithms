import java.util.ArrayList;
import java.util.LinkedList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.Collections;
import java.util.Comparator;

public class Inventory implements Device {
    private static LinkedList<ArrayList<Product>> inventory = new LinkedList<>(); /* for oop stndart, it is privite */

    /* getter for inventory list */
    public static LinkedList<ArrayList<Product>> get_inventory() {
        return inventory;
    }

    public static void add(Product product, String category) {
        boolean flag = false; /* this flag check the category is exist or not */
        for (int i = 0; i < inventory.size(); i++) {
            if (get_inventory().get(i).get(0).get_category().equals(category)) {
                /* inventory list's any element match with category */
                get_inventory().get(i).add(product);
                flag = true; /* if category exist change the flag side */
            }
        }
        if (!flag) { /* If this category is not exist in the inventory list */
            ArrayList<Product> arr = new ArrayList<>(); /* create an arraylist */
            arr.add(product); /* add element to arraylist */
            get_inventory().add(arr); /* add arraylist to linkedlist */
        }

    }

    @Override
    public void add_device(String category, String name, double price, int quantity) { /* main add method is this */
        System.out.println(category + ", " + name + ", " + price + ", " + quantity + " amount adding...");

        switch (category) {
            /*
             * I wrote this switch case for decide addign elements category is and create
             * element for it.
             * created element send to helper method (add)
             */
            case "tv": {
                tv a1 = new tv(name, price, quantity);
                add(a1, "tv");
            }
                break;
            case "laptop": {
                laptop a2 = new laptop(name, price, quantity);
                add(a2, "laptop");

            }
                break;
            case "monitor": {
                monitor a3 = new monitor(name, price, quantity);
                add(a3, "monitor");
            }
                break;
            case "smartphone": {
                smartphone a4 = new smartphone(name, price, quantity);
                add(a4, "smartphone");

            }
                break;
            case "mouse": {
                mouse a5 = new mouse(name, price, quantity);
                add(a5, "mouse");
            }
                break;
            default: {
                System.err.println("There is no like category.");
            }
                break;
        }

    }

    @Override
    public void remove_device(String name) {

        /* search find and delete */
        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < inventory.get(i).size(); j++) {
                if (inventory.get(i).get(j).get_name().equals(name)) {
                    inventory.get(i).remove(j);
                    System.out.println("This item deleted: " + name);
                    /* if it's find delete and close the method. */
                    return;
                }
            }
        }
    }

    @Override
    public void update_device(String name, double price, int quantity) {
        boolean flag = false; /* flag check the element is exist or not */
        /* find the device and assign the new infos */
        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < inventory.get(i).size(); j++) {
                Product product = inventory.get(i).get(j); /* I define a product, because calling element very long */
                if (product.get_name().equals(name)) {
                    product.set_price(price);
                    product.set_quantity(quantity);
                    flag = true;
                    System.out.println(product.get_name() + " details updated: Price - " + product.get_price()
                            + ", Quantity - " + product.get_quantity());
                }
            }
        }
        if (flag == false) {
            System.out.println("There is no product that name.");
        }

    }

    @Override
    public void display() {
        System.out.println("\nDevice List: ");
        for (int i = 0; i < get_inventory().size(); i++) {
            for (int j = 0; j < inventory.get(i).size(); j++) {
                System.out.println((i + 1) + ". Category: " + get_inventory().get(i).get(0).get_category());

                Product product = get_inventory().get(i).get(j);
                System.out.println("Name: " + product.get_name());
                System.out.println("Price: " + product.get_price());
                System.out.println("Quantity: " + product.get_quantity() + "\n");
            }
            /*
             * For every element, print details to console
             */
        }
    }

    @Override
    public void id_min_price_device() {
        double min = Double.MAX_VALUE;
        /*
         * initialy assign the min value to max_value, because every element is less
         * than
         */
        Product temp = inventory.getFirst().get(0);
        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < inventory.get(i).size(); j++) {
                Product product = inventory.get(i).get(j);
                if (product.get_price() < min) { /* if elements value less than previouns min value, */
                    min = product.get_price(); /*
                                                * assign the min value to this price, for comparisons for other elements
                                                */
                    temp = product; /* also save the which element is this */
                }
            }

        }
        System.out.println("The cheapest device is:"); /* print */
        System.out.println("Category: " + temp.get_category() + ", Name: " + temp.get_name() +
                ", Price:" + temp.get_price() + ", Quantity: " + temp.get_quantity());
    }

    @Override
    public void sort_devices() {
        System.out.println("Devices sorted by price: ");
        ArrayList<Product> sortedArr = new ArrayList<>(); /* template array for arraylist */

        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < inventory.get(i).size(); j++) {
                sortedArr.add(inventory.get(i).get(j)); /* add all elements to this array, for every category */
            } /* now we have only one arraylist */
        }
        /* I used the collections's sort method */
        Collections.sort(sortedArr, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) { /* this compared the all elements with each others */
                return Double.compare(o1.get_price(), o2.get_price());
            } /* and assign the sortedArr */
        });

        for (int i = 0; i < sortedArr.size(); i++) { /* print */
            System.out.println((i + 1) + ". Category: " + sortedArr.get(i).get_category() +
                    ", Name: " + sortedArr.get(i).get_name() + ", Price: " +
                    sortedArr.get(i).get_price() + ", Quantity: " + sortedArr.get(i).get_quantity());
        }
    }

    @Override
    public double total_value() {
        double sum = 0.0; /* initial sum definition for total value */
        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < inventory.get(i).size(); j++) {
                Product product = inventory.get(i).get(j);
                sum += product.get_price() * product.get_quantity();
                /*
                 * if we have 5 element that prices each 2, we have 10 (5*2) total value.
                 * So ı multiply all elements with #of quantities
                 */
            }
        }

        return sum;
    }

    @Override
    public void restock_devices(String name, String choose, int num) {
        int total = 1;
        /*
         * in last section there is no adding or removing.
         * I directy sum all, total made this.
         */
        if (choose.equals("Add") || choose.equals("add")) {
            total *= 1;
        } else if (choose.equals("Remove") || choose.equals("remove")) {
            total *= -1;
        } else {
            System.err.println("There is no way. Choose Add or Remove.."); /* your entry is wrong */
        }

        boolean flag = false; /* used for name is exist or not */

        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < inventory.get(i).size(); j++) {
                Product product = inventory.get(i).get(j);
                if (product.get_name().equals(name)) {
                    flag = true;
                    if (product.get_quantity() < num && (choose.equals("Remove") || choose.equals("remove"))) {
                        /*
                         * if entered value less than #of quantity, say error and dont remove
                         */
                        System.err.println("Initial quantity amount is less than entered value.");
                    } else {
                        product.set_quantity(product.get_quantity() + (num * total));
                        /*
                         * if add, total will be +1, so +num
                         * if remove, total will be -1, so -num
                         */
                    }
                }
            }
        }
        if (flag == false) {
            System.err.println("There is no product that name.");
        }

    }

    @Override
    public void inventory_file() { /* it write the details to file. */
        String filePath = "inventoryFile.txt";
        try {
            FileWriter file = new FileWriter(filePath); /* file path way */
            BufferedWriter buff = new BufferedWriter(file); /* writing to file with buffer */

            buff.write("Electronics Shop Inventory Report\n");
            buff.write("Generated on: " + LocalDate.now() + "\n");
            buff.write("---------------------------------------\r\n" +
                    "| No. | Category | Name | Price | Quantity |\n" +
                    "---------------------------------------\r\n");
            int counter = 1; /* #of product */
            for (int i = 0; i < inventory.size(); i++) {
                for (int j = 0; j < inventory.get(i).size(); j++) {
                    Product product = inventory.get(i).get(j);
                    buff.write("| " + counter++ + " | " + product.get_category() +
                            " | " + product.get_name() + " | " + product.get_price() +
                            " | " + product.get_quantity() + " |\n");
                }
            }
            buff.write("---------------------------------------\n\n");
            buff.write("Summary:\n");
            buff.write("- Total Number of Devices: " + (counter - 1));
            buff.write("\n- Total Inventory Value: " + total_value());
            buff.write("\n\nEnd of Report\n");

            buff.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
