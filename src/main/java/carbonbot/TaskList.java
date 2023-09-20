package carbonbot;

import java.util.ArrayList;
import java.util.List;

import carbonbot.task.Task;

/**
 * TaskList contains the task list and provides operations to interact with the tasks in the list.
 */
public class TaskList {
    private List<Task> tasks;

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
     * Searches for tasks containing the specific keyword within the task list, and
     * returns the matching tasks with their indexes as a single string.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A formatted string containing the indices and details of matching tasks.
     */
    public String findTasksFormatted(String keyword) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = this.get(i);
            if (t.getDescription().contains(keyword)) {
                sb.append(String.format("%d.%s%n", i, t));
            }
        }
        return sb.toString();
    }

    /**
     * Sets this task list to contain the tasks from the provided TaskList.
     * @param taskList TaskList to copy the tasks from.
     */
    public void setTaskList(TaskList taskList) {
        this.tasks = taskList.tasks;
    }

    /**
     * Serializes the tasks in the list to a String.
     *
     * @return Serialized task list.
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.serialize()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
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
     * @return Number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }
}
