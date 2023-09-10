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
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @return Confirmation message indicating task addition.
     */
    public String addTask(Task task) {
        int initialSize = store.size();
        store.add(task);
        assert store.size() == initialSize + 1 : "Task addition failed!";
        String output = "";
        output += "Got it. I've added this task:\n";
        output += "\t" + task + "\n";
        output += "Now you have " + store.size() + " tasks in the list.\n";
        return output;
    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param index Index of the task to be deleted.
     * @return Confirmation message indicating task deletion.
     * @throws InvalidIndexException If the provided index is out of bounds.
     */
    public String deleteTask(int index) throws InvalidIndexException {
        int initialSize = store.size();
        if (index > store.size()) {
            throw new InvalidIndexException();
        }
        Task task = store.get(index - 1);
        store.remove(index - 1);
        assert store.size() == initialSize - 1 : "Task deletion failed!";
        String output = "";
        output += "Noted. I've removed this task:\n";
        output += "\t" + task + "\n";
        output += "Now you have " + store.size() + " tasks in the list.\n";
        return output;
    }

    /**
     * Provides a list of all tasks currently in the list.
     *
     * @return Formatted string of all tasks.
     */
    public String listTasks() {
        int counter = 1;
        String output = "Here are the tasks in your list:\n";
        for (Task task: store) {
            output += counter + "." + task + "\n";
            counter++;
        }
        return output;
    }

    /**
     * Marks a task as done based on its index.
     *
     * @param index Index of the task to be marked as done.
     * @return Confirmation message indicating task status change to done.
     * @throws InvalidIndexException If the provided index is out of bounds.
     */
    public String markTask(int index) throws InvalidIndexException {
        if (index > store.size()) {
            throw new InvalidIndexException();
        }
        Task curr = store.get(index - 1);
        String output = "";
        curr.mark();
        output += "Nice! I've marked this task as done:\n";
        output += "\t" + curr + "\n";
        return output;
    }

    /**
     * Marks a task as not done based on its index.
     *
     * @param index Index of the task to be marked as not done.
     * @return Confirmation message indicating task status change to not done.
     * @throws InvalidIndexException If the provided index is out of bounds.
     */
    public String unmarkTask(int index) throws InvalidIndexException {
        if (index > store.size()) {
            throw new InvalidIndexException();
        }
        Task curr = store.get(index - 1);
        String output = "";
        curr.unmark();
        output += "OK, I've marked this task as not done yet\n:";
        output += "\t" + curr + "\n";
        return output;
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
     * Searches and provides tasks containing the specified keyword.
     *
     * @param keyword Keyword to search for in task descriptions.
     * @return Formatted string of matching tasks.
     */
    public String findTasks(String keyword) {
        String output = "";
        output += "Here are the matching tasks in your list:\n";
        boolean found = false;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getDescription().contains(keyword)) {
                found = true;
                output += i + 1 + "." + store.get(i) + "\n";
            }
        }
        if (!found) {
            output += "No matching tasks found.\n";
        }
        return output;
    }
}
