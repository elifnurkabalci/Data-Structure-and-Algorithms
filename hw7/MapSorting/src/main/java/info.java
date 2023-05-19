package main.java;
public class info {
    private int count;
    private String[] words;
    public info(){
        this.count = 0;
        this.words = new String[100];
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String[] getWords() {
        return words;
    }
    public void setWords(String[] words) {
        this.words = words;
    }
    
    public void push(String obj){
        String[] temp = getWords();
        temp[getCount()] = obj;
        setWords(temp);
        setCount(getCount()+1);

    }

}
