package martin.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
