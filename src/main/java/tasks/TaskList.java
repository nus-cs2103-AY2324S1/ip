package tasks;

import java.util.ArrayList;

import exceptions.DukeException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** List of tasks */
    private ArrayList<Task> userTasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.userTasks = new ArrayList<Task>();
    }
    /**
     * Constructor for TaskList.
     * @param userTasks
     */
    public TaskList(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
    }

    /**
     * Set the list of tasks.
     * @param userTasks
     */
    public void setTasks(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
    }

    /**
     * Adds a task to list.
     * @param task
     */
    public void add(Task task) {
        this.userTasks.add(task);

    }

    /**
     * Deletes a task from list.
     * @param taskId
     */
    public void delete(int taskId) {
        this.userTasks.remove(taskId);
    }

    /**
     * Clears the list of tasks.
     */
    public void clear() {
        this.userTasks.clear();
    }

    /**
     * Marks a task as done.
     * @param taskId
     * @throws DukeException
     */
    public void mark(int taskId) throws DukeException {
        this.userTasks.get(taskId).markAsDone();
    }

    /**
     * Marks a task as undone.
     * @param taskId
     * @throws DukeException
     */
    public void unmark(int taskId) throws DukeException {
        this.userTasks.get(taskId).markAsUndone();
    }

    /**
     * Returns the specified task.
     * @param taskID
     * @return Task task
     */
    public Task get(int taskID) {
        return this.userTasks.get(taskID);
    }

    /**
     * Returns the size of the list.
     * @return int size
     */
    public int size() {
        return this.userTasks.size();
    }

    /**
     * Returns the string representation of the list of tasks.
     * @return String
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < userTasks.size(); i++) {
            output += (i + 1) + ". " + userTasks.get(i).toString() + "\n";
        }
        return output;
    }
}
