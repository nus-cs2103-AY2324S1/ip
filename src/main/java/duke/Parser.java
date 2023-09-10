package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.UnmarkDoneCommand;
import duke.exception.DukeException;

/**
 * The Parser class deals with making sense of the user command.
 *
 * @author Inez Kok
 */
public class Parser {
    /**
     * Parses through user command and make sense of it.
     *
     * @param input This is the user input.
     * @param size This is the size of the current task list.
     * @return Command This returns the respective command based on user input.
     * @throws DukeException On input error.
     */
    public static Command parse(String input, int size) throws DukeException {
        assert size >= 0 : "size of task list should be more than equal to 0";

        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            String number = input.replaceFirst("mark", "").trim();
            checkRange(number, size);

            int index = Integer.parseInt(number);
            return new MarkDoneCommand(index);
        } else if (input.startsWith("unmark")) {
            String number = input.replaceFirst("unmark", "").trim();
            checkRange(number, size);

            int index = Integer.parseInt(number);
            return new UnmarkDoneCommand(index);
        } else if (input.startsWith("todo")) {
            String description = input.replaceFirst("todo", "").trim();
            checkToDo(description);

            return new AddToDoCommand(description);
        } else if (input.startsWith("deadline")) {
            String[] deadlineString = input.replaceFirst("deadline", "")
                    .split("/", 2);
            checkDeadline(deadlineString);

            String deadlineDate = deadlineString[1].replaceFirst("by", "").trim();
            checkDate(deadlineDate);

            String description = deadlineString[0].trim();
            LocalDate d = LocalDate.parse(deadlineDate);

            return new AddDeadlineCommand(description, d);
        } else if (input.startsWith("event")) {
            String[] eventString = input.replaceFirst("event", "").split("/", 3);
            checkEvent(eventString);

            String start = eventString[1].replaceFirst("from", "").trim();
            String end = eventString[2].replaceFirst("to", "").trim();
            checkDate(start);
            checkDate(end);

            String description = eventString[0].trim();
            LocalDate d1 = LocalDate.parse(start);
            LocalDate d2 = LocalDate.parse(end);

            return new AddEventCommand(description, d1, d2);
        } else if (input.startsWith("delete")) {
            String number = input.replaceFirst("delete", "").trim();
            checkRange(number, size);

            int index = Integer.parseInt(number);
            return new DeleteCommand(index);
        } else if (input.startsWith("find")) {
            String keyword = input.replaceFirst("find", "").trim();
            checkFind(keyword);
            return new FindCommand(keyword);
        } else {
            throw new DukeException("Boop Beep OOPS! I'm sorry, but I don't know what that means :(");
        }
    }

    /**
     * Checks whether the user input for mark,unmark or delete commands is valid.
     *
     * @param number The string representation of the one-based index of task.
     * @param size The size of the current task list.
     * @throws DukeException On input error.
     */
    private static void checkRange(String number, int size) throws DukeException {
        assert size >= 0 : "size of task list should be more than equal to 0";

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
     * Check whethers the user input for creating a todo is valid.
     *
     * @param description The string representation of the todo description.
     * @throws DukeException On input error.
     */
    private static void checkToDo(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Boop Beep OOPS! The description of a todo cannot be empty.");
        }
    }

    /**
     * Checks whether the user input for creating a deadline is valid.
     *
     * @param deadlineString The array of string representations of the parameters of a Deadline.
     * @throws DukeException On input error.
     */
    private static void checkDeadline(String[] deadlineString) throws DukeException {
        boolean isNotLengthTwo = deadlineString.length != 2;
        boolean isDescriptionBlank = deadlineString[0].isBlank();
        boolean isDateBlank = deadlineString[1].isBlank();

        if (isNotLengthTwo || isDescriptionBlank || isDateBlank) {
            throw new DukeException("Boop Beep OOPS! Please make sure that"
                    + " the description and date of the deadline is not empty.");
        }
    }

    /**
     * Checks whether the user input for creating a event is valid.
     *
     * @param eventString The array of string representations of the parameters of an Event.
     * @throws DukeException On input error.
     */
    private static void checkEvent(String[] eventString) throws DukeException {
        boolean isNotLengthThree = eventString.length != 3;
        boolean isDescriptionBlank = eventString[0].isBlank();
        boolean isFromDateBlank = eventString[1].isBlank();
        boolean isToDateBlank = eventString[2].isBlank();

        if (isNotLengthThree || isDescriptionBlank || isFromDateBlank || isToDateBlank) {
            throw new DukeException("Boop Beep OOPS! Please make sure that"
                    + " the description and dates of the event is not empty.");
        }
    }

    /**
     * Checks whether the user input for a Date is valid.
     *
     * @param date The string representation of the date.
     * @throws DukeException On format error of String date.
     */
    private static void checkDate(String date) throws DukeException {
        assert !date.isBlank() : "date should not be blank";

        try {
            LocalDate d = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Boop Beep OOPS! Please check that the date is in YYYY-MM-DD format.");
        }
    }

    /**
     * Checks whether the keyword for the find command is valid.
     *
     * @param keyword The keyword to be searched for.
     * @throws DukeException When keyword is blank.
     */
    private static void checkFind(String keyword) throws DukeException {
        assert !keyword.isBlank() : "keyword should not be blank";

        if (keyword.isBlank()) {
            throw new DukeException("Boop Beep OOPS! Please check that you have entered a keyword.");
        }
    }
}
