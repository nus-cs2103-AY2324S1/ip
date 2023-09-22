package chatty.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import chatty.command.Command;
import chatty.command.CommandNotFound;
import chatty.command.DeadlineCommand;
import chatty.command.DeleteCommand;
import chatty.command.DoneCommand;
import chatty.command.EventCommand;
import chatty.command.ExitCommand;
import chatty.command.FindCommand;
import chatty.command.ListCommand;
import chatty.command.SetAliasCommand;
import chatty.command.ToDoCommand;
import chatty.command.UndoneCommand;
import chatty.exception.DetailsUnknownException;
import chatty.exception.IncompleteMessageException;
import chatty.exception.InvalidTaskNumberException;

/**
 * Responsible for parsing user input and generating appropriate commands for the chatbot.
 */
public class Parser {
    protected static final Map<String, String> COMMAND_ALIAS = new HashMap<>();
    /**
     * Parse user input and return the specific command to execute.
     *
     * @param input The input provided by the user.
     * @return The specific command to execute.
     * @throws DetailsUnknownException     When the user did not enter the details for the task.
     * @throws IncompleteMessageException  When the user did not enter the task description.
     * @throws InvalidTaskNumberException  When the task number entered by the user is out of range.
     */
    public static Command parse(String input) throws DetailsUnknownException,
            IncompleteMessageException, InvalidTaskNumberException {

        String[] parts = input.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String commandArgs = parts.length > 1 ? parts[1] : "";
        if (COMMAND_ALIAS.containsValue(commandWord)) {
            commandWord = getFullCommand(commandWord);
        }

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return parseDoneCommand(commandArgs);
        case "undone":
            return parseUndoneCommand(commandArgs);
        case "delete":
            return parseDeleteCommand(commandArgs);
        case "deadline":
            return parseDeadlineCommand(commandArgs);
        case "todo":
            return parseTodoCommand(commandArgs);
        case "event":
            return parseEventCommand(commandArgs);
        case "find":
            return parseFindCommand(commandArgs);
        case "set":
            return parseSetAliasCommand(commandArgs);
        default:
            return new CommandNotFound();
        }
    }

    /**
     * Retrieves the full command associated with a given alias.
     *
     * @param commandWord The alias for which to retrieve the full command.
     * @return The full command corresponding to the provided alias, or null if not found.
     */
    public static String getFullCommand(String commandWord) {
        for (Map.Entry<String, String> entry : COMMAND_ALIAS.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(commandWord)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Parse and create a DoneCommand based on the user input.
     *
     * @param args The command arguments.
     * @return A DoneCommand to mark a task as done.
     * @throws IncompleteMessageException  When the user did not provide a task number.
     */
    private static Command parseDoneCommand(String args) throws IncompleteMessageException {
        if (args.isEmpty()) {
            throw new IncompleteMessageException("Done");
        }
        int taskIndex = Integer.parseInt(args);
        return new DoneCommand(taskIndex);
    }

    /**
     * Parse and create an UndoneCommand based on the user input.
     *
     * @param args The command arguments.
     * @return An UndoneCommand to mark a task as not done.
     * @throws IncompleteMessageException  When the user did not provide a task number.
     */
    private static Command parseUndoneCommand(String args) throws IncompleteMessageException {
        if (args.isEmpty()) {
            throw new IncompleteMessageException("Undone");
        }
        int taskIndex = Integer.parseInt(args);
        return new UndoneCommand(taskIndex);
    }

    /**
     * Parse and create a DeleteCommand based on the user input.
     *
     * @param args The command arguments.
     * @return A DeleteCommand to delete a task.
     * @throws IncompleteMessageException  When the user did not provide a task number.
     */
    private static Command parseDeleteCommand(String args) throws IncompleteMessageException {
        if (args.isEmpty()) {
            throw new IncompleteMessageException("Delete");
        }
        int taskIndex = Integer.parseInt(args);
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parse and create a DeadlineCommand based on the user input.
     *
     * @param args The command arguments.
     * @return A DeadlineCommand to add a deadline task.
     * @throws DetailsUnknownException When the user did not provide details for the task.
     */
    private static Command parseDeadlineCommand(String args) throws DetailsUnknownException {
        String[] splitInput = args.split("/by ", 2);
        if (splitInput.length != 2) {
            throw new DetailsUnknownException();
        }
        String taskDescription = splitInput[0];
        LocalDateTime date = LocalDateTime.parse(splitInput[1]);
        String formattedDate = Parser.formatDate(date);
        return new DeadlineCommand(taskDescription, formattedDate);
    }

    /**
     * Parse and create a ToDoCommand based on the user input.
     *
     * @param args The command arguments.
     * @return A ToDoCommand to add a to-do task.
     * @throws IncompleteMessageException When the user did not provide a task description.
     */
    private static Command parseTodoCommand(String args) throws IncompleteMessageException {
        if (args.isEmpty()) {
            throw new IncompleteMessageException("T");
        }
        return new ToDoCommand(args);
    }

    /**
     * Parse and create an EventCommand based on the user input.
     *
     * @param args The command arguments.
     * @return An EventCommand to add an event task.
     * @throws DetailsUnknownException When the user did not provide details for the task.
     */
    private static Command parseEventCommand(String args) throws DetailsUnknownException {
        String[] splitInput = args.split("/from ", 2);
        if (splitInput.length != 2) {
            throw new DetailsUnknownException();
        }
        String taskDescription = splitInput[0];
        String[] splitTime = splitInput[1].split(" /to ", 2);
        LocalDateTime start = LocalDateTime.parse(splitTime[0]);
        String formattedStart = Parser.formatDate(start);
        LocalDateTime end = LocalDateTime.parse(splitTime[1]);
        String formattedEnd = Parser.formatDate(end);
        return new EventCommand(taskDescription, formattedStart, formattedEnd);
    }

    /**
     * Parse and create a FindCommand based on the user input.
     *
     * @param args The command arguments.
     * @return A FindCommand to search for tasks.
     * @throws IncompleteMessageException When the user did not provide a search keyword.
     */
    private static Command parseFindCommand(String args) throws IncompleteMessageException {
        if (args.isEmpty()) {
            throw new IncompleteMessageException("F");
        }
        return new FindCommand(args);
    }
    /**
     * Parses the "setAlias" command and returns a SetAliasCommand instance.
     *
     * @param args The arguments provided for the "setAlias" command.
     * @return A SetAliasCommand instance with the specified alias and associated command.
     * @throws IncompleteMessageException When the input for the "setAlias" command is incomplete.
     */
    private static Command parseSetAliasCommand(String args) throws IncompleteMessageException {
        String[] parts = args.split(" ", 2);
        if (parts.length != 2) {
            throw new IncompleteMessageException("set");
        }
        String alias = parts[0];
        String command = parts[1];
        return new SetAliasCommand(alias, command);
    }

    /**
     * Sets a custom alias for a command.
     *
     * @param alias The alias to set.
     * @param command The command associated with the alias.
     */
    public static int setAlias(String alias, String command) {
        if (aliasInUse(alias)) {
            return -1;
        }
        String existingAlias = checkCommand(command);
        if (existingAlias != null) {
            COMMAND_ALIAS.replace(command, alias);
            return 1;
        } else {
            COMMAND_ALIAS.put(command.toLowerCase(), alias);
            return 0;
        }
    }

    /**
     * Checks if an alias is already in use.
     *
     * @param alias The alias to check.
     * @return true if the alias is already in use, false otherwise.
     */

    public static boolean aliasInUse(String alias) {
        return COMMAND_ALIAS.containsValue(alias);
    }

    /**
     * Checks if a command is associated with an alias.
     *
     * @param command The command to check.
     * @return The alias associated with the command if found, or null if not found.
     */
    public static String checkCommand(String command) {
        for (Map.Entry<String, String> entry : COMMAND_ALIAS.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(command)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Format a LocalDate to a string representation.
     *
     * @param date The LocalDate to be formatted.
     * @return The formatted date in String representation.
     */
    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
        return date.format(formatter);
    }
}
