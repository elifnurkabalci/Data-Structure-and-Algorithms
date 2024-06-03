import java.util.*;

/* This file contains every Person's name, age, hobbies and adding date */
public class Person {
    String name;
    int age;
    List<String> hobbies;
    Date timestamp;
    String time;

    /* constructor */
    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = new Date();
        time = timestamp.toString();
    }

    /* getters */
    public String getname() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getage() {
        return age;
    }

    public Date gettimestamp() {
        return timestamp;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setTime(String a) {
        time = a;
    }

    @Override /* printing the details */
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ")";
    }
}
