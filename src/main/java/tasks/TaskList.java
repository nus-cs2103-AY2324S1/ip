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
    public String printList() {
        StringBuilder ans = new StringBuilder();
        ans.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            ans.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return ans.toString();
    }

    /**
     * Finds and prints tasks in the `TaskList` that match a specified keyword.
     *
     * @param keyword The keyword to search for in task names.
     */
    public String find(String keyword) {
        int count = 0;
        StringBuilder ans = new StringBuilder();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                count++;
                ans.append(count + ". " + task + "\n");
            }
        }
        return ans.toString();
    }
}
