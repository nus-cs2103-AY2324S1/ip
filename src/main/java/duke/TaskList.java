package duke;// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84

import java.util.ArrayList;

/**
 * Manages a list of tasks in the Duke application.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param task The task to be deleted.
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Sets the tasks in the TaskList to the provided list of tasks.
     *
     * @param tasks The list of tasks to set in the TaskList.
     */
    // Set the tasks in the duke.TaskList
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int numTasks() {
        return tasks.size();
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the list of all tasks.
     *
     * @return The list of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
