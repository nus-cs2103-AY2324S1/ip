package duke.parser;

import duke.commands.Command;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.InvalidTaskIndexException;
import duke.exceptions.MissingTaskIndexException;
import duke.tasks.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * The duke.parser.Parser class is responsible for parsing user input and converting it into meaningful commands and tasks.
 */
public class Parser {

    /**
     * Parses an input from user to get duke.commands.Command.
     *
     * @param str The user input string.
     * @return The parsed command.
     */
    public static Command getCommand(String str) {
        String command_word = str.split(" ")[0];
        Command command = Command.valueOf(command_word.toUpperCase());
        return command;
    }

    /**
     * Extracts the task index from a user input string and marks the task as done or undone.
     *
     * @param str    The user input string.
     * @param tasks  The list of tasks to operate on.
     * @return The index of the task that was marked.
     * @throws InvalidTaskIndexException   If the task index is invalid.
     * @throws MissingTaskIndexException  If the task index is missing.
     */
    public static int taskToMark(String str, TaskList tasks) throws InvalidTaskIndexException, MissingTaskIndexException {
        if (str.split(" ").length == 2) {
            int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
            if (taskIndex + 1 > tasks.getSize() || taskIndex < 0) {
                throw new InvalidTaskIndexException("Invalid Task Index.");
            }
            Command command = getCommand(str);
            switch (command) {
            case MARK:
                tasks.markTaskAsDone(taskIndex);
                break;
            case UNMARK:
                tasks.markTaskAsUndone(taskIndex);
                break;
            }
            return taskIndex;
        } else {
            throw new MissingTaskIndexException("Task Index Missing.");
        }
    }

    /**
     * Extracts the task index from a user input string and deletes the task.
     *
     * @param str    The user input string.
     * @param tasks  The list of tasks to operate on.
     * @return The task that was deleted.
     * @throws InvalidTaskIndexException   If the task index is invalid.
     * @throws MissingTaskIndexException  If the task index is missing.
     */
    public static Task taskToDelete(String str, TaskList tasks) throws InvalidTaskIndexException, MissingTaskIndexException{
        if (str.split(" ").length == 2) {
            int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
            if (taskIndex + 1 > tasks.getSize() || taskIndex < 0) {
                throw new InvalidTaskIndexException("Invalid Task Index.");
            }
            Task toRemove = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);
            return toRemove;

        } else {
            throw new MissingTaskIndexException("Task Index Missing.");
        }

    }

    /**
     * Parses a user input string into a task.
     *
     * @param str The user input string.
     * @return The parsed task.
     * @throws InvalidDescriptionException If the task description is invalid.
     * @throws InvalidDateTimeException    If the task date and time are invalid.
     */
    public static Task parseStringToTask(String str) throws InvalidDescriptionException, InvalidDateTimeException {
        Command command = getCommand(str);
        switch(command) {
        case TODO:
            if (str.split(" ").length > 1) {
                ToDo todo = new ToDo(str.split(" ")[1]);
                return todo;
            } else {
                throw new InvalidDescriptionException("Invalid description.");
            }
        case DEADLINE:
            if (str.split(" ").length > 3) {
                String fullTaskDescription = str.split(" ", 2)[1];
                String description = fullTaskDescription.split(" /by ")[0];
                String by = fullTaskDescription.split(" /by ")[1];
                String[] datetime = by.split(" ");
                try {
                    LocalDate date = LocalDate.parse(datetime[0]);
                    LocalTime time = LocalTime.parse(datetime[1]);
                    Deadline deadline = new Deadline(description, date, time);
                    return deadline;
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException("Invalid Datetime.");
                }
            } else {
                throw new InvalidDescriptionException("Invalid description.");
            }
        case EVENT:
            if (str.split(" ").length > 4) {
                String fullTaskDescription = str.split(" ", 2)[1];
                String description = fullTaskDescription.split(" /from ")[0];
                String from = String.join("", fullTaskDescription.split(" /from ")[1]).split(" /to ")[0];
                String to = fullTaskDescription.split(" /to ")[1];
                try {
                    String[] fromDatetime = from.split(" ");
                    String[] toDatetime = to.split(" ");
                    LocalDate fromDate = LocalDate.parse(fromDatetime[0]);
                    LocalTime fromTime = LocalTime.parse(fromDatetime[1]);
                    LocalDate toDate = LocalDate.parse(toDatetime[0]);
                    LocalTime toTime = LocalTime.parse(toDatetime[1]);
                    Event event = new Event(description, fromDate, fromTime, toDate, toTime);
                    return event;
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException("Invalid Datetime");
                }
            } else {
                throw new InvalidDescriptionException("Invalid description.");
            }
        default:
            return null;
        }
    }
}
