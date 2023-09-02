package arona.task;

import arona.storage.Storage;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        storage.loadTasks(this.tasks);
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
