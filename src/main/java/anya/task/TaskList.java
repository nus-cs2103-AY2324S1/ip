package anya.task;

import java.util.ArrayList;

/**
 * Represents a collection of tasks in the Anya application.
 * The `TaskList` class provides methods for adding, removing, accessing, and listing tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new empty `TaskList`.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new `TaskList` with the specified list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the `TaskList`.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the `TaskList`.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the `TaskList`.
     *
     * @param task The task to be removed.
     */
    public void remove(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Retrieves a task from the `TaskList` at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    public Task mark(int index) {
        return this.tasks.get(index).markAsDone();
    }

    public Task unmark(int index) {
        return this.tasks.get(index).markAsNotDone();
    }

    /**
     * Returns the number of tasks in the `TaskList`.
     *
     * @return The number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Lists all tasks in the `TaskList` with their corresponding indices.
     */
    public void list() {
        StringBuilder list = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            list.append(System.lineSeparator());
            list.append(String.format((i + 1) + ". " + tasks.get(i)));
        }
        System.out.println(list.toString());
    }

    /**
     * Finds and displays tasks containing a specified keyword in their descriptions.
     *
     * @param input The keyword to search for in task descriptions.
     */
    public TaskList find(String input) {
        TaskList matchingTasks = new TaskList();
        for (Task t : tasks) {
            if (t.getDescription().contains(input)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
}
