package duke.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks, providing methods for adding, removing,
 * querying, and managing tasks within the list.
 *
 * <p>This class encapsulates the behavior of a list of tasks, ensuring
 * a consistent and easy-to-use interface for managing tasks.</p>
 */
public class TaskList {

    /** The list of tasks. */
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a task list by copying tasks from another task list.
     *
     * @param list The source task list from which to copy tasks.
     */
    public TaskList(TaskList list) {
        this.tasks = new ArrayList<>(list.tasks);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes and returns a task from the list at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        Task taskToRemove = tasks.get(index);
        this.tasks.remove(index);
        return taskToRemove;
    }

    /**
     * @return The number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * @return A reference to the internal list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Clears all tasks from the list.
     */
    public void clearTasks() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Sets the internal list of tasks to the provided list of tasks.
     *
     * @param tasks The new list of tasks.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return {@code true} if the task list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * @return A string representation of the tasks in the list.
     */
    @Override
    public String toString() {
        StringBuilder listContent = new StringBuilder();
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            listContent.append(i + 1).append(". ").append(this.getTask(i)).append("\n");
        }
        return listContent.toString();
    }
}
