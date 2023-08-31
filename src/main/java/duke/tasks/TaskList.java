package duke.tasks;

import java.util.ArrayList;

import duke.exception.InvalidIndexException;

/**
 * Represents a list of tasks, allowing for various operations such as
 * adding, deleting, marking tasks as done, and unmarking tasks.
 *
 * <p> The TaskList class provides a comprehensive set of methods to manage and manipulate
 * a list of tasks. Each task in the list can be accessed by its index.
 * Methods throw {@link InvalidIndexException} if operations are attempted on invalid indices. </p>
 */
public class TaskList {
    private ArrayList<Task> store;

    /**
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        store = new ArrayList<>();
    }

    /**
     * Initializes a task list with a given list of tasks.
     *
     * @param tasks An ArrayList of tasks to be added to the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        store = tasks;
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    /**
     * Removes a task from the list based on its index and prints a confirmation message.
     *
     * @param index The index of the task to be deleted.
     * @throws InvalidIndexException If the provided index is out of bounds.
     */
    public void deleteTask(int index) throws InvalidIndexException {
        if (index > store.size()) {
            throw new InvalidIndexException();
        }
        Task task = store.get(index - 1);
        store.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    /**
     * Prints all the tasks currently in the list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task task: store) {
            System.out.println(counter + "." + task);
            counter++;
        }
    }

    /**
     * Marks a task as done based on its index and prints a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     * @throws InvalidIndexException If the provided index is out of bounds.
     */
    public void markTask(int index) throws InvalidIndexException {
        if (index > store.size()) {
            throw new InvalidIndexException();
        }
        Task curr = store.get(index - 1);
        curr.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + curr);
    }

    /**
     * Marks a task as not done based on its index and prints a confirmation message.
     *
     * @param index The index of the task to be marked as not done.
     * @throws InvalidIndexException If the provided index is out of bounds.
     */
    public void unmarkTask(int index) throws InvalidIndexException {
        if (index > store.size()) {
            throw new InvalidIndexException();
        }
        Task curr = store.get(index - 1);
        curr.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + curr);
    }

    /**
     * Retrieves the current list of tasks.
     *
     * @return An ArrayList containing all the tasks.
     */
    public ArrayList<Task> getTasks() {
        return store;
    }

    /**
     * Finds and prints tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void findTasks(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        boolean found = false;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getDescription().contains(keyword)) {
                found = true;
                System.out.println(i + 1 + "." + store.get(i));
            }
        }
        if (!found) {
            System.out.println("No matching tasks found.");
        }
    }
}
