package carbonbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import carbonbot.command.AddCommand;
import carbonbot.command.Command;
import carbonbot.command.DeleteCommand;
import carbonbot.command.ExitCommand;
import carbonbot.command.FindCommand;
import carbonbot.command.ListCommand;
import carbonbot.command.MarkCommand;
import carbonbot.task.Deadline;
import carbonbot.task.Event;
import carbonbot.task.Todo;

/**
 * The Parser class helps to parse user input strings to return the appropriate Command.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command to be executed
     *
     * @param fullCommand The raw string the user has input
     * @return The Command if the fullCommand string was valid
     */
    public static Command parse(String fullCommand) throws DukeException {
        String commandType = fullCommand.split(" ")[0];

        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            // Get all the characters after todo as the description
            String desc = fullCommand.substring("todo".length()).trim();

            // Validates if the description is empty (or only whitespaces)
            if (desc.isBlank()) {
                throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new AddCommand(new Todo(desc));
            }
        case "deadline":
            // deadline: Adds a deadline Task to the list

            int indexOfBy = fullCommand.indexOf("/by");
            // Validates the existence of /by syntax
            if (indexOfBy == -1) {
                throw new DukeException(":( OOPS!!! Please specify the deadline using /by.");
            }

            desc = fullCommand.substring("deadline".length(), indexOfBy).trim();
            String by = fullCommand.substring(indexOfBy + "/by".length()).trim();
            if (desc.isBlank()) {
                throw new DukeException(":( OOPS!!! The description of a deadline cannot be empty.");
            }
            if (by.isBlank()) {
                throw new DukeException(":( OOPS!!! The 'by' of a deadline cannot be empty.");
            }

            try {
                LocalDateTime byDt = parseDateTimeString(by);
                return new AddCommand(new Deadline(desc, byDt));
            } catch (DateTimeParseException ex) {
                throw new DukeException(":( OOPS!!! The 'by' datetime was not in a valid format."
                        + " Example of valid datetime: 26/12/2019 1800");
            }
        case "event":
            //event: Adds a event Task to the list

            int indexOfFrom = fullCommand.indexOf("/from");
            int indexOfTo = fullCommand.indexOf("/to");
            if (indexOfFrom == -1 || indexOfTo == -1) {
                throw new DukeException(":( OOPS!!! Please specify the start and end of the"
                        + " event using /from and /to.");
            }
            if (indexOfFrom > indexOfTo) {
                throw new DukeException(":( OOPS!!! Please specify the /from before the /to!");
            }

            desc = fullCommand.substring("event ".length(), indexOfFrom).trim();
            String from = fullCommand.substring(indexOfFrom + "/from".length(), indexOfTo).trim();
            String to = fullCommand.substring(indexOfTo + "/to".length()).trim();

            if (desc.isBlank()) {
                throw new DukeException(":( OOPS!!! The description of an event cannot be empty.");
            }
            if (from.isBlank()) {
                throw new DukeException(":( OOPS!!! The 'from' of an event cannot be empty.");
            }
            if (to.isBlank()) {
                throw new DukeException(":( OOPS!!! The 'to' of an event cannot be empty.");
            }
            try {
                LocalDateTime fromDt = parseDateTimeString(from);
                LocalDateTime toDt = parseDateTimeString(to);
                return new AddCommand(new Event(desc, fromDt, toDt));
            } catch (DateTimeParseException ex) {
                throw new DukeException(":( OOPS!!! The given datetime was not in a valid format."
                        + " Example of valid datetime: 26/12/2019 1800");
            }
        case "mark":
            return new MarkCommand(getIntegerArgument(fullCommand), true);
        case "unmark":
            return new MarkCommand(getIntegerArgument(fullCommand), false);
        case "delete":
            return new DeleteCommand(getIntegerArgument(fullCommand));
        case "find":
            String keyword = fullCommand.substring("find".length()).trim();
            return new FindCommand(keyword);
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-("
                    + "\nMy supported commands are: list, mark, unmark, todo, deadline, event, find, bye.");
        }
    }

    private static LocalDateTime parseDateTimeString(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private static int getIntegerArgument(String fullCommand) throws DukeException {
        try {
            return Integer.parseInt(fullCommand.split(" ")[1]);
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please provide a valid integer for the index.");
        } catch (ArrayIndexOutOfBoundsException aie) {
            throw new DukeException("No index was provided. Please enter the task index to be updated.");
        }
    }

}
