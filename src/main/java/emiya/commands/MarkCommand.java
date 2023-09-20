package emiya.commands;

import emiya.emiyaexception.AlreadyMarkedException;
import emiya.emiyaexception.EmptyMarkException;
import emiya.emiyaexception.OutOfListBoundsException;
import emiya.emiyaexception.UnknownCommandException;
import emiya.logic.Logic;
import emiya.storage.Storage;
import emiya.task.TaskList;
import emiya.ui.Ui;

/**
 * A class that represents the Mark command.
 */
public class MarkCommand extends Command {

    /**
     * Marks the task within the task list at the specified position as completed.
     *
     * @param posString The position at which the task should be deleted.
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
    public static String mark(String posString, TaskList taskList, Storage storage, Ui ui, String fileName,
                              String dirName) throws EmptyMarkException, OutOfListBoundsException,
            UnknownCommandException, AlreadyMarkedException {

        if (posString.equals("")) {
            throw new EmptyMarkException();
        }

        if (!Logic.isNumeric(posString)) {
            throw new UnknownCommandException();
        }

        int pos = Integer.parseInt(posString);

        if (pos <= 0 || pos > taskList.size()) {
            throw new OutOfListBoundsException();
        }
        taskList.get(pos - 1).setMarked();
        storage.writeToFileFromTaskList(taskList, fileName, dirName);
        return ui.markedMessage(pos, taskList);
    }

    @Override
    public String giveHelpDescription() {
        return "Mark: This command marks any task in the list as completed!!\n"
                + "The format for the input is as follows:\n"
                + "mark INDEX,\n"
                + "where INDEX is the position of the task within the list.\n";
    }
}
