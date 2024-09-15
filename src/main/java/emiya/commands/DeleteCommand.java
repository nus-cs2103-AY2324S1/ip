package emiya.commands;

import emiya.emiyaexception.EmptyDeleteException;
import emiya.emiyaexception.OutOfListBoundsException;
import emiya.emiyaexception.UnknownCommandException;
import emiya.logic.Logic;
import emiya.storage.Storage;
import emiya.task.Task;
import emiya.task.TaskList;
import emiya.ui.Ui;

/**
 * A class that represents the Delete command.
 */
public class DeleteCommand extends Command {

    /**
     * Removes a Task from the TaskList instance, at a specified position within the TaskList.
     *
     * @param posString The position at which the task should be deleted.
     * @param taskList The TaskList instance associated with the task bot.
     * @param storage The Storage instance associated with the task bot.
     * @param ui The Ui instance associated with the task bot.
     * @param fileName The name of the file that the newly updated task list will be written into.
     * @param dirName The name of the directory that the newly updated task list will be written into.
     * @return A String that indicates that the task has been successfully deleted from the task list.
     * @throws OutOfListBoundsException An exception that is thrown when the user tries to access a task that
     *     does not exist.
     * @throws EmptyDeleteException An exception that is thrown when the user uses a delete command without
     *     providing a list index.
     */
    public static String delete(String posString, TaskList taskList, Storage storage, Ui ui, String fileName,
                                String dirName) throws OutOfListBoundsException,
            EmptyDeleteException, UnknownCommandException {

        if (posString.equals("")) {
            throw new EmptyDeleteException();
        }

        if (!Logic.isNumeric(posString)) {
            throw new UnknownCommandException();
        }

        int pos = Integer.parseInt(posString);

        if (pos <= 0 || pos > taskList.size()) {
            throw new OutOfListBoundsException();
        }
        Task task = taskList.get(pos - 1);
        taskList.remove(task);
        storage.writeToFileFromTaskList(taskList, fileName, dirName);
        if (taskList.size() == 1) {
            return ui.deletedSingularMessage(task, taskList);
        } else {
            return ui.deletedPluralMessage(task, taskList);
        }
    }

    @Override
    public String giveHelpDescription() {
        return "Delete: This command deletes any task!\n"
                + "The format for the input is as follows:\n"
                + "delete INDEX,\n"
                + "where INDEX is the position of the task within the list.\n";
    }
}
