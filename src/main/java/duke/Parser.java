package duke;

import java.time.LocalDate;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;

/**
 * Represents a parser that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Returns the Command from the user input.
     *
     * @param userInput user input.
     * @return Command based on the user input.
     * @throws DukeException if user input gives an invalid command.
     */
    public static Command parse(String userInput) throws DukeException {
        String command = userInput.split(" ", 2)[0].toUpperCase();
        switch (command) {
        case "BYE":
            return new ExitCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return new MarkCommand(getTaskNumber(userInput));
        case "UNMARK":
            return new UnmarkCommand(getTaskNumber(userInput));
        case "TODO":
            return new TodoCommand(parseTodo(userInput));
        case "DEADLINE":
            return new TodoCommand(parseDeadline(userInput));
        case "EVENT":
            return new TodoCommand(parseEvent(userInput));
        case "FIND":
            return new FindCommand(getKeyword(userInput));
        case "DELETE":
            return new DeleteCommand(getTaskNumber(userInput));
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    private static String getKeyword(String userInput) throws DukeException {
        if (userInput.split(" ").length < 2) {
            throw new DukeException("☹ OOPS!!! The keyword cannot be empty.");
        }
        return userInput.split(" ", 2)[1];
    }

    private static ToDoTask parseTodo(String userInput) throws DukeException {

        if (userInput.split(" ").length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = userInput.split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDoTask(description);
    }

    private static DeadlineTask parseDeadline(String userInput) throws DukeException {
        if (userInput.split("/by").length < 2) {
            throw new DukeException("☹ OOPS!!! The date of deadline cannot be empty.");
        }
        String by = userInput.split("/by")[1].trim();
        LocalDate byDate = parseDate(by);

        String description = userInput.split("/by")[0].trim().split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        return new DeadlineTask(description, byDate);
    }

    private static EventTask parseEvent(String userInput) throws DukeException {
        if (userInput.split("/from").length < 2) {
            throw new DukeException("☹ OOPS!!! The date of event cannot be empty.");
        }

        String date = userInput.split("/from")[1];
        String from = date.split("/to")[0].trim();
        if (from.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The date of 'from' cannot be empty.");
        }

        if (userInput.split("/to").length < 2) {
            throw new DukeException("☹ OOPS!!! The date of 'to' cannot be empty.");
        }
        String to = date.split("/to")[1].trim();

        LocalDate fromDate = parseDate(from);
        LocalDate toDate = parseDate(to);

        String description = userInput.split("/from")[0].trim().split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }

        return new EventTask(description, fromDate, toDate);
    }

    private static int getTaskNumber(String userInput) throws DukeException {
        if (userInput.split(" ").length < 2) {
            throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
        }
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }

    /**
     * Parses the date string to LocalDate object.
     *
     * @param date date string in the format of yyyy-mm-dd.
     * @return LocalDate object.
     * @throws DukeException DukeException if the date string is not in the correct format.
     */
    private static LocalDate parseDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid date in the format: yyyy-mm-dd");
        }
    }
}
