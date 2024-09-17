package duke.tasks;

import java.util.ArrayList;

/**
 * Represents list of tasks
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates new TaskList object
     *
     * @param tasks can be empty list or existing tasks from Storage
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds tasks to List
     *
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets all tasks
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves specific task by index
     *
     * @param index
     * @return Task
     */
    public Task getOneTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes specific task by index
     *
     * @param index
     * @return Task removed
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Get number of tasks in list
     *
     * @return int
     */
    public int listSize() {
        return tasks.size();
    }
}
