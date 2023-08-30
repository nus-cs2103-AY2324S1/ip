package dukeapp.tasks;

import dukeapp.DukeConstants;
import dukeapp.Parser;
import dukeapp.exceptions.InsufficientArgumentsException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a generic task containing the description and own task state.
 */
public abstract class Task {
    protected static final String OUTPUT_DATE_TIME_PATTERN = "d MMM yyyy, HHmm";
    protected static final String STORAGE_DATE_TIME_PATTERN = "yyyy-MM-dd HHmm";

    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
        this(description, false);
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Creates a task based on task type and input string.
     *
     * @param taskType The string representing the task type.
     * @param input    The input string used for creating a new task.
     * @return The created task.
     * @throws InsufficientArgumentsException If input is insufficient to
     *                                        create task.
     */
    public static Task createTask(String taskType, String input)
            throws InsufficientArgumentsException, DateTimeParseException {
        if (Objects.equals(input, "")) {
            throw new InsufficientArgumentsException(String.format(
                    DukeConstants.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                    "description", taskType));
        }
        String description = input;
        String[] args;
        switch (taskType) {
            case "deadline":
                Task.validateContainsArgument(input, taskType, "by");
                args = input.split("/by");
                description = args[0].trim();
                LocalDateTime by = Parser.parseDate(args[1].trim());
                return new Deadline(description, by);
            case "event":
                Task.validateContainsArgument(input, taskType, "from");
                Task.validateContainsArgument(input, taskType, "to");
                args = input.split("/from|/to");
                description = args[0].trim();
                LocalDateTime from = Parser.parseDate(args[1].trim());
                LocalDateTime to = Parser.parseDate(args[2].trim());
                return new Event(description, from, to);
            case "todo":
                return new ToDo(description);
            default:
                return null;
        }
    }

    /**
     * Parse an input from storage to create a task.
     *
     * @param taskCode The char representing the task type.
     * @param input    The input string used for creating a new task.
     * @return The created task.
     * @throws InsufficientArgumentsException If input is insufficient to
     *                                        create task.
     */
    public static Task parse(String taskCode, String input)
            throws InsufficientArgumentsException, DateTimeParseException {
        String taskType;
        switch (taskCode) {
            case "D":
                taskType = "deadline";
                break;
            case "E":
                taskType = "event";
                break;
            case "T":
                taskType = "todo";
                break;
            default:
                taskType = "";
        }

        if (Objects.equals(input, "")) {
            throw new InsufficientArgumentsException(String.format(
                    DukeConstants.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                    "isDone", taskType));
        }
        if (input.length() < 5) {
            throw new InsufficientArgumentsException(String.format(
                    DukeConstants.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                    "description", taskType));
        }
        boolean isDone = input.charAt(0) == '1';
        String description;
        switch (taskCode) {
            case "D":
                input = input.substring(4);
                description = input.substring(0, input.indexOf(" | "));
                if (Objects.equals(input.substring(input.indexOf(" | ") + 3), "")) {
                    throw new InsufficientArgumentsException(String.format(
                            DukeConstants.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                            "by", taskType));
                }
                LocalDateTime by = Parser.parseDate(input.substring(
                        input.indexOf(" | ") + 3));

                return new Deadline(description, isDone, by);
            case "E":
                input = input.substring(4);
                description = input.substring(0, input.indexOf(" | "));
                input = input.substring(input.indexOf(" | ") + 3);
                if (input.length() < OUTPUT_DATE_TIME_PATTERN.length()) {
                    throw new InsufficientArgumentsException(String.format(
                            DukeConstants.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                            "from", taskType));
                }
                if (input.length() < OUTPUT_DATE_TIME_PATTERN.length() * 2 + 1) {
                    throw new InsufficientArgumentsException(String.format(
                            DukeConstants.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                            "to", taskType));
                }
                // dates in storage should be formatted consistently
                LocalDateTime from =
                        Parser.parseDate(input.substring(OUTPUT_DATE_TIME_PATTERN.length()));
                LocalDateTime to =
                        Parser.parseDate(input.substring(OUTPUT_DATE_TIME_PATTERN.length() + 1
                        ));
                return new Event(description, isDone, from, to);
            case "T":
                description = input.substring(4);
                return new ToDo(description, isDone);
            default:
                return null;
        }
    }

    /**
     * Validates arguments in input string.
     *
     * @param input         The input string used to create task.
     * @param parameterName The parameter name to be checked.
     * @throws InsufficientArgumentsException If input is missing arguments
     *                                        from task.
     */
    private static void validateContainsArgument(String input,
                                                 String taskType,
                                                 String parameterName)
            throws InsufficientArgumentsException {
        if (!input.contains(String.format("/%s", parameterName))) {
            throw new InsufficientArgumentsException(String.format(
                    DukeConstants.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                    parameterName, taskType));
        }
    }

    /**
     * Encodes a Task into the string representation to save into storage.
     *
     * @return The string representing a task for storage.
     */
    public abstract String encode();

    /**
     * Gets the appropriate status icon string for the task.
     *
     * @return The status icon for the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks a task.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}