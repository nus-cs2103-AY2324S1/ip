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
     * Checks if the given task is a duplicate of an existing task in the list.
     * @param task The task to be checked.
     * @return {@code true} if the task is a duplicate, {@code false} otherwise.
     */
    public boolean isDuplicate(Task task) {
        return tasks.contains(task);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) throws SimonException {
        assert task != null : "Task to be added cannot be null";
        if (isDuplicate(task)) {
            throw new SimonException("☹ OOPS!!! This task is a duplicate.");
        }
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

        Task task = tasks.get(index);
        assert task != null : "Task to be marked cannot be null";

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
        assert task != null : "Task to be deleted cannot be null";

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
        Task task = this.tasks.get(index);
        assert task != null : "Task retrieved should not be null";
        return task;
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

    /**
     * Parses the input to retrieve the keyword and finds tasks containing the keyword.
     *
     * @param inData The input command to be parsed.
     * @return A TaskList containing the matched tasks.
     * @throws SimonException If the input format is incorrect or no keyword is provided.
     */
    public TaskList findTasks(String inData) throws SimonException {
        String keyword = parseKeyword(inData);
        validateKeyword(keyword);
        return find(keyword);
    }

    /**
     * Parses the input to retrieve the keyword for the find command.
     *
     * @param inData The input command to be parsed.
     * @return The keyword from the input command.
     * @throws SimonException If no keyword is provided.
     */
    private String parseKeyword(String inData) throws SimonException {
        String[] split = inData.split(" ");
        try {
            return split[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a keyword to search.");
        }
    }

    /**
     * Validates that the keyword is not empty or null.
     *
     * @param keyword The keyword to be validated.
     * @throws SimonException If the keyword is empty or null.
     */
    private void validateKeyword(String keyword) throws SimonException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new SimonException("Keyword cannot be empty. Please provide a valid keyword.");
        }
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword The keyword to be searched in tasks.
     * @return A TaskList containing the matched tasks.
     */
    private TaskList find(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword to search for cannot be null or empty";
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return new TaskList(matchedTasks);
    }


}
