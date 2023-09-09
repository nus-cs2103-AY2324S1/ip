package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks, providing various operations like
 * adding, removing tasks, and retrieving the size of the list.
 */
public class TaskList {

<<<<<<< .merge_file_vXWGHb
    private final ArrayList<Task> tasks;
=======
    /** List to store tasks. */
    private ArrayList<Task> tasks;
>>>>>>> .merge_file_rWOu9c

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a task list with the given list of tasks.
     *
     * @param tasks List of tasks to initialize the task list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the end of the task list.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns a task at the specified index from the task list.
     * Throws an IndexOutOfBoundsException if the index is out of the range of the list.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed from the list.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the total number of tasks present in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Provides access to the internal list of tasks.
     *
     * @return The list of tasks stored.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
