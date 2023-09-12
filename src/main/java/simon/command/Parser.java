package simon.command;

import simon.SimonException;
import simon.task.Deadline;
import simon.task.Event;
import simon.task.Task;
import simon.task.ToDo;

/**
 * The {@code Parser} class is responsible for interpreting user input commands and
 * converting them into actions that the Simon application can understand and execute.
 * It serves as a bridge between user inputs and the internal representation of commands.
 */
public class Parser {

    public enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, FIND, UNKNOWN
    }

    public static Command parseCommand(String input) {
        try {
            return Parser.Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Parser.Command.UNKNOWN;
        }
    }

    public static Task parseAddTask(String userInput, Command commandType) throws SimonException {
        switch (commandType) {
        case TODO:
            return parseToDoTask(userInput);

        case DEADLINE:
            return parseDeadlineTask(userInput);

        case EVENT:
            return parseEventTask(userInput);

        default:
            throw new SimonException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Task parseToDoTask(String userInput) throws SimonException {
        String description = userInput.replaceFirst("todo", "").trim();
        if (description.isEmpty()) {
            throw new SimonException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(description);
    }

    private static Task parseDeadlineTask(String userInput) throws SimonException {
        String[] parts = userInput.split("deadline | /by ");
        if (parts.length < 3) {
            throw new SimonException("☹ OOPS!!! The format for deadline is incorrect. Expected format: "
                    + "'deadline [task description] /by [dd/mm/yyyy HHmm]'. Time(HHmm) is optional.");
        }

        String description = parts[1].trim();
        String endDate = parts[2].trim();

        if (description.isEmpty() || endDate.isEmpty()) {
            throw new SimonException("☹ OOPS!!! The description or deadline date cannot be empty.");
        }
        return new Deadline(description, endDate);
    }

    private static Task parseEventTask(String userInput) throws SimonException {
        String[] parts = userInput.split("event | /from | /to ");
        if (parts.length < 4) {
            throw new SimonException("☹ OOPS!!! The format for event is incorrect. Expected format: "
                    + "'event [event description] /from [dd/mm/yyyy HHmm] /to [dd/mm/yyyy HHmm]'. "
                    + "The time(HHmm) is optional.");
        }

        String description = parts[1].trim();
        String startDate = parts[2].trim();
        String endDate = parts[3].trim();

        if (description.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            throw new SimonException("☹ OOPS!!! The description, start date, or end date for an event cannot be empty.");
        }
        return new Event(description, startDate, endDate);
    }
}
