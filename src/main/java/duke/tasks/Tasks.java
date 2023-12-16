package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of Task instances.
 */
public class Tasks {
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Total number of tasks.
     *
     * @return an integer value of the number of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a Task object given an index.
     *
     * @param idx the index to select the Task from
     * @return the Task object returned
     */
    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * Removes a Task object given the index.
     *
     * @param idx the index of the Task to be removed
     * @return the Task object removed
     */
    public Task remove(int idx) {
        return this.tasks.remove(idx);
    }

    /**
     * Adds a new Task
     *
     * @param task the Task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns a Task given an index while checking if the index is valid.
     *
     * @param id the index to retrieve the Task
     * @return the Task returned by the index
     */
    public Task getTaskByIndex(int id) {
        if (id > this.tasks.size() || id <= 0) {
            return null;
        }

        return this.tasks.get(id - 1);
    }

    /**
     * Gets all the Tasks currently held.
     *
     * @return a List of all the Tasks
     */
    public List<Task> getAll() {
        return this.tasks;
    }
}
