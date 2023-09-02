package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void remove(int index) {
        this.list.remove(index);
    }
}
