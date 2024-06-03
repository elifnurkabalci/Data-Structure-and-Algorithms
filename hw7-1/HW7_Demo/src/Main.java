import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    private static List<Integer> treesize;
    private static List<Long> avgtimeAdd;
    private static List<Long> avgtimeRemove;
    private static List<Long> avgtimeSearch;

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 1) { /* if input is not given */
            System.out.println("Usage: java Main <input_file>");
            return;
        }
        /* global declarations initials */
        treesize = new ArrayList<>();
        avgtimeAdd = new ArrayList<>();
        avgtimeSearch = new ArrayList<>();
        avgtimeRemove = new ArrayList<>();

        /* input.txt maker script's calling method */
        pythonScriptCalling();

        String inputFile = args[0]; /* file name take */
        StockDataManager manager = new StockDataManager(); /* connect with Stockmanager */

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line; /* Read the file and send to process */
            while ((line = br.readLine()) != null) {
                processCommand(line, manager);
                treesize.add(manager.getTree().get_nodeCounter()); /* add the tree size's every count */
                /* Perform a simple performance analysis */

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* manager.getTree().preOrderTraversal(); */

        /* For visulization the tree */
        AVLTreePanel treePanel = new AVLTreePanel(manager.getTree().root);
        JFrame frame = new JFrame("AVL Tree Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(treePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        /* analysis the perfomance instead of tree size, generate avg time values */
        performPerformanceAnalysis(manager, manager.getTree().get_nodeCounter());

        /* call the gui visualization class */
        SwingUtilities.invokeLater(() -> {
            GUIVisualization guiAdd = new GUIVisualization("scatter", avgtimeAdd,
                    treesize.subList(0, avgtimeAdd.size()),
                    "Add");
            guiAdd.setVisible(true);
        });
        SwingUtilities.invokeLater(() -> {
            GUIVisualization guiRemove = new GUIVisualization("scatter", avgtimeRemove,
                    treesize.subList(0, avgtimeRemove.size()), "Remove");
            guiRemove.setVisible(true);
        });

        SwingUtilities.invokeLater(() -> {
            GUIVisualization guiSearch = new GUIVisualization("scatter", avgtimeSearch,
                    treesize.subList(0, avgtimeSearch.size()), "Search");
            guiSearch.setVisible(true);
        });

    }

    private static void pythonScriptCalling() {
        try {
            String pythonExecutablePath = "C:\\Users\\e.kabalci2018\\AppData\\Local\\Programs\\Python\\Python311\\python.exe";
            String pythonScriptPath = "C:\\Users\\e.kabalci2018\\Desktop\\HW7\\HW7_Demo\\src\\script.py";
            String scriptDirectory = ".";

            /* Run python */
            ProcessBuilder processBuilder = new ProcessBuilder(pythonExecutablePath, pythonScriptPath);
            processBuilder.directory(new File(scriptDirectory));
            Process process = processBuilder.start();

            /* Read file and also errors */
            try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String s;
                while ((s = stdError.readLine()) != null) {
                    System.err.println(s);
                }
            }

            /* Exit python code */
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Python script failed with exit code: " + exitCode);
                return;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch (command) {
            case "ADD":
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]),
                        Long.parseLong(tokens[4]));
                break;
            case "REMOVE":
                manager.removeStock(tokens[1]);
                break;
            case "SEARCH":
                Stock stock = manager.searchStock(tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                manager.updateStock(tokens[1], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]),
                        Long.parseLong(tokens[5]));
                break;
            default:
                // System.out.println("Unknown command: " + command);
                break;
        }
    }

    private static void performPerformanceAnalysis(StockDataManager manager, int size) {
        long startTime, endTime;

        /* Measure time for ADD operation */
        for (int i = 0; i < size; i++) {
            startTime = System.nanoTime();
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000),
                    (long) (Math.random() * 1000000000));
            endTime = System.nanoTime();
            avgtimeAdd.add((endTime - startTime));
        }
        // System.out.println("Average ADD time: " + (endTime - startTime) / size + "
        // ns");

        /* Measure time for SEARCH operation */
        for (int i = 0; i < size; i++) {
            startTime = System.nanoTime();
            manager.searchStock("SYM" + i);
            endTime = System.nanoTime();
            avgtimeSearch.add((endTime - startTime));
        }
        // System.out.println("Average SEARCH time: " + (endTime - startTime) / size + "
        // ns");

        /* Measure time for REMOVE operation */
        for (int i = 0; i < size; i++) {
            startTime = System.nanoTime();
            manager.removeStock("SYM" + i);

            endTime = System.nanoTime();
            avgtimeRemove.add((endTime - startTime));
        }
        // System.out.println("Average REMOVE time: " + (endTime - startTime) / size + "
        // ns");
    }
}
