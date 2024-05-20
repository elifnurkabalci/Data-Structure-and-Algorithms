import java.util.Scanner;

public class mainTest extends Inventory {

    public static void menu() {
        Inventory a = new Inventory();
        /*
         * I create a inventory object for calling the methods
         */
        Scanner scanner = new Scanner(System.in); /* scanner for getting inputs from user */
        int choose = -1; /* this is indicator for user's menu choose */

        System.out.println("Welcome to the Electronics Inventory Management System!");

        while (choose != 0) { /* It will run again again until you enter the 0 (Exit). */
            System.out.println("\n\n\nPlease select an option:");
            System.out.println("1. Add a new device"); /* it calles ad_device method */
            System.out.println("2. Remove a device"); /* it called remove_device method */
            System.out.println("3. Update device details"); /* it called upgrade device method */
            System.out.println("4. List all devices"); /* it will display all the list and details */
            System.out.println("5. Find the cheapest device"); /* it will find the device that have min price */
            System.out.println("6. Sort device by price"); /* this will sort the list by price */
            System.out.println("7. Calculate total inventory value"); /*
                                                                       * this will calculate the total value of the
                                                                       * inventory.
                                                                       */
            System.out.println("8. Restock a device"); /* it will change the device details */
            System.out.println("9. Export inventory report"); /* it will write details about inventory to file */
            System.out.println("0. Exit"); /* it will exit the menu */

            choose = scanner.nextInt();
            scanner.nextLine(); /*
                                 * in scanner, calling nextInt and after calling nextLine create error, so this
                                 * line will using for reduce the errors
                                 */

            switch (choose) { /* check the choose */
                case 1: {
                    System.out.print("Enter category name:");
                    String category = scanner.nextLine();
                    System.out.print("Enter device name:");
                    String name = scanner.nextLine();
                    System.out.print("Enter price:");
                    String price = scanner.nextLine();
                    System.out.print("Enter quantity:");
                    String quantity = scanner.nextLine();
                    /*
                     * I took the every element from user that string
                     */
                    a.add_device(category, name, Double.parseDouble(price), Integer.parseInt(quantity));
                    /* calling add device needs to be parsing for price and quantity values */
                }
                    break;
                case 2: {
                    System.out.println("Enter device name that will be remove:");
                    String name = scanner.nextLine(); /* take device name from user */
                    a.remove_device(name);
                }
                    break;
                case 3: {
                    System.out.println("Enter the name of the device to update:");
                    String name = scanner.nextLine();
                    System.out.println("Enter new price (leave blank to keep current price):");
                    String price = scanner.nextLine();
                    System.out.println("Enter new quantity (leave blank to keep current quantity):");
                    String quantity = scanner.nextLine();
                    a.update_device(name, Double.parseDouble(price), Integer.parseInt(quantity));
                }
                    break;
                case 4: {
                    a.display();
                }
                    break;
                case 5: {
                    a.id_min_price_device();
                }
                    break;
                case 6: {
                    a.sort_devices();
                }
                    break;
                case 7: {
                    System.out.println("Total inventory value: " + a.total_value());
                }
                    break;
                case 8: {
                    System.out.println("Enter the name of the device to restock:");
                    String name = scanner.nextLine();
                    System.out.println("Do you want to add or remove stock? (Add/Remove):");
                    String choose1 = scanner.nextLine();

                    System.out.println("Enter the quantity to " + choose1 + ": ");
                    int num = scanner.nextInt();
                    scanner.nextLine(); /* cleaning for using nextLine after using nextInt */
                    a.restock_devices(name, choose1, num);
                }
                    break;
                case 9: {
                    a.inventory_file();
                }
                    break;
                case 0: {
                    System.out.println("Exit");
                }
                    break;
                default: {
                    System.err.println("There is no section pls enter again.");
                }
                    break;
            }
        }

        scanner.close();
    }

    public static void test() { /* I wrote this method for checking the working methods. */
        System.out.println("Initialy otomatic test section: ");

        Inventory a = new Inventory();

        a.add_device("smartphone", "samsung1", 500.0, 180);
        a.add_device("smartphone", "samsung2", 1555.0, 50);
        a.add_device("tv", "tv1", 3500.0, 150);

        a.display();

        a.id_min_price_device();

        System.out.println("Total inventory value: " + a.total_value());

        a.inventory_file();

        a.sort_devices();

        a.display();

        a.restock_devices("tv1", "add", 50);

        a.display();

        a.restock_devices("tv1", "remove", 50);

        a.display();

        a.remove_device("tv1");

        a.display();

    }

    public static void main(String[] args) throws Exception {

        // test();
        menu();
    }

}
