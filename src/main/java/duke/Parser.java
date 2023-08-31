package duke;

import duke.command.*;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user's commands.
 */
public class Parser {
    /**
     * Adds user's inputs into the task list and throws any errors encountered.
     * @param str User's command
     * @return Command
     * @throws DukeException incorrect user inputs
     */
    public static Command addToList(String str) throws DukeException {
        if (str.startsWith("todo")) {
            if (str.length() <= 5) {
                throw new DukeException("So um, what exactly do you need to do?"
                        + "Add it as the description of the todo.");
            } else {
                return new AddToDoCommand(str.substring(5));
            }
        } else if (str.startsWith("event")) {
            if (str.length() <= 6) {
                throw new DukeException("So um, what exactly do you have? Add it as the description of the event.");
            }
            if (!str.contains("/from") || !str.contains("/to")) {
                throw new DukeException("When's the event? Write it explicitly. "
                        + "eg. Holiday /from 2023-12-07 1800 /to 2023-12-20 1800");
            }
            int indexFrom = str.lastIndexOf("/from");
            int indexTo = str.lastIndexOf("/to");
            try {
                return new AddEventCommand(str.substring(6, indexFrom - 1),
                        LocalDateTime.parse(str.substring(indexFrom + 6, indexTo - 1),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        LocalDateTime.parse(str.substring(indexTo + 4),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
            } catch (DateTimeParseException parseError) {
                throw new DukeException("Enter a proper date in the YYYY-MM-DD format."
                        + "eg. Holiday /from 2023-12-07 1800 /to 2023-12-20 1800");
            }
        } else if (str.startsWith("deadline")) {
            if (str.length() <= 9) {
                throw new DukeException("So um, what exactly do you need to do?"
                        + "Add it as the description of the deadline.");
            }
            if (!str.contains("/by")) {
                throw new DukeException("When's the deadline? Write it explicitly."
                        + "eg. Assignment /by 2023-12-12 1800");
            }
            int indexBy = str.lastIndexOf("/by");
            try {
                return new AddDeadlineCommand(str.substring(9, indexBy - 1),
                        LocalDateTime.parse(str.substring(indexBy + 4),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
            } catch (DateTimeParseException parseError) {
                throw new DukeException("Enter a proper date in the YYYY-MM-DD format."
                        + "eg. Assignment /by 2023-12-12 1800");
            }
        } else if (str.startsWith("mark")) {
            throw new DukeException("Specify the task number after the word 'mark', please. I can't read minds.");
        } else if (str.startsWith("unmark")) {
            throw new DukeException("Specify the task number after the word 'unmark', please. I can't read minds.");
        } else if (str.startsWith("delete")) {
            throw new DukeException("Specify the task number after the word 'delete', please. I can't read minds.");
        } else {
            throw new DukeException("Uncivilised speech. Please try again with words I can understand.");
        }
    }

    /**
     * Parses user inputs and produces the respective result.
     * @param command user input
     * @return Command
     * @throws DukeException incorrect user input
     */
    public static Command parse(String command) throws DukeException {
        assert (command != null);
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark") && command.length() > 5) {
            int index = Integer.parseInt(command.substring(5));
            return new MarkCommand(index-1);
        } else if (command.startsWith("unmark") && command.length() > 7) {
            int index = Integer.parseInt(command.substring(7));
            return new UnmarkCommand(index-1);
        } else if (command.startsWith("delete") && command.length() > 7) {
            int index = Integer.parseInt(command.substring(7));
            return new DeleteCommand(index-1);
        } else if (command.startsWith("find") && command.length() > 5) {
            return new FindCommand(command.substring(5));
        } else {
            return addToList(command);
        }
    }
}
