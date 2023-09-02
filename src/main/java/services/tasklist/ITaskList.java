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
     * @return the description of the task added and the number of tasks in the list.
     * @throws JarvisException if the task cannot be added.
     */
    String add(String description, CommandType taskType, String... args) throws JarvisException;

    /**
     * Deletes a task from the task list with the given task number.
     *
     * @param taskNumber the task number of the task to be deleted.
     * @return the description of the task deleted and the number of tasks in the list.
     * @throws JarvisException if the task cannot be deleted.
     */
    String delete(int taskNumber) throws JarvisException;

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword the keyword to be searched.
     * @return the list of tasks that contain the given keyword.
     */
    String find(String keyword);

    /**
     * Marks a task as done with the given task number.
     *
     * @param taskNumber the task number of the task to be marked as done.
     * @return the description of the task marked as done.
     * @throws JarvisException if the task cannot be marked as done.
     */
    String markDone(int taskNumber) throws JarvisException;

    /**
     * Marks a task as undone with the given task number.
     *
     * @param taskNumber the task number of the task to be marked as undone.
     * @return the description of the task marked as undone.
     * @throws JarvisException if the task cannot be marked as undone.
     */
    String markUndone(int taskNumber) throws JarvisException;

    /**
     * Prints the task list.
     *
     * @return the task list and each task's description.
     */
    String show();
}
