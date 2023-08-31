package duke.parser;

import duke.Duke;
import duke.command.*;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The `Parser` class is responsible for parsing user input commands into executable commands.
 */
public class Parser {

    /**
     * Parses a user input command and returns the corresponding `Command` object.
     *
     * @param command The user input command to be parsed.
     * @return A `Command` object representing the parsed command.
     * @throws DukeException If the command cannot be parsed or contains invalid arguments.
     */
    public static Command parse(String command) throws DukeException {
        String[] commandArr = command.split(" ", 2);
        String keyword = commandArr[0];
        String description;
        switch (keyword) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! Please include the task number you would like to mark.");
            }
            try {
                int markNumber = Integer.parseInt(commandArr[1].trim());
                return new MarkCommand(markNumber);
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!! Please indicate a number for the task.");
            }
        case "unmark":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! Please include the task number you would like to unmark.");
            }
            try {
                int unmarkNumber = Integer.parseInt(commandArr[1].trim());
                return new UnmarkCommand(unmarkNumber);
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!! Please indicate a number for the task.");
            }
        case "delete":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! Please include the task number you would like to delete.");
            }
            try {
                int deleteNumber = Integer.parseInt(commandArr[1].trim());
                return new DeleteCommand(deleteNumber);
            }  catch (NumberFormatException e) {
                throw new DukeException("OOPS!! Please indicate a number for the task.");
            }
        case "todo":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            description = commandArr[1];
            return new ToDoCommand(description);
        case "deadline":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            description = commandArr[1];
            String deadlineName = "";
            String deadlineBy = "";
            String[] deadlineDescription = description.split("/");
            for (String str : deadlineDescription) {
                if (str.startsWith("by")) {
                    deadlineBy = str.split(" ", 2)[1].trim();
                } else {
                    deadlineName = str.trim();
                }
            }
            if (deadlineName == "") {
                throw new DukeException("OOPS!! Please include the name of the deadline.");
            }
            if (deadlineBy == "") {
                throw new DukeException("OOPS!! Please include when the deadline is by.");
            }
            try {
                LocalDateTime byParsed = LocalDateTime.parse(deadlineBy, Duke.DATETIME_INPUT_FORMAT);
                return new DeadlineCommand(deadlineName, byParsed);
            } catch (DateTimeParseException e) {
                throw new DukeException("Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.");
            }
        case "event":
            if (commandArr.length < 2) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            description = commandArr[1];
            String eventName = "";
            String eventFrom = "";
            String eventTo = "";
            String[] eventDescription = description.split("/");
            for (String str : eventDescription) {
                if (str.startsWith("from")) {
                    eventFrom = str.split(" ", 2)[1].trim();
                } else if (str.startsWith("to")) {
                    eventTo = str.split(" ", 2)[1].trim();
                } else {
                    eventName = str.trim();
                }
            }
            if (eventName == "") {
                throw new DukeException("OOPS!! Please include the name of the event.");
            }
            if (eventFrom == "") {
                throw new DukeException("OOPS!! Please include when the event is from.");
            }
            if (eventTo == "") {
                throw new DukeException("OOPS!! Please include when the event is till.");
            }
            try {
                LocalDateTime fromParsed =  LocalDateTime.parse(eventFrom, Duke.DATETIME_INPUT_FORMAT);
                LocalDateTime toParsed = LocalDateTime.parse(eventTo, Duke.DATETIME_INPUT_FORMAT);
                return new EventCommand(eventName, fromParsed, toParsed);
            } catch (DateTimeParseException e) {
                throw new DukeException("Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.");
            }
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
