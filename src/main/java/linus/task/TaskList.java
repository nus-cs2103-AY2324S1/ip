package linus.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import linus.exception.LinusException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks = null;

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
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds the specified task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to be deleted.
     * @throws LinusException
     */
    public void delete(int index) throws LinusException {
        if (index < 0 || index >= tasks.size()) {
            throw new LinusException("Cannot delete task. Please provide a valid index.");
        }
        Task task = tasks.get(index);
        tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws LinusException
     */
    public void mark(int index) throws LinusException {
        if (index < 0 || index >= tasks.size()) {
            throw new LinusException("Cannot mark task. Please provide a valid index.");
        }
        tasks.get(index).mark();
    }

    /**
     * Unmarks the task at the specified index as done.
     *
     * @param index The index of the task to be unmarked as done.
     * @throws LinusException
     */
    public void unmark(int index) throws LinusException {
        if (index < 0 || index >= tasks.size()) {
            throw new LinusException("Cannot unmark task. Please provide a valid index.");
        }
        tasks.get(index).unmark();
    }

    /**
     * Finds and prints the tasks that contain the specified keyword.
     *
     * @param keyword The keyword to be searched for.
     */
    public List<Task> find(String keyword) {
        return tasks.stream()
                .filter(task -> task.description.contains(keyword))
                .collect(Collectors.toList());
    }
}
