package arona.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Stack;

import arona.commands.Command;
import arona.commands.DeadlineCommand;
import arona.commands.DeleteCommand;
import arona.commands.ErrorCommand;
import arona.commands.EventCommand;
import arona.commands.FindCommand;
import arona.commands.InvalidCommand;
import arona.commands.ListCommand;
import arona.commands.MarkCommand;
import arona.commands.ToDoCommand;
import arona.commands.UndoCommand;
import arona.commands.UnmarkCommand;
import arona.exception.IllegalArgumentAronaException;
import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * The `Parser` class is responsible for parsing user input and extracting relevant information.
 */
public class Parser {

    /**
     * Parses the user input into an array of tokens, splitting by spaces.
     *
     * @param userInput The user's input as a single string.
     * @return An array of tokens.
     */
    public static String[] parseUserInput(String userInput) {
        return userInput.split("\\s+");
    }

    /**
     * Extracts the command keyword from the tokens.
     *
     * @param tokens The array of tokens.
     * @return The command keyword as a lowercase string.
     */
    public static String getCommand(String[] tokens) {
        if (tokens.length > 0) {
            return tokens[0].toLowerCase();
        }
        return "";
    }

    /**
     * Gets the description of a to-do task from the tokens.
     *
     * @param tokens The array of tokens.
     * @return The description of the to-do task.
     * @throws IllegalArgumentAronaException If the description is missing.
     */
    public static String getToDoDescription(String[] tokens) throws IllegalArgumentAronaException {
        if (tokens.length < 2) {
            throw new IllegalArgumentAronaException("Oh no! You forgot to specify the task!");
        }
        return String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
    }

    /**
     * Gets the description and deadline date from the tokens for a deadline task.
     *
     * @param tokens The array of tokens.
     * @return An array containing the description and deadline date.
     * @throws IllegalArgumentAronaException If the description or '/by' is missing.
     */
    public static String[] getDeadlineDescription(String[] tokens) throws IllegalArgumentAronaException {
        if (tokens.length < 2) {
            throw new IllegalArgumentAronaException("Oh no! You forgot to specify the task!");
        }

        boolean isByFound = false;
        for (String token : tokens) {
            if (token.equals("/by")) {
                isByFound = true;
                break;
            }
        }

        if (!isByFound) {
            throw new IllegalArgumentAronaException(
                    "Whoopsie! The deadline seems a bit confused. Please use '/by' to set it.");
        }

        int index = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].charAt(0) == '/') {
                index = i;
                tokens[i] = tokens[i].substring(1) + ":";
                break;
            }
        }
        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, index));
        String by = String.join(" ", Arrays.copyOfRange(tokens, index + 1, tokens.length));

        if (description.trim().equals("")) {
            throw new IllegalArgumentAronaException("Oh no! You forgot to specify the task!");
        }

        String[] descriptions = {description, by};
        return descriptions;
    }

    /**
     * Gets the description, start date, and end date from the tokens for an event task.
     *
     * @param tokens The array of tokens.
     * @return An array containing the description, start date, and end date.
     * @throws IllegalArgumentAronaException If the description, '/from', or '/to' is missing.
     */
    public static String[] getEventDescription(String[] tokens) throws IllegalArgumentAronaException {
        if (tokens.length < 2) {
            throw new IllegalArgumentAronaException("Oh no! You forgot to specify the event!");
        }

        boolean isFromFound = false;
        boolean isToFound = false;

        for (String token : tokens) {
            if (token.equals("/from")) {
                isFromFound = true;
            } else if (isFromFound && token.equals("/to")) {
                isToFound = true;
                break;
            }
        }

        if (!(isFromFound && isToFound)) {
            throw new IllegalArgumentAronaException(
                    "Whoopsie! The deadline seems a bit confused. Please use '/from' and '/to' to set it.");
        }

        int indexStart = -1;
        int indexEnd = -1;
        boolean first = true;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].charAt(0) == '/') {
                if (first) {

                    indexStart = i;
                    first = false;
                    tokens[i] = tokens[i].substring(1) + ":";
                } else {
                    indexEnd = i;
                    tokens[i] = tokens[i].substring(1) + ":";
                    break;
                }
            }
        }

        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, indexStart));
        String from = String.join(" ", Arrays.copyOfRange(tokens, indexStart, indexEnd));
        String to = String.join(" ", Arrays.copyOfRange(tokens, indexEnd, tokens.length));

        if (from.substring(5).trim().isEmpty() || to.substring(3).trim().isEmpty()) {
            throw new IllegalArgumentAronaException("Oh no! The start and/or end time cannot be empty.");
        }

        if (description.trim().equals("")) {
            throw new IllegalArgumentAronaException("Oh no! You forgot to specify the event!");
        }

        String[] descriptions = {description, from, to};
        return descriptions;
    }


    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateStr The date string in 'YYYY-MM-DD' format.
     * @return The parsed LocalDate.
     * @throws IllegalArgumentAronaException If the date format is invalid.
     */
    public static LocalDate parseDate(String dateStr) throws IllegalArgumentAronaException {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentAronaException(
                    "Whoopsie! The deadline seems a bit confused. Please use a valid 'YYYY-MM-DD' format to set it.");
        }
    }

    /**
     * Gets the task index from the tokens, used for marking or deleting tasks.
     *
     * @param tokens The array of tokens.
     * @return The task index.
     */
    public static int getTaskIndex(String[] tokens) {
        if (tokens.length > 1) {
            try {
                return Integer.parseInt(tokens[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }

    /**
     * Extracts and returns a keyword from an array of tokens.
     *
     * @param tokens An array of tokens containing user input.
     * @return The keyword to be used for searching.
     * @throws IllegalArgumentAronaException If the input format is incorrect:
     *                                      - If no keyword is specified, an exception is thrown.
     *                                      - If more than one keyword is provided, an exception is thrown.
     */
    public static String getKeyWord(String[] tokens) throws IllegalArgumentAronaException {
        if (tokens.length < 2) {
            throw new IllegalArgumentAronaException("Sorry... Please specify a keyword so I can help you.");
        } else if (tokens.length > 2) {
            throw new IllegalArgumentAronaException("Sorry... I can only handle one keyword.");
        } else {
            return tokens[1];
        }
    }

    /**
     * Parses the user input into the command to be executed.
     *
     * @param command       The command in String.
     * @param inputTokens   The descriptions of the command.
     * @param tasks         The list of tasks.
     * @param ui            The user interface for displaying messages.
     * @param storage       The storage responsible for loading and saving tasks.
     * @param commandHistory A stack to keep track of command history for undo.
     * @return The command to be executed.
     */
    public static Command parseCommand(String command, String[] inputTokens, TaskList tasks,
                                       Ui ui, Storage storage, Stack<Command> commandHistory) {
        try {
            switch (command) {
            case "list":
                return new ListCommand(tasks, ui);
            case "unmark":
                return new UnmarkCommand(tasks, ui, storage, getTaskIndex(inputTokens));
            case "mark":
                return new MarkCommand(tasks, ui, storage, getTaskIndex(inputTokens));
            case "todo":
                return new ToDoCommand(tasks, ui, storage, getToDoDescription(inputTokens));
            case "deadline":
                return new DeadlineCommand(tasks, ui, storage, getDeadlineDescription(inputTokens));
            case "event":
                return new EventCommand(tasks, ui, storage, getEventDescription(inputTokens));
            case "delete":
                return new DeleteCommand(tasks, ui, storage, getTaskIndex(inputTokens));
            case "find":
                return new FindCommand(tasks, ui, getKeyWord(inputTokens));
            case "undo":
                return new UndoCommand(tasks, ui, storage, commandHistory);
            default:
                return new InvalidCommand(tasks, ui);
            }
        } catch (Exception exception) {
            return new ErrorCommand(tasks, ui, exception);
        }
    }
}

