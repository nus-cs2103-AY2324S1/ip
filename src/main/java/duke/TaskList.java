package duke;

import java.util.ArrayList; // import the ArrayList class

public class TaskList {
    private final ArrayList<Task> myList;

    public TaskList(ArrayList<Task> myList) {
        this.myList = myList;
    }

    public void add(Task t) {
        this.myList.add(t);
    }

    public void delete(int i) {
        int num = i - 1;
        this.myList.remove(num);
    }

    public ArrayList<Task> getList() {
        return this.myList;
    }

    public Task get(int i) {
        int num = i - 1;
        return this.myList.get(num);
    }
}
