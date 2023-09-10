package duke.tasks;

import java.util.ArrayList;

import duke.exception.InvalidIndexException;

/**
 * Represents a list of tasks, allowing for various operations such as
 * adding, deleting, marking tasks as done, and unmarking tasks.
 *
 * <p>The TaskList class provides a comprehensive set of methods to manage and manipulate
 * a list of tasks. Each task in the list can be accessed by its index.
 * Methods throw {@link InvalidIndexException} if operations are attempted on invalid indices.</p>
 */
public class TaskList {

    private ArrayList<Task> store;

    /**
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this.store = new ArrayList<>();
    }

    /**
     * Initializes a task list with a given list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the task list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            throw new IllegalArgumentException("Task list cannot be null");
        }
        this.store = new ArrayList<>(tasks);
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
        return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n", task, store.size());
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
        validateIndex(index);
        Task task = store.remove(index - 1);
        assert store.size() == initialSize - 1 : "Task deletion failed!";
        return String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.\n", task, store.size());
    }

    /**
     * Lists all tasks currently in the list.
     *
     * @return Formatted string of all tasks.
     */
    public String listTasks() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        int counter = 1;
        for (Task task : store) {
            output.append(counter++).append(".").append(task).append("\n");
        }
        return output.toString();
    }
    /**
     * Marks a task as done based on its index.
     *
     * @param index Index of the task to be marked as done.
     * @return Confirmation message indicating task status change to done.
     * @throws InvalidIndexException If the provided index is out of bounds.
     */
    public String markTask(int index) throws InvalidIndexException {
        validateIndex(index);

        Task curr = store.get(index - 1);
        curr.mark();
        return String.format("Nice! I've marked this task as done:\n\t%s\n", curr);
    }

    /**
     * Marks a task as not done based on its index.
     *
     * @param index Index of the task to be marked as not done.
     * @return Confirmation message indicating task status change to not done.
     * @throws InvalidIndexException If the provided index is out of bounds.
     */
    public String unmarkTask(int index) throws InvalidIndexException {
        validateIndex(index);

        Task curr = store.get(index - 1);
        curr.unmark();
        return String.format("OK, I've marked this task as not done yet:\n\t%s\n", curr);
    }

    /**
     * Retrieves the current list of tasks.
     *
     * @return A clone of the ArrayList containing all the tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(store);
    }

    /**
     * Searches and provides tasks containing the specified keyword.
     *
     * @param keyword Keyword to search for in task descriptions.
     * @return Formatted string of matching tasks.
     */
    public String findTasks(String keyword) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        boolean found = false;

        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getDescription().contains(keyword)) {
                found = true;
                output.append(i + 1).append(".").append(store.get(i)).append("\n");
            }
        }

        if (!found) {
            output.append("No matching tasks found.\n");
        }

        return output.toString();
    }

    /**
     * Validates the provided index against the task store's size.
     *
     * @param index Index to validate.
     * @throws InvalidIndexException if the index is invalid.
     */
    private void validateIndex(int index) throws InvalidIndexException {
        if (index < 1 || index > store.size()) {
            throw new InvalidIndexException();
        }
    }
}
