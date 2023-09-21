package dre.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import dre.exception.DreException;

/**
 * Represents a list of tasks, providing functionalities to manage and manipulate tasks.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Creates a new empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new task list with the given initial tasks.
     *
     * @param tasks A list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Changes the index starting from 1 in the wrapper class to start from 0.
     *
     * @param index the index to be adjusted.
     * @return adjusted index.
     */
    private int adjustIndex(int index) {
        return index - 1;
    }

    /**
     * Marks a task as done using its index.
     *
     * @param index The index of the task to be marked as done.
     * @throws DreException If the provided index is invalid.
     */
    public void mark(int index) throws DreException {
        try {
            Task task = tasks.get(adjustIndex(index));
            task.done();
        } catch (IndexOutOfBoundsException e) {
            throw new DreException("Invalid dre.task index.");
        }
    }

    /**
     * Marks a task as undone using its index.
     *
     * @param index The index of the task to be unmarked.
     * @throws DreException If the provided index is invalid.
     */
    public void unmark(int index) throws DreException {
        try {
            Task task = tasks.get(adjustIndex(index));
            task.undo();
        } catch (IndexOutOfBoundsException e) {
            throw new DreException("Invalid dre.task index.");
        }
    }

    /**
     * Retrieves a task from the list using its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws DreException If the provided index is invalid.
     */
    public Task getTask(int index) throws DreException {
        try {
            return tasks.get(adjustIndex(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DreException("Invalid dre.task index.");
        }
    }

    /**
     * Deletes a task from the list using its index.
     *
     * @param index The index of the task to be deleted.
     * @throws DreException If the provided index is invalid.
     */
    public void deleteTask(int index) throws DreException {
        try {
            tasks.remove(adjustIndex(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DreException("Invalid dre.task index.");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Finds task in the tasklist that contain the keyword.
     *
     * @param keyword The String used for search.
     * @return TaskList of tasks that match the search.
     */
    public TaskList findTasksByKeyword(String keyword) {
        List<Task> foundTasks = this.tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return new TaskList(foundTasks);
    }

    /**
     * Retrieves the size (number of tasks) of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }
}
