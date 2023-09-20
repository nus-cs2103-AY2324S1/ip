package slay;

import slay.exception.InvalidTaskIndexException;
import slay.task.Task;
import slay.exception.DuplicatedMarkException;

import java.util.ArrayList;

/**
 * Represents a TaskList containing a list of tong.task.Task to be done.
 */
public class TaskList {
    public static final String MESSAGE_EMPTY_LIST = "Oops! Your task list is empty. Add some tasks now:)";
    public static final String PRINT_LIST_HEADING = "Slaying...";
    public static final String PRINT_LIST_ENDING = "---------- %1$d TASKS IN TOTAL ----------";

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return MESSAGE_EMPTY_LIST;
        }

        String result = PRINT_LIST_HEADING;
        for (int i = 0; i < this.tasks.size(); i++) {
            int visibleIndex = i + 1;
            result += "\n" + visibleIndex + ". " + this.tasks.get(i);
        }
        result += "\n" + String.format(PRINT_LIST_ENDING, this.tasks.size());

        return result;
    }

    public Boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the Task indicating by the input index.
     *
     * @param targetVisibleIndex Index of the target Task that is visible to the users.
     * @return The target Task.
     * @throws InvalidTaskIndexException If input index is out of bound.
     */
    public Task getTask(int targetVisibleIndex) throws InvalidTaskIndexException {
        int targetInvisibleIndex = targetVisibleIndex - 1;
        if (targetInvisibleIndex >= this.tasks.size() || targetVisibleIndex < 1) {
            throw new InvalidTaskIndexException("Input task index out of bound.");
        }
        return this.tasks.get(targetInvisibleIndex);
    }

    /**
     * Adds a new Task into the TaskList.
     *
     * @param task Task to be added into the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks the Task indicating by the input index as done.
     *
     * @param task Task to be marked as done.
     * @throws DuplicatedMarkException If the target Task has already been marked as done.
     */
    public void markTask(Task task) throws DuplicatedMarkException {
        task.markAsDone();
    }

    /**
     * Marks the Task indicating by the input index as not done.
     *
     * @param task Task to be marked as not done.
     * @throws DuplicatedMarkException If the target Task is currently marked as not done.
     */
    public void unmarkTask(Task task) throws DuplicatedMarkException {
        task.markAsNotDone();
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param task Task to be deleted from the TaskList.
     */
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Tags a Task with the specified label.
     *
     * @param task Task to be tagged.
     * @param label String representing the label to tag the Task with.
     */
    public void tagTask(Task task, String label) {
        task.tag(label);
    }
}
