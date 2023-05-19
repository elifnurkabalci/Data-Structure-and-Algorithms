package main.java;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class myMap {
    private String str; // original string
    private String inputValue; // preprocessed string
    private int mapSize; 
    private LinkedHashMap<Character, info> map;

    public String getInputValue() { return inputValue; }
    public void setInputValue(String inputValue) { this.inputValue = inputValue; }
    public LinkedHashMap<Character, info> getMap() { return map; }
    public int getMapSize() { return mapSize; }
    public void setMapSize(int mapSize) { this.mapSize = mapSize; }
    public void setMap(LinkedHashMap<Character, info> map) { this.map = map; }
    public void setStr(String str) { this.str = str; }
    public String getStr() { return str; }
    
    public void menu(){
        // step 1
        scan();
        lowerControl();
        charControl();

        // step 2
        buildMap();
    }
    public myMap(){
        this.inputValue = "";
        this.mapSize = 0;
        this.map = new LinkedHashMap<>();
        this.str = "";
    }
    public void charControl(){
        String temp = getInputValue().replaceAll("[^a-zA-Z\\s]", "");
        setInputValue(temp);
    }
    public void lowerControl(){
        String temp = getInputValue().toLowerCase();
        setInputValue(temp);    
    }
    public void scan(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        setInputValue(input);
        setStr(input);
        scanner.close();
    }
    public void declaration(int[] start, int[] end, int i){
        start[0] = getInputValue().lastIndexOf(' ', i) + 1;
        i++;
        while(getInputValue().charAt(i) != ' ' && (i!= getInputValue().length())){
            i++;
        } 
        end[0] = i+1;
    }
    public void buildMap(){
        setInputValue(getInputValue().concat(" "));
        for(int i=0; i<getInputValue().length(); i++){ 
            if(!getMap().containsKey(getInputValue().charAt(i)) && getInputValue().charAt(i) != ' '){ // if map doesnt contain this character
                getMap().put(getInputValue().charAt(i), new info());
                setMapSize(getMapSize()+1);
            }
            if(getInputValue().charAt(i) != ' '){
                int[] start = new int[1];
            int[] end = new int[1];
            declaration(start, end, i);
            // push the word, using substring begin to end for word
            getMap().get(getInputValue().charAt(i)).
            push(getInputValue().substring(start[0], end[0]));
            }
            
        }
    }
    public static void main(String[] args) {
        
        myMap map1 = new myMap();
        map1.menu();
        
        //merge sort
        long startTime = System.nanoTime();
        System.out.println("Merge Sort");
        mergeSort mergemap = new mergeSort(map1);
        mergemap.print();
        long endTime = System.nanoTime();
        long mergetime = endTime - startTime;


        //selection sort
        startTime = System.nanoTime();
        System.out.println("Selection Sort");
        selectionSort selectmap = new selectionSort(map1);
        selectmap.print();
        endTime = System.nanoTime();
        long selectiontime = endTime - startTime;

        //insertion sort
        startTime = System.nanoTime();
        System.out.println("Insertion Sort");
        insertionSort insertmap = new insertionSort(map1);
        insertmap.print();
        endTime = System.nanoTime();
        long insertiontime = endTime - startTime;
        
        //bubble Sort
        startTime = System.nanoTime();
        System.out.println("Bubble Sort");
        bubbleSort bubmap = new bubbleSort(map1);
        bubmap.print();
        endTime = System.nanoTime();
        long bubbletime = endTime - startTime;

        // quick sort
        startTime = System.nanoTime();
        System.out.println("Quick Sort");
        quickSort quickmap = new quickSort(map1);
        quickmap.print();
        endTime = System.nanoTime();
        long quicktime = endTime - startTime;

        System.out.println("Merge Sort Time: " + mergetime + " nanoseconds");
        System.out.println("Selection Sort Time: " + selectiontime + " nanoseconds");
        System.out.println("Insertion Sort Time: " + insertiontime + " nanoseconds");
        System.out.println("Bubble Sort Time: " + bubbletime + " nanoseconds");
        System.out.println("Quick Sort Time: " + quicktime + " nanoseconds");
        
        
    }
}
