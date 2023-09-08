package dude.task;

import java.util.ArrayList;

import dude.exception.InvalidTaskIndexException;

/**
 * List of tasks.
 */
public class TaskList {
    public static final String emptyTaskList = "You currently have no tasks in your list.";
    public static final String taskListPrefix = "Here's your tasks list:\n";
    public static final String SEARCH_RESULTS_PREFIX = "Here are the matching tasks in your list:\n";
    public static final String NO_SEARCH_RESULTS_MSG = "Couldn't find any tasks matching \"%s\".";
    /**
     * Tasks stored by user.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList from existing list of tasks.
     *
     * @param tasks ArrayList of existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task to tasks list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove task from tasks list.
     *
     * @param index 1-based index of task to remove.
     * @return Removed task.
     * @throws InvalidTaskIndexException If task number does not exist.
     */
    public Task remove(int index) throws InvalidTaskIndexException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(String.format("%d", index));
        }
    }

    /**
     * Get number of tasks.
     *
     * @return Number of tasks.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Get task from list.
     *
     * @param index 1-based index of task to get.
     * @throws InvalidTaskIndexException If task number does not exist.
     */
    public Task getTask(int index) throws InvalidTaskIndexException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(String.format("%d", index));
        }
    }

    /**
     * Finds list of tasks with descriptions containing given substring.
     *
     * @param substring Substring to search (case-insensitive).
     * @return ArrayList of tasks with given keyword.
     */
    public ArrayList<Task> search(String substring) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(substring.strip().toLowerCase())) {
                results.add(task);
            }
        }
        return results;
    }

    /**
     * Finds list of tasks with descriptions containing given substring.
     *
     * @param substring Substring to search (case-insensitive).
     * @return String list of tasks with given keyword.
     */
    public String displaySearch(String substring) {
        StringBuilder tasksList = new StringBuilder(SEARCH_RESULTS_PREFIX);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.description.toLowerCase().contains(substring.strip().toLowerCase())) {
                String taskNumberPrefix = String.format("%3s-", i + 1);
                String taskStr = taskNumberPrefix + task + "\n";
                tasksList.append(taskStr);
            }
        }
        if (tasksList.toString().equals(SEARCH_RESULTS_PREFIX)) {
            return String.format(NO_SEARCH_RESULTS_MSG, substring);
        } else {
            return tasksList.toString();
        }
    }

    /**
     * Returns list of all tasks as a string.
     *
     * @return Tasks list formatted as a string.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return emptyTaskList;
        }
        StringBuilder tasksList = new StringBuilder(taskListPrefix);
        for (int i = 0; i < tasks.size(); i++) {
            String taskNumberPrefix = String.format("%3s-", i + 1);
            String taskStr = taskNumberPrefix + tasks.get(i).toString() + "\n";
            tasksList.append(taskStr);
        }
        return tasksList.toString();
    }

    /**
     * Gets list of all tasks as an ArrayList.
     *
     * @return ArrayList of task instances.
     */
    public ArrayList<Task> toArrayList() {
        return tasks;
    }
}
