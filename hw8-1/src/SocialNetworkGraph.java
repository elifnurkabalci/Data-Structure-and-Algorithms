import java.util.*;
import java.util.stream.Collectors;

public class SocialNetworkGraph {
    Map<String, Person> people = new HashMap<>();
    Map<Person, List<Person>> friendships = new HashMap<>();

    /* Method to add a person */
    public void addPerson(String name, int age, List<String> hobbies) {
        /*
         * if we try to add exatly same people, they have different timestamp , so they
         * become not same
         */
        Person person = new Person(name, age, hobbies);
        people.put(name, person); /* add person list */
        friendships.put(person, new ArrayList<>()); /* add friendship key list */
        System.out.println("Person added: " + person + " (Timestampt: " + person.gettimestamp() + ")");
        /* print added person name and added time that we create in person class */

    }

    /* Method to remove a person */
    public void removeperson(String name, String time) {
        Person person = people.get(name);
        people.keySet().remove(name); /* delete from people's */
        friendships.keySet().remove(person); /* delete from friendship's key */
        if (!person.getTime().equals(time)) {
            System.out.println("Given time is not equal with eixst one.");
            return;
        }
        /* delete from friends to other's */
        for (Person p : friendships.keySet()) {
            if (friendships.get(p).contains(person)) {
                /* if is exist and given time and person's added time are equal */
                friendships.get(p).remove(person);
            }
        }
    }

    /* Method to add a friendship */
    public void addFriendship(String name1, String name2, String time1, String time2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (!time1.equals(person1.getTime()) || !time2.equals(person2.getTime())) {
            System.out.println("Times are not equal with given by user.");
            return;
        }
        if (person1 != null && person2 != null) {
            /* if persons are exist and person's time are equal with given time values */
            friendships.get(person1).add(person2);
            friendships.get(person2).add(person1);
            /*
             * key:person1 value:person2
             * key:person2 value:person1
             */
            System.out.println("Friendship added between " + person1.name + " and " + person2.name);
        } else {
            System.out
                    .println("One or both persons not found in the network.");
        }
    }

    public void displayPeople() {
        /*
         * for printing people map
         */
        System.out.println("\n\n\nPeople in the network:");
        for (Person person : people.values()) {
            System.out.println(person);
        }
        System.out.println();
    }

    public void displayFriendships() {
        /* for printing friendship map */
        System.out.println("\n\n\nFriendships in the network:");
        for (Map.Entry<Person, List<Person>> entry : friendships.entrySet()) {
            System.out.println(entry.getKey().getname() + " is friends with " + entry.getValue());
        }
        System.out.println();
    }

    /* Method to remove a friendship */
    public void removeFriendship(String name1, String name2, String time1, String time2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (!time1.equals(person1.getTime()) || !time2.equals(person2.getTime())) {
            System.out.println("Time's are not equal with exist ones.");
            return;
        }
        if (person1 != null && person2 != null) {
            friendships.get(person1).remove(person2);
            friendships.get(person2).remove(person1);
            System.out.println("Friendship deleted between " + person1.name + " and " + person2.name);
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    // Method to find the shortest path using BFS
    public void findShortestPath(String startName, String endName, String time1, String time2) {
        Person start = people.get(startName);
        Person end = people.get(endName);

        if (start == null || end == null) {
            System.out.println("Person not found in the network.");
            return;
        }
        if (!start.getTime().equals(time1) || !end.getTime().equals(time2)) {
            System.out.println("Given time values are not match.");
            return;
        }

        Queue<Person> queue = new LinkedList<>();
        Map<Person, Person> prev = new HashMap<>(); /* previos person */
        Set<Person> visited = new HashSet<>(); /* visited nodes stored there */

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();

            /* if we reach to the end break the while loop */
            if (current.equals(end)) {
                break;
            }

            for (Person neighbor : friendships.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                }
            }
        }

        /* print shortest path */
        printPath(start, end, prev);
    }

    private void printPath(Person start, Person end, Map<Person, Person> prev) {
        List<Person> path = new ArrayList<>();
        for (Person at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        if (path.get(0).equals(start)) {
            System.out.print("Shortest path: ");
            for (int i = 0; i < path.size(); i++) {
                if (i != 0) {
                    System.out.print(" -> ");
                }
                System.out.print(path.get(i).getname());
            }
            System.out.println();
        } else {
            System.out.println("No path found between " + start.getname() + " and " + end.getname());
        }
    }

    // Method to count clusters using BFS
    public int countClusters() {
        Set<Person> visited = new HashSet<>();
        List<List<Person>> clusterList = new ArrayList<>();

        for (Person person : people.values()) {
            if (!visited.contains(person)) {
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);
                clusterList.add(cluster);
            }
        }
        for (int i = 0; i < clusterList.size(); i++) {
            System.out.println("Cluster " + (i + 1) + ":");
            for (Person person : clusterList.get(i)) {
                System.out.println(person.getname());
            }
            System.out.println();
        }
        return clusterList.size();
    }

    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            cluster.add(current);
            for (Person neighbor : friendships.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    public List<Person> suggestFriends(String name, int maxSuggestions, String time) {
        Person person = people.get(name);
        Map<Person, Double> scores = new HashMap<>();

        if (!person.getTime().equals(time)) {
            System.out.println("Person's added time is wrong");
            return null;
        }
        for (Person friend : friendships.get(person)) {
            for (Person friendOfFriend : friendships.get(friend)) {
                if (!friendOfFriend.equals(person) && !friendships.get(person).contains(friendOfFriend)) {
                    double score = calculateScore(person, friendOfFriend);
                    scores.put(friendOfFriend, scores.getOrDefault(friendOfFriend, 0.0) + score);
                }
            }
        }

        return scores.entrySet().stream()
                .sorted(Map.Entry.<Person, Double>comparingByValue().reversed())
                .limit(maxSuggestions)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double calculateScore(Person person, Person candidate) {
        long mutualFriends = friendships.get(person).stream().filter(friendships.get(candidate)::contains).count();
        long commonHobbies = person.getHobbies().stream().filter(candidate.getHobbies()::contains).count();
        return mutualFriends * 1.0 + commonHobbies * 0.5;
    }
}
