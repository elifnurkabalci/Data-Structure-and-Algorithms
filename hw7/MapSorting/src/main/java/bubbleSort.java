package main.java;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bubbleSort {
    private myMap originalMap;
    private myMap sortedMap;
    private Character[] aux; // key

    
    public myMap getOriginalMap() {
        return originalMap;
    }
    public myMap getSortedMap() {
        return sortedMap;
    }
    public Character[] getAux() {
        return aux;
    }
    public void setAux(Character[] aux) {
        this.aux = aux;
    }
    public void fillAux(){
        Set<Character> temp = getOriginalMap().getMap().keySet();
        setAux(temp.toArray(new Character[0]));
    }
    public void sortedAux(){
        Set<Character> temp = getSortedMap().getMap().keySet();
        setAux(temp.toArray(new Character[0]));
    }
    public void sort(List<Map.Entry<Character, info>> list, int start, int end) {
        if (start < end) {
            bubble(list, start, end);
        }
    }
    
    private static void bubble(List<Map.Entry<Character, info>> list, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = start; j < end - i; j++) {
                if (list.get(j).getValue().getCount() > list.get(j + 1).getValue().getCount()) {
                    // Swap elements
                    Map.Entry<Character, info> temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
    
    public void sortMap(LinkedHashMap<Character, info> map) {  // according to count
        if (map == null || map.isEmpty()) {
            return;
        }
        List<Map.Entry<Character, info>> entryList = new ArrayList<>(map.entrySet());
        sort(entryList, 0, entryList.size() - 1);
    
        for (Map.Entry<Character, info> entry : entryList) {
            getSortedMap().getMap().put(entry.getKey(), entry.getValue());
        }
    }
    
    public void print(){
        System.out.println(" ");
        System.out.println("Original String:     " + getOriginalMap().getStr());
        System.out.println("Preprocessed String: " + getOriginalMap().getInputValue());
        System.out.println(" ");
        System.out.println("The original (unsorted) map:");

        LinkedHashMap<Character, info> a = getOriginalMap().getMap();
        for(int i=0; i<getOriginalMap().getMapSize(); i++){
            Character letter = getAux()[i];
            Character index = letter;
            System.out.print("Letter: " +  letter + 
            " - Count: " + a.get(index).getCount() + 
            " - Words: [");
            for(int j=0; j<a.get(index).getCount(); j++){
                System.out.print(a.get(index).getWords()[j] + " ");
            }
            System.out.print("]");
            System.out.println(" ");
        }
        sortMap(getOriginalMap().getMap());
        sortedAux();
        System.out.println(" ");
        System.out.println("The sorted map:");
        
        a = getSortedMap().getMap();
        for(int i=0; i<getAux().length; i++){
            Character letter = getAux()[i];
            Character index = letter;

            System.out.print("Letter: " + letter + 
            " - Count: " + a.get(index).getCount() + 
            " - Words: [");
            for(int j=0; j<a.get(index).getCount(); j++){
                System.out.print(a.get(index).getWords()[j] + " ");
            }
            System.out.print("]");
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    public bubbleSort(myMap map){
        this.originalMap = map;
        this.aux = new Character[20];
        this.sortedMap = new myMap();
        fillAux();
    }
}
