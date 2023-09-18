package dukduk;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manage them.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    private Ui ui;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Sets the list of tasks in the TaskList.
     *
     * @param tasks An ArrayList of tasks to set as the tasks in the TaskList.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks in the TaskList.
     *
     * @return An ArrayList of tasks stored in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the count of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Checks if a given task index is valid.
     *
     * @param taskIndex The index of the task to check.
     * @return True if the task index is valid, false otherwise.
     */
    private boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < tasks.size();
    }

    /**
     * Gets a task from the TaskList based on its index.
     *
     * @param taskIndex The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Task getTask(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            return tasks.get(taskIndex);
        }
        return null;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add to the TaskList.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public void markTaskAsDone(int taskIndex) {
        assert isValidTaskIndex(taskIndex) : "Invalid task index";
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).markAsDone();
        }
    }

    /**
     * Unmarks a task at the specified index as not done.
     *
     * @param taskIndex The index of the task to unmark.
     */
    public void unMarkTask(int taskIndex) {
        assert isValidTaskIndex(taskIndex) : "Invalid task index";
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).unmark();
        }
    }

    /**
     * Deletes a task at the specified index from the TaskList.
     *
     * @param taskIndex The index of the task to delete.
     * @throws DukdukException If the task index is invalid.
     */
    public String deleteTask(int taskIndex) throws DukdukException {
        assert isValidTaskIndex(taskIndex) : "Invalid task index";
        if (isValidTaskIndex(taskIndex)) {
            Task removedTask = tasks.remove(taskIndex);
            return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                    removedTask, tasks.size());
        } else {
            throw new DukdukException("OOPS!!! Task not found. Please provide a valid task number.");
        }
    }

    /**
     * Finds a task by searching for a keyword.
     *
     * @param keyword The index of the task to delete.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "Keyword cannot be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
