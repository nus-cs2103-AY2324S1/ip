package duke.task;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.DukeException;

/**
 * Represents a list of tasks.
 * This class provides a container to store tasks and common functionalities
 * such as adding, getting, and removing tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    /**
     * Initializes a task list with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(Task... tasks) {
        this.tasks.addAll(Arrays.asList(tasks));
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

    /**
     * Searches for tasks containing the specified description and returns them as a new TaskList.
     * <p>
     * This method takes a description string as input and filters out tasks from the current
     * list that contain the given description. The result is returned as a new TaskList.
     * If the provided description is blank, a DukeException is thrown.
     * </p>
     *
     * @param keyword The keyword to search for in tasks.
     * @return A new TaskList containing tasks that match the given description.
     * @throws DukeException If the provided description is blank.
     */
    public TaskList findTasks(String keyword) throws DukeException {
        if (keyword.isBlank()) {
            throw new DukeException("!!!: Please provide a description to search for");
        }
        return new TaskList(this.tasks.stream()
                .filter(t -> t.description.contains(keyword))
                .toArray(Task[]::new)
        );
    }
    /**
     * Retrieves a {@link TaskList} containing all {@link Deadline} tasks sorted by their deadlines in ascending order.
     * <p>
     * This method filters the tasks in the current list to only include {@link Deadline} tasks.
     * It then sorts these tasks based on their deadlines and returns the result as a new {@link TaskList}.
     * </p>
     *
     * @return A new {@link TaskList} containing {@link Deadline} tasks sorted by their deadlines.
     */
    public TaskList getSortedDeadlines() {
        Task[] sortedDeadlines = this.tasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .sorted((d1, d2) -> d1.getBy().compareTo(d2.getBy()))
                .toArray(Task[]::new);
        return new TaskList(sortedDeadlines);
    }
}
