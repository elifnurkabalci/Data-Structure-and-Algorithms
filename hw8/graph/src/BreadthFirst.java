import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirst {
    private int[] start; // Start node coordinates [x, y]
    private int[] end; // End node coordinates [x, y]
    private ArrayList<int[]> map; // List of possible coordinates [x, y] for edges

    public BreadthFirst(int[] start, int[] end, ArrayList<int[]> edges) {
        this.start = start;
        this.end = end;
        this.map = edges;
    }

    public ArrayList<int[]> findShortestPath() {
        Queue<int[]> queue = new LinkedList<>();
        int[][] previousNode = new int[map.size()][map.get(0).length];
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).length; j++) {
                previousNode[i][j] = -1; // Initialize with -1 indicating no previous node
            }
        }

        queue.offer(start);
        previousNode[start[0]][start[1]] = -2; // Use -2 to indicate the start node

        while (!queue.isEmpty()) {
            int[] currentNode = queue.poll();
            int currentX = currentNode[0];
            int currentY = currentNode[1];

            if (currentX == end[0] && currentY == end[1]) {
                break; // Reached the end node, exit the loop
            }

            // Define the valid neighbor positions (up, down, left, right)
            int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            for (int[] neighbor : neighbors) {
                int neighborX = currentX + neighbor[0];
                int neighborY = currentY + neighbor[1];

                if (isValidCoordinate(neighborX, neighborY) && previousNode[neighborX][neighborY] == -1 && (map.get(neighborX)[neighborY] == 0 || map.get(neighborX)[neighborY] == -1)) {
                    queue.offer(new int[]{neighborX, neighborY});
                    previousNode[neighborX][neighborY] = currentX * map.get(0).length + currentY; // Store the previous node's coordinates
                }
            }
        }

        // Reconstruct the shortest path
        ArrayList<int[]> shortestPath = new ArrayList<>();
        int[] currentNode = new int[]{end[0], end[1]};
        while (currentNode[0] != -2 && currentNode[1] != -2) {
            shortestPath.add(0, currentNode);
            int previousX = previousNode[currentNode[0]][currentNode[1]] / map.get(0).length;
            int previousY = previousNode[currentNode[0]][currentNode[1]] % map.get(0).length;
            currentNode = new int[]{previousX, previousY};
        }

        return shortestPath;
    }

    private boolean isValidCoordinate(int x, int y) {
        // Check if the coordinate is within the grid boundaries
        return x >= 0 && x < map.size() && y >= 0 && y < map.get(0).length;
    }
}
