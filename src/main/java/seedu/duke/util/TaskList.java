package seedu.duke.util;

import seedu.duke.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with a list of tasks.
     *
     * @param tasks An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the task from the task list at a specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task to the task list at a specified index.
     *
     * @param index The task to be deleted from the task list at a specified index.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Checks if the task list has any tasks.
     *
     * @return true if the task list has no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets a copy of all tasks in the task list.
     *
     * @return The list of all tasks in the task list.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}