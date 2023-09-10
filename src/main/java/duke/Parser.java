package duke;

import java.io.IOException;
import java.time.DateTimeException;

import duke.Command.ByeCommand;
import duke.Command.Command;
import duke.Command.DeadlineCommand;
import duke.Command.DeleteCommand;
import duke.Command.EventCommand;
import duke.Command.FindCommand;
import duke.Command.ListCommand;
import duke.Command.MarkCommand;
import duke.Command.ToDoCommand;
import duke.Command.UnmarkCommand;
import duke.Exception.DukeException;
import duke.task.Task;


/**
 * Interpretes and executes the inputs that the user passes in.
 */
public class Parser {
    private static Ui ui = new Ui();


    /**
     * Deals with user inputs beginning with mark, unmark,
     * delete, list.
     * It also deals with what happens when the user types in an unrecognised
     * command.
     *
     * @param input what the user is typing in.
     * @param storage the storage that is being used.
     * @param tasks the TaskList that is being used to store the tasks.
     * @throws DukeException
     * @throws NumberFormatException
     */

    public static Command userCommand(String input, Storage storage, TaskList tasks) throws DukeException,
            NumberFormatException {

        if (input.startsWith("mark")) {
            int taskIndex = Integer.parseInt(input.substring(5));

            if (taskIndex > tasks.getSize()) {
                throw new DukeException("\tThis number is out of bounds!");
            }
            if (tasks.getTask(taskIndex - 1).getStatusIcon() == "X") {
                throw new DukeException("\tThis task has already been marked as done!");
            }
            return new MarkCommand(taskIndex - 1);

        } else if (input.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(input.substring(7));
            if (taskIndex > tasks.getSize()) {
                throw new DukeException("\tThis number is out of bounds!");
            }
            if (tasks.getTask(taskIndex - 1).getStatusIcon() == " ") {
                throw new DukeException("\tThis task has already been marked as not done!");
            }
            return new UnmarkCommand(taskIndex - 1);

        } else if (input.startsWith("delete")) {
            int pos = Integer.parseInt(input.substring(7).trim());
            if (pos > tasks.getSize() || pos == 0) {
                throw new DukeException("\tThis number is out of bounds! ");
            }
            Task element = tasks.getTask(pos - 1);
            return new DeleteCommand(element, pos - 1);
        } else if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("find")) {
            String keyword = input.substring(5).trim();
            return new FindCommand(keyword);
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else {
            throw new DukeException("\tHey bud! Sorry I don't quite know what you mean :-(");
        }
    }


    /**
     * Deals with user commands to add a task to the tasklist.
     * If the command is not one that adds a task, it will call the userCommand
     * method.
     *
     * @param input what the user is typing in.
     * @param storage the storage that is being used.
     * @param tasks the TaskList that is being used to store the tasks.
     */
    public static Command addToList(String input, Storage storage, TaskList tasks) throws DukeException {
        if (input.startsWith("todo")) {
            if (input.trim().length() <= 4) {
                throw new DukeException("\t Sorry! The description of a todo cannot be empty :(");
            }
            return new ToDoCommand(input.substring(5));
        } else if (input.startsWith("deadline")) {
            if (input.trim().length() <= 8) {
                throw new DukeException("\t Sorry! The description of a deadline cannot be empty :(");
            }
            if (!input.contains("/by")) {
                throw new DukeException("\t Hey bud! Please include when the deadline is! "
                        + "\n\t For example you can type: deadline read /by 2023-09-01 1700");
            }
            int index = input.lastIndexOf("/by");
            if (input.substring(9, index).isEmpty()) {
                throw new DukeException("\t Sorry! The description of a deadline cannot be empty :(");
            }

            return new DeadlineCommand(input.substring(9, index - 1), input.substring(index + 4));

        } else if (input.startsWith("event")) {
            if (input.trim().length() <= 5) {
                throw new DukeException("\t Sorry! The description of a event cannot be empty :(");
            }
            if (!input.contains("/from")) {
                throw new DukeException("\t Hey bud! Please include when the event is!"
                        + "\n\t For example you can type: event hangout /from 2023-09-01 1700 /to 2023-09-01 2000");
            }
            int indexFrom = input.lastIndexOf("/from");
            int indexTo = input.lastIndexOf("/to");

            if ((input.substring(6, indexFrom).isEmpty())) {
                throw new DukeException("\t Sorry! The description of an event cannot be empty :(");
            }
            if (!input.contains("/to")) {
                throw new DukeException("\t Hey bud! Please include when the end date of the event is!"
                        + "\n\t For example you can type: event hangout /from 2023-09-01 1700 /to 2023-09-01 2000");
            }
            return new EventCommand(input.substring(6, indexFrom - 1),
                    input.substring(indexFrom + 6, indexTo - 1), input.substring(indexTo + 4));
        } else {
            return userCommand(input, storage, tasks);
        }
    }


    /**
     * Handles the various exceptions being thrown.
     *
     * @param e Exception that is being handled.
     */
    public static String handleException(Exception e) {
        if (e instanceof DukeException) {
            return e.getMessage();
        } else if (e instanceof DateTimeException) {
           return "\tPlease put a valid date and time in the format YYYY-MM-DD HHMM."
                    + "\n\tFor example: 2023-08-08 1800";
        } else if (e instanceof IOException) {
            return "\tAn error occurred while performing a file operation: " + e.getMessage();
        } else if (e instanceof NumberFormatException ) {
            return "\tYou can only perform this action on an integer!";
        } else {
            return "\tAn unexpected error occurred: " + e.getMessage();
        }
    }
}
