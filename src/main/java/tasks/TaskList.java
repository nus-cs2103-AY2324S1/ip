package tasks;

import java.util.ArrayList;

/**
 * The `TaskList` class represents a collection of tasks and provides methods
 * for managing and interacting with these tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty `TaskList`.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the `TaskList`.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the `TaskList`.
     *
     * @param task The task to be removed.
     */
    public void remove(Task task) {
        tasks.remove(task);
    }

    /**
     * Retrieves a task from the `TaskList` based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task retrieve(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the `TaskList`.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Prints the list of tasks in the `TaskList`.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Finds and prints tasks in the `TaskList` that match a specified keyword.
     *
     * @param keyword The keyword to search for in task names.
     */
    public void find(String keyword) {
        int count = 0;
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                count++;
                System.out.println(count + ". " + task);
            }
        }
        if (count == 0) {
            System.out.println("No matching tasks found.");
        }
    }
}
