package carbonbot;

import java.util.ArrayList;
import java.util.List;

import carbonbot.task.Task;

/**
 * TaskList contains the task list and provides operations to interact with the tasks in the list.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given a list of tasks.
     * @param tasks List of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the index.
     *
     * @param index 1-based index of the task
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        if (index <= tasks.size() && index >= 1) {
            tasks.remove(index - 1);
        } else {
            throw new IndexOutOfBoundsException("The task list does not contain the given index.");
        }
    }

    /**
     * Fetches the task at the index.
     *
     * @param index 1-based index of the task
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        if (index <= tasks.size() && index >= 1) {
            return tasks.get(index - 1);
        } else {
            throw new IndexOutOfBoundsException("The task list does not contain the given index.");
        }
    }

    /**
     * Serializes the tasks in the list to a String.
     *
     * @return Serialized tasks
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.serialize()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtains the tasks in the task list as a single string.
     * @return The tasks in the list labelled with an index
     */
    public String getTasks() {
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        for (Task t : tasks) {
            sb.append(String.format("%d.%s%n", idx, t));
            idx++;
        }
        return sb.toString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Size of the list
     */
    public int size() {
        return this.tasks.size();
    }
}
