package duke.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Encapsulates a list of tasks.
 * It provides methods to add, remove, and get tasks.
 * It also provides a method to get the number of tasks in the list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     * The list of tasks is represented as an ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the given list of tasks.
     * The list of tasks is represented as an ArrayList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the given task to the list of tasks.
     *
     * @param task The task to be added to the list of tasks.
     */
    public void add(Task task) {
        tasks.add(task);
    }


    /**
     * Returns the task at the given index in the list of tasks.
     *
     * @param index The index of the task to be returned.
     * @return The task at the given index in the list of tasks.
     * @throws DukeException If the given index is invalid.
     */
    public Task get(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list of tasks.
     *
     * @return The number of tasks in the list of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes the task at the given index in the list of tasks.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     * @throws DukeException If the given index is invalid.
     */
    public Task remove(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index");
        } else if (tasks.size() == 0) {
            throw new DukeException("There are no tasks to delete");
        }

        Task deletedTask = this.get(index);
        tasks.remove(index);
        return deletedTask;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a new TaskList object with tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to filter the tasks by.
     * @return A new TaskList object with tasks that contain the given keyword in their description.
     */
    public TaskList filter(String keyword) {
        List<Task> filtered = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return new TaskList(filtered);
    }
}
