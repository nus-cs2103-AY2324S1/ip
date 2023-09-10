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
        store.add(task);
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
        if (index > store.size()) {
            throw new InvalidIndexException();
        }
        Task task = store.get(index - 1);
        store.remove(index - 1);
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
    /**
     * Updates the attributes of a task in the task list based on the provided index.
     * <p>
     * The method supports updating the description for all task types. For {@code Event} tasks,
     * the 'from' and 'to' attributes can be updated. For {@code Deadline} tasks, the 'by' attribute
     * can be updated. Only the provided attributes are updated, while the others remain unchanged.
     * </p>
     *
     * @param index Index of the task in the task list to be updated.
     * @param newDescription New description for the task. If {@code null}, description remains unchanged.
     * @param newFrom New starting time/date for an {@code Event} task. If {@code null} or if task is not an event,
     *               remains unchanged.
     * @param newTo New ending time/date for an {@code Event} task. If {@code null} or if task is not an event,
     *             remains unchanged.
     * @param newBy New due date for a {@code Deadline} task. If {@code null} or if task is not a deadline,
     *             remains unchanged.
     * @return A confirmation message indicating the updated task details.
     * @throws InvalidIndexException If the provided index is out of the range of the task list.
     */
    public String updateTask(int index, String newDescription, String newFrom, String newTo, String newBy)
            throws InvalidIndexException {
        validateIndex(index);
        Task task = store.get(index - 1);

        if (newDescription != null) {
            task.setDescription(newDescription);
        }

        if (task instanceof Event) {
            Event eventTask = (Event) task;
            if (newFrom != null) {
                eventTask.setFrom(newFrom);
            }
            if (newTo != null) {
                eventTask.setTo(newTo);
            }
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            if (newBy != null) {
                deadlineTask.setBy(newBy);
            }
        }

        return "Updated task:\n\t" + task;
    }

    /**
     * Validates the provided index for array list access.
     *
     * @param index The index to validate.
     * @throws InvalidIndexException If the index is out of bounds.
     */
    private void validateIndex(int index) throws InvalidIndexException {
        if (index <= 0 || index > store.size()) {
            throw new InvalidIndexException();
        }
    }
}
