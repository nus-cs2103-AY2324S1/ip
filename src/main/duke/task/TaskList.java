package task;

import storage.Storage;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        storage.loadTasks(this.tasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
