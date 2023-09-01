package peko;

import java.util.Arrays;

public class Find {
    Task[] tempTaskList;
    String searchQuery;

    public Find(String s) {
        System.out.println("Find: " + s);
        if (s.isEmpty()) {
            System.out.println("You're not searching for anything Peko?");
        } else {
            searchQuery = s;
            tempTaskList = new Task[100];
            search();
            //display();
        }
    }

    private void search() {
        this.tempTaskList = StorageHandler.search(searchQuery);
    }
    public void display() {
        for (Task t : tempTaskList) {
            if (t == null) {
                break;
            }
            System.out.println(t);
        }
    }

}
