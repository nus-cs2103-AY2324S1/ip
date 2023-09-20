package duke.main;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
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
        if (userInput.equals("bye") || userInput.equals("exit")) {
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
            String toDoDescription = validateToDo(userInput);
            return new ToDoCommand(toDoDescription);
        } else if (userInput.startsWith("deadline")) {
            String[] info = validateDeadline(userInput);
            return new DeadlineCommand(info[0], info[1]);
        } else if (userInput.startsWith("event")) {
            String[] info = validateEvent(userInput);
            return new EventCommand(info[0], info[1], info[2]);
        } else if (userInput.startsWith("delete")) {
            int taskIndex = validateMarkAndDelete(userInput.substring(7));
            return new DeleteCommand(taskIndex);
        } else if (userInput.startsWith("find")) {
            String keyword = validateFind(userInput);
            return new FindCommand(keyword);
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

    private static String validateFind(String input) throws DukeException {
        int commandLength = "find".length();
        String keyWord = input.trim().substring(commandLength).trim();
        if (keyWord.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please enter a keyword to find.");
        }
        return keyWord;
    }

    /**
     * Checks if a given String can be converted to an integer.
     *
     * @param str The String to be checked.
     * @return True if the String can be converted to an integer, false otherwise.
     */
    private static boolean isNumeric(String str) {
        if (str == null || str.equals("")) {
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
     * @param input The toDo description to be validated.
     * @throws DukeException If the toDo description is empty.
     */
    private static String validateToDo(String input) throws DukeException {
        int commandLength = "todo".length(); //get length of string to remove
        String description = input.trim().substring(commandLength).trim();

        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        return description;
    }

    /**
     * Validates and extracts information from user input for creating a deadline.
     *
     * @param input The user input for creating a deadline.
     * @return A String array containing the task description and deadline information.
     * @throws DukeException If the input format is invalid.
     */
    private static String[] validateDeadline(String input) throws DukeException {
        // Split the input string into words
        String[] splitInput = input.split(" /by ");

        if (splitInput.length != 2) {
            throw new DukeException(
                    "☹ OOPS!!! Invalid deadline format. Please use: deadline <description> /by <date and time>");
        }
        int commandLength = "deadline".length(); //get length of string to remove
        String description = splitInput[0].substring(commandLength).trim();
        String dateTime = splitInput[1].trim();
        if (dateTime.isEmpty() || description.isBlank()) {
            throw new DukeException("☹ OOPS!!! Deadline description or date and time cannot be empty.");
        }

        return new String[] { description, dateTime };
    }

    /**
     * Validates and extracts information from user input for creating an event.
     *
     * @param input The user input for creating an event.
     * @return An array containing the event description and timing information.
     * @throws DukeException If the input format is invalid.
     */
    private static String[] validateEvent(String input) throws DukeException {
        String[] splitInput = input.split(" /from | /to ");

        if (splitInput.length != 3) {
            throw new DukeException(
                "Invalid event format. Please use: event [description] /from [start date/time] /to [end date/time]");
        }

        int commandLength = "event ".length(); //get length of string to remove
        String description = splitInput[0].substring(commandLength).trim();
        String startDateAndTime = splitInput[1].trim();
        String endDateAndTime = splitInput[2].trim();

        if (description.isBlank() || startDateAndTime.isBlank() || endDateAndTime.isBlank()) {
            throw new DukeException("Description and date/time cannot be empty.");
        }

        String[] eventInfo = new String[3];
        eventInfo[0] = description;
        eventInfo[1] = startDateAndTime;
        eventInfo[2] = endDateAndTime;

        return eventInfo;

    }
}
