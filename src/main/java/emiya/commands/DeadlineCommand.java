package emiya.commands;

import emiya.emiyaexception.EmptyDeadlineException;
import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.NoByException;
import emiya.emiyaexception.UnknownCommandException;
import emiya.emiyaexception.WrongDateTimeFormatException;
import emiya.parser.Parser;
import emiya.storage.Storage;
import emiya.task.Deadline;
import emiya.task.TaskList;
import emiya.ui.Ui;

/**
 * A class that represents the Deadline command.
 */
public class DeadlineCommand implements Command {

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
     * @throws WrongDateTimeFormatException An exception that is thrown when the date that the user gives
     *     is in the wrong format.
     * @throws UnknownCommandException An exception that is thrown when an unknown command is given by the user.
     */
    public static String createDeadline(String taskDetails, Parser parser, TaskList taskList, Storage storage,
            Ui ui, String fileName, String dirName) throws EmptyDeadlineException,
            NoByException, InvalidDateException, WrongDateTimeFormatException, UnknownCommandException {
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

    @Override
    public String giveHelpDescription() {
        return "Deadline: This command creates a Deadline to help you keep track of any tasks with a deadline!\n"
                + "The format for the input is as follows:\n"
                + "deadline NAME_OF_TASK /by DATE_TIME,\n"
                + "where DATE_TIME is in the format YYYY-MM-DD TTTT.\n"
                + "Note that the time given has to be in 24 hour format!\n";
    }
}
