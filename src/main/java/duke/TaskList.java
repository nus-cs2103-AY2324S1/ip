package duke;

import duke.exceptions.InvalidTaskIndexException;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * The class containing methods to manipulate the task list of the user.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * A method to grab the task in the task list using an index specifier. The taskList is 0-indexed.
     *
     * @param idx The index of the task in the task list.
     * @return The task in the task list of index idx.
     * @throws InvalidTaskIndexException If the given index is out of the range of the task list.
     */
    public Task getTask(int idx) throws InvalidTaskIndexException {
        if (idx < 0 || idx > taskList.size() - 1) {
            throw new InvalidTaskIndexException();
        }
        return taskList.get(idx);
    }

    /**
     * Adds a task into the task list.
     *
     * @param task The task to be added into the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list using an index specifier.
     *
     * @param idx The index of the task to be removed.
     * @throws InvalidTaskIndexException If the given index is out of the range of the task list.
     */
    public void removeTask (int idx) throws InvalidTaskIndexException {
        if (idx < 0 || idx > taskList.size() - 1) {
            throw new InvalidTaskIndexException();
        }
        taskList.remove(idx);
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }
}
