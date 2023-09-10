package koko;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns and deletes the task at the specified index.
     * @param index The index of the task to return but delete.
     * @return The task at the specified index.
     * @throws DukeException If the index is out of bounds.
     */
    public Task deleteTaskAtIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     * @param index The index of the task to mark as done.
     * @return The task at the specified index.
     * @throws DukeException If the index is out of bounds.
     */
    public Task markTaskAtIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the specified index as undone.
     * @param index The index of the task to mark as undone.
     * @return The task at the specified index.
     * @throws DukeException If the index is out of bounds.
     */
    public Task unmarkTaskAtIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * Returns the size of the list of tasks.
     * @return The size of the list of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Converts the list of tasks to a string representation suitable for display in the UI.
     * @return A string representation of the list of tasks for Text UI.
     */
    public String toStringForUi() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .collect(Collectors.joining("\n"));
    }

    /**
     * Converts the list of tasks to a string representation suitable for storage in a file.
     * @return A string representation of the list of tasks for file storage.
     */
    public String toStringForFile() {
        return tasks.stream()
                .map(Task::toFileFormat)
                .collect(Collectors.joining("\n"));
    }

}
