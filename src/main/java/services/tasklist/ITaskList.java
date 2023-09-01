package services.tasklist;

import command.CommandType;
import services.bizerrors.JarvisException;

/**
 * Represents a task list manager.
 * It is responsible for adding, deleting, marking tasks as done/undone, and printing the task list.
 */
public interface ITaskList {
    /**
     * Adds a task to the task list.
     *
     * @param description the content of the task.
     * @param taskType    the type of the task. See {@link CommandType}.
     * @param args        the argument list of the task.
     * @throws JarvisException if the task cannot be added.
     */
    void add(String description, CommandType taskType, String... args) throws JarvisException;

    /**
     * Deletes a task from the task list with the given task number.
     *
     * @param taskNumber the task number of the task to be deleted.
     * @throws JarvisException if the task cannot be deleted.
     */
    void delete(int taskNumber) throws JarvisException;

    /**
     * Marks a task as done with the given task number.
     *
     * @param taskNumber the task number of the task to be marked as done.
     * @throws JarvisException if the task cannot be marked as done.
     */
    void markDone(int taskNumber) throws JarvisException;

    /**
     * Marks a task as undone with the given task number.
     *
     * @param taskNumber the task number of the task to be marked as undone.
     * @throws JarvisException if the task cannot be marked as undone.
     */
    void markUndone(int taskNumber) throws JarvisException;

    /**
     * Prints the task list in order.
     */
    void show();
}
