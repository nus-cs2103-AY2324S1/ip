package emiya.commands;

import emiya.emiyaexception.EmptyMarkException;
import emiya.emiyaexception.OutOfListBoundsException;
import emiya.storage.Storage;
import emiya.task.TaskList;
import emiya.ui.Ui;

/**
 * A class that represents the Mark command.
 */
public class MarkCommand {

    /**
     * Marks the task within the task list at the specified position as completed.
     *
     * @param pos The position at which the task should be deleted.
     * @param taskList The TaskList instance associated with the task bot.
     * @param storage The Storage instance associated with the task bot.
     * @param ui The Ui instance associated with the task bot.
     * @param fileName The name of the file that the newly updated task list will be written into.
     * @param dirName The name of the directory that the newly updated task list will be written into.
     * @return A String that indicates that the task has been successfully marked in the task list.
     * @throws EmptyMarkException An exception that is thrown when the mark command is used by the user without
     *     providing an index for the list
     * @throws OutOfListBoundsException An exception that is thrown when the user tries to access a task that
     *     does not exist.
     */
    public static String mark(Integer pos, TaskList taskList, Storage storage, Ui ui, String fileName, String dirName)
            throws EmptyMarkException, OutOfListBoundsException {
        if (pos != null) {
            if (pos <= 0 || pos > taskList.size()) {
                throw new OutOfListBoundsException();
            }
            taskList.get(pos - 1).setMarked();
            storage.writeToFileFromTaskList(taskList, fileName, dirName);
            return ui.markedMessage(pos, taskList);
        } else {
            throw new EmptyMarkException();
        }
    }
}
