package main.java;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
// libraries definitons

public class TreeSystem {
    // private section
    private String filepath; // for reading file 
    private ArrayList< ArrayList<String>> data; // collect the file data
    private DefaultMutableTreeNode root; // creation of the tree

    //public section - getter setters
    public String getFilepath() { return filepath; }
    public ArrayList<ArrayList<String>> getData() { return data; }
    public DefaultMutableTreeNode getRoot() { return root; }
    public void setRoot(DefaultMutableTreeNode root) { this.root = root; }

    public TreeSystem() throws IOException{ // constractor
        this.filepath = Paths.get("src\\main\\java\\tree.txt").toAbsolutePath().toString();
        // vscode doesnt know the tree.txt so I give the all path
        this.data = new ArrayList<>(); // first design
        ReadFromFile();
        CreateTree();
        show_frame();
    }
    public void BF_Search(String input){
        Queue<DefaultMutableTreeNode> queue = new LinkedList<>();
        int count=1; // for print screen the steps
        queue.offer(getRoot()); // add root of the tree to queue
        while (!queue.isEmpty()) {
            DefaultMutableTreeNode currentNode = queue.poll(); // delete the element, so it counter for while
            if(currentNode.toString() != null){ // there is no end of file
                System.out.println("Step " + count + "-> " + currentNode);
                count++;
            }
            if (currentNode.toString().equals(input)) { // found the node
                System.out.println("Found!");
                return;
            }
            for (int i = 0; i < currentNode.getChildCount(); i++) { // add the childs to queue
                queue.offer((DefaultMutableTreeNode) currentNode.getChildAt(i));

            }
        }
        System.out.println("Not found!");
    }
    public void show_frame(){ // create the frame and show the tree 
        JTree tree = new JTree(getRoot());
        JFrame frame = new JFrame("Tree from File");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(tree, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    public void CreateTree(){
        setRoot(new DefaultMutableTreeNode("Root")); // create the initial rot for tree
        for(ArrayList<String> row : getData()){
            DefaultMutableTreeNode currentNode = getRoot();
            for(String column : row){
                boolean temp = false;
                for (int i = 0; i < currentNode.getChildCount(); i++) {
                    if (column.equals(currentNode.getChildAt(i).toString())) {
                        currentNode = (DefaultMutableTreeNode) currentNode.getChildAt(i);
                        temp = true;
                        break;
                    }
                }
                if (!temp) {
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(column);
                    currentNode.add(newNode);
                    currentNode = newNode;
                }
            }
        }
    }
    public void ReadFromFile() throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader(getFilepath()));
        // I use the buffer, because of normal input scanner is gave the error
        String line;
        // read line by line and add the arraylist row and add all of them in one 2d arraylist
        while((line = buf.readLine()) != null){
            String[] values = line.split(";");
            ArrayList<String> row = new ArrayList<String>(Arrays.asList(values));
            getData().add(row);
        }
    }
    public String readerMethod() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // I use the buffer, because of normal input scanner is gave the error
        System.out.print("Enter a string to search in the tree: ");
        String input = reader.readLine();
        reader.close(); // close the scanner or reader
        return input;
    }  
    public void DF_Search(String input){  // left to right
        Stack<DefaultMutableTreeNode> stack = new Stack<>();
        int count=1;// counter for printing steps
        stack.push(getRoot()); // add the root to stack
        while(!stack.empty()){
            DefaultMutableTreeNode currentNode = stack.pop(); // delete one by one every loop
            if(currentNode.toString() != null){
                System.out.println("Step " + count + "-> " + currentNode); 
                count++;
            }
            if(currentNode.toString().equals(input)){ // found the input
                System.out.println("Found!");
                return;
            }
            for(int i = currentNode.getChildCount() - 1; i >= 0; i--){  // for every child the nodes
                stack.push((DefaultMutableTreeNode)currentNode.getChildAt(i));
            }
        }
        System.out.println("Not found.");
    }
    public boolean postorderTraversal(DefaultMutableTreeNode node, String target, int count) {
    if (node == null) { return false; }
    int childCount = node.getChildCount();
    
    for (int i = 0; i < childCount; i++) {
        if (postorderTraversal((DefaultMutableTreeNode) node.getChildAt(i), target,count)) {
            return true;
        }
    }
    
    if (node.toString().equals(target)) {
        System.out.println("Bulundu: " + node);
        return true;
    }

    return false;
}
    public void moveNode(String sourceYear, String sourceCourse, String sourceLecture, String destinationYear) {
    // find the source node
    DefaultMutableTreeNode sourceYearNode = null;
    DefaultMutableTreeNode sourceCourseNode = null;
    DefaultMutableTreeNode sourceLectureNode = null;
    for (int i = 0; i < getRoot().getChildCount(); i++) {
        DefaultMutableTreeNode yearNode = (DefaultMutableTreeNode) getRoot().getChildAt(i);
        if (yearNode.toString().equals(sourceYear)) {
            sourceYearNode = yearNode;
            for (int j = 0; j < yearNode.getChildCount(); j++) {
                DefaultMutableTreeNode courseNode = (DefaultMutableTreeNode) yearNode.getChildAt(j);
                if (courseNode.toString().equals(sourceCourse)) {
                    sourceCourseNode = courseNode;
                    for (int k = 0; k < courseNode.getChildCount(); k++) {
                        DefaultMutableTreeNode lectureNode = (DefaultMutableTreeNode) courseNode.getChildAt(k);
                        if (lectureNode.toString().equals(sourceLecture)) {
                            sourceLectureNode = lectureNode;
                            break;
                        }
                    }
                    break;
                }
            }
            break;
        }
    }
    if (sourceYearNode == null || sourceCourseNode == null || sourceLectureNode == null) {
        System.out.println("Source node not found.");
        return;
    }

    // find or create the destination year node
    DefaultMutableTreeNode destinationYearNode = null;
    for (int i = 0; i < getRoot().getChildCount(); i++) {
        DefaultMutableTreeNode yearNode = (DefaultMutableTreeNode) getRoot().getChildAt(i);
        if (yearNode.toString().equals(destinationYear)) {
            destinationYearNode = yearNode;
            break;
        }
    }
    if (destinationYearNode == null) {
        destinationYearNode = new DefaultMutableTreeNode(destinationYear);
        getRoot().add(destinationYearNode);
    }

    // check if the destination already has a node with the same name and overwrite it
    for (int i = 0; i < destinationYearNode.getChildCount(); i++) {
        DefaultMutableTreeNode courseNode = (DefaultMutableTreeNode) destinationYearNode.getChildAt(i);
        if (courseNode.toString().equals(sourceCourse)) {
            courseNode.remove(sourceLectureNode);
            courseNode.add(sourceLectureNode);
            System.out.println("Overwritten existing node.");
            return;
        }
    }

    // add the node to the destination
    sourceYearNode.remove(sourceCourseNode);
    sourceCourseNode.remove(sourceLectureNode);
    destinationYearNode.add(sourceCourseNode);
    System.out.println("Node moved successfully.");
}

    public static void main(String[] args) throws IOException {
        TreeSystem test1 = new TreeSystem();
        // in hw pdf said, you can use the same input for every seach algorithm
        String input = test1.readerMethod();
        
        System.out.println("BF_Search");
        test1.BF_Search(input);
        
        System.out.println("DF_Search");
        test1.DF_Search(input);

        //System.out.println("Post Order Traversal");
        //int count =1;
        //test1.postorderTraversal(test1.getRoot(), input, count);
    }
}
