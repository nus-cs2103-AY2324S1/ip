package task;

import task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the task.TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the specified index from the task list.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
