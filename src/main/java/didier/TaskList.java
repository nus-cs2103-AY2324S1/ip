package didier;

import java.util.ArrayList;

import didier.exception.TaskNumberException;
import didier.task.Task;

/**
 * Represents a list of tasks that the bot stores. Allows for several operations
 * on this list, so that the user can manipulate their task list by interacting with
 * the bot.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList.
     * Initialises the arrayList internally used to represent the task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
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
     * Removes the task at the requested index from the task list and returns the removed task.
     *
     * @param taskNumber The index of the task to be removed (1-based indexing).
     * @return The task removed.
     * @throws TaskNumberException If taskNumber is an invalid index.
     */
    public Task removeTask(int taskNumber) throws TaskNumberException {
        try {
            return tasks.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberException(String.valueOf(taskNumber));
        }
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Fetches the task at the requested index from the task list and returns the task.
     *
     * @param taskNumber The index of the desired task (1-based indexing).
     * @return The task at index given by taskNumber.
     * @throws TaskNumberException If taskNumber is an invalid index.
     */
    public Task getTask(int taskNumber) throws TaskNumberException {
        try {
            return tasks.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberException(String.valueOf(taskNumber));
        }
    }

    /**
     * Returns whether the task list contains the given task.
     *
     * @param task The task to be queried.
     * @return True if the task list contains the task else false.
     */
    public boolean containsTask(Task task) {
        return tasks.contains(task);
    }
}
