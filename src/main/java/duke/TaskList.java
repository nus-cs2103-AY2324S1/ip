package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the Duke application.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);

    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        this.tasks.remove(index);
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Prints all the tasks in the list.
     */
    public void printList() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Searches for tasks that contain the specified keyword and prints them.
     * If no tasks match the keyword, a message indicating no matches is printed.
     *
     * @param keyword The keyword to search for within the tasks.
     */
    public void find(String keyword) {
        int count = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).toString().contains(keyword)) {
                System.out.println((count + 1) + ". " + this.tasks.get(i));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No matching tasks found.");
        }
    }

}
