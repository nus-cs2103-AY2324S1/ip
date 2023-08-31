package duke.main;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ToDoCommand;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

/**
 * Handles the parsing of user input commands to create corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the user input to create a corresponding Command object.
     *
     * @param userInput The user's input as a String.
     * @return A Command object representing the parsed user input.
     * @throws DukeException If the user input is invalid.
     */
    static Command parse(String userInput) throws DukeException {
        if (userInput.equals("bye") || userInput.equals("exit")){
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            int taskIndex = validateMarkAndDelete(userInput.substring(5));
            return new MarkCommand(taskIndex);
        } else if (userInput.startsWith("unmark")) {
            int taskIndex = validateMarkAndDelete(userInput.substring(7));
            return new UnmarkCommand(taskIndex);
        } else if (userInput.startsWith("todo")) {
            String todoDescription = userInput.replace("todo ", "");
            validateTodo(todoDescription);
            return new ToDoCommand(todoDescription);
        } else if (userInput.startsWith("deadline")) {
            String[] info = validateDeadline(userInput);
            return new DeadlineCommand(info[0], info[1]);
        } else if (userInput.startsWith("event")) {
            String[] info = validateEvent(userInput);
            return new EventCommand(info[0], info[1], info[2]);
        } else if (userInput.startsWith("delete")) {
            int taskIndex = validateMarkAndDelete(userInput.substring(7));
            return new DeleteCommand(taskIndex);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Validates and extracts the task index from the user input for mark and delete commands.
     *
     * @param input The user input containing the task index.
     * @return The validated task index as an integer.
     * @throws DukeException If the task index is not valid.
     */
    private static int validateMarkAndDelete(String input) throws DukeException {
        if (input.isEmpty() || !isNumeric(input)) {
            throw new DukeException("☹ OOPS!!! The index cannot be blank or not an integer.");
        }

        return Integer.parseInt(input);
    }

    /**
     * Checks if a given String can be converted to an integer.
     *
     * @param str The String to be checked.
     * @return True if the String can be converted to an integer, false otherwise.
     */
    private static boolean isNumeric(String str) {
        if (str == null || str == "") {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates a toDo description to ensure it is not empty.
     *
     * @param todo The toDo description to be validated.
     * @throws DukeException If the toDo description is empty.
     */
    private static void validateTodo(String todo) throws DukeException {
        if (todo.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Validates and extracts information from user input for creating a deadline.
     *
     * @param input The user input for creating a deadline.
     * @return A String array containing the task description and deadline information.
     * @throws DukeException If the input format is invalid.
     */
    private static String[] validateDeadline(String input) throws DukeException {
        String[] splitInput = input.replace("deadline", "").split(" /by ");

        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim(); //remove whitespace
        }

        if (splitInput.length != 2 || splitInput[0].isBlank() || splitInput[1].isBlank()) {
            throw new DukeException("☹ OOPS!!! Please remember to put a task description and deadline");
        }
        return splitInput;
    }

    /**
     * Validates and extracts information from user input for creating an event.
     *
     * @param input The user input for creating an event.
     * @return An array containing the event description and timing information.
     * @throws DukeException If the input format is invalid.
     */
    private static String[] validateEvent(String input) throws DukeException {
        String[] info = input.replace("event ", "").split(" /from ");

        if (info.length != 2) {
            throw new DukeException("☹ OOPS!!! Please provide a valid event format: event <description> /from <start time> /to <end time>");
        }

        String description = info[0];

        String[] timeInfo = info[1].split(" /to ");
        if (timeInfo.length != 2) {
            throw new DukeException("☹ OOPS!!! Please provide both start and end times for the event.");
        }

        String startDateTime = timeInfo[0];
        String endDateTime = timeInfo[1];

        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (startDateTime.isBlank()) {
            throw new DukeException("☹ OOPS!!! The start time of this event cannot be empty.");
        } else if (endDateTime.isBlank()) {
            throw new DukeException("☹ OOPS!!! The end time of this event cannot be empty.");
        }

        return new String[]{description, startDateTime, endDateTime};
    }
}
