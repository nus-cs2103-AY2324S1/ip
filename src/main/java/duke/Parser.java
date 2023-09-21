package duke;

import java.io.IOException;
import java.time.DateTimeException;

import duke.Command.ByeCommand;
import duke.Command.Command;
import duke.Command.DeadlineCommand;
import duke.Command.DeleteCommand;
import duke.Command.EventCommand;
import duke.Command.FindCommand;
import duke.Command.HelpCommand;
import duke.Command.ListCommand;
import duke.Command.MarkCommand;
import duke.Command.MassOpsCommand;
import duke.Command.ToDoCommand;
import duke.Command.UnmarkCommand;
import duke.Exception.DukeException;


/**
 * Interprets and executes the inputs that the user passes in.
 */
public class Parser {
    private static Ui ui = new Ui();


    /**
     * Deals with user inputs beginning with mark, unmark, delete, list and find.
     * It also deals with what happens when the user types in an unrecognised
     * command.
     *
     * @param input what the user is typing in.
     * @throws DukeException
     * @throws NumberFormatException
     */

    public static Command userCommand(String input) throws DukeException,
            NumberFormatException {
        if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("massDelete")) {
            return new MassOpsCommand(input);
        } else if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else {
            throw new DukeException("Hey bud! Sorry I don't quite know what you mean :-(");
        }
    }


    /**
     * Deals with user commands to add a task to the tasklist.
     * If the command is not one that adds a task, it will call the userCommand
     * method.
     *
     * @param input what the user is typing in.
     */
    public static Command addToList(String input) throws DukeException {
        if (input.startsWith("todo")) {
            return new ToDoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else {
            return userCommand(input);
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
            return "Please put a valid date and time in the format YYYY-MM-DD HHMM."
                    + "\nFor example: 2023-08-08 1800";
        } else if (e instanceof IOException) {
            return "An error occurred while performing a file operation: " + e.getMessage();
        } else if (e instanceof NumberFormatException ) {
            return "You can only perform this action on an integer!";
        } else {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }
}
