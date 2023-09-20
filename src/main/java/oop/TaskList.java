package oop;


import java.util.ArrayList;
import java.util.List;

import tasks.Task;

/**
 * The class that manipulates the main group of tasks known to wallE.
 */
public class TaskList {
    /** The list of tasks to be encapusated in this class */
    private List<Task> tasks;


    /**
     * Constructs a TaskList instance with a specified list of tasks
     *
     * @param tasks The list of tasks to be encapsulated in this class.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList instance with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int i) {
        assert i < tasks.size() && i >= 0;
        return tasks.get(i);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }


    /**
     * Deletes a task from the task list.
     *
     * @param id The index of the task to be deleted.
     * @return The task that was removed.
     */
    public Task deleteTask(int id) {
        assert id < tasks.size() && id >= 0;
        return this.tasks.remove(id);
    }

    /**
     * Marks a task as done.
     *
     * @param id The index of the task to be marked as done.
     */
    public void markTask(int id) {
        assert id < tasks.size() && id >= 0;
        this.getTask(id).markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param id The index of a task to be marked as not done.
     */
    public void unmarkTask(int id) {
        assert id < tasks.size() && id >= 0;
        this.getTask(id).markAsUndone();
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the current list.
     */
    public int getSize() {
        return this.tasks.size();
    }
    public List<Task> getTasks() {
        return this.tasks;
    }

}
