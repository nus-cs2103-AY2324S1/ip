package fishron;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a task list with the given initial list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        this.taskList.get(index - 1).markAsDone();
    }

    /**
     * Marks a task as undone at the specified index.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void markAsUndone(int index) {
        this.taskList.get(index - 1).markAsUndone();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }
}
