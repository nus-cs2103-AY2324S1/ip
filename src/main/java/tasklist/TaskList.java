package main.java.tasklist;

import main.java.Task;
import main.java.storage.Storage;

import java.util.ArrayList;

/**
 * The TaskList class represents a collection of tasks and provides methods for managing tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new `TaskList` instance with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int numOfTasks() {
        return this.taskList.size();
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    /**
     * Removes a task from the task list.
     *
     * @param task The task to be removed.
     */
    public void removeTask(Task task) {
        this.taskList.remove(task);
    }

}
