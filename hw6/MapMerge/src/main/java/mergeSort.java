package main.java;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class mergeSort {
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
            int mid = (start + end) / 2;
            sort(list, start, mid);
            sort(list, mid + 1, end);
            merge(list, start, mid, end);
        }
    }
    private static void merge(List<Map.Entry<Character, info>> list, int start, int mid, int end) {
        List<Map.Entry<Character, info>> merged = new ArrayList<>();
        int i = start;
        int j = mid + 1;

        while (i <= mid && j <= end) {
            if (list.get(i).getValue().getCount() <= list.get(j).getValue().getCount()) {
                merged.add(list.get(i++));
            } else {
                merged.add(list.get(j++));
            }
        }

        while (i <= mid) {
            merged.add(list.get(i++));
        }

        while (j <= end) {
            merged.add(list.get(j++));
        }

        for (int k = 0; k < merged.size(); k++) {
            list.set(start + k, merged.get(k));
        }
    }
    public void sortMap(LinkedHashMap<Character, info> map){  // according to count
        if (map == null || map.isEmpty()) {
            return;
        }
        List<Map.Entry<Character, info>> entryList = new ArrayList<>(map.entrySet());
        sort(entryList, 0, entryList.size() - 1);
        
        for (Map.Entry<Character, info> entry : entryList) {
            getSortedMap().getMap().put(entry.getKey(), entry.getValue());
            
        }// fill sortedmap with merged list
    }
    public void print(){
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
    }
    public mergeSort(myMap map){
        this.originalMap = map;
        this.aux = new Character[20];
        this.sortedMap = new myMap();
        fillAux();
        
        
    }
}
