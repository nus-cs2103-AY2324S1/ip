package duke.task;

import duke.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 * This class provides a container to store tasks and common functionalities
 * such as adding, getting, and removing tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a task list with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the task list based on the given index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes and returns a task from the task list based on the given index.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves the tasks in the task list.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    public TaskList findTasks(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("!!!: Please provide a description to search for");
        }
        return new TaskList(tasks.stream()
                .filter(task -> task.description.contains(description))
                .collect(Collectors.toCollection(ArrayList::new)));
    }
}
