import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSE222Map {
    private int[][] map;
    private int[] start_point;
    private int[] end_point;
    public void setMap(int[][] map) {
        this.map = map;
    }
    public int[][] getMap() {
        return map;
    }
    public int[] getStart_point() {
        return start_point;
    }
    public int[] getEnd_point() {
        return end_point;
    }
    public void setEnd_point(String[] strings) { // turn string to int and store in objects
        this.end_point[0] = Integer.parseInt(strings[0]);
        this.end_point[1] = Integer.parseInt(strings[1]);
    }
    public void setStart_point(String[] strings) {
        this.start_point[0] = Integer.parseInt(strings[0]);
        this.start_point[1] = Integer.parseInt(strings[1]);
    }
    public CSE222Map(String filename){
        this.map = new int[1000][1000];
        this.start_point = new int[2];
        this.end_point = new int[2];
        readFile(filename);
    }
    public void readFile(String filename){
        int x=500;
        if(filename =="vatican.txt" || filename=="pisa.txt" || filename=="tokyo.txt" || filename=="triumph.txt"){ // there sizes are 1000 not 500
            x=1000;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String start = reader.readLine(); 
            String end = reader.readLine();

            setStart_point(start.split(","));       
            setEnd_point(end.split(",")); 
            
            int[][] temp_map = new int[1000][1000];      
            for(int i=0; i<x; i++){ // start the map datas after reading start and end coordinates
                String line = reader.readLine();
                String[] temp = line.split(","); // split with , and store
                for(int j=0; j<x; j++){
                    temp_map[i][j] = Integer.parseInt(temp[j]);
                }
            }
            setMap(temp_map);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
