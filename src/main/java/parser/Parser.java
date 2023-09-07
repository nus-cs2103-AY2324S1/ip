package parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import command.ByeCommand;
import command.ClearCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import dukeexception.DukeException;

/**
 * A parser that takes the input data and checks its syntax followed bt excute it.
 */
public class Parser {

    //final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * A method that takes the user input and calls the right command for it.
     *
     * @param fullCommand the user command input.
     * @return a command that will be excute base on the users input.
     * @throws DukeException if the command is invalid in any ways.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] split = fullCommand.split(" ", 2);
        String commandAction = split[0];
        switch (commandAction) {
        case "list":
            return new ListCommand();
        case "mark":
            if (split.length > 2) {
                throw new DukeException("Please state in this format (mark 1)");
            }
            try {
                return new MarkCommand(Integer.parseInt(split[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid Index");
            }
        case "unmark":
            if (split.length > 2) {
                throw new DukeException("Please state in this format (unmark 1)");
            }
            try {
                return new UnmarkCommand(Integer.parseInt(split[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid Index");
            }
        case "delete":
            if (split.length > 2) {
                throw new DukeException("Please state in this format (delete 1)");
            }
            try {
                return new DeleteCommand(Integer.parseInt(split[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid Index");
            }
        case "todo":
            if (split.length < 2 || split[1].isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new TodoCommand(split[1]);
        case "deadline":
            if (split.length < 2 || split[1].isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a Tasks.Deadline cannot be empty.");
            }
            String[] info = split[1].split("/by ");
            if (info.length != 2) {
                throw new DukeException("☹ OOPS!!! The description of a deadline is invalid.");
            }
            try {
                String deadDate = LocalDate.parse(info[1]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return new DeadlineCommand(info[0], deadDate);
            } catch (DateTimeException e) {
                throw new DukeException("☹ OOPS!!! The description of a time must be in yyyy-mm-dd");
            }
        case "event":
            if (split.length < 2 || split[1].isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a Tasks.Event cannot be empty.");
            }
            String[] infoEvent = split[1].split("/from | /to ");
            if (infoEvent.length != 3) {
                throw new DukeException("☹ OOPS!!! The description of a event is invalid.");
            }
            try {
                String startDate = LocalDate.parse(infoEvent[1]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                String endDate = LocalDate.parse(infoEvent[2]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return new EventCommand(infoEvent[0], startDate, endDate);
            } catch (DateTimeException e) {
                throw new DukeException("☹ OOPS!!! The description of a time must be in yyyy-mm-dd");
            }
        case "clear":
            return new ClearCommand();
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindCommand(split[1]);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
