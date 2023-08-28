package duke;

import duke.command.*;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
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

            String description = deadlineString[0].trim();
            String deadlineDate = deadlineString[1].replaceFirst("by", "").trim();

            validateDate(deadlineDate);
            LocalDate d = LocalDate.parse(deadlineDate);

            return new AddDeadlineCommand(description, d);
        } else if (input.startsWith("event")) {
            String[] eventString = input.replaceFirst("event", "").split("/", 3);
            validateEvent(eventString);

            String description = eventString[0].trim();
            String start = eventString[1].replaceFirst("from", "").trim();
            String end = eventString[2].replaceFirst("to", "").trim();

            validateDate(start);
            validateDate(end);
            LocalDate d1 = LocalDate.parse(start);
            LocalDate d2 = LocalDate.parse(end);

            return new AddEventCommand(description, d1, d2);
        } else if (input.startsWith("delete")) {
            String number = input.replaceFirst("delete", "").trim();
            validateMarkOrUnmarkorDelete(number, size);
            int index = Integer.parseInt(number);
            return new DeleteCommand(index);
        } else {
            throw new DukeException("Boop Beep OOPS! I'm sorry, but I don't know what that means :(");
        }
    }

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

    private static void validateToDo(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Boop Beep OOPS! The description of a todo cannot be empty.");
        }
    }

    private static void validateDeadline(String[] deadlineString) throws DukeException {
        if (deadlineString.length != 2 || deadlineString[0].isBlank() || deadlineString[1].isBlank()) {
            throw new DukeException("Boop Beep OOPS! Please make sure that"
                    + " the description and date of the deadline is not empty.");
        }
    }

    private static void validateEvent(String[] eventString) throws DukeException {
        if (eventString.length != 3 || eventString[0].isBlank() || eventString[1].isBlank() || eventString[2].isBlank()) {
            throw new DukeException("Boop Beep OOPS! Please make sure that"
                    + " the description and dates of the event is not empty.");
        }
    }

    private static void validateDate(String date) throws DukeException {
        try {
            LocalDate d = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Boop Beep OOPS! Please check that the date is in YYYY-MM-DD format.");
        }
    }
}
