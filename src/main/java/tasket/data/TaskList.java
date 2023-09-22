package tasket.data;

import static tasket.commons.Messages.MESSAGE_EXCEEDS_LIST;
import static tasket.commons.Messages.MESSAGE_LESS_THAN_ONE;

import java.util.ArrayList;

import tasket.exception.TasketException;
import tasket.task.Task;

/**
 * The class for task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * The constructor for task list.
     * This is called when there are tasks saved before.
     *
     * @param tasks A task list with saved tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null;
        this.tasks = tasks;
    }

    /**
     * The constructor for task list.
     * This is called when there are no saved tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert task != null;
        tasks.add(task);
    }

    /**
     * Marks a task to be completed.
     *
     * @param taskNo The index for the task to be marked.
     */
    public void mark(int taskNo) {
        tasks.get(taskNo).markAsDone();
    }

    /**
     * Marks a task to be not completed.
     *
     * @param taskNo The index for the task to be marked.
     */
    public void unmark(int taskNo) {
        tasks.get(taskNo).markAsUndone();
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskNo The index for the task to be removed.
     */
    public Task remove(int taskNo) {
        return tasks.remove(taskNo);
    }

    /**
     * Returns the task in string format.
     *
     * @param taskNo The index for the task to be obtained.
     * @return The task in string format.
     */
    public String getTaskString(int taskNo) {
        return tasks.get(taskNo).toString();
    }

    /**
     * Returns the task in save format.
     *
     * @param taskNo The index for the task to be obtained.
     * @return The task in save format.
     */
    public String getTaskSaveString(int taskNo) {
        return tasks.get(taskNo).toSaveString();
    }

    /**
     * Checks if the index exceeds the list range.
     * @param index The index.
     * @throws TasketException If the index exceeds the range.
     */
    public void checkIndexRange(int index) throws TasketException {
        if (index <= 0) {
            throw new TasketException(MESSAGE_LESS_THAN_ONE);
        } else if (index > size()) {
            throw new TasketException(MESSAGE_EXCEEDS_LIST);
        }
    }
}
