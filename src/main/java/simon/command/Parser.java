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

    /**
     * Enumeration of valid commands that the Simon application can process.
     * Each command type corresponds to a specific action or operation
     * that can be performed by the Simon application.
     */
    public enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, FIND, UNKNOWN
    }

    /**
     * Parses the given user input string to determine its corresponding command type.
     * If the input does not match any known command, it defaults to {@code UNKNOWN}.
     *
     * @param input The raw user input command as a string.
     * @return The {@code Command} enumeration representing the type of command.
     */
    public static Command parseCommand(String input) {
        try {
            return Parser.Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Parser.Command.UNKNOWN;
        }
    }

    /**
     * Parses the user input to construct a specific {@code Task} object based on the command type.
     * This method can handle task types like {@code ToDo}, {@code Deadline}, and {@code Event}.
     *
     * @param userInput The raw user input string containing task details.
     * @param commandType The type of command the user input string represents.
     * @return A {@code Task} object representing the task detailed in the user input.
     * @throws SimonException If there is an error in interpreting the user input.
     */
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

    /**
     * Parses the user input to create a {@code ToDo} task.
     *
     * @param userInput The raw user input string containing the ToDo task details.
     * @return A {@code ToDo} task parsed from the input.
     * @throws SimonException If the task description is missing or invalid.
     */
    private static Task parseToDoTask(String userInput) throws SimonException {
        String description = userInput.replaceFirst("todo", "").trim();
        if (description.isEmpty()) {
            throw new SimonException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(description);
    }

    /**
     * Parses the user input to create a {@code Deadline} task.
     *
     * @param userInput The raw user input string containing the Deadline task details.
     * @return A {@code Deadline} task parsed from the input.
     * @throws SimonException If the task description or end date is missing or in an incorrect format.
     */
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

    /**
     * Parses the user input to create an {@code Event} task.
     *
     * @param userInput The raw user input string containing the Event task details.
     * @return An {@code Event} task parsed from the input.
     * @throws SimonException If the task description, start date, or end date is missing or in an incorrect format.
     */
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
            throw new SimonException("☹ OOPS!!! The description, start date, "
                    + "or end date for an event cannot be empty.");
        }
        return new Event(description, startDate, endDate);
    }
}
