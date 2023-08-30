package duke;

import duke.DukeException;
import duke.Task;

import java.util.ArrayList;
/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList instance with the provided list of tasks.
     *
     * @param tasks The initial list of tasks for the TaskList.
     * @throws DukeException If the provided list of tasks is null.
     */
    public TaskList(ArrayList<Task> tasks) throws DukeException {
        if (tasks == null) {
            throw new DukeException("Empty taskList");
        } else {
            this.tasks = tasks;
        }
    }

    /**
     * Creates an empty TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    };

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the TaskList.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was removed from the TaskList.
     */
    public Task delete(int index) {
        Task removedTask = tasks.remove(index);
        return removedTask;
    }

    /**
     * Retrieves a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }
}
