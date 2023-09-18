package main.java.tasklist;

import main.java.Task;
import java.util.ArrayList;

/**
 * The TaskList class represents a collection of tasks and provides methods for managing tasks.
 */
public class TaskList implements Cloneable {
    private ArrayList<Task> taskList;
    private ArrayList<Task> previousTaskList;

    /**
     * Constructs a new `TaskList` instance with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
        this.previousTaskList = null;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.previousTaskList = this.copyTaskList();
        this.taskList.add(task);
    }

    /**
     * Revert to the previous iteration of tasklist.
     */
    public void revertPreviousTaskList() {
        if (this.previousTaskList != null) {
            this.taskList = this.previousTaskList;
        }
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
     * Retrieves a task from the task list at the specified index, this will change previousTaskList.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTaskToEdit(int i) {
        this.previousTaskList = this.copyTaskList();
        return this.taskList.get(i);
    }

    /**
     * Retrieves a task from the task list at the specified index, this will not change previousTaskList.
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
        this.previousTaskList = this.copyTaskList();
        this.taskList.remove(task);
    }

    public ArrayList<Task> copyTaskList() {
        ArrayList<Task> tmp = new ArrayList<Task>();
        int size = this.taskList.size();
        for (int i = 0; i < size; i++) {
            Task t = this.taskList.get(i).copy();
            tmp.add(t);
        }
        return tmp;
    }
}
