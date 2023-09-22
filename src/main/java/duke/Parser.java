package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.exceptions.DukeDateTimeOrderException;
import duke.exceptions.DukeDateTimeParseException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeParseException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Implementation for the parser.
 */
public class Parser {

    /**
     * Converts String input to Task object.
     *
     * @param userInput User input in String format.
     * @return Task object.
     * @throws DukeException If error is encountered while parsing.
     */
    public Task getTask(String userInput) throws DukeException {
        Task task;

        if (userInput.startsWith("todo")) {
            task = parseTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            task = parseDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            task = parseEvent(userInput);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return task;
    }

    /**
     * Converts String input to Todo object.
     *
     * @param userInput User input in String format.
     * @return Todo object.
     * @throws DukeParseException If error is encountered while parsing.
     */
    public Todo parseTodo(String userInput) throws DukeParseException {
        String todoDescription = userInput.substring(userInput.indexOf(' ') + 1);

        if (todoDescription.isEmpty() || todoDescription.equals("todo")) {
            throw new DukeParseException("todo");
        }

        return new Todo(todoDescription);
    }

    /**
     * Converts String input to Deadline object.
     *
     * @param userInput User input in String format.
     * @return Deadline object.
     * @throws DukeParseException If error is encountered while parsing.
     */
    public Deadline parseDeadline(String userInput) throws DukeParseException {
        try {
            String deadlineDescription = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
            String deadlineBy = userInput.substring(userInput.indexOf("/by") + 4);
            return new Deadline(deadlineDescription, LocalDateTime.parse(deadlineBy, Duke.TIME_FORMAT));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeParseException("deadline");
        } catch (DateTimeException e) {
            throw new DukeDateTimeParseException();
        }
    }

    /**
     * Converts String input to Event object.
     *
     * @param userInput User input in String format.
     * @return Event object.
     * @throws DukeParseException If error is encountered while parsing.
     */
    public Event parseEvent(String userInput) throws DukeParseException, DukeDateTimeOrderException {
        try {
            String eventDescription = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
            String eventFrom = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
            String eventTo = userInput.substring(userInput.indexOf("/to") + 4);
            if (LocalDateTime.parse(eventFrom, Duke.TIME_FORMAT)
                    .isAfter(LocalDateTime.parse(eventTo, Duke.TIME_FORMAT))) {
                throw new DukeDateTimeOrderException();
            }
            return new Event(eventDescription, LocalDateTime.parse(eventFrom, Duke.TIME_FORMAT),
                    LocalDateTime.parse(eventTo, Duke.TIME_FORMAT));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeParseException("event");
        } catch (DateTimeException e) {
            throw new DukeDateTimeParseException();
        }
    }
}
