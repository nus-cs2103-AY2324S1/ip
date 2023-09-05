package duke;

import duke.commands.*;
import duke.exceptions.InsufficientArgumentsException;
import duke.exceptions.UnknownCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Represents the Application object responsible for storing and executing commands.
 */
public class Parser {
    public static final String OUTPUT_DATE_TIME_PATTERN = "d MMM yyyy, HHmm";
    public static final String STORAGE_DATE_TIME_PATTERN = "yyyy-MM-dd HHmm";

    /**
     * All supported date time formats for our application.
     */
    private static final List<String> dateTimeFormats = Arrays.asList(
            "d/M/yyyy", "d/M/yyyy HHmm",
            "d/MM/yyyy", "d/MM/yyyy HHmm",
            "dd/M/yyyy", "dd/M/yyyy HHmm",
            "dd/MM/yyyy", "dd/MM/yyyy HHmm",
            "yyyy-M-d", "yyyy-M-d HHmm",
            "yyyy-M-dd", "yyyy-M-dd HHmm",
            "yyyy-MM-d", "yyyy-MM-d HHmm",
            "yyyy-MM-dd", "yyyy-MM-dd HHmm"
    );
    /**
     * All commands stored in a map.
     */
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public Parser() {
        this.addCommand("delete", new DeleteCommand());
        this.addCommand("list", new ListCommand());

        this.addCommand("mark", new MarkCommand());
        this.addCommand("unmark", new UnmarkCommand());

        this.addCommand("find", new FindCommand());

        this.addCommand(new String[]{"deadline", "event", "todo"}, new InsertCommand());
        this.addCommand(new String[]{"bye", "exit", "leave", "quit"}, new ExitCommand());
    }

    /**
     * Parses a date time string to get a local date time object.
     *
     * @param dateTimeString The date time string to be parsed.
     * @return The local date time object based on the string.
     */
    public static LocalDateTime parseDate(String dateTimeString) {
        for (String format : dateTimeFormats) {
            try {
                if (format.contains("HHmm")) {
                    return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format));
                } else {
                    // input without specifying time will default to 0000
                    LocalDate date = LocalDate.parse(dateTimeString,
                            DateTimeFormatter.ofPattern(format));
                    return LocalDateTime.of(date, LocalTime.MIDNIGHT);
                }
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new DateTimeParseException(String.format(Messages.ERROR_PREFIX, String.format(
                Messages.INVALID_DATE_TIME_FORMAT, dateTimeString)), dateTimeString, 0);
    }


    /**
     * Creates a task based on task type and input string.
     *
     * @param taskType The string representing the task type.
     * @param input    The input string used for creating a new task.
     * @return The created task.
     * @throws InsufficientArgumentsException If input is insufficient to create task.
     */
    public static Task createTask(String taskType, String input)
            throws InsufficientArgumentsException, DateTimeParseException {
        if (Objects.equals(input, "")) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "description", taskType));
        }
        String description = input;
        String[] args;
        switch (taskType) {
        case "deadline":
            Parser.validateContainsArgument(input, taskType, "by");
            args = input.split("/by");
            description = args[0].trim();
            LocalDateTime by = Parser.parseDate(args[1].trim());
            return new Deadline(description, by);
        case "event":
            Parser.validateContainsArgument(input, taskType, "from");
            Parser.validateContainsArgument(input, taskType, "to");
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
     * Parses an input from storage to create a task.
     *
     * @param taskCode The char representing the task type.
     * @param input    The input string used for creating a new task.
     * @return The created task.
     * @throws InsufficientArgumentsException If input is insufficient to create task.
     * @throws DateTimeParseException         If the date in storage is formatted wrongly.
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
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                    "isDone", taskType));
        }
        if (input.length() < 5) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "description", taskType));
        }
        boolean isDone = input.charAt(0) == '1';
        String description;
        switch (taskCode) {
        case "D":
            input = input.substring(4);
            description = input.substring(0, input.indexOf(" | "));
            if (Objects.equals(input.substring(input.indexOf(" | ") + 3), "")) {
                throw new InsufficientArgumentsException(String.format(
                        Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "by", taskType));
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
                        Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "from", taskType));
            }
            if (input.length() < STORAGE_DATE_TIME_PATTERN.length() * 2 + 1) {
                throw new InsufficientArgumentsException(String.format(
                        Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "to", taskType));
            }
            // dates in storage should be formatted consistently
            LocalDateTime from =
                    Parser.parseDate(input.substring(0, OUTPUT_DATE_TIME_PATTERN.length() - 1));
            LocalDateTime to =
                    Parser.parseDate(input.substring(OUTPUT_DATE_TIME_PATTERN.length()));
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
     * @throws InsufficientArgumentsException If input is missing arguments from task.
     */
    private static void validateContainsArgument(String input, String taskType,
                                                 String parameterName)
            throws InsufficientArgumentsException {
        if (!input.contains(String.format("/%s", parameterName))) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                    parameterName, taskType));
        }
    }

    /**
     * Adds a command to the application's command map.
     *
     * @param name    The name of the command.
     * @param command The associated command.
     */
    private void addCommand(String name, Command command) {
        this.commandMap.put(name, command);
    }

    /**
     * Adds a command to the application's command map.
     *
     * @param names   The names of the command.
     * @param command The associated command.
     */
    private void addCommand(String[] names, Command command) {
        for (String name : names) {
            this.commandMap.put(name, command);
        }
    }

    /**
     * Executes a command given an input.
     *
     * @param input The input of the user.
     * @throws UnknownCommandException If no known command can be found from
     *                                 the input.
     */
    public void executeCommand(String input, TaskList taskList, Ui ui) throws UnknownCommandException {
        // Separate the command name and the command input
        String[] args = input.split(" ");
        String commandName = args[0];

        Command command = commandMap.get(commandName);
        if (command != null) {
            command.run(input, taskList, ui);
        } else {
            throw new UnknownCommandException(Messages.UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }
}