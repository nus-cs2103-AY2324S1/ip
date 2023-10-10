package socrates.data.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import socrates.data.exception.InvalidTaskIndexException;

/**
 * Represents a collection of tasks within the chatbot application.
 */
public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList(Task... tasks) {
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns and removes the task specified from the collection of tasks.
     *
     * @param taskNumber The index of the task in the list, starting from 1.
     * @return The task specified.
     * @throws InvalidTaskIndexException If no such task exists.
     */
    public Task remove(int taskNumber) throws InvalidTaskIndexException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskIndexException("No such task exists.");
        }

        return tasks.remove(taskNumber - 1);
    }

    /**
     * Returns and marks the task specified as done.
     *
     * @param taskNumber The index of the task in the list, starting from 1.
     * @return The task specified.
     * @throws InvalidTaskIndexException If no such task exists.
     */
    public Task mark(int taskNumber) throws InvalidTaskIndexException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskIndexException("No such task exists.");
        }

        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Returns and marks the task specified as not done yet.
     *
     * @param taskNumber The index of the task in the list, starting from 1.
     * @return The task specified.
     * @throws InvalidTaskIndexException If no such task exists.
     */
    public Task unmark(int taskNumber) throws InvalidTaskIndexException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskIndexException("No such task exists.");
        }

        Task task = tasks.get(taskNumber - 1);
        task.markAsUndone();
        return task;
    }

    public void clear() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public String getStorageString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.getStorageString()).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns an instance of {@code TaskList} with the tasks that
     * match the given keyword.
     *
     * @param keyword The given keyword to match against the task descriptions.
     * @return An instance of {@code TaskList} with the matched tasks.
     */
    public TaskList filter(String keyword) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            boolean hasKeyword = task.toString().contains(keyword);

            if (hasKeyword) {
                filteredTasks.add(task);
            }
        }
        return new TaskList(filteredTasks);
    }

    /**
     * Returns the list of tasks as a formatted indexed list.
     *
     * @return List of tasks as a formatted indexed list.
     */
    public String getFormattedList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("\t%d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }
}
