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
     * Converts a given raw user input into its corresponding {@code Command} type.
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
     * Analyzes the input data to construct a specific {@code Task} object based on
     * the provided command type. This method can handle various task types like
     * {@code ToDo}, {@code Deadline}, and {@code Event}.
     *
     * <p>
     * The method throws a {@code SimonException} for invalid or incomplete user inputs.
     * </p>
     *
     * @param inData The raw user input string containing task details.
     * @param commandType The type of command the user input string represents.
     * @return A {@code Task} object representing the task detailed in the user input.
     * @throws SimonException If there is an error in interpreting the user input.
     */
    public static Task parseAddTask(String inData, Command commandType) throws SimonException {
        Task task;
        switch (commandType) {
            case TODO:
                String description = inData.split("todo ").length > 1 ? inData.split("todo ")[1] : "";
                if (description.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new ToDo(description);
                break;

            case DEADLINE:
                String[] deadlineParts = inData.split("deadline ");
                if (deadlineParts.length <= 1 || !inData.contains(" /by ")) {
                    throw new SimonException("☹ OOPS!!! The format for deadline is incorrect. Expected format: 'deadline [task description] /by [dd/mm/yyyy HHmm]'. Time(HHmm) is optional.");
                }
                String nameDeadline = deadlineParts.length > 1 ? deadlineParts[1].split(" /by ")[0] : "";
                if (nameDeadline.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String endDate = deadlineParts[1].split(" /by ").length > 1 ? deadlineParts[1].split(" /by ")[1] : "";
                if (endDate.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The deadline date cannot be empty.");
                }
                task = new Deadline(nameDeadline, endDate);
                break;

            case EVENT:
                String[] eventParts = inData.split("event ");
                if (eventParts.length <= 1 || !inData.contains("/from ") || !inData.contains("/to ")) {
                    throw new SimonException("☹ OOPS!!! The format for event is incorrect. Expected format: 'event [event description] /from [dd/mm/yyyy HHmm] /to [dd/mm/yyyy HHmm]'. The time(HHmm) is optional.");
                }

                String[] fromToParts = eventParts[1].split(" /from ");
                if (fromToParts.length < 2) {
                    throw new SimonException("☹ OOPS!!! The format for event is missing 'from' information.");
                }

                String eventName = fromToParts[0].trim();
                if (eventName.isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of an event cannot be empty.");
                }

                String[] toParts = fromToParts[1].split(" /to ");
                if (toParts.length < 2) {
                    throw new SimonException("☹ OOPS!!! The format for event is missing 'to' information.");
                }

                String startDate = toParts[0].trim();
                String endDateEvent = toParts[1].trim();

                task = new Event(eventName, startDate, endDateEvent);
                break;

            default:
                throw new SimonException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return task;
    }
}
