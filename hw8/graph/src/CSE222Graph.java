import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class CSE222Graph {
    private ArrayList<int[]> edges;
    private String filename;
    public ArrayList<int[]> getEdges() {
        return edges;
    }
    public void setEdges(int[][] arr) { // 2d int array to 2d int arraylist
        for(int[] row : arr){
            this.edges.add(row);
        }
    }
    public void setFilename(String filename) { // for naming output files
        this.filename = filename;
    }
    public String getFilename() {
        return filename;
    }
    public CSE222Graph(CSE222Map map){ 
        this.edges = new ArrayList<>();
        this.filename = "";
        setEdges(map.getMap()); 
    }
    public void writeTxt(List<int[]> shortestPath, String type) throws IOException{ // output files , type is bfs or dijkstra
        String file = getFilename() + type +"output.txt";
        FileWriter writer = new FileWriter(file);
        for (int[] node : shortestPath) {
            writer.write("(" + node[0] + ", " + node[1] + ")");

        }
        writer.close();
    }
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a filename: ");
        String userInput = scanner.nextLine();
        scanner.close();

        CSE222Map map = new CSE222Map(userInput);
        // txt readed, map object saved. map datas are saved
        if(map.getMap()[map.getStart_point()[0]][map.getStart_point()[1]] != 0 && map.getMap()[map.getEnd_point()[0]][map.getEnd_point()[1]] != 0){
            System.out.println("Start or end point have a obstacles. Program ending..");
        } // if there is an error
        else{
            CSE222Graph graph = new CSE222Graph(map);
            graph.setFilename(userInput.substring(0, userInput.length()-4)); // for creation outputfile name , dynamic naming structure

            //TextToImage tex = new TextToImage((userInput), ("graph/" + graph.filename + ".png"));
            
            long startTime = System.currentTimeMillis();
            //BFS
            BreadthFirst bfs = new BreadthFirst(map.getStart_point(), map.getEnd_point(), graph.getEdges());
            ArrayList<int[]> shortestPathBFS = bfs.findShortestPath();

            System.out.println("BFS # of steps: " + shortestPathBFS.size());
            graph.writeTxt(shortestPathBFS, "BFS");
            long endTime = System.currentTimeMillis();
            long runningTime = endTime - startTime;
        
            System.out.println("Running time: " + runningTime + " milliseconds");
            
            startTime = System.currentTimeMillis();
            //dijkstra
            Dijkstra diji = new Dijkstra(map.getStart_point(), map.getEnd_point(), graph.getEdges());
            List<int[]> shortestPathD = diji.findShortestPath(userInput);

            System.out.println("Dijikstra # of steps: " + shortestPathD.size());
            graph.writeTxt(shortestPathD, "DIJKSTRA");
            endTime = System.currentTimeMillis();
            runningTime = endTime - startTime;
        
            System.out.println("Running time: " + runningTime + " milliseconds");
            //tex.turned(shortestPath);

        }    
    }
}
