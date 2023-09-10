package koko;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing of user commands and input validation.
 */
public class Parser {

    /**
     * Checks if input string contains any invalid characters.
     * @param input The input string from the user.
     * @return True if it contains invalid characters, false otherwise.
     */
    public boolean hasInvalidCharacters(String input) {
        return input.contains("|");
    }

    /**
     * Parses the type of command from the input string.
     * @param input The input string from the user.
     * @return A Command enum representing the type of command.
     * @throws DukeException If the input is invalid or empty.
     */
    public Command parseCommandType(String input) throws DukeException {
        if (input == null || input.trim().isEmpty()) {
            throw new DukeException("Command input cannot be null or empty."
                    + "Each message should start with one of the following commands:"
                    + "list, mark, unmark, todo, deadline, event");
        }

        try {
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Each message should start with one of the following commands:"
                    + "list, mark, unmark, todo, deadline, event");
        } catch (Exception e) {
            throw new DukeException("Unexpected error while parsing command: " + e.getMessage());
        }
    }

    /**
     * Extracts the remaining arguments from the user input after the command.
     * @param commandType The type of command.
     * @param input The full user input string.
     * @return The remaining arguments as a string.
     * @throws DukeException If the remaining arguments are not provided or invalid.
     */
    public String parseRemainingArgs(Command commandType, String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String remaining = parts.length > 1 ? parts[1] : "";
        if (remaining.isEmpty()) {
            switch (commandType) {
            case MARK: // Fallthrough
            case UNMARK:
                throw new DukeException("Please specify a task number.");
            case TODO:
                throw new DukeException("Description for todo cannot be empty.");
            case DEADLINE:
                throw new DukeException("Description and date for deadline cannot be empty.");
            case EVENT:
                throw new DukeException("Description, start date, and end date for event cannot be empty.");
            }
        }
        return remaining;
    }

    /**
     * Parses the remaining arguments for a deadline command.
     * @param remaining The remaining arguments after the command.
     * @return A ParsedDeadlineArgs object containing the task name and due date.
     * @throws DukeException If the remaining arguments are invalid.
     */
    public ParsedDeadlineArgs parseDeadlineString(String remaining) throws DukeException {
        String[] parts = remaining.split("/by ", 2);
        if (parts.length < 2) {
            throw new DukeException("Missing '/by' or date for deadline.");
        }
        LocalDate byDate;
        try {
            byDate = LocalDate.parse(parts[1].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Deadline /by date should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }
        return new ParsedDeadlineArgs(parts[0].trim(), byDate);
    }

    /**
     * Parses the remaining arguments for an event command.
     * @param remaining The remaining arguments after the command.
     * @return A ParsedEventArgs object containing the task name, start date, and end date.
     * @throws DukeException If the remaining arguments are invalid.
     */
    public ParsedEventArgs parseEventString(String remaining) throws DukeException {
        String[] splitByTo = remaining.split("/to ", 2);
        if (splitByTo.length < 2) {
            throw new DukeException("Missing '/to' or end date for event.");
        }
        String[] splitByFrom = splitByTo[0].split("/from ", 2);
        if (splitByFrom.length < 2) {
            throw new DukeException("Missing '/from' or start date for event.");
        }
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(splitByFrom[1].trim());
            endDate = LocalDate.parse(splitByTo[1].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Event /from or /to dates should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }

        return new ParsedEventArgs(splitByFrom[0].trim(), startDate, endDate);
    }

    /**
     * Parses the remaining arguments for a todo command.
     * @param remaining The remaining arguments after the command.
     * @return A ParsedTodoArgs object containing the task name.
     * @throws DukeException If the remaining arguments are invalid.
     */
    public ParsedTodoArgs parseTodoString(String remaining) throws DukeException {
        if (remaining.isEmpty()) {
            throw new DukeException("Description for todo cannot be empty.");
        }
        return new ParsedTodoArgs(remaining);
    }

    /**
     * Wrapper class for command arguments.
     */
    public static abstract class ParsedTaskArgs {
        public final String taskName;

        public ParsedTaskArgs(String taskName) {
            this.taskName = taskName;
        }
    }

    /**
     * Represents the arguments for a deadline command.
     */
    public static class ParsedDeadlineArgs extends ParsedTaskArgs {
        public final LocalDate byDate;

        public ParsedDeadlineArgs(String taskName, LocalDate byDate) {
            super(taskName);
            this.byDate = byDate;
        }
    }

    /**
     * Represents the arguments for an event command.
     */
    public static class ParsedEventArgs extends ParsedTaskArgs {
        public final LocalDate startDate;
        public final LocalDate endDate;

        public ParsedEventArgs(String eventName, LocalDate startDate, LocalDate endDate) {
            super(eventName);
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    /**
     * Represents the arguments for a todo command.
     */
    public static class ParsedTodoArgs extends ParsedTaskArgs {

        public ParsedTodoArgs(String taskName) {
            super(taskName);
        }
    }

}
