
//import java.security.Timestamp;
import java.util.*;

public class Main {
    public static SocialNetworkGraph network = new SocialNetworkGraph();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
        String name1 = "", name2 = "", hoby = "";
        int age1 = 0, suggestion = 0;
        List<String> hobbies;
        String time1 = "", time2 = "";

        while (choose != 8) {
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.println("Please select an option:");
            choose = Integer.parseInt(scanner.nextLine());

            switch (choose) {
                case 1:
                    System.out.print("Enter name: ");
                    name1 = scanner.nextLine();
                    System.out.println("Enger age: ");
                    age1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter hobbies (comma-separated): ");
                    hoby = scanner.nextLine();
                    hobbies = new ArrayList<>(Arrays.asList(hoby.split(",")));
                    network.addPerson(name1, age1, hobbies);
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    name1 = scanner.nextLine();

                    System.out.print("Enter first person's timestamp: ");
                    time1 = scanner.nextLine();

                    network.removeperson(name1, time1);
                    break;
                case 3:
                    System.out.print("Enter first person's name: ");
                    name1 = scanner.nextLine();

                    System.out.print("Enter first person's timestamp: ");
                    time1 = scanner.nextLine();

                    System.out.print("Enter second person's name: ");
                    name2 = scanner.nextLine();

                    System.out.print("Enter second person's timestamp: ");
                    time2 = scanner.nextLine();

                    network.addFriendship(name1, name2, time1, time2);
                    break;
                case 4:
                    System.out.print("Enter first person's name: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter second person's name: ");
                    name2 = scanner.nextLine();
                    network.removeFriendship(name1, name2, time1, time2);
                    break;
                case 5:
                    System.out.print("Enter first person's name: ");
                    name1 = scanner.nextLine();

                    System.out.print("Enter first person's timestamp: ");
                    time1 = scanner.nextLine();

                    System.out.print("Enter second person's name: ");
                    name2 = scanner.nextLine();

                    System.out.print("Enter second person's timestamp: ");
                    time2 = scanner.nextLine();

                    network.findShortestPath(name1, name2, time1, time2);
                    break;
                case 6:
                    System.out.print("Enter first person's name: ");
                    name1 = scanner.nextLine();

                    System.out.print("Enter first person's timestamp: ");
                    time1 = scanner.nextLine();

                    System.out.print("Enter maximum number of friends to suggest: ");
                    suggestion = Integer.parseInt(scanner.nextLine());
                    network.suggestFriends(name1, suggestion, time1);
                    break;
                case 7:
                    System.out.println("Counting clusters int the social network");
                    System.out.println("Number of clusters found: " + network.countClusters());
                    break;
                case 8:
                    System.out.println("Exiting..");
                    break;
                default:
                    System.out.println("Command is wrong pls enter again.");
                    break;
            }
        }
        scanner.close();

    }

    public static void test() {
        /* Adding 10 people */
        network.addPerson("John Doe", 25, Arrays.asList("reading", "hiking", "cooking"));
        network.addPerson("Jane Smith", 22, Arrays.asList("swimming", "cooking"));
        network.addPerson("Alice Johnson", 27, Arrays.asList("hiking", "painting"));
        network.addPerson("Bob Brown", 30, Arrays.asList("reading", "swimming"));
        network.addPerson("Emily Davis", 28, Arrays.asList("running", "swimming"));
        network.addPerson("Frank Wilson", 26, Arrays.asList("reading", "hiking"));
        network.addPerson("George White", 23, Arrays.asList("gaming", "swimming"));
        network.addPerson("Hannah Green", 29, Arrays.asList("reading", "cooking"));
        network.addPerson("Irene Black", 24, Arrays.asList("hiking", "dancing"));
        network.addPerson("Jack Brown", 31, Arrays.asList("running", "gaming"));

        System.out.println("After adding 10 people");
        network.displayPeople();

        /************************************************************************************ */
        /* Adding friendships */
        network.addFriendship("John Doe", "Jane Smith", network.people.get("John Doe").getTime(),
                network.people.get("Jane Smith").getTime());
        network.addFriendship("John Doe", "Alice Johnson", network.people.get("John Doe").getTime(),
                network.people.get("Alice Johnson").getTime());
        network.addFriendship("Jane Smith", "Bob Brown", network.people.get("Jane Smith").getTime(),
                network.people.get("Bob Brown").getTime());
        network.addFriendship("Emily Davis", "Frank Wilson", network.people.get("Emily Davis").getTime(),
                network.people.get("Frank Wilson").getTime());
        network.addFriendship("George White", "Hannah Green", network.people.get("George White").getTime(),
                network.people.get("Hannah Green").getTime());
        network.addFriendship("Irene Black", "Jack Brown", network.people.get("Irene Black").getTime(),
                network.people.get("Jack Brown").getTime());
        network.addFriendship("John Doe", "George White", network.people.get("John Doe").getTime(),
                network.people.get("George White").getTime());
        network.addFriendship("Emily Davis", "Hannah Green", network.people.get("Emily Davis").getTime(),
                network.people.get("Hannah Green").getTime());

        System.out.println("After adding friendships");
        network.displayFriendships();
        /*************************************************************************************** */

        /* Removing a person */
        network.removeperson("Jack Brown", network.people.get("Jack Brown").getTime());

        System.out.println("After deleting Jack Brown");
        network.displayPeople();
        /*************************************************************************************** */

        /* Removing a friendship */
        network.removeFriendship("John Doe", "Jane Smith", network.people.get("John Doe").getTime(),
                network.people.get("Jane Smith").getTime());

        System.out.println("After removing friendships - John Due - Jane Smith");
        network.displayFriendships();

        /************************************************************************************** */
        /* Finding shortest path - Cannot find */
        network.findShortestPath("John Doe", "Bob Brown", network.people.get("John Doe").getTime(),
                network.people.get("Bob Brown").getTime());

        /* Finding shortest path - can find */
        network.findShortestPath("John Doe", "Alice Johnson", network.people.get("John Doe").getTime(),
                network.people.get("Alice Johnson").getTime());
        /****************************************************************************** */
        /* Suggesting friends */
        List<Person> l = network.suggestFriends("John Doe", 3, network.people.get("John Doe").getTime());
        System.out.println("\nJohn Doe friend suggestions: ");
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l + "\n");
        }
        /*********************************************************************************** */
        /* Counting clusters */
        System.out.println("Number of clusters found: " + network.countClusters());
    }

    public static void main(String[] args) {
        test();
        // menu();
    }
}
