package emiya.commands;

import emiya.emiyaexception.AlreadyUnmarkedException;
import emiya.emiyaexception.EmptyUnmarkException;
import emiya.emiyaexception.OutOfListBoundsException;
import emiya.emiyaexception.UnknownCommandException;
import emiya.logic.Logic;
import emiya.storage.Storage;
import emiya.task.TaskList;
import emiya.ui.Ui;

/**
 * A class that represents the Unmark command.
 */
public class UnmarkCommand extends Command {

    /**
     * Marks the task within the task list at the specified position as not completed.
     *
     * @param posString The position at which the task should be deleted.
     * @param taskList The TaskList instance associated with the task bot.
     * @param storage The Storage instance associated with the task bot.
     * @param ui The Ui instance associated with the task bot.
     * @param fileName The name of the file that the newly updated task list will be written into.
     * @param dirName The name of the directory that the newly updated task list will be written into.
     * @return A String that indicates that the task has been successfully marked in the task list.
     * @throws EmptyUnmarkException An exception that is thrown when the unmark command is used by the user
     *     without providing an index for the list
     * @throws OutOfListBoundsException An exception that is thrown when the user tries to access a task that
     *     does not exist.
     */
    public static String unmark(String posString, TaskList taskList, Storage storage, Ui ui, String fileName,
                                String dirName) throws EmptyUnmarkException, OutOfListBoundsException,
            UnknownCommandException, AlreadyUnmarkedException {

        if (posString.equals("")) {
            throw new EmptyUnmarkException();
        }

        if (!Logic.isNumeric(posString)) {
            throw new UnknownCommandException();
        }

        int pos = Integer.parseInt(posString);

        if (pos <= 0 || pos > taskList.size()) {
            throw new OutOfListBoundsException();
        }
        taskList.get(pos - 1).setUnmarked();
        storage.writeToFileFromTaskList(taskList, fileName, dirName);
        return ui.unmarkedMessage(pos, taskList);

    }

    @Override
    public String giveHelpDescription() {
        return "Unmark: This command marks any task in the list as incomplete!!\n"
                + "The format for the input is as follows:\n"
                + "unmark INDEX,\n"
                + "where INDEX is the position of the task within the list.\n";
    }
}
