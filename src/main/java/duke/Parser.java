package duke;

import duke.exceptions.DukeDateTimeParseException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeParseException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {

    /**
     * Converts String input to Task object.
     *
     * @param userInput User input in String format.
     * @return Task object.
     * @throws DukeException If error is encountered while parsing.
     */
    public Task getTask(String userInput) throws DukeException {
        Task add;

        if (userInput.startsWith("todo")) {
            add = parseTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            add = parseDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            add = parseEvent(userInput);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return add;
    }

    /**
     * Converts String input to Todo object.
     *
     * @param userInput User input in String format.
     * @return Todo object.
     * @throws DukeParseException If error is encountered while parsing.
     */
    public Todo parseTodo(String userInput) throws DukeParseException {
        String description = userInput.substring(userInput.indexOf(' ') + 1);

        if (description.isEmpty() || description.equals("todo")) {
            throw new DukeParseException("todo");
        }

        return new Todo(description);
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
            String description = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);
            return new Deadline(description, LocalDateTime.parse(by, Duke.TIME_FORMAT));
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
    public Event parseEvent(String userInput) throws DukeParseException {
        try {
            String description = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
            String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
            String to = userInput.substring(userInput.indexOf("/to") + 4);
            return new Event(description, LocalDateTime.parse(from, Duke.TIME_FORMAT),
                    LocalDateTime.parse(to, Duke.TIME_FORMAT));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeParseException("event");
        } catch (DateTimeException e) {
            throw new DukeDateTimeParseException();
        }
    }

    public TaskList getTaskList(String userInput, TaskList taskList) {
        TaskList filtered = new TaskList();
        String keyword = userInput.substring(5);

        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            if (taskList.getTaskAt(i).containsKeyword(keyword)) {
                filtered.addToList(taskList.getTaskAt(i));
            }
        }
        return filtered;
    }
}
