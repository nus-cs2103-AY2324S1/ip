package emiya.commands;

import emiya.emiyaexception.EmptyDeadlineException;
import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.NoByException;
import emiya.emiyaexception.WrongDateFormatException;
import emiya.parser.Parser;
import emiya.storage.Storage;
import emiya.task.Deadline;
import emiya.task.TaskList;
import emiya.ui.Ui;

/**
 * A class that represents the Deadline command.
 */
public class DeadlineCommand {

    /**
     * Creates a Deadline task object and adds the task into the task list.
     *
     * @param taskDetails A String that contains the input from the user.
     * @param parser The Parser instance associated with the task bot.
     * @param taskList The TaskList instance associated with the task bot.
     * @param storage The Storage instance associated with the task bot.
     * @param ui The Ui instance associated with the task bot.
     * @param fileName The name of the file that the newly updated task list will be written into.
     * @param dirName The name of the directory that the newly updated task list will be written into.
     * @return A String that indicates that the Deadline task has been successfully added
     *     into the task list.
     * @throws EmptyDeadlineException An exception that is thrown when the user uses a deadline command
     *     but does not input in task details.
     * @throws NoByException An exception that is thrown when the user does not include /by in their deadline command.
     * @throws InvalidDateException An exception that is thrown when the user input contains an invalid date.
     * @throws WrongDateFormatException An exception that is thrown when the date that the user gives
     *     is in the wrong format.
     */
    public static String createDeadline(String taskDetails, Parser parser, TaskList taskList, Storage storage,
            Ui ui, String fileName, String dirName) throws EmptyDeadlineException,
            NoByException, InvalidDateException, WrongDateFormatException {
        if (taskDetails.equals("")) {
            throw new EmptyDeadlineException();
        }
        String[] deadlineDetails = parser.parseForDeadline(taskDetails);
        Deadline deadline = new Deadline(false, deadlineDetails[0], deadlineDetails[1]);
        taskList.add(deadline);
        storage.writeToFileFromTaskList(taskList, fileName, dirName);
        if (taskList.size() == 1) {
            return ui.addedSingularMessage(deadline, taskList);
        } else {
            return ui.addedPluralMessage(deadline, taskList);
        }
    }
}
