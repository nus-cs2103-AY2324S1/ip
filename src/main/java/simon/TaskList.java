package simon;

import java.util.ArrayList;
import simon.task.Task;


/**
 * The {@code TaskList} class manages a list of tasks, allowing for operations such as
 * adding, marking, and deleting tasks.
 */
public class TaskList {
    /** List containing tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new {@code TaskList} with the given list of tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks or unmarks a task as done based on the provided index.
     *
     * @param inData The input string containing the task index.
     * @param markAsDone Whether the task should be marked as done or undone.
     * @return The task that was marked or unmarked.
     * @throws SimonException If there's an issue with the provided index.
     */
    public Task markTask(String inData, boolean markAsDone) throws SimonException {
        int index = parseIndex(inData);
        validateIndex(index);

        if (markAsDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsUndone();
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task from the list based on the provided index.
     *
     * @param inData The input string containing the task index.
     * @return The task that was deleted.
     * @throws SimonException If there's an issue with the provided index.
     */
    public Task deleteTask(String inData) throws SimonException {
        int index = parseIndex(inData);
        validateIndex(index);

        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Parses the provided input data to extract the task index.
     *
     * @param inData The input string containing the task index.
     * @return The parsed task index.
     * @throws SimonException If the provided data does not contain a valid index.
     */
    private int parseIndex(String inData) throws SimonException {
        String[] split = inData.split(" ");

        try {
            return Integer.parseInt(split[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SimonException("Please provide a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a task number.");
        }
    }

    /**
     * Validates if the given index is within the bounds of the task list.
     *
     * @param index The task index to validate.
     * @throws SimonException If the index is out of bounds.
     */
    private void validateIndex(int index) throws SimonException {
        if (this.tasks.isEmpty()) {
            throw new SimonException("There are no tasks to modify.");
        }
        if (index < 0 || index >= this.tasks.size()) {
            throw new SimonException("Invalid task number. Choose a number between 1 and " + tasks.size() + ".");
        }
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The total number of tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Returns a copy of all tasks in the list.
     *
     * @return An {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }
}
