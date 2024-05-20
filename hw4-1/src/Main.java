import java.util.Scanner;

public class Main {
    private static FileSystem fs = new FileSystem(); /* file system element declaration for using callings */

    private static Scanner scan = new Scanner(System.in); /* scanning from user element */

    private static Directory currentDirectory; /* current directory */

    public static void menu() {
        /*
         * Time complexity is depend on methods and how many loop in while
         * if while is continue until infinity, this means that O(infinity)
         */
        while (true) {
            System.out.println("Current directory: " + currentDirectory);
            System.out.println("===== File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file");
            System.out.println("4. Create directory");
            System.out.println("5. Delete file");
            System.out.println("6. Delete Directory");
            System.out.println("7. Move file/directory");
            System.out.println("8. Search file/directory");
            System.out.println("9. Print directory tree");
            System.out.println("10. Sort contents by date created");
            System.out.println("11. Exit");
            System.out.println("Please select an option:");

            int choice = scan.nextInt();
            scan.nextLine(); /* for reduce nextInt method's empty */

            System.out.println("");
            System.out.println("");

            switch (choice) {
                case 1:
                    changeDirectory();
                    break;
                case 2:
                    listContens();
                    break;
                case 3:
                    createFile();
                    break;
                case 4:
                    createDirectory();
                    break;
                case 5:
                    deleteFile();
                    break;
                case 6:
                    deleteDirectory();
                    break;
                case 7:
                    movElement();
                    break;
                case 8:
                    search();
                    break;
                case 9:
                    printDirectoryTree();
                    break;
                case 10:
                    sortDirectoryByDate();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again..");

            }
            System.out.println("");
            System.out.println("");

        }
    }

    public static void changeDirectory() {
        /*
         * Tkae path from user and call changedirectory method
         * Time complexity is equal with file system's changeDirectory method
         */
        System.out.println("Current directory: " + currentDirectory.getName());
        System.out.print("Enter new directory path: ");
        String path = scan.nextLine();
        currentDirectory = fs.changeDirectory(path);
        System.out.println("Directory changed to: " + currentDirectory.getName());

    }

    public static void listContens() {
        /*
         * This method call only file system list content method
         */
        fs.listContent(currentDirectory);
    }

    public static void createFile() {
        /*
         * This method calls the fs's create file method
         */
        System.out.print("Enter file name to create: ");
        String name = scan.nextLine();
        fs.createFile(name, currentDirectory);
        System.out.println("File created: " + name);
    }

    public static void createDirectory() {
        /*
         * This method calls the fs's create directory method
         */
        System.out.print("Enter directory name to create: ");
        String name = scan.nextLine();
        fs.createDirectory(name, currentDirectory);
        System.out.println("Directory created: " + name);
    }

    public static void deleteFile() {
        /*
         * This method calls the fs's delete file method
         */
        System.out.print("Enter file name to delete: ");
        String name = scan.nextLine();
        fs.deleteFile(name, currentDirectory);
        System.out.println("File deleted: " + name);
    }

    public static void deleteDirectory() {
        /*
         * This method calls the fs's delete directory method
         */
        System.out.print("Enter directory name to delete: ");
        String name = scan.nextLine();
        fs.deleteDirectory(name, currentDirectory);
        System.out.println("Directory deleted: " + name);
    }

    public static void movElement() {
        /*
         * File or directory
         * Move element in one direction to another
         */
        System.out.println("Current directory: " + currentDirectory.getName());
        System.out.print("Enter the name of file/directory to move:");
        String name = scan.nextLine();
        System.out.print("Enter new directory path");
        String path = scan.nextLine();
        Directory dir = (Directory) fs.found(path);
        fs.moveelement(name, dir);
        System.out.println("File moved: " + name + " to " + path);
    }

    public static void search() {
        /*
         * Searching an element
         * if it's found search method return found element
         */
        System.out.print("Enter search query: ");
        String query = scan.nextLine();
        boolean found = true;
        if (fs.search(query, fs.getRoot()) == null)
            found = false;

        System.out.println("Search result:" + (found ? "Found" : "NotFound"));
    }

    public static void printDirectoryTree() {
        /*
         * Print the tree
         * Space will increase with recursion
         */
        fs.printDirectoryTree(fs.getRoot(), "    ");
    }

    public static void sortDirectoryByDate() {
        /*
         * Calls sorting method in fs
         */
        fs.sortDirectoryByDate(currentDirectory);
    }

    public static void test() {
        /*
         * test method
         */
        fs.createDirectory("home", currentDirectory);

        fs.createDirectory("desktop", currentDirectory);

        fs.createFile("project.txt", currentDirectory);
        System.out.println();

        System.out.println("After adding 2 directory, 1 file");
        fs.listContent(currentDirectory);

        fs.deleteFile("project.txt", currentDirectory);
        System.out.println();

        System.out.println("After delete file");
        fs.listContent(currentDirectory);

        fs.deleteDirectory("desktop", currentDirectory);
        System.out.println();

        System.out.println("After delete directory");
        fs.listContent(currentDirectory);

        System.out.println();
        fs.sortDirectoryByDate(currentDirectory);

        System.out.println();
        fs.createDirectory("path1", currentDirectory);
        fs.createDirectory("path2", currentDirectory);

        fs.createFile("file1.txt", currentDirectory);

        System.out.println();
        fs.printDirectoryTree(fs.getRoot(), "    ");

        System.out.println("************************");

        currentDirectory = fs.changeDirectory("root/home");

        System.out.println("Current directory: " + currentDirectory.getName());

        System.out.println();
        System.out.println("Search and found: " + fs.search("path1", fs.getRoot()).getName());

        System.out.println();
        Directory d = (Directory) fs.search("home", (Directory) fs.getRoot());
        fs.moveelement("file1.txt", d);
        System.out.println();

        System.out.println("After moving file1.txt root to home");
        fs.printDirectoryTree(fs.getRoot(), "    ");

    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        currentDirectory = fs.getRoot();
        menu();
        // test();
    }
}
