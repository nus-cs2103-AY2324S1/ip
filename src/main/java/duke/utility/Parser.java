package duke.utility;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * A utility class for parsing and validating user commands in the Duke application.
 */
public class Parser {

    /**
     * Processes user input into a corresponding command object.
     *
     * @param input The user's input as a string.
     * @param ui    The user interface for displaying messages.
     * @return A command object based on the user's input, or null if input is invalid.
     */
    public static Command processInputIntoCommand(String input, Ui ui) {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];

        try {
            switch (command) {
            case "list":
                String queryParam = Parser.validateListCommand(input);
                return new ListCommand(queryParam);
            case "mark":
                int markTaskNumber = Parser.parseInt(inputArr[1]);
                return new MarkCommand(markTaskNumber);
            case "unmark":
                int unmarkTaskNumber = Parser.parseInt(inputArr[1]);
                return new UnmarkCommand(unmarkTaskNumber);
            case "todo":
                String todoDescription = Parser.validateToDoCommand(input);
                return new AddCommand(todoDescription);
            case "deadline":
                String deadlineDescription = Parser.validateDeadlineCommand(input);
                return new AddCommand(deadlineDescription);
            case "event":
                String eventDescription = Parser.validateEventCommand(input);
                return new AddCommand(eventDescription);
            case "delete":
                int deleteTaskNumber = Parser.parseInt(inputArr[1]);
                return new DeleteCommand(deleteTaskNumber);
            case "find":
                String keyword = input.replace("find", "").trim();
                return new FindCommand(keyword);
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }
        return null;
    }

    /**
     * Validates and extracts the description from a todo command.
     *
     * @param input The user input containing the todo command.
     * @return The description of the todo task.
     * @throws DukeException If the description is empty.
     */
    public static String validateToDoCommand(String input) throws DukeException {
        assert input != null : "Input should not be null";
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return input.replace("todo", "");
    }

    /**
     * Validates and extracts the description and deadline from a deadline command.
     *
     * @param input The user input containing the deadline command.
     * @return The description of the deadline task.
     * @throws DukeException If the format is invalid or the description is empty.
     */
    public static String validateDeadlineCommand(String input) throws DukeException {
        assert input != null : "Input should not be null";
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] description = input.replace("deadline", "").trim().split(" ");
        int index = -1;
        if (description.length > 2) {
            for (int i = 0; i < description.length; i++) {
                if (description[i].equals("/by")) {
                    index = i;
                    break;
                }
            }
        }

        if (index == -1 || index == 0 || index == description.length - 1) {
            throw new DukeException("☹ OOPS!!! The format of a deadline is invalid. "
                    + "Format: deadline <task name> /by <date>");
        }

        String[] taskInformation = input.split(" /");
        String by = taskInformation[1].replace("by ", "");


        if (DateParser.isInvalidDateTime(by)) {
            throw new DukeException("☹ OOPS!!! The format of the date time is invalid. "
                    + "Format: YYYY-MM-DD HH:MM");
        }

        return input.replace("deadline", "");
    }

    /**
     * Validates and extracts the description and event timing from an event command.
     *
     * @param input The user input containing the event command.
     * @return The description of the event task.
     * @throws DukeException If the format is invalid or the description is empty.
     */
    public static String validateEventCommand(String input) throws DukeException {
        assert input != null : "Input should not be null";
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }

        String[] description = input.replace("event", "").trim().split(" ");
        int fromIndex = -1;
        int toIndex = -1;
        if (description.length > 2) {
            for (int i = 0; i < description.length; i++) {
                if (description[i].equals("/from")) {
                    fromIndex = i;
                }
                if (description[i].equals("/to")) {
                    toIndex = i;
                }
            }
        }

        boolean isFromIndexBeforeToIndex = fromIndex < toIndex;
        boolean isFromIndexValid = fromIndex != -1 && fromIndex != 0 && fromIndex != description.length - 1;
        boolean isToIndexValid = toIndex != -1 && toIndex != 0 && toIndex != description.length - 1;

        if (!isFromIndexBeforeToIndex || !isFromIndexValid || !isToIndexValid) {
            throw new DukeException("☹ OOPS!!! The format of a event is invalid. Format: event <task name> "
                    + "/from <date> /to <date>");
        }

        String[] taskInformation = input.split(" /");
        String from = taskInformation[1].replace("from ", "");
        String to = taskInformation[2].replace("to ", "");

        if (DateParser.isInvalidDateTime(from) || DateParser.isInvalidDateTime(to)) {
            throw new DukeException("☹ OOPS!!! The format of the date time is invalid. "
                    + "Format: YYYY-MM-DD HH:MM");
        }

        return input.replace("event", "");
    }

    /**
     * Parses an integer from a string.
     *
     * @param input The string to be parsed.
     * @return The parsed integer, or -1 if parsing fails.
     */
    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please enter a valid number");
            return -1;
        }
    }

    /**
     * Validates and extracts the query parameter from a list command.
     *
     * @param input The user input containing the list command.
     * @return The query parameter of the list command.
     * @throws DukeException If the format is invalid.
     */
    public static String validateListCommand(String input) throws DukeException {
        assert input != null : "Input should not be null";
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            return null;
        }

        String[] description = input.replace("list", "").trim().split(" ");
        boolean isQueryInvalid = description.length != 3 || !description[0].equals("sortBy")
                || (!description[2].equals("asc") && !description[2].equals("desc"));

        if (isQueryInvalid) {
            throw new DukeException("☹ OOPS!!! The format of a list is invalid. "
                    + "Format: list sortBy <queryParam> <asc/desc>" + description[0] );
        }

        if (!description[1].equals("description")) {
            throw new DukeException("☹ Sorry!!! Tasks can only be sorted by description currently. "
                    + "Format: list sortBy <queryParam> <asc/desc>");
        }

        return description[1] + " " + description[2];
    }
}
