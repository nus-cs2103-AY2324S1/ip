package Jelly.main;

import Jelly.task.Task;

import java.util.ArrayList;

/**
 * Contains all the commands regarding the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for a new empty tasklist.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a tasklist with existing tasks.
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the total number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a specified task into the list.
     *
     * @param task The task to be added into the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at the specified index in the list.
     *
     * @param index The index of the task to be removed.
     */
    public void delete(int index) {
        taskList.remove(index);
    }

    /**
     * Retrieves the task at the specified index in the list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task that was retrieved.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Getter for the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Marks a task at the specified index of the list, as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Marks a task at the specified index of the list, as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void markAsUndone(int index) {
        taskList.get(index).markAsUndone();
    }
}
