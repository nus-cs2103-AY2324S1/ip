package chatty.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import chatty.command.Command;
import chatty.command.CommandNotFound;
import chatty.command.DeadlineCommand;
import chatty.command.DeleteCommand;
import chatty.command.DoneCommand;
import chatty.command.EventCommand;
import chatty.command.ExitCommand;
import chatty.command.FindCommand;
import chatty.command.ListCommand;
import chatty.command.ToDoCommand;
import chatty.command.UndoneCommand;
import chatty.exception.DetailsUnknownException;
import chatty.exception.IncompleteMessageException;
import chatty.exception.InvalidTaskNumberException;

/**
 * Responsible for parsing user input and generating appropriate commands for the chatbot.
 */
public class Parser {


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
        default:
            return new CommandNotFound();
        }
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
        LocalDate date = LocalDate.parse(splitInput[1]);
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
        LocalDate start = LocalDate.parse(splitTime[0]);
        String formattedStart = Parser.formatDate(start);
        LocalDate end = LocalDate.parse(splitTime[1]);
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
     * Format a LocalDate to a string representation.
     *
     * @param date The LocalDate to be formatted.
     * @return The formatted date in String representation.
     */

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }
}
