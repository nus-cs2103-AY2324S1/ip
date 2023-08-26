package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;

    public TaskList (ArrayList<Task> items) {
        this.items = items;
    }

    public int size() {
        return items.size();
    }

    public Task get(int i) {
        return items.get(i);
    }

    public Task remove(int i) {
        return items.remove(i);
    }

    public void add(Task add) {
        items.add(add);
    }

    public ArrayList<Task> toArray() {
        return items;
    }
}
