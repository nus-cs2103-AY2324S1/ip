package duke;

import duke.command.Command;
import duke.command.FindCommand;
import duke.command.DeleteCommand;
import duke.command.AddToDoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.UnmarkDoneCommand;
import duke.command.MarkDoneCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Parser class deals with making sense of the user command.
 *
 * @author Inez Kok
 */
public class Parser {
    /**
     * This method is used to parse through user command and make sense of it.
     *
     * @param input This is the user input.
     * @param size This is the size of the current task list.
     * @return Command This returns the respective command based on user input.
     * @throws DukeException On input error.
     */
    public static Command parse(String input, int size) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            String number = input.replaceFirst("mark", "").trim();
            validateMarkOrUnmarkorDelete(number, size);

            int index = Integer.parseInt(number);
            return new MarkDoneCommand(index);
        } else if (input.startsWith("unmark")) {
            String number = input.replaceFirst("unmark", "").trim();
            validateMarkOrUnmarkorDelete(number, size);

            int index = Integer.parseInt(number);
            return new UnmarkDoneCommand(index);
        } else if (input.startsWith("todo")) {
            String description = input.replaceFirst("todo", "").trim();
            validateToDo(description);

            return new AddToDoCommand(description);
        } else if (input.startsWith("deadline")) {
            String[] deadlineString = input.replaceFirst("deadline", "")
                    .split("/", 2);
            validateDeadline(deadlineString);

            String deadlineDate = deadlineString[1].replaceFirst("by", "").trim();
            validateDate(deadlineDate);

            String description = deadlineString[0].trim();
            LocalDate d = LocalDate.parse(deadlineDate);

            return new AddDeadlineCommand(description, d);
        } else if (input.startsWith("event")) {
            String[] eventString = input.replaceFirst("event", "").split("/", 3);
            validateEvent(eventString);

            String start = eventString[1].replaceFirst("from", "").trim();
            String end = eventString[2].replaceFirst("to", "").trim();
            validateDate(start);
            validateDate(end);

            String description = eventString[0].trim();
            LocalDate d1 = LocalDate.parse(start);
            LocalDate d2 = LocalDate.parse(end);

            return new AddEventCommand(description, d1, d2);
        } else if (input.startsWith("delete")) {
            String number = input.replaceFirst("delete", "").trim();
            validateMarkOrUnmarkorDelete(number, size);

            int index = Integer.parseInt(number);
            return new DeleteCommand(index);
        } else if (input.startsWith("find")) {
            String keyword = input.replaceFirst("find", "").trim();
            validateFind(keyword);
            return new FindCommand(keyword);
        } else {
            throw new DukeException("Boop Beep OOPS! I'm sorry, but I don't know what that means :(");
        }
    }

    /**
     * This method is used to check whether the user input for mark,
     * unmark or delete commands is valid.
     *
     * @param number The string representation of the one-based index of task.
     * @param size The size of the current task list.
     * @throws DukeException On input error.
     */
    private static void validateMarkOrUnmarkorDelete(String number, int size) throws DukeException {
        if (number.isBlank()) {
            throw new DukeException("Boop Beep OOPS! Please make sure that the index of the task is not empty.");
        } else {
            try {
                int numberInt = Integer.parseInt(number);
                if (numberInt <= 0 || numberInt > size) {
                    throw new DukeException("Boop Beep OOPS! Please make sure that"
                            + " the index of the task is within range.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Boop Beep OOPS! Please make sure that the index of the task is an integer.");
            }
        }
    }

    /**
     * This method is used to check whether the user input for creating a todo is valid.
     *
     * @param description The string representation of the todo description.
     * @throws DukeException On input error.
     */
    private static void validateToDo(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Boop Beep OOPS! The description of a todo cannot be empty.");
        }
    }

    /**
     * This method is used to check whether the user input for creating a deadline is valid.
     *
     * @param deadlineString The array of string representations of the parameters of a Deadline.
     * @throws DukeException On input error.
     */
    private static void validateDeadline(String[] deadlineString) throws DukeException {
        if (deadlineString.length != 2 || deadlineString[0].isBlank() || deadlineString[1].isBlank()) {
            throw new DukeException("Boop Beep OOPS! Please make sure that"
                    + " the description and date of the deadline is not empty.");
        }
    }

    /**
     * This method is used to check whether the user input for creating a event is valid.
     *
     * @param eventString The array of string representations of the parameters of an Event.
     * @throws DukeException On input error.
     */
    private static void validateEvent(String[] eventString) throws DukeException {
        if (eventString.length != 3 || eventString[0].isBlank() || eventString[1].isBlank() ||
                eventString[2].isBlank()) {
            throw new DukeException("Boop Beep OOPS! Please make sure that"
                    + " the description and dates of the event is not empty.");
        }
    }

    /**
     * This method is used to check whether the user input for a Date is valid.
     *
     * @param date The string representation of the date.
     * @throws DukeException On format error of String date.
     */
    private static void validateDate(String date) throws DukeException {
        try {
            LocalDate d = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Boop Beep OOPS! Please check that the date is in YYYY-MM-DD format.");
        }
    }

    /**
     * This method is used to check whether the keyword for the find command is valid.
     *
     * @param keyword The keyword to be searched for.
     * @throws DukeException When keyword is blank.
     */
    private static void validateFind(String keyword) throws DukeException {
        if (keyword.isBlank()) {
            throw new DukeException("Boop Beep OOPS! Please check that you have entered a keyword.");
        }
    }
}
