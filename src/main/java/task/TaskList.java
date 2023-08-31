package task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    public Task get(int pos) {
        return this.taskArrayList.get(pos);
    }

    public void remove(Task task) {
        this.taskArrayList.remove(task);
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    public void addToList() {

    }

}
