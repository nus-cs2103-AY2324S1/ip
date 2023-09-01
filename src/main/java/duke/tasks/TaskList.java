package duke.tasks;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 * A <code>TaskList</code> object corresponds to the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a <code>TaskList</code> object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a <code>TaskList</code> object with a selected list of tasks.
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list of tasks.
     * @param index The index of the task to be removed.
     * @return The task that is removed.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets the size of the list of tasks.
     * @return The size of the list of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     * @return The task that is marked as done.
     */
    public Task markDone(int index) {
        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    /**
     * Marks a task as not done.
     * @param index The index of the task to be marked as not done.
     * @return The task that is marked as not done.
     */
    public Task unmarkDone(int index) {
        Task task = tasks.get(index);
        task.unmarkDone();
        return task;
    }

    /**
     * Gets a task from the list of tasks.
     * @param index The index of the task to be retrieved.
     * @return The task that is retrieved.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
