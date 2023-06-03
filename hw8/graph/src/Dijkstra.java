import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra {
    private int[] start; // Start node coordinates [x, y]
    private int[] end; // End node coordinates [x, y]
    private ArrayList<int[]> edges; // List of possible coordinates [x, y] for edges

    static class Node { // node struct , because others gives error.
        int x;
        int y;
        int distance;
        Node previous;
        
        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    public Dijkstra(int[] start, int[] end, ArrayList<int[]> edges) {
        this.start = start;
        this.end = end;
        this.edges = edges;
    }

    public List<int[]> findShortestPath(String filename) {
        // rows-500 cols-500 or 1000
        int q=1000;
        int[][] distances = new int[q][q];
        boolean[][] visited = new boolean[q][q];
        
        for (int i = 0; i < q; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
        
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        queue.offer(new Node(start[0], start[1], 0));
        distances[start[0]][start[1]] = 0;
        
        int[] dx = {-1, 1, 0, 0}; // for 4 directioon neighbours
        int[] dy = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            Node curr = queue.poll(); // current node
            int x = curr.x;
            int y = curr.y;
            int distance = curr.distance;
            
            if (visited[x][y]) {
                continue;
            }
            
            visited[x][y] = true;
            
            if (x == end[0] && y == end[1]) {
                // Reconstruct the path using a stack
                Stack<Node> stack = new Stack<>();
                stack.push(curr);
                Node node = curr.previous; // it gives end to start so we use previous node
                while (node != null) {
                    stack.push(node);
                    node = node.previous;
                }
                
                List<int[]> shortestPath = new ArrayList<>();
                while (!stack.isEmpty()) { 
                    Node pathNode = stack.pop();
                    shortestPath.add(new int[]{pathNode.x, pathNode.y});
                }
                
                return shortestPath;
            }
            
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                
                if (newX >= 0 && newX < q && newY >= 0 && newY < q && !visited[newX][newY] && edges.get(newX)[newY] != 1) {
                    int newDistance = distance + 1;
                    
                    if (newDistance < distances[newX][newY]) { // control wich direction distance are shortest
                        distances[newX][newY] = newDistance;
                        Node newNode = new Node(newX, newY, newDistance);
                        newNode.previous = curr; // Set the previous node
                        queue.offer(newNode);
                        // until queue go end , add to new node
                    }
                }
            }
        }
        System.out.println("There is no path between start and end points"); // for error checking
        return new ArrayList<>(); // If there is no path from the start to the end point
    }
    
}
